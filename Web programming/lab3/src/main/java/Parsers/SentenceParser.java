package Parsers;

import Model.TextParts.Code.CodeBlock;
import Model.TextParts.Code.CodeLine;
import Model.TextParts.Text.Paragraph;
import Model.TextParts.Text.Sentence;
import Model.TextParts.TextPart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser {

    private static final Logger logger = (Logger) LogManager.getLogger();
    private final WordParser nextSplitter;

    public SentenceParser() {
        nextSplitter = new WordParser();
    }

    /**
     * @param textParts text parts to parse
     * @return parsed parts
     * @throws InvalidParserException
     */
    public ArrayList<TextPart> split(ArrayList<TextPart> textParts) throws InvalidParserException {
        logger.info("Parsing the sentence");
        ArrayList<TextPart> result = new ArrayList<>();
        for (var textPart : textParts) {
            if (textPart.getClass() == CodeBlock.class) {
                String[] Commands = textPart.getValue().split(CodeLine.DIVIDER);
                CodeLine currentCommand = null;
                CodeBlock codeBlock = new CodeBlock(textPart.getValue());
                for (String Command : Commands) {
                    Command = trim(Command);
                    if (Command.length() > 0) {
                        currentCommand = new CodeLine((Command));
                        codeBlock.addCodeLine(currentCommand);
                    }
                }
                result.add(codeBlock);
            } else if (textPart.getClass() == Paragraph.class) {
                Sentence sentence;
                Matcher matcher = Pattern.compile(Sentence.SPLITTING_REGEX).matcher(textPart.getValue());
                result.add(textPart);
                while (matcher.find()) {
                    sentence = new Sentence(trim(matcher.group()));
                    result.add(sentence);
                }
            }
        }

        if (result.isEmpty())
            throw new InvalidParserException("There is no sentence or code lines");
        ArrayList<TextPart> splited;

        try {
            splited = nextSplitter.split(result);
        }
        catch (Exception e) {
            throw e;
        }
        logger.info("The sentence is parsed");
        return splited;
    }

    /**
     * replace tabs
     * @param text text
     * @return replaced text
     */
    protected String trim(String text) {
        logger.info("Replacing tabs");
        text = text.trim();
        text = text.replaceAll("[\t ]+", " ");
        logger.info("Tabs are replaced");
        return text;
    }

    @Override
    public String toString() {
        return "Splitting into sentences and code lines";
    }
}
