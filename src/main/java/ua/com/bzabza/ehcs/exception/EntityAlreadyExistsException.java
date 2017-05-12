package ua.com.bzabza.ehcs.exception;

import javax.persistence.PersistenceException;

public class EntityAlreadyExistsException extends PersistenceException {

    public EntityAlreadyExistsException() {}

    public EntityAlreadyExistsException(Class entity, String message) {
        super("This " + entity.getName() + " already exists! " + message);
    }

    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
