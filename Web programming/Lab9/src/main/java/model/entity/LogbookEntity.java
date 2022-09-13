package model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "logbook", schema = "public", catalog = "library")
@NamedQueries({
        @NamedQuery(name = "logbookByReader", query = "SELECT l FROM LogbookEntity l WHERE l.readerId = ?1"),
        @NamedQuery(name = "allLogbooks", query = "SELECT l FROM  LogbookEntity  l")
})
public class LogbookEntity {
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Getter
    @Setter
    @Basic
    @Column(name = "reader_id")
    private int readerId;

    @Getter
    @Setter
    @Basic
    @Column(name = "book_id")
    private int bookId;

    @Getter
    @Setter
    @Basic
    @Column(name = "end_date")
    private Date endDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogbookEntity that = (LogbookEntity) o;

        if (id != that.id) return false;
        if (readerId != that.readerId) return false;
        if (bookId != that.bookId) return false;
        if (!Objects.equals(endDate, that.endDate)) return false;

        return true;
    }

    /**
     * @return hash of this object
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + readerId;
        result = 31 * result + bookId;
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }


}
