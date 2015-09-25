package bookshelf.inventory.api;

/**
 * Created by Administrador on 28/08/2015.
 */
public class BookNotFoundException extends Exception {

    public BookNotFoundException() {
        super();
    }

    public BookNotFoundException(String err) {
        super(err);
    }
}
