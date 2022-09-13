package Model.TextParts.Text;

import Model.TextParts.TextPart;
import Model.TextParts.TextPartType;

public class Paragraph extends TextPart {

    public static final String DIVIDER = "\n";
    public static final String DIVIDER_PATTERN = DIVIDER;

    /**
     * @param text text value
     */
    public Paragraph(String text) {
        super(text, TextPartType.PARAGRAPH);
    }

    @Override
    public String toString() {
        return "\n";
    }

}
