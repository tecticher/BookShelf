package bookshelf.inventory.impl.mock;

import bookshelf.inventory.api.MutableBook;

/**
 * Created by Administrador on 29/08/2015.
 */
public class MutableBookImpl implements MutableBook {

    private String isbn;
    private String title;
    private String author;
    private String category;
    private int rating;

    public MutableBookImpl(String isbn ) {
        setIsbn(isbn);
    }

    @Override
    public String toString() {
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append(getCategory()).append(": ");
        strBuffer.append(getTile()).append(" from ").append(getAuthor());
        strBuffer.append(" [").append(getRating()).append("]");

        return strBuffer.toString();
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRating(String rating) {
        this.rating = Integer.parseInt(String.valueOf(rating));
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTile() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public int getRating() {
        return rating;
    }
}
