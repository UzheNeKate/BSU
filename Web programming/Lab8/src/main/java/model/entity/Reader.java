package model.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Person who get books in library
 */
public class Reader {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String name;

    /**
     * @param id id of reader
     * @param name name of reader
     */
    public Reader(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Reader:" +
                "id=" + id +
                ", name='" + name + '\'';
    }
}
