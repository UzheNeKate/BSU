package Model.TextParts.Text;

import Model.TextParts.TextPart;
import Model.TextParts.TextPartType;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Sentence extends TextPart {

    public static final String DIVIDER = "\\.!\\?";
    public static final String SPLITTING_REGEX = "[^" + DIVIDER + "]+([" + DIVIDER + "]+|\\z)";
    private ArrayList<TextPart> sentence;

    /**
     * constructor
     * @param text text sentence value
     */
    public Sentence(String text) {
        super(text, TextPartType.SENTENCE);
        sentence = new ArrayList<>();
    }

    /**
     * add word to sentence
     * @param word word
     */
    public void addWord(Word word) {
        sentence.add(word);
    }

    public ArrayList<Word> getWords() {
        ArrayList<Word> words = new ArrayList<>();
        for (TextPart textPart : sentence) {
            if (textPart.getClass() == Word.class)
                words.add((Word)textPart);
        }
        return words;
    }

    /**
     * add  punctuation mark
     * @param punctuationMark punctuation mark
     */
    public void addPunctuationMark(PunctuationMark punctuationMark) {
        sentence.add(punctuationMark);
    }

    @Override
    public String toString() {
        StringBuilder textToString = new StringBuilder();
        for (TextPart textPart : sentence) {
            if (textPart.getClass() != PunctuationMark.class && sentence.indexOf(textPart) != 0)
                textToString.append(" ");
            textToString.append(textPart);

        }
        return textToString.toString();
    }

}
