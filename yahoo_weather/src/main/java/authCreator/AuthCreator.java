package authCreator;

import org.springframework.http.HttpEntity;

/**
  *Данный интерфейс предназначен для реализации генерации и шифрования заголовков для аутентификации в сервисе Yahoo
  */
public interface AuthCreator {
     HttpEntity<String> create(String city, String region);
}
