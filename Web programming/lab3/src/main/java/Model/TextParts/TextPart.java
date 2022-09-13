package Model.TextParts;

import lombok.Getter;

public abstract class TextPart {
    @Getter
    protected TextPartType type;
    @Getter
    protected String value;

    /**
     * @param value value of text part
     * @param type type of text part
     */
    protected TextPart(String value, TextPartType type){
        if( type == null){
            throw new IllegalArgumentException( "Illegal argument type" );
        }
        this.type = type;
        this.value = value;
    }

}
