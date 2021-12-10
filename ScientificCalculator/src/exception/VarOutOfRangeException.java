package exception;

/**
 * This is the RunTimeException called when the variable is not contained in [a, b, ..., z]
 * 
 * @author Group 10
 * 
 */
public class VarOutOfRangeException extends RuntimeException {

    /**
     * Creates a new instance of <code>VarOutOfRange</code> without detail
     * message.
     */
    public VarOutOfRangeException() {
    }

    /**
     * Constructs an instance of <code>VarOutOfRange</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public VarOutOfRangeException(String msg) {
        super(msg);
    }
}
