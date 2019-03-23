package retriever;


import authCreator.AuthCreator;
import dto.yahooforecast.YahooForecast;
import exceptions.AdminException;
import jms.JmsSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;


@ApplicationScoped
public class YahooRetrieverImpl implements YahooRetriever {
    private final Logger log = LoggerFactory.getLogger(YahooRetrieverImpl.class);

    @Resource(mappedName = "java:/jms/queue/dbQueue")
    private Queue queue;

    @Inject
    private JMSContext context;

    @Inject
    private AuthCreator authCreator;

    @Inject
    private JmsSender jmsSender;

    @Override
    public void retrieve(String city, String region){

        HttpEntity<String> entity = authCreator.create(city, region);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<YahooForecast> result = restTemplate.exchange(
                "https://weather-ydn-yql.media.yahoo.com/forecastrss?location=" + city + "," + region + "&format=json&u=c",
                HttpMethod.GET, entity, YahooForecast.class);

        if(result.getStatusCodeValue() != 200) {
            throw new AdminException("Service is temporarily unavailable. Try later");
        }

        YahooForecast yahooForecast = result.getBody();
        if(yahooForecast == null || yahooForecast.getLocationView() == null || yahooForecast.getLocationView().getWoeid() == null) {
            throw new AdminException("Wrong city or region! Try again");
        }
        if(yahooForecast.getCurrObservationView() == null || yahooForecast.getCurrObservationView().getPubDate() == null ||
                yahooForecast.getForecasts() == null || yahooForecast.getForecasts().isEmpty() ||
                yahooForecast.getForecasts().get(0) == null) {
            throw new AdminException("Service is temporarily unavailable. Try later");
        }

        try {
            jmsSender.sendMessage(yahooForecast,context,queue);
            log.info("Message has been send to dbQueue");
        }catch (Exception e){

        }

    }
}












































































