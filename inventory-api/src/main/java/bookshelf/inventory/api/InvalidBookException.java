package bookshelf.inventory.api;

/**
 * Created by Administrador on 28/08/2015.
 */
public class InvalidBookException extends Exception {

    public InvalidBookException() {
        super();
    }

    public InvalidBookException(String err) {
        super(err);
    }
}
