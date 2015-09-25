package bookshelf.service.api.impl.activator;

import bookshelf.inventory.api.BookAlreadyExistsException;
import bookshelf.inventory.api.BookNotFoundException;
import bookshelf.inventory.api.InvalidBookException;
import bookshelf.service.api.BookInventoryNotRegisteredRuntimeException;
import bookshelf.service.api.BookshelfService;
import bookshelf.service.api.InvalidCredentialsException;
import bookshelf.service.api.SessionNotValidRuntimeException;
import bookshelf.service.api.impl.BookshelfServiceImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import java.util.Set;

/**
 * Created by Administrador on 30/08/2015.
 */
public class BookshelfServiceImplActivator implements BundleActivator {


    private ServiceRegistration reg = null;

    public void start(BundleContext bundleContext) throws Exception {

        reg = bundleContext.registerService(
                BookshelfService.class.getName(), new BookshelfServiceImpl(bundleContext), null);

        // testService(bundleContext);
    }

    public void stop(BundleContext bundleContext) throws Exception {

        if (reg != null) {
            bundleContext.ungetService(reg.getReference());
        }
    }


    private void testService(BundleContext context) {

        // Retrieve service
        String name = BookshelfService.class.getName();
        ServiceReference ref = context.getServiceReference(name);
        if (ref == null) {
            throw new RuntimeException("Service not registered: " + name);
        }

        BookshelfService service = (BookshelfService) context.getService(ref);

        String sessionId;

        // Auenticate and get session
        System.out.println("\nSigning in...");
        try {
            sessionId = service.login("admin", "admin".toCharArray());
        } catch (InvalidCredentialsException e) {
            e.printStackTrace();
            return;
        }

        // Add a few books
        System.out.println("\nAdding books...");
        try {
            service.addBook(sessionId, "123-1", "Book Title 1", "John Doe", "Group 1", 0);
            service.addBook(sessionId, "123-2", "Book Title 2", "Will Smith", "Group 1", 0);
            service.addBook(sessionId, "123-3", "Book Title 3", "John Doe", "Group 2", 0);
            service.addBook(sessionId, "123-4", "Book Title 4", "Jane Doe", "Group 2", 0);
        } catch (BookAlreadyExistsException bookAlreadyExistsExcepion) {
            bookAlreadyExistsExcepion.printStackTrace();
        } catch (InvalidBookException e) {
            e.printStackTrace();
        }

        // And test search
        String authorLike = "%Doe";
        System.out.println("Searching for books with author like: " + authorLike);
        Set<String> results = service.searchBooksByAuthor(sessionId, authorLike);
        if (results == null) {
            return;
        }
        for (String isbn : results) {
            try {
                System.out.println(" - " + service.getBook(sessionId, isbn));
            } catch (BookNotFoundException e) {
                e.printStackTrace();
            } catch (SessionNotValidRuntimeException e) {
                e.printStackTrace();
            } catch (BookInventoryNotRegisteredRuntimeException e) {
                e.printStackTrace();
            }
        }

    }
}
