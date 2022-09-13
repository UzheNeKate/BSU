package fx.disk.compositions.instrumental;

import fx.disk.compositions.AbstractComposition;
import fx.disk.compositions.CompositionBuilder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Objects;

public abstract class InstrumentalComposition extends AbstractComposition {
    @Getter
    @Setter
    private String tonality;

    /**
     * @param name -- the name of the composition
     * @param duration -- the duration of the composition
     * @param composer -- the composer of the composition
     * @param performers -- performers of the composition
     * @param tonality -- tonality of the composition
     */
    public InstrumentalComposition(String name, int duration, String composer, ArrayList<String> performers,
                                   String tonality) {
        super(name, duration, composer, performers);
        this.tonality = tonality;
    }

    /**
     * @param builder -- the CompositionBuilder used to create new composition
     */
    public InstrumentalComposition(CompositionBuilder builder) {
        super(builder);
        this.tonality = builder.getTonality();
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
        InstrumentalComposition ic = (InstrumentalComposition) o;
        return Objects.equals(tonality, ic.tonality);
    }

    /**
     * @return hash of the composition
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tonality);
    }
}
