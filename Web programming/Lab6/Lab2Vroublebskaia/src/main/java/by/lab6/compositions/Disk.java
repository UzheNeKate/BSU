package by.lab6.compositions;

import by.lab6.compositions.cmp.CompositionComparator;
import by.lab6.compositions.cmp.Fields;
import lombok.SneakyThrows;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class Disk implements Iterable<AbstractComposition> {
    List<AbstractComposition> compositions = new ArrayList<>();

    /**
     * @param composition -- the composition to be added to the disk
     */
    private void addNewComposition(AbstractComposition composition) {
        compositions.add(composition);
    }

    /**
     * @param compositions -- the enum value for the type of composition to be recorded
     * @param name -- the name of recorded composition
     * @param duration -- the duration of recorded composition
     * @param composer -- the composer of recorded composition
     * @param performer -- the performer of recorded composition
     * @param tonality -- the tonality of recorded composition
     * @param textWriter -- the text writer of recorded composition
     * @param genre -- the genre of recorded composition
     * @param instrument -- the instrument of recorded composition
     */
    @SneakyThrows
    public void recordComposition(Compositions compositions, String name, int duration, String composer,
                                  String performer, String tonality, String textWriter, String genre, String instrument)
    {
        CompositionBuilder builder = new CompositionBuilder(name).setComposer(composer)
                .setDuration(duration).setPerformer(performer).setGenre(genre).setInstrument(instrument)
                .setTextWriter(textWriter).setTonality(tonality);
        this.addNewComposition((AbstractComposition) builder.getClass()
                .getMethod("record" + compositions.name()).invoke(builder));
    }

    /**
     * @return iterator for the by.lab6.compositions on the disk
     */
    @Override
    public Iterator<AbstractComposition> iterator() {
        return compositions.iterator();
    }

    /**
     * @param action -- the action to do with each element of the disk
     */
    @Override
    public void forEach(Consumer<? super AbstractComposition> action) {
        compositions.forEach(action);
    }

    /**
     * @param field -- the field by.lab6.compositions will be sorted by
     */
    public void sort(Fields field) {
        this.compositions.sort(new CompositionComparator(field));
    }
}

