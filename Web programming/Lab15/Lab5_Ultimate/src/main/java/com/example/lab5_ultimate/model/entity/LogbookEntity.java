package com.example.lab5_ultimate.model.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "logbook", schema = "public", catalog = "library")
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
    @Column(name = "end_date")
    private Date endDate;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "reader_id", referencedColumnName = "id", nullable = false)
    private ReaderEntity readerByReaderId;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    private BookEntity bookByBookId;


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
