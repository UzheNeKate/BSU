package model.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Book in the library
 */
public class Book {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String author;
    @Getter
    @Setter
    private int numOfItems;

    /**
     * @param id id
     * @param title title
     * @param author author
     * @param numOfItems number of items
     */
    public Book(int id, String title, String author, int numOfItems) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.numOfItems = numOfItems;
    }

    @Override
    public String toString() {
        return "Book:" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", numOfItems=" + numOfItems;
    }
}
