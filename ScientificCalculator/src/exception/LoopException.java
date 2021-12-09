/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author duino
 */
public class LoopException extends Exception {

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
