package test.scientificcalculator;

import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import org.apache.commons.math3.complex.Complex;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import scientificcalculator.ComplexNumOperation;
import exception.ZeroDivisionException;

/**
 * This is the Class used to test ComplexNumOperations methods
 *
 * @author Group 10
 *
 */
public class ComplexNumOperationTest {

    /**
     * Definition of a fixture composed of the stack and of an instance of
     * ComplexNumOperation
     */
    private ComplexNumOperation complexNumOperation;
    private Deque<Complex> stack;

    /**
     * Method to initialize the fixture
     */
    @Before
    public void setUp() {
        stack = new LinkedList<>();
        complexNumOperation = new ComplexNumOperation(stack);
    }

    /**
     * Method to test the insertion method
     */
    @Test
    public void testInsertion() {
        complexNumOperation.insertion(new Complex(2, 5));
        assertEquals(new Complex(2, 5), stack.getFirst());

        complexNumOperation.insertion(new Complex(2, 8));
        assertEquals(new Complex(2, 8), stack.removeFirst());
        assertEquals(new Complex(2, 5), stack.removeFirst());
    }

    /**
     * Method to test the sum method
     */
    @Test
    public void testSum() {
        Complex op1 = new Complex(6, 7);
        complexNumOperation.insertion(op1);
        Complex op2 = new Complex(5, 3);
        complexNumOperation.insertion(op2);
        complexNumOperation.sum();
        assertEquals(new Complex(11, 10), stack.getFirst());
        assertEquals(1, stack.size());

        Complex op3 = new Complex(2, 4);
        complexNumOperation.insertion(op3);
        Complex op4 = new Complex(3, 5);
        complexNumOperation.insertion(op4);
        Complex op5 = new Complex(7, 6);
        complexNumOperation.insertion(op5);
        complexNumOperation.sum();
        assertEquals(3, stack.size());
        assertEquals(new Complex(10, 11), stack.removeFirst());
        assertEquals(new Complex(2, 4), stack.removeFirst());
        assertEquals(new Complex(11, 10), stack.removeFirst());

    }

    /**
     * Method to test the sum method fail calling the NoSuchElementException
     */
    @Test(expected = NoSuchElementException.class)
    public void testSumExc() {
        Complex op1 = new Complex(2, 4);
        complexNumOperation.insertion(op1);
        complexNumOperation.sum();

    }

    /**
     * Method to test the subtraction method
     */
    @Test
    public void testSubtraction() {
        Complex op1 = new Complex(7, 8);
        complexNumOperation.insertion(op1);
        Complex op2 = new Complex(6, 9);
        complexNumOperation.insertion(op2);
        complexNumOperation.subtraction();
        assertEquals(new Complex(1, -1), stack.getFirst());
        assertEquals(1, stack.size());

        Complex op3 = new Complex(5, 6);
        complexNumOperation.insertion(op3);
        Complex op4 = new Complex(4, 3);
        complexNumOperation.insertion(op4);
        Complex op5 = new Complex(2, 8);
        complexNumOperation.insertion(op5);
        complexNumOperation.subtraction();
        assertEquals(3, stack.size());
        assertEquals(new Complex(2, -5), stack.removeFirst());
        assertEquals(new Complex(5, 6), stack.removeFirst());
        assertEquals(new Complex(1, -1), stack.removeFirst());

    }

    /**
     * Method to test the subtraction method fail calling the
     * NoSuchElementException
     */
    @Test(expected = NoSuchElementException.class)
    public void testSubtractionExc() {
        Complex op1 = new Complex(2, 3);
        complexNumOperation.insertion(op1);
        complexNumOperation.sum();

    }

    /**
     * Method to test the product method
     */
    @Test
    public void testProduct() {
        Complex op1 = new Complex(2, 5);
        complexNumOperation.insertion(op1);
        Complex op2 = new Complex(3, 4);
        complexNumOperation.insertion(op2);
        complexNumOperation.product();
        assertEquals(new Complex(-14, 23), stack.getFirst());
        assertEquals(1, stack.size());

        Complex op3 = new Complex(4, 2);
        complexNumOperation.insertion(op3);
        Complex op4 = new Complex(5, 8);
        complexNumOperation.insertion(op4);
        Complex op5 = new Complex(3, 9);
        complexNumOperation.insertion(op5);
        complexNumOperation.product();
        assertEquals(3, stack.size());
        assertEquals(new Complex(-57, 69), stack.removeFirst());
        assertEquals(new Complex(4, 2), stack.removeFirst());
        assertEquals(new Complex(-14, 23), stack.removeFirst());
    }

    /**
     * Method to test the product method fail calling the NoSuchElementException
     */
    @Test(expected = NoSuchElementException.class)
    public void testProductExc() {
        Complex op1 = new Complex(2, 3);
        complexNumOperation.insertion(op1);
        complexNumOperation.product();

    }

    /**
     * Method to test the division method
     */
    @Test
    public void testDivision() {
        Complex op1 = new Complex(6, 9);
        complexNumOperation.insertion(op1);
        Complex op2 = new Complex(2, 2);
        complexNumOperation.insertion(op2);
        complexNumOperation.division();
        assertEquals(new Complex(3.75, 0.75), stack.getFirst());
        assertEquals(1, stack.size());

        Complex op3 = new Complex(24, 9);
        complexNumOperation.insertion(op3);
        Complex op4 = new Complex(31, 21);
        complexNumOperation.insertion(op4);
        Complex op5 = new Complex(15, 5);
        complexNumOperation.insertion(op5);
        complexNumOperation.division();
        assertEquals(3, stack.size());
        assertEquals(new Complex(2.28, 0.64), stack.removeFirst());
        assertEquals(new Complex(24, 9), stack.removeFirst());
        assertEquals(new Complex(3.75, 0.75), stack.removeFirst());
    }

    /**
     * Method to test the division method fail calling the
     * NoSuchElementException
     */
    @Test(expected = NoSuchElementException.class)
    public void testDivisionExc() {
        Complex op1 = new Complex(2, 3);
        complexNumOperation.insertion(op1);
        complexNumOperation.division();

    }

    /**
     * Method to test the division method fail calling the ZeroDivisionException
     */
    @Test(expected = ZeroDivisionException.class)
    public void testDivisionZeroExc() {
        Complex op1 = new Complex(2, 3);
        complexNumOperation.insertion(op1);
        Complex op2 = new Complex(0);
        complexNumOperation.insertion(op2);
        complexNumOperation.division();

    }

    /**
     * Method to test the square root method
     */
    @Test
    public void testSquareRoot() {
        Complex op1 = new Complex(3, 4);
        complexNumOperation.insertion(op1);
        complexNumOperation.squareRoot();
        assertEquals(new Complex(2, 1), stack.getFirst());
        assertEquals(1, stack.size());

    }

    /**
     * Method to test the square root method fail calling the
     * NoSuchElementException
     */
    @Test(expected = NoSuchElementException.class)
    public void testSquareRootExc() {
        complexNumOperation.squareRoot();

    }

    /**
     * Method to test the inverted sign method
     */
    @Test
    public void testInvertedSign() {
        Complex op1 = new Complex(3, 2);
        complexNumOperation.insertion(op1);
        complexNumOperation.invertedSign();
        assertEquals(new Complex(-3, -2), stack.getFirst());
        assertEquals(1, stack.size());

    }

    /**
     * Method to test the inverted sign method fail calling the
     * NoSuchElementException
     */
    @Test(expected = NoSuchElementException.class)
    public void testInvertedSignExc() {
        complexNumOperation.invertedSign();

    }
    
    /**
     * Method to test the mod method
     */
    @Test
    public void testMod(){
        Complex op1 = new Complex(4,0);
        Complex op2 = new Complex(-4,0);
        Complex op3 = new Complex(3,4);
        Complex op4 = new Complex(-3,-4);
        
        complexNumOperation.insertion(op1);
        complexNumOperation.mod();
        assertEquals(new Complex(4,0), stack.removeFirst());
        complexNumOperation.insertion(op2);
        complexNumOperation.mod();
        assertEquals(new Complex(4,0), stack.removeFirst());
        
        complexNumOperation.insertion(op3);
        complexNumOperation.mod();
        assertEquals(new Complex(5,0), stack.removeFirst());
        
        complexNumOperation.insertion(op4);
        complexNumOperation.mod();
        assertEquals(new Complex(5,0), stack.removeFirst());
    }

    /**
     * Method to test the mod method fail calling the
     * NoSuchElementException
     */
    @Test(expected = NoSuchElementException.class)
    public void testModExc() {
        complexNumOperation.mod();

    }
    
}
