package by.lab6.compositions.vocal;

import by.lab6.compositions.CompositionBuilder;

import java.util.ArrayList;

public class Opera extends VocalComposition{
    /**
     * @param name -- the name of the composition
     * @param duration -- the duration of the composition
     * @param composer -- the composer of the composition
     * @param performers -- performers of the composition
     * @param textWriter -- text writer of the composition
     */
    public Opera(String name, int duration, String composer, ArrayList<String> performers, String textWriter) {
        super(name, duration, composer, performers, textWriter);
    }

    /**
     * @param builder -- the CompositionBuilder used to create new composition
     */
    public Opera(CompositionBuilder builder) {
        super(builder);
    }
}
