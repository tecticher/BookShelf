package bookshelf.tui;

import bookshelf.inventory.api.Book;
import bookshelf.inventory.api.BookAlreadyExistsException;
import bookshelf.inventory.api.BookNotFoundException;
import bookshelf.inventory.api.InvalidBookException;
import bookshelf.service.api.BookInventoryNotRegisteredRuntimeException;
import bookshelf.service.api.BookshelfService;
import bookshelf.service.api.InvalidCredentialsException;
import bookshelf.service.api.SessionNotValidRuntimeException;
import org.apache.felix.service.command.Descriptor;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class BookshelfServiceProxyImpl implements BookshelfServiceProxy {


    private BundleContext context;

    /**
     * @param context
     */
    public BookshelfServiceProxyImpl(BundleContext context) {
        this.context = context;
    }

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
    public String add(@Descriptor("username") String username,
                      @Descriptor("password") String password,
                      @Descriptor("ISBN") String isbn,
                      @Descriptor("Title") String title,
                      @Descriptor("Author") String author,
                      @Descriptor("Category") String category,
                      @Descriptor("Rating (0..10)") int rating)
            throws InvalidCredentialsException, BookAlreadyExistsException, InvalidBookException {

        BookshelfService service = lookupService();
        String sessionId = service.login(username, password.toCharArray());

        service.addBook(sessionId, isbn, title, author, category, rating);
        return isbn;
    }


    /**
     * @param username
     * @param password
     * @param attribute
     * @param filter
     * @return
     * @throws InvalidCredentialsException
     */
    @Descriptor("Search books by author, title or category")
    public Set<Book> search(
            @Descriptor("username") String username,
            @Descriptor("password") String password,
            @Descriptor("search on attribute: author, title or category") String attribute,
            @Descriptor("match like (use % at the beginning er end of <like> for wild-card)") String filter
    ) throws InvalidCredentialsException {

        BookshelfService service = lookupService();

        String sessionId = service.login(username, password.toCharArray());

        Set<String> results;

        switch (attribute) {
            case "title":
                results = service.searchBooksByTitle(sessionId, filter);
                break;
            case "author":
                results = service.searchBooksByAuthor(sessionId, filter);
                break;
            case "category":
                results = service.searchBooksByCategory(sessionId, filter);
                break;
            default:
                throw new RuntimeException("Invalid attribute, expecting one of {'title', 'author', 'category' } got " + attribute);
        }

        return getBooks(sessionId, service, results);


    }

    /**
     * @param username
     * @param password
     * @param attribute
     * @param lower
     * @param upper
     * @return
     * @throws InvalidCredentialsException
     */
    @Descriptor("Search books by rating")
    public Set<Book> search(
            @Descriptor("username") String username,
            @Descriptor("password") String password,
            @Descriptor("search on attribute: rating") String attribute,
            @Descriptor("lower rating limit (inclusive)") int lower,
            @Descriptor("upper rating limit (inclusive)") int upper
    ) throws InvalidCredentialsException {
        if (!"rating".equals(attribute)) {
            throw new RuntimeException("Invalid attribute, expecting 'rating' got '" + attribute + "'");
        }

        BookshelfService service = lookupService();
        String sessionId = service.login(username, password.toCharArray());

        Set<String> results = service.searchBooksByRating(sessionId, lower, upper);
        return getBooks(sessionId, service, results);
    }

    /**
     * @return
     */
    protected BookshelfService lookupService() {

        ServiceReference reference = context.getServiceReference(BookshelfService.class.getName());
        if (reference == null) {
            throw new RuntimeException("Bookshelf Service not registered, cannot invoke operation");
        }
        BookshelfService service = (BookshelfService) context.getService(reference);
        if (service == null) {
            throw new RuntimeException("Bookshelf Service not registered, cannot invoke operation");
        }
        return service;
    }

    /**
     * @param sessionId
     * @param service
     * @param results
     * @return
     */
    private Set<Book> getBooks(String sessionId, BookshelfService service, Set<String> results) {

        Set<Book> books = new HashSet<Book>();

        for (String isbn : results) {
            Book book;
            try {
                book = service.getBook(sessionId, isbn);
                books.add(book);
            } catch (BookNotFoundException ex) {
                System.err.println("ISBN " + isbn + " referenced but not found");
            } catch (SessionNotValidRuntimeException e) {
                System.err.println("Session not valid");
            } catch (BookInventoryNotRegisteredRuntimeException e) {
                System.err.println("BookInventoryNotRegisteredRuntimeException");
            }
        }

        return books;
    }
}