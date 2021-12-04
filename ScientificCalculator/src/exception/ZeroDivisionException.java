package exception;

/**
 *
 * @author Group 10
 * @brief This is the Exception called when an operand is divided by 0
 */
public class ZeroDivisionException extends Exception {

    /**
     * Creates a new instance of <code>ZeroDivisionException</code> without
     * detail message.
     */
    public ZeroDivisionException() {
    }

    /**
     * Constructs an instance of <code>ZeroDivisionException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ZeroDivisionException(String msg) {
        super(msg);
    }
}
