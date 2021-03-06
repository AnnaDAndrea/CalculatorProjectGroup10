package exception;

/**
 * This is the RuntimeException called when there is a restoring attempt and the
 * stack pointer is equal to 0 (so there aren't previous set of variables values
 * to restore)
 *
 * @author Group 10
 *
 */
public class IllegalStackPointerException extends RuntimeException {

    /**
     * Creates a new instance of <code>IllegalStackPointerException</code>
     * without detail message.
     */
    public IllegalStackPointerException() {
    }

    /**
     * Constructs an instance of <code>IllegalStackPointerException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public IllegalStackPointerException(String msg) {
        super(msg);
    }
}
