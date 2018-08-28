package moe.yiheng.exceptions;

public class CallbackNotFoundException extends Exception {
    public CallbackNotFoundException() {
    }

    public CallbackNotFoundException(String message) {
        super(message);
    }

    public CallbackNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CallbackNotFoundException(Throwable cause) {
        super(cause);
    }

    public CallbackNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
