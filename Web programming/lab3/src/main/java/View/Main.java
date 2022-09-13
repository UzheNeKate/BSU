package View;

import Controller.*;
import Model.Text;
import Model.TextParts.Text.Word;
import Parsers.InvalidParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main {

    private static final Logger logger = (Logger) LogManager.getLogger();
    public static String path = "path";
    public static void main(String[] args) {
        logger.info("Program started");
        ResourceBundle bundle = ResourceBundle.getBundle("locale_bel_BY", Locale.getDefault());
        ArrayList<Word> word = new ArrayList<>();
        word.add(new Word("ñîëü"));
        word.add(new Word("Java"));
        word.add(new Word("ÿçûê"));
        System.out.println(bundle.getString("readingFile"));
        String textStr = null;
        try {
            textStr = Controller.loadText(System.getenv(path));
        } catch (FilesException e) {
            e.printStackTrace();
        }
        System.out.println(bundle.getString("parsingText"));
        Text text = null;
        try {
            text = Controller.parseTextStringToText(textStr);
        } catch (InvalidParserException e) {
            System.out.println(bundle.getString("parseException"));
        }
        System.out.println(bundle.getString("sortSymb"));
        var sortedText = Controller.sortWordsWithChar(text, 'à');
        System.out.println(bundle.getString("sorted"));
        System.out.println(bundle.getString("sortedWords"));
        for (var w:sortedText) {
            System.out.println(w);
        }
        System.out.println(bundle.getString("sortPop"));
        ArrayList<Word> words = null;
        try {
            words = Controller.sortWordsWithÑount(text, word);
        } catch (SortingWordsException e) {
            e.printStackTrace();
        }
        System.out.println(bundle.getString("sorted"));
        System.out.println(bundle.getString("sortedWords"));
        for (var w:words) {
            System.out.println(w);
        }
        logger.info("Program finished");
    }
}
