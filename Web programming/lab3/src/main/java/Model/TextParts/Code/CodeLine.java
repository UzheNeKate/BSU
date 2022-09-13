package Model.TextParts.Code;

import Model.TextParts.TextPart;
import Model.TextParts.TextPartType;
import lombok.Getter;

public class CodeLine extends TextPart {

    public static final String DIVIDER = "\n";
    @Getter
    private String codeLine;


    /**
     * @param value value of code line
     */
    public CodeLine(String value ){
        super(value, TextPartType.CODE_LINE );
        codeLine = value;
    }

    @Override
    public String toString() {
        return codeLine;
    }

}