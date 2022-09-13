package fx.disk.compositions.vocal;

import fx.disk.compositions.CompositionBuilder;

import java.util.ArrayList;

public class Romance extends VocalComposition{
    /**
     * @param name -- the name of the composition
     * @param duration -- the duration of the composition
     * @param composer -- the composer of the composition
     * @param performers -- performers of the composition
     * @param textWriter -- text writer of the composition
     */
    public Romance(String name, int duration, String composer, ArrayList<String> performers, String textWriter) {
        super(name, duration, composer, performers, textWriter);
    }

    /**
     * @param builder -- the CompositionBuilder used to create new composition
     */
    public Romance(CompositionBuilder builder) {
        super(builder);
    }
}
