package retriever;

/**
 * Интерфейс для получения данных с Yahoo
 */
public interface YahooRetriever {

    /**
     * Получить данные с сервиса https://weather-ydn-yql.media.yahoo.com/forecastrss
     *
     * @param city
     * @param region
     */
    void retrieve(String city, String region);
}
