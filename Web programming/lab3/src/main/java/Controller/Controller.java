package Controller;

import Model.Text;
import Model.TextParts.Text.Word;
import Parsers.InvalidParserException;
import Parsers.TextParser;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Controller {

    private static final Logger logger = (Logger) LogManager.getLogger();
    @Getter
    private static String textString = "";

    /**
     * constructor
     * @param textString text to parse 
     */
    public Controller(String textString) {
        Controller.textString = textString;
    }

    /**
     * load text
     * @param path path to file
     * @return text string
     */
    public static String loadText(String path) throws FilesException {

        logger.info("Text is reading from file...");
        try {
            byte[] text = Files.readAllBytes(Paths.get(path));
            textString = new String(text, Charset.forName("windows-1251"));
        } catch (IOException e) {
            logger.fatal("Cannot load the text!");
            throw new FilesException(e.getMessage());
        }
        logger.info("Text is loaded!");
        return textString;
    }

    /**
     * parse text string to text object
     * @param text
     * @return text object
     */
    public static Text parseTextStringToText(String text) throws InvalidParserException {
        logger.info("Parsing text to string...");
        TextParser textParser = new TextParser();
        Text parsedText;
        try {
            parsedText = textParser.splitText(textString);
        } catch (InvalidParserException e) {
            throw e;
        }
        logger.info("Text is parsed!");
        return parsedText;
    }

    /**
     * sort words with char descent
     * @param t text to sort words in
     * @param c char for sorting
     * @return sorted words
     */
    public static ArrayList<Word> sortWordsWithChar(Text t, char c) {
        return t.sortWordsWithChar(c);
    }

    /**
     * sort words with append in text
     * @param t text to find words in
     * @param words words to sort
     * @return sorted words
     */
    public static ArrayList<Word> sortWordsWith—ount(Text t, ArrayList<Word> words) throws SortingWordsException {
        logger.info("Sorting words...");
        words.sort((a, b) -> (int) (Pattern.compile(b.toString()).splitAsStream(t.toString()).count()
                - (int) Pattern.compile(a.toString()).splitAsStream(t.toString()).count()));
        if(words.isEmpty())
            throw new SortingWordsException("Cannot find words!");
        logger.info("Words are sorted!");
        return words;
    }


}
