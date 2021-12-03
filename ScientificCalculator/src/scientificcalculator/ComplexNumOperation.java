package scientificcalculator;

import exception.ZeroDivisionException;
import java.util.Deque;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author Group 10
 */
public class ComplexNumOperation {

    private Deque<Complex> stack;

    public ComplexNumOperation(Deque<Complex> stack) {
        this.stack = stack;
    }

    public void insertion(Complex z) {
        stack.addFirst(z);
    }
    
    public void sum() {

        Complex op1 = stack.removeFirst();
        Complex op2 = stack.removeFirst();

        stack.addFirst(op1.add(op2));

    }
    
    public void subtraction() {
        
        Complex op1 = stack.removeFirst();
        Complex op2 = stack.removeFirst();

        stack.addFirst(op2.subtract(op1));
        
    }
    
    public void product(){
        
        Complex op1 = stack.removeFirst();
        Complex op2 = stack.removeFirst();

        stack.addFirst(op1.multiply(op2));
    }

    public void division() throws ZeroDivisionException {

        Complex op1 = stack.removeFirst();
        Complex op2 = stack.removeFirst();
        Complex res = op2.divide(op1);
        if(!res.isNaN() && !res.isInfinite())
            stack.addFirst(res);
        else
            throw new ZeroDivisionException();

    }
    
    public void squareRoot() {

        Complex op1 = stack.removeFirst();

        stack.addFirst(op1.sqrt());

    }

    public void invertedSign() {

        Complex op1 = stack.removeFirst();

        stack.addFirst(op1.negate());

    }
    
    
    
    
}
