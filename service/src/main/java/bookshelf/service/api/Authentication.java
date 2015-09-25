package bookshelf.service.api;

/**
 * Created by Administrador on 29/08/2015.
 */
public interface Authentication {

    /**
     *
     * @param username
     * @param password
     * @return
     * @throws InvalidCredentialsException
     */
    String login(String username, char[] password) throws InvalidCredentialsException;

    /**
     *
     * @param sessionId
     * @throws SessionNotValidRuntimeException
     */
    void logout(String sessionId) throws SessionNotValidRuntimeException;

    /**
     *
     * @param sessionId
     * @return
     */
    boolean sessionIsValid(String sessionId);
}
