package scientificcalculator;

/**
 *
 * @author Group 10
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
