/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author Group 10
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
