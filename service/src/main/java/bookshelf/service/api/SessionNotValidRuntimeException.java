package bookshelf.service.api;

/**
 * Created by Administrador on 30/08/2015.
 */
public class SessionNotValidRuntimeException extends Exception {
    public SessionNotValidRuntimeException(String sessionId) {
        super(sessionId);
    }

    public SessionNotValidRuntimeException() {
        super();
    }
}
