package by.lab6.compositions.instrumental;

import by.lab6.compositions.CompositionBuilder;

import java.util.ArrayList;

public class Sonata extends InstrumentalComposition{
    /**
     * @param name -- the name of the composition
     * @param duration -- the duration of the composition
     * @param composer -- the composer of the composition
     * @param performers -- performers of the composition
     * @param tonality -- tonality of the composition
     */
    public Sonata(String name, int duration, String composer, ArrayList<String> performers, String tonality) {
        super(name, duration, composer, performers, tonality);
    }

    /**
     * @param builder -- the CompositionBuilder used to create new composition
     */
    public Sonata(CompositionBuilder builder) {
        super(builder);
    }
}
