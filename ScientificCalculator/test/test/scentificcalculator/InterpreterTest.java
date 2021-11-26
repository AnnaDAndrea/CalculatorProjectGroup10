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

/**
 *
 * @author Anna
 */
public class InterpreterTest {
    
    private Interpreter interpreter;
    Deque<Complex> stack;
    
    public InterpreterTest() {
    }
    

    @Before
    public void setUp() {
        stack=new LinkedList<>();
        interpreter=new Interpreter(stack);
     
       
    }
   
    
    @Test
    public void testParseInsertion() throws InterpreterException
    {
        
        interpreter.parse("1+5j");
        assertEquals(stack.getFirst(), new Complex(1, 5));
        
        interpreter.parse("42");
        assertEquals(stack.getFirst(), new Complex(42,0));
         
    }
    
    @Test(expected = InterpreterException.class)
    public void testParseInsertion1() throws InterpreterException
    {
        interpreter.parse("42j");
    }
    
    @Test
    public void testParseSum() throws InterpreterException
    {
        interpreter.parse("1+1j 2+2j +");
        assertEquals(stack.getFirst(),new Complex(3,3));
        
    }
    
    @Test
    public void testParseProduct() throws InterpreterException
    {
        interpreter.parse("1+1j 2+2j *");
        assertEquals(stack.getFirst(),new Complex(0,4));
        
    }
    
    @Test
    public void testParseDivision() throws InterpreterException{
        interpreter.parse("1+1j 2+2j /");
        assertEquals(stack.getFirst(),new Complex(0.5,0));
    }
    
    @Test
    public void testParseSubctration() throws InterpreterException{
        interpreter.parse("1+1j 2+2j -");
        assertEquals(stack.getFirst(),new Complex(-1,-1));
    }
    
    @Test
    public void testParseSquareRoot() throws InterpreterException{
        interpreter.parse("-4 sqrt");
        assertEquals(stack.getFirst(),new Complex(0,2));
    }
    
    @Test
    public void testParseInvertSign() throws InterpreterException{
        interpreter.parse("1+1j +-");
        assertEquals(stack.getFirst(),new Complex(-1,-1));
    }
    
    @Test
    public void testParseClear() throws InterpreterException{
        interpreter.parse("1+1j clear");
        assertEquals(stack.size(),0);
    }
    
    @Test
    public void testParseDup() throws InterpreterException{
        interpreter.parse("1+1j 2+2j dup");
        assertEquals(stack.getFirst(),new Complex(2,2));
    }
    
    @Test
    public void testParseDrop() throws InterpreterException{
        interpreter.parse("1+1j 2+2j drop");
        assertEquals(stack.getFirst(),new Complex(1,1));
    }
    
    @Test
    public void testParseOver() throws InterpreterException{
        interpreter.parse("1+1j 2+2j over");
        assertEquals(stack.getFirst(),new Complex(1,1));
    }
       
    @Test
    public void testParseSwap() throws InterpreterException{
        interpreter.parse("1+1j 2+2j swap");
        assertEquals(stack.getFirst(),new Complex(1,1));
    }
    
    
    
}
