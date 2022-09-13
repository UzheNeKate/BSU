package fx.disk.compositions;

import lombok.Getter;
import java.util.ArrayList;
import java.util.Objects;

public abstract class AbstractComposition {
    @Getter
    private final String name;
    @Getter
    private final int duration;
    @Getter
    private final String composer;
    @Getter
    private final ArrayList<String> performers;

    /**
     * @param name -- the name of the composition
     * @param duration -- the duration of the composition
     * @param composer -- the composer of the composition
     * @param performers -- performers of the composition
     */
    public AbstractComposition(String name, int duration, String composer, ArrayList<String> performers) {
        this.name = name;
        this.duration = duration;
        this.composer = composer;
        this.performers = performers;
    }

    /**
     * @param builder -- the CompositionBuilder used to create new composition
     */
    public AbstractComposition(CompositionBuilder builder) {
        this.name = builder.getName();
        this.composer = builder.getComposer();
        this.performers = builder.getPerformers();
        this.duration = builder.getDuration();
    }

    /**
     * @param o -- the object to compare to
     * @return true if the objects to compare are equals, else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractComposition plane = (AbstractComposition) o;
        return Objects.equals(name, plane.name) && duration == plane.duration
                && Objects.equals(composer, plane.composer) && performers == plane.performers;
    }

    /**
     * @return hash of the composition
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, duration, composer, performers);
    }

}
