package Model.TextParts.Text;


/**
 * the enum for punctuation marks
 */
public enum PunctuationMarkType {

    DOT("."),
    COMMA(","),
    EXCLAMATION("!"),
    QUESTION("?"),
    COLON(":"),
    SEMICOLON(";"),
    SINGLE_QUOTES("'"),
    QUOTES("\"");

    private final String text;

    PunctuationMarkType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
