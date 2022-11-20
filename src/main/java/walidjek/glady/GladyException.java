package walidjek.glady;

public class GladyException extends RuntimeException {

    public GladyException(String message, Exception exception) {
        super(message, exception);
    }

    public GladyException(String message) {
        super(message);
    }

}
