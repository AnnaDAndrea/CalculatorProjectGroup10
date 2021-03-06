package exception;

/**
 * This is the RuntimeException called when there is a syntax error into the
 * string read by Interpreter
 *
 * @author Group 10
 *
 */
public class InterpreterException extends RuntimeException {

    /**
     * Creates a new instance of <code>InterpreterException</code> without
     * detail message.
     */
    public InterpreterException() {
    }

    /**
     * Constructs an instance of <code>InterpreterException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InterpreterException(String msg) {
        super(msg);
    }
}
