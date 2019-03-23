package authCreator;

/**
  *Данный класс содержит параметры аутентификации в сервисе Yahoo
  */
public class YahooAuth {
    final String appId = "77vrIz32";
    final String consumerKey = "dj0yJmk9NGFYME9CeTI5M1RxJnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PWNh";
    final String consumerSecret = "e195eabea9495b167755ebf46cb2c32e42de3229";
    final String url = "https://weather-ydn-yql.media.yahoo.com/forecastrss";

    public String getAppId() {
        return appId;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public String getUrl() {
        return url;
    }
}
