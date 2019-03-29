package authcreator;

import org.springframework.http.HttpEntity;

/**
 * Данный интерфейс предназначен для реализации генерации и шифрования заголовков для аутентификации в сервисе Yahoo
 */
public interface AuthCreator {
    /**
     * Создать и зашифровать заголовки для отправки на Yahoo
     * <p>
     * Для этого используются такие ключи, хранящиеся в объекте класса YahooAuth:
     * <p>
     * AppID
     * ClientID (Consumer Key)
     * Client Secret (Consumer Secret)
     * <p>
     * Примеры полной строки URL:
     * https://weather-ydn-yql.media.yahoo.com/forecastrss?location=sunnyvale,ca&format=json&u=c
     * https://weather-ydn-yql.media.yahoo.com/forecastrss?location=saratov,saratovoblast&format=json&u=c
     *
     * @param city
     * @param region
     * @return объект Http-запроса с зашифрованными данными
     */
    HttpEntity<String> createHeaders(String city, String region);
}
