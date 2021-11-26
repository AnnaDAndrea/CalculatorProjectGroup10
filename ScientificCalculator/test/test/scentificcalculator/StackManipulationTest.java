/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.scentificcalculator;

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
 * @author Carmine
 */
public class StackManipulationTest {
    
    private StackManipulation s;
    private Deque<Complex> stack;
    
    public StackManipulationTest() {
    }
    
    @Before
    public void setUp() {
        stack = new LinkedList<>();
        s = new StackManipulation(stack);
    }
    
    @Test
    public void testClear() {
        s.clear();
        assertEquals(true, stack.isEmpty());
    }
    
    @Test
    public void testDrop() {
        stack.addFirst(new Complex(3,3));
        int s1 = stack.size();
        s.drop();
        assertEquals(s1 - 1, stack.size());
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testDropFail() {
        int s1 = stack.size();
        s.drop();
        assertEquals(s1, stack.size());      
    }
    
    @Test
    public void testDup() {
        Complex c = new Complex(3,3);
        stack.addFirst(c);
        int s1 = stack.size();
        s.dup();
        assertEquals(s1 + 1, stack.size());
        assertEquals(stack.peekFirst(), c);
    }
    
    @Test
    public void testDup2() {
        Complex c = new Complex(3,3);
        Complex c1 = new Complex(5,3);
        stack.addFirst(c);
        stack.addFirst(c1);
        int s1 = stack.size();
        s.dup();
        assertEquals(s1 + 1, stack.size());
        assertEquals(stack.peekFirst(), c1);
    }
        
    @Test(expected = NoSuchElementException.class)
    public void testDupFail() {
        int s1 = stack.size();
        s.dup();
        assertEquals(s1, stack.size());     
    }

    @Test
    public void testSwap() {
        Complex c1 = new Complex(3,3);
        Complex c2 = new Complex(5,5);
        stack.addFirst(c1);
        stack.addFirst(c2);
        int s1 = stack.size();
        s.swap();
        assertEquals(s1, stack.size());
        assertEquals(stack.peekFirst(), c1);
    } 
        
    @Test(expected = NoSuchElementException.class)
    public void testSwapFail() {
        s.swap();
        assertEquals(0, stack.size());     
    }
    
    @Test
    public void testOver() {
        Complex c1 = new Complex(3,3);
        Complex c2 = new Complex(5,5);
        stack.addFirst(c1);
        stack.addFirst(c2);
        int s1 = stack.size();
        s.over();
        assertEquals(s1 + 1, stack.size());
        assertEquals(stack.peekFirst(), c1);
    } 
    
    @Test(expected = NoSuchElementException.class)
    public void testOverFail() {
        int s1 = stack.size();
        s.over();
        assertEquals(s1, stack.size());  
    } 
}
    
