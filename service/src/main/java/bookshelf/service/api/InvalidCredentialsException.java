package bookshelf.service.api;

/**
 * Created by Administrador on 29/08/2015.
 */
public class InvalidCredentialsException extends Exception {

    public InvalidCredentialsException() {
        super();
    }

    public InvalidCredentialsException(String err) {
        super(err);
    }
}
