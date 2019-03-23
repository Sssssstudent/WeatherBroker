package jms;

import dto.yahooforecast.YahooForecast;

import javax.jms.JMSContext;
import javax.jms.Queue;

public class JmsSender {
    public void sendMessage(YahooForecast yahooForecast, JMSContext context, Queue queue){
        context.createProducer().send(queue, yahooForecast);
    }
}
