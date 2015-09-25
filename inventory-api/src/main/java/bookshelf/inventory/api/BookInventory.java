package bookshelf.inventory.api;

import java.util.Map;
import java.util.Set;

/**
 * Created by Administrador on 28/08/2015.
 */
public interface BookInventory {
    enum SearchCriteria {
        ISBN_LIKE,
        TITLE_LIKE,
        AUTHOR_LIKE,
        CATEGORY_LIKE,
        GROUP_LIKE,
        RATING_GT,
        RATING_LT
    }

    Set<String> getCategories();

    MutableBook createBook(String isbn) throws BookAlreadyExistsException;

    MutableBook loadBookForEdit(String isbn) throws BookNotFoundException;

    String storeBook(MutableBook book) throws InvalidBookException;

    Book loadBook(String isbn) throws BookNotFoundException;

    void removeBook(String isbn) throws BookNotFoundException;

    Set<String> searchBooks(Map<SearchCriteria, String> criteria);
}
