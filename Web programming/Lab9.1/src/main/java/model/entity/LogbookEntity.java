package model.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "logbook", schema = "public", catalog = "library")
public class LogbookEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "end_date")
    private Date endDate;
    @ManyToOne
    @JoinColumn(name = "reader_id", referencedColumnName = "id", nullable = false)
    private ReaderEntity readerByReaderId;
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    private BookEntity bookByBookId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogbookEntity that = (LogbookEntity) o;

        if (id != that.id) return false;
        return Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }

    public ReaderEntity getReaderByReaderId() {
        return readerByReaderId;
    }

    public void setReaderByReaderId(ReaderEntity readerByReaderId) {
        this.readerByReaderId = readerByReaderId;
    }

    public BookEntity getBookByBookId() {
        return bookByBookId;
    }

    public void setBookByBookId(BookEntity bookByBookId) {
        this.bookByBookId = bookByBookId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LogbookEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("endDate=" + endDate)
                .add("readerByReaderId=" + readerByReaderId)
                .add("bookByBookId=" + bookByBookId)
                .toString();
    }
}
