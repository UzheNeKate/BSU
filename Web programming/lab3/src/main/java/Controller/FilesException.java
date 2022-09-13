package Controller;

public class FilesException extends Exception {

    /**
     * Constructor with specified string
     * @param message string
     */
    public FilesException(String message) {
        super("Cannot read the file, " + message);
    }

    /**
     * Constructor with specified string and exception
     * @param message string
     * @param e error covered
     */
    public FilesException(String message, Throwable e){
        super("Cannot read the file, " + message, e);
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
