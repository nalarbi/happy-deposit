package walidjek.glady.deposit;

import walidjek.glady.GladyException;

public class UnhandledDepositTypeException extends GladyException {

    public UnhandledDepositTypeException(String message) {
        super(message);
    }
}
