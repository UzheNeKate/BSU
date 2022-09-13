package Parsers;

import Model.TextParts.Code.CodeBlock;
import Model.TextParts.Text.Paragraph;
import Model.TextParts.TextPart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.ArrayList;

public class ParagraphParser {

    private static final Logger logger = (Logger) LogManager.getLogger();
    private SentenceParser nextSplitter;

    ParagraphParser(){
        nextSplitter = new SentenceParser();
    }

    /**
     * @param text text to parse
     * @return parsed text
     * @throws InvalidParserException
     */
    public ArrayList<TextPart> split(String text) throws InvalidParserException {
        logger.info("Parsing the paragraph");
        ArrayList<TextPart> result = new ArrayList<>();
        String[] split = text.split(CodeBlock.DIVIDER);
        for (int i = 0; i < split.length; i++){
            if ((i & 1) == 1) {
                split[i] = trim(split[i]);
                if(split[i].length() > 0){
                    result.add(new CodeBlock(split[i]));
                }
            } else {
                String [] paragraphs = split[i].split(Paragraph.DIVIDER_PATTERN);
                for(int j = 0; j < paragraphs.length; j++){
                    paragraphs[j] = trim(paragraphs[j]);
                    if(paragraphs[j].length() > 0){
                        result.add(new Paragraph(paragraphs[j]));
                    }
                }
            }
            logger.info("Paragraph is parsed!");
        }

        if (result.isEmpty())
            throw new InvalidParserException("There is no paragraphs or code blocks");
        ArrayList<TextPart> splited;

        try {
            splited = nextSplitter.split(result);
        }
        catch (Exception e) {
            throw e;
        }

        return splited;
    }

    /**
     * replace tabs
     * @param text text
     * @return replaced text
     */
    protected String trim(String text){
        logger.info("Replacing tabs");
        text = text.trim();
        text = text.replaceAll( "[\t ]+", " ");
        logger.info("Tabs are replaced");
        return text;
    }

    @Override
    public String toString() {
        return "TextParser of paragraphs and code blocks";
    }
}
