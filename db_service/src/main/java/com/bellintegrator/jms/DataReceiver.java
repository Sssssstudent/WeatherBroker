package com.bellintegrator.jms;

import com.bellintegrator.service.WeatherService;
import com.bellintegrator.dto.yahooforecast.YahooForecast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * Получатель сообщения с данными для сохранения в БД
 */
@MessageDriven(name = "DataReceiver", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/dbQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class DataReceiver implements MessageListener {

    private final Logger log = LoggerFactory.getLogger(DataReceiver.class);

    private WeatherService weatherService;

    @Inject
    public DataReceiver(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public DataReceiver() {
    }

    /**
     * Получить сообщение
     *
     * @param message пришедшее сообщение
     */
    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = null;
        try {
            if (message instanceof ObjectMessage) {
                objectMessage = (ObjectMessage) message;
                YahooForecast yahooForecast = objectMessage.getBody(YahooForecast.class);
                log.info("WeatherInfo message has been received");
                weatherService.saveWeather(yahooForecast);
            } else {
                log.warn("Message of wrong type: " + message.getClass().getName());
                throw new IllegalArgumentException("Message must be of type ObjectMessage");
            }
        } catch (JMSException ex) {
            throw new RuntimeException("Error processing JMS message", ex);
        }
    }
}
