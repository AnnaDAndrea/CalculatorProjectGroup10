package test.scientificcalculator;

import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.NullArgumentException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import exception.VarOutOfRangeException;
import scientificcalculator.Variables;

/**
 *
 * @author Group 10
 * @brief This is the Class used to test the Variables methods
 */
public class VariablesTest {
    
    /**
     * @brief Definition of a fixture composed of the stack and of an instance of Variables
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
     * @brief Method to test the assignToVar method
     */
    @Test
    public void assignToVarTest(){  
        Complex c1 = new Complex(3,3);
        Complex c2 = new Complex(3,5);
        stack.addFirst(c1);
        stack.addFirst(c2);
        int s1 = stack.size();
        varStack.assignToVar('a');
        
        // Checking stack size and if c2 has effectively been removed from stack 
        assertEquals(s1 - 1, stack.size());
        assertEquals(c1, stack.peekFirst());
        
        // Checking if c2 has effectively been assigned to varStack
        varStack.copyFromVar('a');
        assertEquals(c2, stack.peekFirst());
    }
    
    /**
     * @brief Method to test the assignToVar method with more Complex numbers using 2 variables
     */
    @Test
    public void assignToVarTest2(){  
        Complex c1 = new Complex(3,3);
        Complex c2 = new Complex(3,5);
        stack.addFirst(c1);
        stack.addFirst(c2);
        int s1 = stack.size();
        varStack.assignToVar('a');
        
        // Checking stack size and if c2 has effectively been removed from stack 
        assertEquals(s1 - 1, stack.size());
        assertEquals(c1, stack.peekFirst());
        
        varStack.assignToVar('b');
        
        // Checking stack size and if c1 has effectively been removed from stack 
        assertEquals(0, stack.size());
        assertEquals(null, stack.peekFirst());
        
        // Checking if c2 has effectively been assigned to varStack
        varStack.copyFromVar('a');
        assertEquals(c2, stack.peekFirst());
        
        // Checking if c1 has effectively been assigned to varStack
        varStack.copyFromVar('b');
        assertEquals(c1, stack.peekFirst());        
    }
    
    /**
     * @brief Method to test the assignToVar method using all the availables variables
     */
    @Test
    public void assignToVarTest3(){  
        for(int i = 0; i < 26; i++)
            stack.addFirst(new Complex(i));
        
        for(char c = 'a'; c <= 'z'; c++)
            varStack.assignToVar(c);
        
        assertEquals(0, stack.size());
        
        for(char c = 'a'; c <= 'z'; c++)
            varStack.copyFromVar(c);
        
        for(char c = 'a'; c <= 'z'; c++)
            varStack.copyFromVar(c);
        
        assertEquals(52, stack.size());
        
        for(int i = 0; i < 26; i++){
            assertEquals(new Complex(i), stack.removeFirst());
        }
        
        for(int i = 0; i < 26; i++){
            assertEquals(new Complex(i), stack.removeFirst());
        }         
    }
    
    /**
     * @brief Method to test the assignToVar method fail calling the NoSuchElementException (empty stack)
     */
    @Test(expected = NoSuchElementException.class)
    public void assignToVarTestFail(){       
        varStack.assignToVar('a');
    }
    
    /**
     * @brief Method to test the assignToVar method fail trying to assign a value 
     * to a non existent variable (', that is part of the special characters that are limit cases).
     * It calls the VarOutOfRangeException 
     */
    @Test(expected = VarOutOfRangeException.class)
    public void assignToVarTestFail2(){
        Complex c1 = new Complex(3,3);
        stack.addFirst(c1);
        varStack.assignToVar('\'');
    }
    
    /**
     * @brief Method to test the assignToVar method fail trying to assign a value 
     * to a non existent variable ({, that is part of the special characters that are limit cases).
     * It calls the VarOutOfRangeException 
     */
    @Test(expected = VarOutOfRangeException.class)
    public void assignToVarTestFail3(){    
        Complex c1 = new Complex(3,3);
        stack.addFirst(c1);
        varStack.assignToVar('{');
    }
    
    /**
     * @brief Method to test the assignToVar method fail trying to assign a value 
     * to a non existent variable (A, that is part of the uppercase characters that are limit cases).
     * It calls the VarOutOfRangeException 
     */
    @Test(expected = VarOutOfRangeException.class)
    public void assignToVarTestFail4(){    
        Complex c1 = new Complex(3,3);
        stack.addFirst(c1);
        varStack.assignToVar('A');
    }
    
    /**
     * @brief Method to test the assignToVar method fail trying to assign a value 
     * to a non existent variable (à, that is part of the accented characters that are limit cases).
     * It calls the VarOutOfRangeException 
     */
    @Test(expected = VarOutOfRangeException.class)
    public void assignToVarTestFail5(){    
        Complex c1 = new Complex(3,3);
        stack.addFirst(c1);
        varStack.assignToVar('à');
    }
    
    /**
     * @brief Method to test the assignToVar method fail trying to assign a value 
     * to a non existent variable (1, that is part of the numbers that are limit cases).
     * It calls the VarOutOfRangeException 
     */
    @Test(expected = VarOutOfRangeException.class)
    public void assignToVarTestFail6(){    
        Complex c1 = new Complex(3,3);
        stack.addFirst(c1);
        varStack.assignToVar('1');
    }
    
    /**
     * @brief Method to test the copyFromVar method
     */
    @Test
    public void copyFromVarTest(){
        Complex c1 = new Complex(3,3);
        Complex c2 = new Complex(3,5);
        stack.addFirst(c1);
        stack.addFirst(c2);
        varStack.assignToVar('a');
        int s1 = stack.size();
        
        // Checking if an element has effectively been added to stack and if it is the correct one
        varStack.copyFromVar('a');
        assertEquals(s1 + 1, stack.size());
        assertEquals(c2, stack.peekFirst());
        
        // Checking if after the editing of the value copied from the variable, the value contained in the variable is unchanged
        Complex c2mod = stack.removeFirst().add(1);
        stack.addFirst(c2mod);
        
        varStack.copyFromVar('a');
        assertEquals(s1 + 2, stack.size());
        assertEquals(c2, stack.peekFirst());
        
        // Checking if after adding a new value to the stack and assigning it to the variable, the stack content is unchanged
        stack.addFirst(new Complex(1, 1));
        varStack.assignToVar('a');
        assertEquals(new Complex(3, 5), stack.removeFirst());
        assertEquals(new Complex (4, 5), stack.removeFirst());
        assertEquals(new Complex(3, 3), stack.removeFirst());
    }
    
    /**
     * @brief Method to test the copyFromVar method fail calling the NullArgumentException (empty variable)
     */
    @Test(expected = NullArgumentException.class)
    public void copyFromVarTestFail(){  
        varStack.copyFromVar('a');
    }
    
    /**
     * @brief Method to test the copyFromVar method fail trying to copy a value 
     * from a non existent variable (', that is part of the special characters that are limit cases).
     * It calls the VarOutOfRangeException 
     */
    @Test(expected = VarOutOfRangeException.class)
    public void copyFromVarTestFail2(){
        varStack.copyFromVar('\'');
    }
    
    /**
     * @brief Method to test the copyFromVar method fail trying to copy a value 
     * from a non existent variable ({, that is part of the special characters that are limit cases).
     * It calls the VarOutOfRangeException 
     */
    @Test(expected = VarOutOfRangeException.class)
    public void copyFromVarTestFail3(){    
        varStack.copyFromVar('{');
    }
    
    /**
     * @brief Method to test the copyFromVar method fail trying to copy a value 
     * from a non existent variable (A, that is part of the uppercase characters that are limit cases).
     * It calls the VarOutOfRangeException 
     */
    @Test(expected = VarOutOfRangeException.class)
    public void copyFromVarTestFail4(){    
        varStack.copyFromVar('A');
    }
    
    /**
     * @brief Method to test the copyFromVar method fail trying to copy a value 
     * from a non existent variable (à, that is part of the accented characters that are limit cases).
     * It calls the VarOutOfRangeException 
     */
    @Test(expected = VarOutOfRangeException.class)
    public void copyFromVarTestFail5(){    
        varStack.copyFromVar('à');
    }
    
    /**
     * @brief Method to test the copyFromVar method fail trying to copy a value 
     * from a non existent variable (1, that is part of the numbers that are limit cases).
     * It calls the VarOutOfRangeException 
     */
    @Test(expected = VarOutOfRangeException.class)
    public void copyFromVarTestFail6(){    
        varStack.copyFromVar('1');
    }
    
    
    /**
     * @brief Method to test the sumToVar method
     */
    @Test
    public void sumToVarTest(){
        Complex c1 = new Complex(3,5);
        Complex c2 = new Complex(5,1);
        stack.addFirst(c1);
        stack.addFirst(c2);
        varStack.assignToVar('a');
        int s1 = stack.size();
        
        // Checking if an element has effectively been removed from the stack and if the sum has been done correctly
        varStack.sumToVar('a');
        assertEquals(s1 - 1, stack.size());
        varStack.copyFromVar('a');
        assertEquals(s1, stack.size());
        assertEquals(new Complex(8,6), stack.peekFirst());
        
        // Checking if recalling the sumToVar method, the previous result hasn't changed
        stack.addFirst(new Complex(1));
        varStack.sumToVar('a');
        assertEquals(new Complex(8,6), stack.peekFirst());
    }
    
    /**
     * @brief Method to test the sumToVar method using all the availables variables
     */
    @Test
    public void sumToVarTest2(){  
        for(int i = 0; i < 26; i++)
            stack.addFirst(new Complex(i));
        
        for(int i = 0; i < 26; i++)
            stack.addFirst(new Complex(i)); 
        
        for(char c = 'a'; c <= 'z'; c++)
            varStack.assignToVar(c);
        
        assertEquals(26, stack.size());
        
        for(char c = 'a'; c <= 'z'; c++)
            varStack.sumToVar(c);
        
        assertEquals(0, stack.size());
        
        for(char c = 'a'; c <= 'z'; c++)
            varStack.copyFromVar(c);
        
        assertEquals(26, stack.size());
        
        for(int i = 0; i < 26; i++){
            assertEquals(new Complex(i*2), stack.removeFirst());
        }        
    }
    
    /**
     * @brief Method to test the sumToVar method fail calling the NoSuchElementException (empty stack)
     */
    @Test(expected = NoSuchElementException.class)
    public void sumToVarTestFail(){  
        Complex c1 = new Complex(3,5);
        stack.addFirst(c1);
        varStack.assignToVar('a');
        
        varStack.sumToVar('a');
    }
    
    /**
     * @brief Method to test the sumToVar method fail calling the NullArgumentException (empty variable)
     */
    @Test(expected = NullArgumentException.class)
    public void sumToVarTestFail2(){  
        Complex c1 = new Complex(3,3);
        stack.addFirst(c1);
        varStack.sumToVar('a');
    }
    
    
    /**
     * @brief Method to test the sumToVar method fail trying to sum a value 
     * in a non existent variable (', that is part of the special characters that are limit cases).
     * It calls the VarOutOfRangeException 
     */
    @Test(expected = VarOutOfRangeException.class)
    public void sumToVarTestFail3(){
        varStack.sumToVar('\'');
    }
    
    
    /**
     * @brief Method to test the copyFromVar method fail trying to trying to sum a value 
     * in a non existent variable ({, that is part of the special characters that are limit cases).
     * It calls the VarOutOfRangeException 
     */
    @Test(expected = VarOutOfRangeException.class)
    public void sumToVarTestFail4(){    
        varStack.sumToVar('{');
    }
    
    /**
     * @brief Method to test the copyFromVar method fail trying to sum a value 
     * in a non existent variable (A, that is part of the uppercase characters that are limit cases).
     * It calls the VarOutOfRangeException 
     */
    @Test(expected = VarOutOfRangeException.class)
    public void sumToVarTestFail5(){    
        varStack.sumToVar('A');
    }
    
    /**
     * @brief Method to test the copyFromVar method fail trying to sum a value 
     * in a non existent variable (à, that is part of the accented characters that are limit cases).
     * It calls the VarOutOfRangeException 
     */
    @Test(expected = VarOutOfRangeException.class)
    public void sumToVarTestFail6(){    
        varStack.sumToVar('à');
    }
    
    /**
     * @brief Method to test the copyFromVar method fail trying to sum a value 
     * in a non existent variable (1, that is part of the numbers that are limit cases).
     * It calls the VarOutOfRangeException 
     */
    @Test(expected = VarOutOfRangeException.class)
    public void sumToVarTestFail7(){    
        varStack.sumToVar('1');
    }

    /**
     * @brief Method to test the subtractToVar method
     */
    @Test
    public void subtractToVarTest(){
        Complex c1 = new Complex(3,5);
        Complex c2 = new Complex(5,1);
        stack.addFirst(c1);
        stack.addFirst(c2);
        varStack.assignToVar('a');
        int s1 = stack.size();
        
        // Checking if an element has effectively been removed from the stack and if the subtraction has been done correctly
        varStack.subtractToVar('a');
        assertEquals(s1 - 1, stack.size());
        varStack.copyFromVar('a');
        assertEquals(s1, stack.size());
        assertEquals(new Complex(2,-4), stack.peekFirst());
        
        // Checking if recalling the subtractToVar method, the previous result hasn't changed
        stack.addFirst(new Complex(1));
        varStack.subtractToVar('a');
        assertEquals(new Complex(2,-4), stack.peekFirst());
    }
    
    /**
     * @brief Method to test the subtractToVar method using all the availables variables
     */
    @Test
    public void subtractToVarTest2(){  
        for(int i = 0; i < 26; i++)
            stack.addFirst(new Complex(i+1));
        
        for(int i = 0; i < 26; i++)
            stack.addFirst(new Complex(i)); 
        
        for(char c = 'a'; c <= 'z'; c++)
            varStack.assignToVar(c);
        
        assertEquals(26, stack.size());
        
        for(char c = 'a'; c <= 'z'; c++)
            varStack.subtractToVar(c);
        
        assertEquals(0, stack.size());
        
        for(char c = 'a'; c <= 'z'; c++)
            varStack.copyFromVar(c);
        
        assertEquals(26, stack.size());
        
        for(int i = 0; i < 26; i++){
            assertEquals(new Complex(-1), stack.removeFirst());
        }        
    }
    
    
    /**
     * @brief Method to test the subtractToVar method fail calling the NoSuchElementException (empty stack)
     */
    @Test(expected = NoSuchElementException.class)
    public void subtractToVarTestFail(){  
        Complex c1 = new Complex(3,5);
        stack.addFirst(c1);
        varStack.assignToVar('a');
        
        varStack.subtractToVar('a');
    }
    
    
    /**
     * @brief Method to test the subtractToVar method fail calling the NullArgumentException (empty variable)
     */
    @Test(expected = NullArgumentException.class)
    public void subtractToVarTestFail2(){  
        Complex c1 = new Complex(3,3);
        stack.addFirst(c1);
        varStack.subtractToVar('a');
    }
    
    /**
     * @brief Method to test the subtractToVar method fail trying to subtract a value 
     * in a non existent variable (', that is part of the special characters that are limit cases).
     * It calls the VarOutOfRangeException 
     */
    @Test(expected = VarOutOfRangeException.class)
    public void subtractToVarTestFail3(){
        varStack.subtractToVar('\'');
    }
    
    /**
     * @brief Method to test the subtractToVar method fail trying to trying to subtract a value 
     * in a non existent variable ({, that is part of the special characters that are limit cases).
     * It calls the VarOutOfRangeException 
     */
    @Test(expected = VarOutOfRangeException.class)
    public void subtractToVarTestFail4(){    
        varStack.subtractToVar('{');
    }
    
    /**
     * @brief Method to test the subtractToVar method fail trying to subtract a value 
     * in a non existent variable (A, that is part of the uppercase characters that are limit cases).
     * It calls the VarOutOfRangeException 
     */
    @Test(expected = VarOutOfRangeException.class)
    public void subtractToVarTestFail5(){    
        varStack.subtractToVar('A');
    }
    
    /**
     * @brief Method to test the subtractToVar method fail trying to subtract a value 
     * in a non existent variable (à, that is part of the accented characters that are limit cases).
     * It calls the VarOutOfRangeException 
     */
    @Test(expected = VarOutOfRangeException.class)
    public void subtractToVarTestFail6(){    
        varStack.subtractToVar('à');
    }
    
    /**
     * @brief Method to test the subtractToVar method fail trying to subtract a value 
     * in a non existent variable (1, that is part of the numbers that are limit cases).
     * It calls the VarOutOfRangeException 
     */
    @Test(expected = VarOutOfRangeException.class)
    public void subtractToVarTestFail7(){    
        varStack.subtractToVar('1');
    }
    
}
