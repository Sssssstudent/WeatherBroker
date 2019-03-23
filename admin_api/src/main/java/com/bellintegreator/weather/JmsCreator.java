package com.bellintegreator.weather;

import dto.AdminRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSContext;

/**
 * Отправитель сообщения с параметрами city и region в очередь модуля yahoo_weather
 */
public class JmsCreator {

    private final Logger log = LoggerFactory.getLogger(JmsCreator.class);

    @Resource (mappedName = "java:/jms/queue/yahoo")
    private javax.jms.Queue queue;

    @Inject
    private JMSContext context;

    public void sendMessage(AdminRequest adminRequest){
        try{
            context.createProducer().send(queue, adminRequest);
            log.info("City message has been sent");
        } catch (Exception e){

        }
    }
}
