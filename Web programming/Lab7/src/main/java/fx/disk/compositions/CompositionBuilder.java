package fx.disk.compositions;

import fx.disk.compositions.instrumental.*;
import fx.disk.compositions.vocal.*;
import lombok.Getter;
import java.util.ArrayList;

public class CompositionBuilder {
    @Getter
    private String name;
    @Getter
    private int duration;
    @Getter
    private String composer;
    @Getter
    private final ArrayList<String> performers = new ArrayList<>();
    @Getter
    private String tonality;
    @Getter
    private String textWriter;
    @Getter
    private String genre;
    @Getter
    private String instrument;

    /**
     * @param name -- the name of the composition to build
     */
    public CompositionBuilder(String name) {
        this.name = name;
    }

    /**
     * @param duration -- the duration of the composition to set
     * @return this CompositionBuilder
     */
    public CompositionBuilder setDuration(int duration){
        this.duration = duration;
        return this;
    }

    /**
     * @param composer -- the composer of the composition to set
     * @return this CompositionBuilder
     */
    public CompositionBuilder setComposer(String composer){
        this.composer = composer;
        return this;
    }

    /**
     * @param performer -- the performer of the composition to set
     * @return this CompositionBuilder
     */
    public CompositionBuilder setPerformer(String performer){
        this.performers.add(performer);
        return this;
    }

    /**
     * @param tonality -- the tonality of the composition to set
     * @return this CompositionBuilder
     */
    public CompositionBuilder setTonality(String tonality){
        this.tonality = tonality;
        return this;
    }

    /**
     * @param textWriter -- the text writer of the composition to set
     * @return this CompositionBuilder
     */
    public CompositionBuilder setTextWriter(String textWriter){
        this.textWriter = textWriter;
        return this;
    }

    /**
     * @param genre -- the genre of the composition to set
     * @return this CompositionBuilder
     */
    public CompositionBuilder setGenre(String genre){
        this.genre = genre;
        return this;
    }

    /**
     * @param instrument -- the instrument of the composition to set
     * @return this CompositionBuilder
     */
    public CompositionBuilder setInstrument(String instrument){
        this.instrument = instrument;
        return this;
    }

    /**
     * @return the object Opera created with this builder
     */
    public Opera recordOpera(){
        return new Opera(this);
    }

    /**
     * @return the object Romance created with this builder
     */
    public Romance recordRomance(){
        return new Romance(this);
    }

    /**
     * @return the object Song created with this builder
     */
    public Song recordSong(){
        return new Song(this);
    }

    /**
     * @return the object Concert created with this builder
     */
    public Concert recordConcert(){
        return new Concert(this);
    }

    /**
     * @return the object Sonata created with this builder
     */
    public Sonata recordSonata(){
        return new Sonata(this);
    }

    /**
     * @return the object Symphony created with this builder
     */
    public Symphony recordSymphony(){
        return new Symphony(this);
    }
}
