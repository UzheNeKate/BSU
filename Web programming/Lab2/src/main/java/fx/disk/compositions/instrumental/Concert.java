package fx.disk.compositions.instrumental;

import fx.disk.compositions.CompositionBuilder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Objects;

public class Concert extends InstrumentalComposition{
    @Getter
    private String instrument;

    /**
     * @param name -- the name of the composition
     * @param duration -- the duration of the composition
     * @param composer -- the composer of the composition
     * @param performers -- performers of the composition
     * @param tonality -- tonality of the composition
     * @param instrument -- instrument the concert is for
     */
    public Concert(String name, int duration, String composer, ArrayList<String> performers,
                   String tonality, String instrument) {
        super(name, duration, composer, performers, tonality);
        this.instrument = instrument;
    }

    /**
     * @param builder -- the CompositionBuilder used to create new composition
     */
    public Concert(CompositionBuilder builder) {
        super(builder);
        this.instrument = builder.getInstrument();
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
        Concert concert = (Concert) o;
        return Objects.equals(instrument, concert.instrument);
    }

    /**
     * @return hash of the composition
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), instrument);
    }
}
