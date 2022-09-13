package Controller;

public class SortingWordsException extends Exception {

    /**
     * Constructor with specified string
     * @param message string
     */
    public SortingWordsException(String message) {
        super( "Cannot sort the words, " + message);
    }

    /**
     * Constructor with specified string and exception
     * @param message string
     * @param e error covered
     */
    public SortingWordsException(String message, Throwable e) {
        super("Cannot sort the words, " + message, e);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }

}
