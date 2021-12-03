package test.scientificcalculator;

import com.sun.org.apache.bcel.internal.generic.StackConsumer;
import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import org.apache.commons.math3.complex.Complex;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import scientificcalculator.StackManipulation;

/**
 *
 * @author Group 10
 * @brief This is the Class used to test StackManipulation methods
 */

public class StackManipulationTest {
    
    /**
     * @brief Definition of a fixture composed of the stack and of an instance of StackManipulation
     */
    private Deque<Complex> stack;
    private StackManipulation s;
    
    public StackManipulationTest() {
    }
    
    /**
     * @brief Method to initialize the fixture 
     */
    @Before 
    public void setUp() {
        stack = new LinkedList<>();
        s = new StackManipulation(stack);
    }
    
    /**
     * @brief Method to test the clear method
     */
    @Test 
    public void testClear() {
        s.clear();
        assertEquals(true, stack.isEmpty());
    }
    
    /**
     * @brief Method to test the drop method
     */
    @Test 
    public void testDrop() {
        Complex c1 = new Complex(3,3);
        Complex c2 = new Complex(3,5);
        stack.addFirst(c1);
        stack.addFirst(c2);
        int s1 = stack.size();
        s.drop();
        assertEquals(s1 - 1, stack.size());
        assertEquals(c1, stack.peekFirst());
        
        // Checking the drop removing also the last element into the stack
        s.drop();
        assertEquals(0, stack.size());
        assertEquals(null, stack.peekFirst());
    }
    
    /**
     * @brief Method to test the drop method fail calling the NoSuchElementException
     */
    @Test(expected = NoSuchElementException.class) 
    public void testDropFail() {
        s.drop();   
    }
    
    /**
     * @brief Method to test the dup method
     */
    @Test
    public void testDup() {
        Complex c = new Complex(3,3);
        stack.addFirst(c);
        int s1 = stack.size();
        s.dup();
        assertEquals(s1 + 1, stack.size());
        assertEquals(c, stack.peekFirst());
        
        // Additional check: verify if the last element is actually the copy of the second-last one
        Complex out1 = stack.removeFirst();
        Complex out2 = stack.removeFirst();
        assertEquals(out1, out2);
    }
    
    /**
     * @brief Method to test the dup method with more Complex numbers
     */
    @Test
    public void testDup2() {
        Complex c = new Complex(3,3);
        Complex c1 = new Complex(5,3);
        stack.addFirst(c);
        stack.addFirst(c1);
        int s1 = stack.size();
        s.dup();
        assertEquals(s1 + 1, stack.size());
        assertEquals(c1, stack.peekFirst());
        
        // Additional check: verify if the last element is actually the copy of the second-last one
        Complex out1 = stack.removeFirst();
        Complex out2 = stack.removeFirst();
        assertEquals(out1, out2);
        
        // Additional check: verify that the last element into the stack corresponds to c
        assertEquals(c, stack.removeFirst());
    }
    
    /**
     * @brief Method to test the dup method fail calling the NoSuchElementException
     */
    @Test(expected = NoSuchElementException.class)
    public void testDupFail() {
        s.dup();  
    }
    
    /**
     * @brief Method to test the swap method
     */
    @Test
    public void testSwap() {
        Complex c1 = new Complex(3,3);
        Complex c2 = new Complex(5,5);
        stack.addFirst(c1);
        stack.addFirst(c2);
        int s1 = stack.size();
        s.swap();
        assertEquals(s1, stack.size());
        assertEquals(c1, stack.peekFirst());
        
        // Additional check: verify the order of the swapped elements
        assertEquals(c1, stack.removeFirst()); 
        assertEquals(c2, stack.removeFirst());
    } 
    
    /**
     * @brief Method to test the swap method with more Complex numbers
     */
    @Test
    public void testSwap2() {
        Complex c1 = new Complex(3,3);
        Complex c2 = new Complex(5,5);
        Complex c3 = new Complex(1,1);
        Complex c4 = new Complex(1,5);
        stack.addFirst(c1);
        stack.addFirst(c2);
        stack.addFirst(c3);
        stack.addFirst(c4);
        int s1 = stack.size();
        s.swap();
        assertEquals(s1, stack.size());
        assertEquals(c3, stack.peekFirst());
        
        // Additional check: verify the order of the swapped elements and of the remaining ones
        assertEquals(c3, stack.removeFirst()); 
        assertEquals(c4, stack.removeFirst());
        assertEquals(c2, stack.removeFirst());
        assertEquals(c1, stack.removeFirst());
    } 
        
    /**
     * @brief Method to test the swap method fail calling the NoSuchElementException
     */
    @Test(expected = NoSuchElementException.class)
    public void testSwapFail() {
        s.swap();    
    }
    
    /**
     * @brief Method to test the over method
     */
    @Test
    public void testOver() {
        Complex c1 = new Complex(3,3);
        Complex c2 = new Complex(5,5);
        stack.addFirst(c1);
        stack.addFirst(c2);
        int s1 = stack.size();
        s.over();
        assertEquals(s1 + 1, stack.size());
        assertEquals(c1, stack.peekFirst());
        
        // Additional check: verify if the last element is actually the copy of the third-last one
        Complex out1 = stack.removeFirst();
        Complex out2 = stack.removeFirst();
        Complex out3 = stack.removeFirst();
        assertEquals(out1, out3);
        
        // Additional check: verify the general order of the elements
        assertEquals(c1, out1);
        assertEquals(c2, out2);
        assertEquals(c1, out3);
    } 
    
    /**
     * @brief Method to test the over method with more Complex numbers
     */
    @Test
    public void testOver2() {
        Complex c1 = new Complex(3,3);
        Complex c2 = new Complex(5,5);
        Complex c3 = new Complex(1,1);
        Complex c4 = new Complex(1,5);
        stack.addFirst(c1);
        stack.addFirst(c2);
        stack.addFirst(c3);
        stack.addFirst(c4);
        int s1 = stack.size();
        s.over();
        assertEquals(s1 + 1, stack.size());
        assertEquals(stack.peekFirst(), c3);
        
        // Additional check: verify if the last element is actually the copy of the third-last one
        Complex out1 = stack.removeFirst();
        Complex out2 = stack.removeFirst();
        Complex out3 = stack.removeFirst();
        Complex out4 = stack.removeFirst();
        Complex out5 = stack.removeFirst();
        assertEquals(out1, out3);
        
        // Additional check: verify the general order of the elements
        assertEquals(c3, out1);
        assertEquals(c4, out2);
        assertEquals(c3, out3);
        assertEquals(c2, out4);
        assertEquals(c1, out5);
    }
    
    /**
     * @brief Method to test the over method fail calling the NoSuchElementException
     */
    @Test(expected = NoSuchElementException.class)
    public void testOverFail() {
        s.over();
    } 
}
    
