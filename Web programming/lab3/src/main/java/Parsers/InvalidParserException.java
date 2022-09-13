package Parsers;

public class InvalidParserException extends Exception {

    /**
     * Constructor with specified string
     * @param message string
     */
    public InvalidParserException(String message) {
        super(message);
    }

    /**
     * Constructor with specified string and exception
     * @param message string
     * @param e error covered
     */
    public InvalidParserException(String message, Throwable e) {
        super(message, e);
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
