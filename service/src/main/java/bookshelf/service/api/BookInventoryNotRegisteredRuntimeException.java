package bookshelf.service.api;

/**
 * Created by Administrador on 30/08/2015.
 */
public class BookInventoryNotRegisteredRuntimeException extends Exception {
    public BookInventoryNotRegisteredRuntimeException(String name) {
        super(name);
    }

    public BookInventoryNotRegisteredRuntimeException() {
        super();
    }
}
