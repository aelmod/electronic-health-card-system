package ua.com.bzabza.ehcs.user;

import javax.persistence.PersistenceException;

public class BadUserBehaviorException extends PersistenceException {

    public BadUserBehaviorException() {}

    public BadUserBehaviorException(String message) {
        super(message);
    }
}
