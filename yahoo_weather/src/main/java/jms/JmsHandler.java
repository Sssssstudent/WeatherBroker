package jms;

import com.bellintegrator.dto.AdminRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retriever.YahooRetriever;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * Получатель jms-сообщения из очереди модуля admin_api
 */
@MessageDriven(name = "Receiver", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/yahoo"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class JmsHandler implements MessageListener {
    private final Logger log = LoggerFactory.getLogger(JmsHandler.class);

    private YahooRetriever yahooRetriever;

    @Inject
    public JmsHandler(YahooRetriever yahooRetriever) {
        this.yahooRetriever = yahooRetriever;
    }


    public JmsHandler() {
    }


    /**
     * Получить сообщение с названием города из очереди от модуля admin_api,
     * передать название города в YahooService для получения данных о погоде,
     * передать данные о погоде в класс DataSender для отправки сообщения в очередь для сохранения в базу данных
     *
     * @param message пришедшее сообщение из jms-очереди от admin_api
     */
    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage;
        try {
            if (message instanceof ObjectMessage) {
                objectMessage = (ObjectMessage) message;
                AdminRequest adminRequest = objectMessage.getBody(AdminRequest.class);
                log.info("Catch AdminRequest message");
                yahooRetriever.retrieve(adminRequest.getCity().trim().toLowerCase().replaceAll(" ", ""), adminRequest.getRegion().trim()
                        .toLowerCase().replaceAll(" ", ""));
            } else {
                log.warn("Message of wrong type: " + message.getClass().getName());
                throw new IllegalArgumentException("Message must be of type ObjectMessage");
            }
        } catch (JMSException ex) {
            throw new RuntimeException("Error processing JMS message", ex);
        }
    }


}