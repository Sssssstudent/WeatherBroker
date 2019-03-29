package jms;

import com.bellintegrator.dto.yahooforecast.YahooForecast;

import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 * Класс для отправки сообщения в модуль db_service
 */
public class JmsSender {

    /**
     * Отправить сообщение с данными о погоде в очередь для модуля db_service
     *
     * @param yahooForecast
     * @param context
     * @param queue
     */
    public void sendMessage(YahooForecast yahooForecast, JMSContext context, Queue queue) {
        context.createProducer().send(queue, yahooForecast);
    }
}
