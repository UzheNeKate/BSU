package Model.TextParts.Text;

import Model.TextParts.TextPart;
import Model.TextParts.TextPartType;
import lombok.Getter;

public class Word extends TextPart {

    public static final String DIVIDER = ",:;'\"";
    public static final String PATTERN = "[^ " + Paragraph.DIVIDER + Sentence.DIVIDER + DIVIDER +  "]+";

    @Getter
    private String text;

    /**
     * constructor
     * @param text word value
     */
    public Word(String text) {
        super(text, TextPartType.WORD);
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
