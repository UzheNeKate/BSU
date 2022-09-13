package model.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

/**
 * Stores all operations of readers with books
 */
public class Logbook {
    @Getter
    @Setter
    private int bookId;
    @Getter
    @Setter
    private int readerId;
    @Getter
    @Setter
    private Date endDate;

    /**
     * @param bookId id of the book
     * @param readerId id of the reader
     * @param endDate date when the book need to be returned
     */
    public Logbook(int bookId, int readerId, Date endDate) {
        this.bookId = bookId;
        this.readerId = readerId;
        this.endDate = endDate;
    }
}
