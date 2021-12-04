package scientificcalculator;

import exception.ZeroDivisionException;
import java.util.Deque;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author Group 10
 * @brief This is the Class used to apply the basic operations on the complex numbers exploiting the stack
 */
public class ComplexNumOperation {

    private Deque<Complex> stack;

    /**
     * @brief Constructor
     * @param stack stack containing the complex numbers
     */
    public ComplexNumOperation(Deque<Complex> stack) {
        this.stack = stack;
    }

    /**
     * @brief method to insert a complex number onto the stack
     * @param z complex number to add onto the stack
     */
    public void insertion(Complex z) {
        stack.addFirst(z);
    }
    
    /**
     * @brief method to sum 2 complex numbers pulled from the top of the stack
     */
    public void sum() {

        Complex op1 = stack.removeFirst();
        Complex op2 = stack.removeFirst();

        stack.addFirst(op1.add(op2));

    }
    
    /**
     * @brief method to subtract 2 complex numbers pulled from the top of the stack
     */
    public void subtraction() {
        
        Complex op1 = stack.removeFirst();
        Complex op2 = stack.removeFirst();

        stack.addFirst(op2.subtract(op1));
        
    }
    
    /**
     * @brief method to multiply 2 complex numbers pulled from the top of the stack
     */
    public void product(){
        
        Complex op1 = stack.removeFirst();
        Complex op2 = stack.removeFirst();

        stack.addFirst(op1.multiply(op2));
    }

    /**
     * @brief method to divide 2 complex numbers pulled from the top of the stack. 
     * It calls the ZeroDivisionException when a complex number is divided by 0 
     */
    public void division() throws ZeroDivisionException {

        Complex op1 = stack.removeFirst();
        Complex op2 = stack.removeFirst();
        Complex res = op2.divide(op1);
        if(!res.isNaN() && !res.isInfinite())
            stack.addFirst(res);
        else
            throw new ZeroDivisionException();

    }
    
    /**
     * @brief method to apply the square root of a complex number pulled from the top of the stack
     */
    public void squareRoot() {

        Complex op1 = stack.removeFirst();

        stack.addFirst(op1.sqrt());

    }

    /**
     * @brief method to invert the sign of a complex number pulled from the top of the stack
     */
    public void invertedSign() {

        Complex op1 = stack.removeFirst();

        stack.addFirst(op1.negate());

    }

}
