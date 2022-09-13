package fx.disk.compositions.vocal;

import fx.disk.compositions.AbstractComposition;
import fx.disk.compositions.CompositionBuilder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Objects;

public abstract class VocalComposition extends AbstractComposition {
    @Getter
    private final String textWriter;

    /**
     * @param name -- the name of the composition
     * @param duration -- the duration of the composition
     * @param composer -- the composer of the composition
     * @param performers -- performers of the composition
     * @param textWriter -- text writer of the composition
     */
    public VocalComposition(String name, int duration, String composer, ArrayList<String> performers,
                            String textWriter) {
        super(name, duration, composer, performers);
        this.textWriter = textWriter;
    }

    /**
     * @param builder -- the CompositionBuilder used to create new composition
     */
    public VocalComposition(CompositionBuilder builder) {
        super(builder);
        this.textWriter = builder.getTextWriter();
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
        VocalComposition vc = (VocalComposition) o;
        return Objects.equals(textWriter, vc.textWriter);
    }

    /**
     * @return hash of the composition
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), textWriter);
    }
}
