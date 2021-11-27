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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import scientificcalculator.ComplexNumOperation;

/**
 *
 * @author claud
 */
public class ComplexNumOperationTest {
    
     private ComplexNumOperation complexNumOperation;
     private Deque<Complex> stack;
    
    public ComplexNumOperationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        stack = new LinkedList<>();
        complexNumOperation = new ComplexNumOperation(stack);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testInsertion(){
        complexNumOperation.insertion(new Complex(2));
        assertEquals(stack.getFirst(), new Complex(2));
    }
    
    @Test
    public void testAdd(){
        Complex op1 = new Complex(6);
        complexNumOperation.insertion(op1);
        Complex op2 = new Complex(7);
        complexNumOperation.insertion(op2);
        complexNumOperation.sum();
        assertEquals(stack.getFirst(), new Complex(13));
        
        Complex op3 = new Complex(2);
        complexNumOperation.insertion(op3);
        Complex op4 = new Complex(3);
        complexNumOperation.insertion(op4);
        Complex op5 = new Complex(4);
        complexNumOperation.insertion(op5);
        complexNumOperation.sum();
        assertEquals(stack.getFirst(),new Complex(7));
        assertEquals(stack.size(),3);
        
    }
    @Test(expected = NoSuchElementException.class)
    public void testAddExc(){
        Complex op1 = new Complex(2);
        complexNumOperation.insertion(op1);
        complexNumOperation.sum();
    
    }
    
    @Test
    public void testSubtraction(){
        Complex op1 = new Complex(7);
        complexNumOperation.insertion(op1);
        Complex op2 = new Complex(6);
        complexNumOperation.insertion(op2);
        complexNumOperation.subtraction();
        assertEquals(stack.getFirst(), new Complex(1));
        
        Complex op3 = new Complex(5);
        complexNumOperation.insertion(op3);
        Complex op4 = new Complex(4);
        complexNumOperation.insertion(op4);
        Complex op5 = new Complex(2);
        complexNumOperation.insertion(op5);
        complexNumOperation.subtraction();
        assertEquals(stack.getFirst(), new Complex(2));
        assertEquals(stack.size(),3);
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testSubtractionExc(){
        Complex op1 = new Complex(2);
        complexNumOperation.insertion(op1);
        complexNumOperation.sum();
    
    }
    
    @Test
    public void testProduct(){
        Complex op1 = new Complex(2);
        complexNumOperation.insertion(op1);
        Complex op2 = new Complex(3);
        complexNumOperation.insertion(op2);
        complexNumOperation.product();
        assertEquals(stack.getFirst(), new Complex(6));
        
        Complex op3 = new Complex(4);
        complexNumOperation.insertion(op3);
        Complex op4 = new Complex(5);
        complexNumOperation.insertion(op4);
        Complex op5 = new Complex(3);
        complexNumOperation.insertion(op5);
        complexNumOperation.product();
        assertEquals(stack.getFirst(), new Complex(15));
        assertEquals(stack.size(), 3);
    }
    
     @Test(expected = NoSuchElementException.class)
    public void testProductExc(){
        Complex op1 = new Complex(2);
        complexNumOperation.insertion(op1);
        complexNumOperation.product();
    
    }
    
    @Test
    public void testDivision(){
        Complex op1 = new Complex(6);
        complexNumOperation.insertion(op1);
        Complex op2 = new Complex(2);
        complexNumOperation.insertion(op2);
        complexNumOperation.division();
        assertEquals(stack.getFirst(), new Complex(3));
        
        Complex op3 = new Complex(24);
        complexNumOperation.insertion(op3);
        Complex op4 = new Complex(20);
        complexNumOperation.insertion(op4);
        Complex op5 = new Complex(4);
        complexNumOperation.insertion(op5);
        complexNumOperation.division();
        assertEquals(stack.getFirst(), new Complex(5));
        assertEquals(stack.size(), 3);
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testDivisionExc(){
        Complex op1 = new Complex(2);
        complexNumOperation.insertion(op1);
        complexNumOperation.division();
    
    }
    
    @Test
    public void testSquareRoot(){
        Complex op1 = new Complex(25);
        complexNumOperation.insertion(op1);
        complexNumOperation.squareRoot();
        assertEquals(stack.getFirst(), new Complex(5));
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
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testInvertedSignExc(){
        complexNumOperation.invertedSign();
    
    }
    
}
