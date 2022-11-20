package walidjek.glady.user;

import walidjek.glady.GladyException;

public class UserNotFoundException extends GladyException {

    public UserNotFoundException(String message) {
        super(message);
    }

}
