package exception;

/**
 * This is the RunTimeException called when a UserDefinedOperation generates a
 * loop
 *
 * @author Group 10
 */
public class LoopException extends RuntimeException {

    /**
     * Creates a new instance of <code>LoopException</code> without detail
     * message.
     */
    public LoopException() {
    }

    /**
     * Constructs an instance of <code>LoopException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public LoopException(String msg) {
        super(msg);
    }
}
