package Parsers;

import Model.Text;
import Model.TextParts.TextPart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.ArrayList;

public class TextParser {

    private static final Logger logger = (Logger) LogManager.getLogger();
    private ParagraphParser nextSplitter;


    public TextParser() {
        nextSplitter = new ParagraphParser();
    }

    /**
     * split text codeBlock and paragraph
     * @param textString text
     * @return return Text object
     */
    public Text splitText(String textString) throws InvalidParserException {
        logger.info("Parse the text");
        Text text = new Text();
        ArrayList<TextPart> result;
        try {
            result = nextSplitter.split(trim(textString));
        } catch (Exception e) {
            throw e;
        }

        text.setText(result);
        logger.info("Text is parsed");
        return text;
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

}
