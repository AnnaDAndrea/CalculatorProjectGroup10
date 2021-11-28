package test.scentificcalculator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Deque;
import java.util.LinkedList;
import org.apache.commons.math3.complex.Complex;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import scientificcalculator.Interpreter;
import scientificcalculator.InterpreterException;
import scientificcalculator.ZeroDivisionException;

/**
 *
 * @author Group 10
 */
/**
 * @brief This is a tester class to test the behavior of the class Interpreter. It tests if the interpreter works correctly
 * @author pasqu
 */
public class InterpreterTest {
    
    private Interpreter interpreter;
    private Deque<Complex> stack;
    
    public InterpreterTest() {
    }
    
/**
 * @biref setUp method is used to create a fixture
 */
    @Before
    public void setUp() {
        stack=new LinkedList<>();
        interpreter=new Interpreter(stack);
     
       
    }
   /**
    * @brief testParseInsertion method verifies that a number inserted in the text field this must be inserted onto stack
    * @throws InterpreterException
    * @throws ZeroDivisionException 
    */
    
    @Test
    public void testParseInsertion() throws InterpreterException, ZeroDivisionException
    {
        
        interpreter.parse("1+5j");
        assertEquals(stack.getFirst(), new Complex(1, 5));
        
        interpreter.parse("42");
        assertEquals(stack.getFirst(), new Complex(42,0));
         
    }
    /**
     * @brief testParseInsertion1 method verifies that a number without real part can't be inserted onto stack
     * @throws InterpreterException
     * @throws ZeroDivisionException 
     */
    @Test(expected = InterpreterException.class)
    public void testParseInsertion1() throws InterpreterException, ZeroDivisionException
    {
        interpreter.parse("42j");
    }
    /**
     * brief the following methods verify that the interpreter recognizes and separates the input to calculate the specified operation 
     * @throws InterpreterException
     * @throws ZeroDivisionException 
     */
    @Test
    public void testParseSum() throws InterpreterException, ZeroDivisionException
    {
        interpreter.parse("1+1j 2+2j +");
        assertEquals(stack.getFirst(),new Complex(3,3));
        
    }

    @Test
    public void testParseProduct() throws InterpreterException, ZeroDivisionException
    {
        interpreter.parse("1+1j 2+2j *");
        assertEquals(stack.getFirst(),new Complex(0,4));
        
    }
 
    @Test
    public void testParseDivision() throws InterpreterException, ZeroDivisionException{
        interpreter.parse("1+1j 2+2j /");
        assertEquals(stack.getFirst(),new Complex(0.5,0));
    }
 
    @Test
    public void testParseSubctration() throws InterpreterException, ZeroDivisionException{
        interpreter.parse("1+1j 2+2j -");
        assertEquals(stack.getFirst(),new Complex(-1,-1));
    }
    
    @Test
    public void testParseSquareRoot() throws InterpreterException, ZeroDivisionException{
        interpreter.parse("-4 sqrt");
        assertEquals(stack.getFirst(),new Complex(0,2));
    }
    
    @Test
    public void testParseInvertSign() throws InterpreterException, ZeroDivisionException{
        interpreter.parse("1+1j +-");
        assertEquals(stack.getFirst(),new Complex(-1,-1));
    }
    
    @Test
    public void testParseClear() throws InterpreterException, ZeroDivisionException{
        interpreter.parse("1+1j clear");
        assertEquals(stack.size(),0);
    }
    
    @Test
    public void testParseDup() throws InterpreterException, ZeroDivisionException{
        interpreter.parse("1+1j 2+2j dup");
        assertEquals(stack.getFirst(),new Complex(2,2));
    }
    
    @Test
    public void testParseDrop() throws InterpreterException, ZeroDivisionException{
        interpreter.parse("1+1j 2+2j drop");
        assertEquals(stack.getFirst(),new Complex(1,1));
    }
    
    @Test
    public void testParseOver() throws InterpreterException, ZeroDivisionException{
        interpreter.parse("1+1j 2+2j over");
        assertEquals(stack.getFirst(),new Complex(1,1));
    }
       
    @Test
    public void testParseSwap() throws InterpreterException, ZeroDivisionException{
        interpreter.parse("1+1j 2+2j swap");
        assertEquals(stack.getFirst(),new Complex(1,1));
    }
    /**
     * @brief testParseSequentiallyOperations method verifies that the interpreter recognizes and separates the input to calculate the operations sequentially 
     * @throws InterpreterException
     * @throws ZeroDivisionException 
     */
    @Test
    public void testParseSequentiallyOperations() throws InterpreterException, ZeroDivisionException{
        interpreter.parse("5 9 - sqrt");
        assertEquals(stack.getFirst(),new Complex(0,2));
    }
    
}
