package exception;

/**
 * This is the RunTimeException called when a key already exists in a data
 * structure
 *
 * @author Group 10
 *
 */
public class KeyAlreadyExistException extends RuntimeException {

    /**
     * Creates a new instance of <code>KeyAlreadyExistException</code> without
     * detail message.
     */
    public KeyAlreadyExistException() {
    }

    /**
     * Constructs an instance of <code>KeyAlreadyExistException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public KeyAlreadyExistException(String msg) {
        super(msg);
    }
}
