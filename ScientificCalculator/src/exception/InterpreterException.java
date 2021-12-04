package exception;

/**
 * @author Group 10
 * @brief This is the Exception called when there is a syntax error into the string read by Interpreter
 */
public class InterpreterException extends Exception {

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
