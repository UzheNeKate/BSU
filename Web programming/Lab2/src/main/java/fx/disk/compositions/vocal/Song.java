package fx.disk.compositions.vocal;

import fx.disk.compositions.CompositionBuilder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Objects;

public class Song extends VocalComposition{
    @Getter
    private final String genre;

    /**
     * @param name -- the name of the composition
     * @param duration -- the duration of the composition
     * @param composer -- the composer of the composition
     * @param performers -- performers of the composition
     * @param textWriter -- text writer of the composition
     * @param genre -- genre of the composition
     */
    public Song(String name, int duration, String composer, ArrayList<String> performers,
                String textWriter, String genre) {
        super(name, duration, composer, performers, textWriter);
        this.genre = genre;
    }

    /**
     * @param builder -- the CompositionBuilder used to create new composition
     */
    public Song(CompositionBuilder builder) {
        super(builder);
        this.genre = builder.getGenre();
    }

    /**
     * @param o -- the object to compare to
     * @return true if the objects to compare are equals, else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Song song = (Song) o;
        return Objects.equals(genre, song.genre);
    }

    /**
     * @return hash of the composition
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), genre);
    }
}
