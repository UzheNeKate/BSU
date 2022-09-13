package model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "reader", schema = "public", catalog = "library")
@NamedQueries({
        @NamedQuery(name = "allReaders", query = "SELECT r FROM ReaderEntity r"),
        @NamedQuery(name = "readerByName", query = "SELECT r FROM ReaderEntity r WHERE r.name = ?1"),
})
public class ReaderEntity {

    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Getter
    @Setter
    @Basic
    @Column(name = "name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        ReaderEntity that = (ReaderEntity) o;

        if (id != that.id)
            return false;
        return Objects.equals(name, that.name);
    }

    /**
     * @return hash of this object
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    /**
     * @return string represents this object
     */
    @Override
    public String toString() {
        return "Reader:" +
                "id=" + id +
                ", name='" + name + '\'';
    }
}
