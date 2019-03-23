package parser;

import dto.yahooforecast.YahooForecast;

import java.io.IOException;

public interface YahooParser  {
   YahooForecast parse(String inputStream) throws IOException;
}
