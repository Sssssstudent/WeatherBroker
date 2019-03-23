package authCreator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Данный класс предназначен для шифрование заголовков запроса на Yahoo
 *
 * Для этого нужно предварительно получить следующие ключи для Вашего API:
 * App ID
 * Client ID (Consumer Key)
 * Client Secret (Consumer Secret)
 *
 * Данные заголовки содержатся в классе  YahooAuth
 * @return объект HTTP-запроса с зашифрованными данными
 */
public class AuthCreatorImpl implements AuthCreator {
    private final Logger log = LoggerFactory.getLogger(AuthCreatorImpl.class);

    @Inject
    private YahooAuth yahooAuth;

        @Override
        public HttpEntity<String> create(String city, String region)  {
        //Создаем рандомную последовательность для аутентификации
        long timestamp = new Date().getTime() / 1000;
        byte[] nonce = new byte[32];
        Random rand = new Random();
        rand.nextBytes(nonce);
        String oauthNonce = new String(nonce).replaceAll("\\W", "");

        //Создаем коллекцию для сортировки параметров аутентификации
        List<String> parameters = new ArrayList<>();
        parameters.add("oauth_consumer_key=" + yahooAuth.getConsumerKey());
        parameters.add("oauth_nonce=" + oauthNonce);
        parameters.add("oauth_signature_method=HMAC-SHA1");
        parameters.add("oauth_timestamp=" + timestamp);
        parameters.add("oauth_version=1.0");
            try {
                parameters.add("location=" + URLEncoder.encode(city + "," + region, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                log.error("Error encoding parameter {}", e.getMessage(), e);
            }
        parameters.add("format=json");
        Collections.sort(parameters);

        //Записываем отсортированные параметры в строку
        StringBuffer parametersList = new StringBuffer();
        for (int i = 0; i < parameters.size(); i++) {
            parametersList.append(((i > 0) ? "&" : "") + parameters.get(i));
        }

        //Составляем строку для запроса
        String signatureString = "";
            try {
                signatureString = "GET&" +
                        URLEncoder.encode(yahooAuth.getUrl(), "UTF-8") + "&" +
                        URLEncoder.encode(parametersList.toString(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                log.error("Error encoding parameter {}", e.getMessage(), e);
            }

        //Шифруем ключ
        String signature = null;
        try {
            SecretKeySpec signingKey = new SecretKeySpec((yahooAuth.getConsumerSecret() + "&").getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHMAC = mac.doFinal(signatureString.getBytes());
            Base64.Encoder encoder = Base64.getEncoder();
            signature = encoder.encodeToString(rawHMAC);
        } catch (Exception e) {
            System.err.println("Unable to append signature");
            System.exit(0);
        }

        //Составляем строку для аутентификации с необходимыми параметрами
        String authorizationLine = "OAuth " +
                "oauth_consumer_key=\"" + yahooAuth.getConsumerKey() + "\", " +
                "oauth_nonce=\"" + oauthNonce + "\", " +
                "oauth_timestamp=\"" + timestamp + "\", " +
                "oauth_signature_method=\"HMAC-SHA1\", " +
                "oauth_signature=\"" + signature + "\", " +
                "oauth_version=\"1.0\"";

            /**
             * Создаем объект HTTP-запроса с зашифрованными данными
             */
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", authorizationLine);
            headers.add("Yahoo-App-Id", yahooAuth.getAppId());
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            return new HttpEntity<>("parameters", headers);
        }
}
