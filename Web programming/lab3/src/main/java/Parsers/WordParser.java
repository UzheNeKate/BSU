package Parsers;

import Model.TextParts.Text.PunctuationMark;
import Model.TextParts.Text.PunctuationMarkType;
import Model.TextParts.Text.Sentence;
import Model.TextParts.Text.Word;
import Model.TextParts.TextPart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParser {

    private static final Logger logger = (Logger) LogManager.getLogger();
    /**
     * split text into words
     * @param textParts some textParts
     * @return parsed text Parts
     */
    public ArrayList<TextPart> split(ArrayList<TextPart> textParts) throws InvalidParserException {
        logger.info("Parsing words");
        ArrayList<TextPart> result = new ArrayList<>();
        for (TextPart textPart : textParts) {
            if (textPart.getClass() == Sentence.class) {
                Matcher matcher = Pattern.compile("(" + Word.PATTERN + "|" +
                        PunctuationMark.PATTERN + ")").matcher(textPart.getValue());
                Sentence sentence = new Sentence(textPart.getValue());
                while (matcher.find()) {
                    if (Pattern.compile(PunctuationMark.PATTERN).matcher(matcher.group()).matches()) {
                        for (PunctuationMarkType type :
                                PunctuationMarkType.values()) {
                            if (type.toString().equals(matcher.group())) {
                                PunctuationMark punctuationMark = new PunctuationMark(type);
                                sentence.addPunctuationMark(punctuationMark);
                                break;
                            }
                        }

                    } else {
                        Word word = new Word(matcher.group());
                        sentence.addWord(word);
                    }
                }
                result.add(sentence);
            } else {
                result.add(textPart);
            }
        }
        if (result.isEmpty())
            throw new InvalidParserException("There is no words and punctuation marks");

        logger.info("Words are parsed");
        return result;
    }

    @Override
    public String toString() {
        return "Splitting into words";
    }
}
