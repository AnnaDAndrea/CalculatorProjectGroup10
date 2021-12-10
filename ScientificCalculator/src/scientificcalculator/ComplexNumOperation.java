package scientificcalculator;

import exception.ZeroDivisionException;
import java.util.Deque;
import java.util.NoSuchElementException;
import org.apache.commons.math3.complex.Complex;

/**
 * This is the Class used to apply the basic operations on the complex numbers
 * exploiting the stack
 *
 * @author Group 10
 *
 */
public class ComplexNumOperation {

    private Deque<Complex> stack;

    /**
     * Constructor
     *
     * @param stack stack containing the complex numbers
     */
    public ComplexNumOperation(Deque<Complex> stack) {
        this.stack = stack;
    }

    /**
     * method to insert a complex number onto the stack.
     *
     * @param z complex number to add onto the stack
     */
    public void insertion(Complex z) {
        stack.addFirst(z);
    }

    /**
     * method to sum 2 complex numbers pulled from the top of the stack. It
     * calls the NoSuchElementException if the stack is empty or if there aren't
     * enough operands
     */
    public void sum() {

        if (stack.size() >= 2) {
            Complex op1 = stack.removeFirst();
            Complex op2 = stack.removeFirst();
            stack.addFirst(op1.add(op2));
        } else {
            throw new NoSuchElementException();
        }

    }

    /**
     * method to subtract 2 complex numbers pulled from the top of the stack It
     * calls the NoSuchElementException if the stack is empty or if there aren't
     * enough operands
     */
    public void subtraction() {

        if (stack.size() >= 2) {
            Complex op1 = stack.removeFirst();
            Complex op2 = stack.removeFirst();

            stack.addFirst(op2.subtract(op1));
        } else {
            throw new NoSuchElementException();
        }

    }

    /**
     * method to multiply 2 complex numbers pulled from the top of the stack It
     * calls the NoSuchElementException if the stack is empty or if there aren't
     * enough operands
     */
    public void product() {

        if (stack.size() >= 2) {
            Complex op1 = stack.removeFirst();
            Complex op2 = stack.removeFirst();

            stack.addFirst(op1.multiply(op2));
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * method to divide 2 complex numbers pulled from the top of the stack. It
     * calls the ZeroDivisionException when a complex number is divided by 0 It
     * calls the NoSuchElementException if the stack is empty or if there aren't
     * enough operands
     */
    public void division() {

        if (stack.size() >= 2) {
            Complex op1 = stack.removeFirst();
            Complex op2 = stack.removeFirst();
            Complex res = op2.divide(op1);
            if (!res.isNaN() && !res.isInfinite()) {
                stack.addFirst(res);
            } else {
                throw new ZeroDivisionException();
            }
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * method to apply the square root of a complex number pulled from the top
     * of the stack It calls the NoSuchElementException if the stack is empty
     */
    public void squareRoot() {

        Complex op1 = stack.removeFirst();

        stack.addFirst(op1.sqrt());

    }

    /**
     * method to invert the sign of a complex number pulled from the top of the
     * stack It calls the NoSuchElementException if the stack is empty
     */
    public void invertedSign() {

        Complex op1 = stack.removeFirst();

        stack.addFirst(op1.negate());

    }

}
