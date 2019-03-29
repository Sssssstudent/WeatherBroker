package com.bellintegreator.weather.jms;

import com.bellintegrator.dto.AdminRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 * Отправитель сообщения с параметрами city и region в очередь для модуля yahoo_weather
 */
public class JmsCreator {

    private final Logger log = LoggerFactory.getLogger(JmsCreator.class);

    private final JMSContext context;


    @Resource(mappedName = "java:/jms/queue/yahoo")
    private Queue queue;

    @Inject
    public JmsCreator(JMSContext context) {
        this.context = context;
    }

    /**
     * Отправить сообщение в очередь для модуля yahoo_service
     *
     * @param adminRequest город
     */
    public void sendMessage(AdminRequest adminRequest) {
        context.createProducer().send(queue, adminRequest);
        log.info("AdminRequest message has been sent");
    }
}
