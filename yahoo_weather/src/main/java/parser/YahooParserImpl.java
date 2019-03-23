package parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.yahooforecast.YahooForecast;


import java.io.IOException;


public class YahooParserImpl implements YahooParser {

    @Override
    public YahooForecast parse(String jsonData) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        YahooForecast yahooForecast = objectMapper.readValue(jsonData, YahooForecast.class);

        System.out.println(yahooForecast.getLocationView().getCity());
        System.out.println(yahooForecast.getCurrObservationView().getWindView().getSpeed());
        System.out.println(yahooForecast.getForecasts().get(0).getDay());
        System.out.println(yahooForecast.getForecasts().get(1).getDate());













/*
        JsonNode jsonNode = objectMapper.readTree(jsonData);
        JsonNode location = jsonNode.path("location");
        JsonNode current_observation = jsonNode.path("current_observation");
        JsonNode condition = current_observation.path("condition");
        Weather jsonWeather = objectMapper.treeToValue(location, Weather.class);
        jsonWeather = objectMapper.treeToValue(condition, Weather.class);
        System.out.println(jsonWeather.getCity());
        System.out.println(jsonWeather.getRegion());
        System.out.println(jsonWeather.getCountry());*/



        return yahooForecast;
    }
}
