package test.scientificcalculator;

import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import org.apache.commons.math3.complex.Complex;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import scientificcalculator.ComplexNumOperation;
import scientificcalculator.ZeroDivisionException;

/**
 *
 * @author Group 10
 * @brief This is the Class used to test ComplexNumOperations methods
 */
public class ComplexNumOperationTest {
    
    /**
     * @brief Definition of the stack and of an instance of complexNumOperation
     */
    private ComplexNumOperation complexNumOperation;
    private Deque<Complex> stack;
    
    /**
     * @brief Initialization of the stack and of the instance of complexNumOperation
     */
    @Before
    public void setUp() {
        stack = new LinkedList<>();
        complexNumOperation = new ComplexNumOperation(stack);
    }
    
    /**
     * @brief Method to test the insertion method
     */
    @Test
    public void testInsertion(){
        complexNumOperation.insertion(new Complex(2, 5));
        assertEquals(stack.getFirst(), new Complex(2,5));
        
        complexNumOperation.insertion(new Complex(2,8));
        assertEquals(stack.removeFirst(), new Complex(2,8));
        assertEquals(stack.removeFirst(), new Complex(2,5));
    }
    
    /**
     * @brief Method to test the sum method
     */
    @Test
    public void testSum(){
        Complex op1 = new Complex(6, 7);
        complexNumOperation.insertion(op1);
        Complex op2 = new Complex(5, 3);
        complexNumOperation.insertion(op2);
        complexNumOperation.sum();
        assertEquals(stack.getFirst(), new Complex(11, 10));
        assertEquals(stack.size(),1);
        
        Complex op3 = new Complex(2, 4);
        complexNumOperation.insertion(op3);
        Complex op4 = new Complex(3, 5);
        complexNumOperation.insertion(op4);
        Complex op5 = new Complex(7, 6);
        complexNumOperation.insertion(op5);
        complexNumOperation.sum();
        assertEquals(stack.size(),3);
        assertEquals(stack.removeFirst(), new Complex(10, 11));
        assertEquals(stack.removeFirst(), new Complex(2, 4));
        assertEquals(stack.removeFirst(), new Complex(11, 10));
        
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testSumExc(){
        Complex op1 = new Complex(2, 4);
        complexNumOperation.insertion(op1);
        complexNumOperation.sum();
    
    }
    
    @Test
    public void testSubtraction(){
        Complex op1 = new Complex(7, 8);
        complexNumOperation.insertion(op1);
        Complex op2 = new Complex(6, 9);
        complexNumOperation.insertion(op2);
        complexNumOperation.subtraction();
        assertEquals(stack.getFirst(), new Complex(1,-1));
        assertEquals(stack.size(),1);
        
        Complex op3 = new Complex(5, 6);
        complexNumOperation.insertion(op3);
        Complex op4 = new Complex(4, 3);
        complexNumOperation.insertion(op4);
        Complex op5 = new Complex(2, 8);
        complexNumOperation.insertion(op5);
        complexNumOperation.subtraction();
        assertEquals(stack.size(),3);
        assertEquals(stack.removeFirst(), new Complex(2, -5));
        assertEquals(stack.removeFirst(), new Complex(5, 6));
        assertEquals(stack.removeFirst(), new Complex(1, -1));
        
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testSubtractionExc(){
        Complex op1 = new Complex(2, 3);
        complexNumOperation.insertion(op1);
        complexNumOperation.sum();
    
    }
    
    @Test
    public void testProduct(){
        Complex op1 = new Complex(2, 5);
        complexNumOperation.insertion(op1);
        Complex op2 = new Complex(3, 4);
        complexNumOperation.insertion(op2);
        complexNumOperation.product();
        assertEquals(stack.getFirst(), new Complex(-14, 23));
        assertEquals(stack.size(),1);
        
        Complex op3 = new Complex(4, 2);
        complexNumOperation.insertion(op3);
        Complex op4 = new Complex(5, 8);
        complexNumOperation.insertion(op4);
        Complex op5 = new Complex(3, 9);
        complexNumOperation.insertion(op5);
        complexNumOperation.product();
        assertEquals(stack.size(), 3);
        assertEquals(stack.removeFirst(), new Complex(-57, 69));
        assertEquals(stack.removeFirst(), new Complex(4, 2));
        assertEquals(stack.removeFirst(), new Complex(-14, 23));
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testProductExc(){
        Complex op1 = new Complex(2, 3);
        complexNumOperation.insertion(op1);
        complexNumOperation.product();
    
    }
    
    @Test
    public void testDivision() throws ZeroDivisionException{
        Complex op1 = new Complex(6, 9);
        complexNumOperation.insertion(op1);
        Complex op2 = new Complex(2, 2);
        complexNumOperation.insertion(op2);
        complexNumOperation.division();
        assertEquals(stack.getFirst(), new Complex(3.75, 0.75));
        assertEquals(stack.size(),1);
        
        Complex op3 = new Complex(24, 9);
        complexNumOperation.insertion(op3);
        Complex op4 = new Complex(31, 21);
        complexNumOperation.insertion(op4);
        Complex op5 = new Complex(15, 5);
        complexNumOperation.insertion(op5);
        complexNumOperation.division();
        assertEquals(stack.size(), 3);
        assertEquals(stack.removeFirst(), new Complex(2.28, 0.64));
        assertEquals(stack.removeFirst(), new Complex(24, 9));
        assertEquals(stack.removeFirst(), new Complex(3.75, 0.75));
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testDivisionExc() throws ZeroDivisionException{
        Complex op1 = new Complex(2, 3);
        complexNumOperation.insertion(op1);
        complexNumOperation.division();
    
    }
    
    @Test(expected = ZeroDivisionException.class)
    public void testDivisionZeroExc() throws ZeroDivisionException{
        Complex op1 = new Complex(2, 3);
        complexNumOperation.insertion(op1);
        Complex op2 = new Complex(0);
        complexNumOperation.insertion(op2);
        complexNumOperation.division();
    
    }
    
    @Test
    public void testSquareRoot(){
        Complex op1 = new Complex(3, 4);
        complexNumOperation.insertion(op1);
        complexNumOperation.squareRoot();
        assertEquals(stack.getFirst(), new Complex(2, 1));
        assertEquals(stack.size(),1);

    }
    
    @Test(expected = NoSuchElementException.class)
    public void testSquareRootExc(){
        complexNumOperation.squareRoot();
    
    }
    
    @Test
    public void testInvertedSign(){
        Complex op1 = new Complex(3,2);
        complexNumOperation.insertion(op1);
        complexNumOperation.invertedSign();
        assertEquals(stack.getFirst(), new Complex(-3,-2));
        assertEquals(stack.size(),1);

    }
    
    @Test(expected = NoSuchElementException.class)
    public void testInvertedSignExc(){
        complexNumOperation.invertedSign();
    
    }
    
}
