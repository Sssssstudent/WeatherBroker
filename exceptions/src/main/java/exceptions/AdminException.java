package exceptions;
/**
 * Ошибка сервиса
 */
public class AdminException extends RuntimeException {
    /**
     * Конструктор ошибки сервиса
     */
    public AdminException(String message) {
        super(message);
    }
}
