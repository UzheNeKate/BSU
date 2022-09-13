package Model.TextParts.Text;

import Model.TextParts.TextPart;
import Model.TextParts.TextPartType;

public class PunctuationMark extends TextPart {

    public static final String PATTERN = "([" + Sentence.DIVIDER + "]|[" + Word.DIVIDER + "])";
    private final PunctuationMarkType punctuationMarkType;


    /**
     * constructor
     * @param type type of punctuation mark
     */
    public PunctuationMark(PunctuationMarkType type) {
        super(type.toString() , TextPartType.PUNCTUATION_MARK);
        punctuationMarkType = type;
    }

    @Override
    public String toString() {
        return punctuationMarkType.toString();
    }

}
