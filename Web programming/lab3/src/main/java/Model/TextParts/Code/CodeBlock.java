package Model.TextParts.Code;

import Model.TextParts.TextPart;
import Model.TextParts.TextPartType;
import lombok.Getter;

import java.util.ArrayList;

public class CodeBlock extends TextPart {

    public static final String DIVIDER = "<code>";
    @Getter
    private ArrayList<CodeLine> code;

    /**
     * @param value code command value
     */
    public CodeBlock(String value) {
        super(value, TextPartType.CODE_BLOCK);
        code = new ArrayList<>();
    }

    /**
     * @param codeLine code Command
     */
    public void addCodeLine(CodeLine codeLine) {
        code.add(codeLine);
    }

    @Override
    public String toString() {
        StringBuilder textToString = new StringBuilder("\n" + DIVIDER);
        for (CodeLine codeLine : code) {
            textToString.append("\n" + codeLine.toString());
        }
        textToString.append("\n" + DIVIDER);
        return textToString.toString();
    }
}
