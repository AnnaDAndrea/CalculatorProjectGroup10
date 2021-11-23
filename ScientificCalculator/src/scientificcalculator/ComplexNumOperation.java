/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import java.util.Deque;
import java.util.NoSuchElementException;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author duino
 */
public class ComplexNumOperation {

    private Deque<Complex> stack;

    public ComplexNumOperation(Deque<Complex> stack) {
        this.stack = stack;
    }

    public void insertion(Complex z) {
        stack.addFirst(z);
    }
    
    public void sum() throws NoSuchElementException{

        Complex op1 = stack.removeFirst();
        Complex op2 = stack.removeFirst();

        stack.addFirst(op1.add(op2));

    }
    
    public void subtraction() throws NoSuchElementException {
        
        Complex op1 = stack.removeFirst();
        Complex op2 = stack.removeFirst();

        stack.addFirst(op1.subtract(op2));
    }
    
    public void product() throws NoSuchElementException {
        
        Complex op1 = stack.removeFirst();
        Complex op2 = stack.removeFirst();

        stack.addFirst(op1.multiply(op2));
    }

    public void division() throws NoSuchElementException {

        Complex op1 = stack.removeFirst();
        Complex op2 = stack.removeFirst();

        stack.addFirst(op1.divide(op2));

    }
    
    public void squareRoot() throws NoSuchElementException {

        Complex op1 = stack.removeFirst();

        stack.addFirst(op1.sqrt());

    }

    public void invertedSign() throws NoSuchElementException {

        Complex op1 = stack.removeFirst();

        stack.addFirst(op1.negate());

    }

    
    
    
}
