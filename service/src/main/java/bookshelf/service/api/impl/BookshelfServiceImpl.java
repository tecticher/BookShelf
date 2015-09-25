package bookshelf.service.api.impl;

import bookshelf.inventory.api.*;
import bookshelf.service.api.BookInventoryNotRegisteredRuntimeException;
import bookshelf.service.api.BookshelfService;
import bookshelf.service.api.InvalidCredentialsException;
import bookshelf.service.api.SessionNotValidRuntimeException;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import java.util.Arrays;
import java.util.Set;

/**
 * Created by Administrador on 30/08/2015.
 */
public class BookshelfServiceImpl implements BookshelfService {

    private String sessionId;
    private BundleContext context;

    /**
     *
     * @param bundleContext
     */
    public BookshelfServiceImpl(BundleContext bundleContext) {
        context = bundleContext;
    }


    /**
     *
     * @param sessionId
     * @return
     */
    public Set<String> getGroups(String sessionId) {
        return null;
    }

    /**
     *
     * @param session
     * @param isbn
     * @param title
     * @param author
     * @param category
     * @param rating
     * @throws BookAlreadyExistsException
     * @throws InvalidBookException
     */
    public void addBook(String session, String isbn, String title, String author, String category, int rating) throws BookAlreadyExistsException, InvalidBookException {

    }

    /**
     *
     * @param session
     * @param isbn
     * @param category
     * @throws BookNotFoundException
     * @throws InvalidBookException
     */
    public void modifyBookCategory(String session, String isbn, String category) throws BookNotFoundException, InvalidBookException {

    }

    /**
     *
     * @param session
     * @param isbn
     * @param rating
     * @throws BookNotFoundException
     * @throws InvalidBookException
     */
    public void modifyBookRating(String session, String isbn, int rating) throws BookNotFoundException, InvalidBookException {

    }

    /**
     *
     * @param session
     * @param isbn
     * @throws BookNotFoundException
     */
    public void removeBook(String session, String isbn) throws BookNotFoundException {

    }


    /**
     * @param session
     * @param isbn
     * @return
     * @throws BookNotFoundException
     * @throws SessionNotValidRuntimeException
     * @throws BookInventoryNotRegisteredRuntimeException
     */
    public Book getBook(String session, String isbn) throws
            BookNotFoundException, SessionNotValidRuntimeException, BookInventoryNotRegisteredRuntimeException {
        checkSession(session);
        BookInventory inventory = lookupBookInventory();
        return inventory.loadBook(isbn);
    }

    /**
     * @param session
     * @param isbn
     * @return
     * @throws BookNotFoundException
     * @throws SessionNotValidRuntimeException
     */
    public MutableBook getBookForEdit(String session, String isbn) throws BookNotFoundException, SessionNotValidRuntimeException {
        return null;
    }

    /**
     *
     * @param session
     * @param categoryLike
     * @return
     */
    public Set<String> searchBooksByCategory(String session, String categoryLike) {
        return null;
    }

    /**
     *
     * @param session
     * @param authorLike
     * @return
     */
    public Set<String> searchBooksByAuthor(String session, String authorLike) {
        return null;
    }

    /**
     *
     * @param session
     * @param titleLike
     * @return
     */
    public Set<String> searchBooksByTitle(String session, String titleLike) {
        return null;
    }

    /**
     *
     * @param session
     * @param ratingLower
     * @param ratingUpper
     * @return
     */
    public Set<String> searchBooksByRating(String session, int ratingLower, int ratingUpper) {
        return null;
    }

    /**
     * @param username
     * @param password
     * @return
     * @throws InvalidCredentialsException
     */
    public String login(String username, char[] password) throws InvalidCredentialsException {

        if ("admin".equals(username) && Arrays.equals(password, "admin".toCharArray())) {
            sessionId = Long.toString(System.currentTimeMillis());
            return sessionId;
        }
        throw new InvalidCredentialsException(username);
    }

    /**
     * @param sessionId
     */
    public void logout(String sessionId) throws SessionNotValidRuntimeException {
        checkSession(sessionId);
        sessionId = null;
    }

    /**
     * @param sessionId
     * @return
     */
    public boolean sessionIsValid(String sessionId) {
        return sessionId != null && sessionId.equals(sessionId);
    }

    /**
     *
     * @param sessionId
     * @throws SessionNotValidRuntimeException
     */
    protected void checkSession(String sessionId) throws SessionNotValidRuntimeException {
        if (!sessionIsValid(sessionId)) {
            throw new SessionNotValidRuntimeException(sessionId);
        }
    }

    /**
     * @return
     * @throws BookInventoryNotRegisteredRuntimeException
     */
    private BookInventory lookupBookInventory() throws BookInventoryNotRegisteredRuntimeException {
        String name = BookInventory.class.getName();
        ServiceReference ref = context.getServiceReference(name);
        if (ref == null) {
            throw new BookInventoryNotRegisteredRuntimeException(name);
        }
        return (BookInventory) context.getService(ref);
    }
}
