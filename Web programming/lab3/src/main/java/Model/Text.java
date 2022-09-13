package Model;

import Model.TextParts.Code.CodeBlock;
import Model.TextParts.Text.Paragraph;
import Model.TextParts.Text.Sentence;
import Model.TextParts.Text.Word;
import Model.TextParts.TextPart;
import Model.TextParts.TextPartType;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.ArrayList;

public class Text extends TextPart {

    private static final Logger logger = (Logger) LogManager.getLogger();
    @Setter
    private ArrayList<TextPart> text;

    public Text() {
        super("", TextPartType.TEXT);
        text = new ArrayList<>();
    }

    /**
     * add sentence to array
     * @param sentence sentence
     */
    public void addSentence(Sentence sentence) {
        text.add(sentence);
    }

    /**
     * add paragraph to array
     * @param paragraph paragraph
     */
    public void addParagraph(Paragraph paragraph) {
        text.add(paragraph);
    }

    /**
     * add codeBlock to array
     * @param codeBlock code block
     */
    public void addCode(CodeBlock codeBlock) {
        text.add(codeBlock);
    }

    /**
     * get all sentences
     * @return  sentences in text
     */
    public ArrayList<Sentence> getSentences() {
        logger.info("Getting sentences...");
        ArrayList<Sentence> sentences = new ArrayList<>();
        for (TextPart textPart : text) {
            if (textPart.getClass() == Sentence.class)
                sentences.add((Sentence)textPart);
        }
        logger.info("Sentences are ready!");
        return sentences;
    }

    /**
     * get all text words
     * @return words in text
     */
    public ArrayList<Word> getAllTextWords() {
        logger.info("Getting words from text...");
        ArrayList<Sentence> sentences = getSentences();
        ArrayList<Word> words = new ArrayList<>();
        for (Sentence sentence : sentences) {
            words.addAll(sentence.getWords());
        }
        logger.info("Words are ready!");
        return words;
    }

    /**
     * get all paragraphs
     * @return  paragraphs in text
     */
    public ArrayList<Paragraph> getParagraphs() {
        logger.info("Getting paragraphs...");
        ArrayList<Paragraph> paragraphs = new ArrayList<>();
        for (TextPart textPart : text) {
            if (textPart.getClass() == Paragraph.class)
                paragraphs.add((Paragraph)textPart);
        }
        logger.info("Paragraphs are ready!");
        return paragraphs;
    }

    /**
     * get all code blocks
     * @return code blocks in text
     */
    public ArrayList<CodeBlock> getCodeBlocks() {
        logger.info("Getting code blocks...");
        ArrayList<CodeBlock> codeBlocks = new ArrayList<>();
        for (TextPart textPart : text) {
            if (textPart.getClass() == CodeBlock.class)
                codeBlocks.add((CodeBlock)textPart);
        }
        logger.info("Code blocks are ready!");
        return codeBlocks;
    }

    @Override
    public String toString() {
        StringBuilder textToString = new StringBuilder();
        for (var textPart : text) {
            textToString.append(textPart.toString());
            if (textPart.getClass() == Sentence.class)
                textToString.append(" ");
        }
        return textToString.toString();
    }

    /**
     * @param c char to sort the words
     * @return sorted words
     */
    public ArrayList<Word> sortWordsWithChar(char c) {

        logger.info("Sorting words...");
        var words = getAllTextWords();
        words.sort((a,b) ->
                (int)a.getValue().chars().filter(ch -> ch == c).count() ==(int)b.getValue().chars().filter(ch -> ch == c).count()
                        ? a.getValue().compareTo(b.getValue()) : (int)a.getValue().chars().filter(ch -> ch == c).count()
                                                                -(int)b.getValue().chars().filter(ch -> ch == c).count());
        logger.info("Words are sorted!");
        return words;
    }
}
