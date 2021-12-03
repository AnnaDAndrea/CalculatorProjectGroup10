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
