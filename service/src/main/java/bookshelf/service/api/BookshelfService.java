package bookshelf.service.api;

import bookshelf.inventory.api.*;

import java.util.Set;

/**
 *
 */
public interface BookshelfService extends Authentication {

    /**
     * @param sessionId
     * @return
     */
    public Set<String> getGroups(String sessionId);

    /**
     * @param session
     * @param isbn
     * @param title
     * @param author
     * @param category
     * @param rating
     * @throws BookAlreadyExistsException
     * @throws InvalidBookException
     */
    public void addBook(String session, String isbn, String title, String author, String category, int rating) throws
            BookAlreadyExistsException, InvalidBookException;


    /**
     * @param session
     * @param isbn
     * @param category
     * @throws BookNotFoundException
     * @throws InvalidBookException
     */
    public void modifyBookCategory(String session, String isbn, String category) throws BookNotFoundException, InvalidBookException;

    /**
     * @param session
     * @param isbn
     * @param rating
     * @throws BookNotFoundException
     * @throws InvalidBookException
     */
    public void modifyBookRating(String session, String isbn, int rating) throws BookNotFoundException, InvalidBookException;

    /**
     * @param session
     * @param isbn
     * @throws BookNotFoundException
     */
    public void removeBook(String session, String isbn) throws BookNotFoundException;


    /**
     * @param session
     * @param isbn
     * @return
     * @throws BookNotFoundException
     */
    public Book getBook(String session, String isbn) throws BookNotFoundException, SessionNotValidRuntimeException, BookInventoryNotRegisteredRuntimeException;

    /**
     * @param session
     * @param isbn
     * @return
     * @throws BookNotFoundException
     * @throws SessionNotValidRuntimeException
     */
    public MutableBook getBookForEdit(String session, String isbn) throws BookNotFoundException, SessionNotValidRuntimeException;

    /**
     * @param session
     * @param categoryLike
     * @return
     */
    public Set<String> searchBooksByCategory(String session, String categoryLike);

    /**
     * @param session
     * @param authorLike
     * @return
     */
    public Set<String> searchBooksByAuthor(String session, String authorLike);

    /**
     * @param session
     * @param titleLike
     * @return
     */
    public Set<String> searchBooksByTitle(String session, String titleLike);

    /**
     * @param session
     * @param ratingLower
     * @param ratingUpper
     * @return
     */
    public Set<String> searchBooksByRating(String session, int ratingLower, int ratingUpper);


}
