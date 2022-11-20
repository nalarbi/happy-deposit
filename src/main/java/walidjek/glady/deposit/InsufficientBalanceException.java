package walidjek.glady.deposit;

import walidjek.glady.GladyException;

public class InsufficientBalanceException extends GladyException {

    public InsufficientBalanceException(String message) {
        super(message);
    }

    public InsufficientBalanceException(String message, Exception exception) {
        super(message, exception);
    }
}
