package com.example.lab5_ultimate.model.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "book", schema = "public", catalog = "library")
@NamedQueries({
        @NamedQuery(name = "allBooks", query = "SELECT b FROM BookEntity b"),
        @NamedQuery(name = "bookByName", query = "SELECT b FROM BookEntity b WHERE b.title = ?1 and b.author =?2"),
        @NamedQuery(name = "booksByAuthor", query = "SELECT b FROM BookEntity b WHERE b.author =?1"),
        @NamedQuery(name = "deleteById", query = "DELETE FROM  BookEntity b WHERE b.id = ?1"),
        @NamedQuery(name = "removeItem", query = "UPDATE BookEntity b SET b.numOfItems = b.numOfItems - 1 WHERE b.id = ?1"),
        @NamedQuery(name = "addItem", query = "UPDATE BookEntity b SET b.numOfItems = b.numOfItems + 1 WHERE b.id = ?1"),
}
)
public class BookEntity {

    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Getter
    @Setter
    @Basic
    @Column(name = "title")
    private String title;

    @Getter
    @Setter
    @Basic
    @Column(name = "author")
    private String author;

    @Getter
    @Setter
    @Basic
    @Column(name = "num_of_items")
    private int numOfItems;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntity that = (BookEntity) o;

        if (id != that.id) return false;
        if (numOfItems != that.numOfItems) return false;
        if (!Objects.equals(title, that.title)) return false;
        if (!Objects.equals(author, that.author)) return false;

        return true;
    }

    /**
     * @return hash of this object
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + numOfItems;
        return result;
    }

    /**
     * @return string represents this object
     */
    @Override
    public String toString() {
        return "Book:" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", numOfItems=" + numOfItems;
    }
}
