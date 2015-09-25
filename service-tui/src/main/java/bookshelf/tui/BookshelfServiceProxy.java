package bookshelf.tui;

/**
 * Created by usuario on 21/9/15.
 */

import bookshelf.inventory.api.Book;
import bookshelf.inventory.api.BookAlreadyExistsException;
import bookshelf.inventory.api.InvalidBookException;
import bookshelf.service.api.InvalidCredentialsException;

import java.util.Set;


public interface BookshelfServiceProxy {

    String SCOPE = "book";

    String[] FUNCTIONS = new String[]{"add","search"};

    /**
     *
     * @param username
     * @param password
     * @param isbn
     * @param title
     * @param author
     * @param category
     * @param rating
     * @return
     * @throws InvalidCredentialsException
     * @throws BookAlreadyExistsException
     * @throws InvalidBookException
     */
    public String add(String username, String password, String isbn, String title, String author,
               String category, int rating) throws InvalidCredentialsException,
            BookAlreadyExistsException, InvalidBookException;

    /**
     *
     * @param username
     * @param password
     * @param attribute
     * @param filter
     * @return
     * @throws InvalidCredentialsException
     */
    public Set<Book> search(String username, String password, String attribute, String filter)
            throws InvalidCredentialsException;

    /**
     *
     * @param username
     * @param password
     * @param attribute
     * @param lower
     * @param upper
     * @return
     * @throws InvalidCredentialsException
     */
    public Set<Book> search(String username, String password, String attribute, int lower, int upper)
            throws InvalidCredentialsException;
}
