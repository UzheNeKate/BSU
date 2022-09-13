package Model;

public class IllegalTimeListException extends Exception {
    /**
     * constructor
     */
    public IllegalTimeListException() {
        super("Times length must equal to stops size - 1");
    }
}
