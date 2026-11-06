package exceptions;

public class BrowserNotSupportException extends RuntimeException {

    public BrowserNotSupportException(String browserName) {
        super(String.format("Браузер %s не поддерживается", browserName));
    }
}
