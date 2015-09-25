package bookshelf.inventory.api;

/**
 * Created by Administrador on 28/08/2015.
 */
public interface MutableBook extends Book {
    void setIsbn(String isbn);

    void setTitle(String title);

    void setAuthor(String author);

    void setCategory(String category);

    void setRating(String rating);
}
