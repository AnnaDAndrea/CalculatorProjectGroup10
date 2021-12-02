package test.scientificcalculator;

import java.util.Deque;
import java.util.LinkedList;
import org.apache.commons.math3.complex.Complex;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import scientificcalculator.Variables;

/**
 *
 * @author Group 10
 * @brief This is the Class used to test Variables methods
 */
public class VariablesTest {
    
    /**
     * @brief Definition of a fixture composed of the stack and of the variables stack
     */
    private Deque<Complex> stack;
    private Variables varStack;
    
    public VariablesTest() {
    }
     
    /**
     * @brief Method to initialize the fixture 
     */
    @Before
    public void setUp() {
        stack = new LinkedList<>();
        varStack = new Variables(stack); 
    }
    
    /**
     * @brief Method to test the assignToVar test
     * @param var variable [a, b, ..., z] that corresponds to the index [1, 2, ..., 26] of variables Stack
     */
    public void assignToVarTest(char var){  //serve il parametro var?
        Complex c1 = new Complex(3,3);
        Complex c2 = new Complex(3,5);
        stack.addFirst(c1);
        stack.addFirst(c2);
        int s1 = stack.size();
        varStack.assignToVar(var);
        assertEquals(s1 - 1, stack.size());
        assertEquals(stack.peekFirst(), c1);
        assertEquals(/*Controllare se c2 corrisponde all'elemento in posizione N di varStack*/, c2);
    }
    
    /**
     * @brief Method to test the copyFromVar test
     * @param var variable [a, b, ..., z] that corresponds to the index [1, 2, ..., 26] of variables Stack
     */
    public void copyFromVarTest(char var){  //serve il parametro var?
        Complex c1 = new Complex(3,3);
        Complex c2 = new Complex(3,5);
        stack.addFirst(c1);
        stack.addFirst(c2);
        int s1 = stack.size();
        varStack.copyFromVar(var);
        assertEquals(s1 + 1, stack.size());
        assertEquals(stack.peekFirst(), /*Valore copiato dal varStack*/);
    }
    
    /**
     * @brief Method to test the sumToVar test
     * @param var variable [a, b, ..., z] that corresponds to the index [1, 2, ..., 26] of variables Stack
     */
    public void sumToVarTest(char var){  //serve il parametro var?
        Complex c1 = new Complex(3,3);
        Complex c2 = new Complex(3,5);
        stack.addFirst(c1);
        stack.addFirst(c2);
        int s1 = stack.size();
        varStack.sumToVar(var);
        assertEquals(s1 - 1, stack.size());
        assertEquals(/*risultato in posizione N - valore precedente in posizione N deve corrispondere a valore stack sommato*/, c2);
    }
    
    /**
     * @brief Method to test the subtractToVar test
     * @param var variable [a, b, ..., z] that corresponds to the index [1, 2, ..., 26] of variables Stack
     */
    public void subtractToVarTest(char var){  //serve il parametro var?
        Complex c1 = new Complex(3,3);
        Complex c2 = new Complex(3,5);
        stack.addFirst(c1);
        stack.addFirst(c2);
        int s1 = stack.size();
        varStack.subtractToVar(var);
        assertEquals(s1 - 1, stack.size());
        assertEquals(/*risultato in posizione N - valore precedente in posizione N deve corrispondere a valore stack sottratto*/, c2);
    }
    
}
