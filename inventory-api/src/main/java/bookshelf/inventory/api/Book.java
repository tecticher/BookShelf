package bookshelf.inventory.api;

/**
 * Created by Administrador on 28/08/2015.
 */
public interface Book {
    String getIsbn();

    String getTile();

    String getAuthor();

    String getCategory();

    int getRating();
}
