package scientificcalculator;

import exception.VarOutOfRangeException;
import java.util.Deque;
import java.util.LinkedList;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.NullArgumentException;

/**
 *
 * @author Group 10
 * @brief This is the Class used to manage the 26 variables and its associated operations
 */
public class Variables {
    
    private Deque<Complex> stack;
    private LinkedList<Complex> stackVar;
    private int sp;

    /**
     * @brief Constructor
     * @param stack stack containing the complex numbers
     */
    public Variables(Deque<Complex> stack) {
        this.stack = stack;
        stackVar = new LinkedList<>();
        sp = 0;
        for(int i=0;i<26;i++)
            stackVar.add(null);
        
    }
    
    /**
     * @brief method to convert the characters associated at the variables [a, b, ..., z] 
     * in the numbers associated at the positions [0, 1, ..., 26]. It calls the VarOutOfRangeException 
     * if the variable is not contained in [a, b, ..., z]
     * @param var variable [a, b, ..., z]
     * @return the position of the variable as a number
     */
    private int charToCode(char var){
        if(var < 'a' || var > 'z')
            throw new VarOutOfRangeException();
        return (int) var - 97;
    }
        
    /**
     * @brief method to pull a complex number from the stack and assign it to a variable
     * @param var indicates the variable that will contain the assigned complex number 
     */
    public void assignToVar(char var){
        int pos = charToCode(var);
        
        stackVar.set(sp + pos, stack.removeFirst());
    }
    
    /**
     * @brief method to copy the value of a variable and push it onto the stack
     * @param var indicates the variable that will contain the value to copy onto the stack 
     */
    public void copyFromVar(char var){
        int pos = charToCode(var);
        Complex val = stackVar.get(sp + pos);
        if (val == null)
            throw new NullArgumentException();
        stack.addFirst(new Complex(val.getReal(), val.getImaginary()));
    }

    /**
     * @brief method to pull a complex number from the stack and sum it to the variable
     * value and save the result into the variable itself
     * @param var indicates the variable that will contain the value of the sum 
     */
    public void sumToVar(char var){
        int pos = charToCode(var);
        Complex stackElem = stack.removeFirst();
        Complex val = stackVar.get(sp + pos);
        if (val == null)
            throw new NullArgumentException();
        stackVar.set(sp + pos, val.add(stackElem));
    }
    
    /**
     * @brief method to pull a complex number from the stack and subtract it to the variable
     * value and save the result into the variable itself
     * @param var indicates the variable that will contain the value of the subtraction  
     */
    public void subtractToVar(char var){
        int pos = charToCode(var);
        Complex stackElem = stack.removeFirst();
        Complex val = stackVar.get(sp + pos);
        if (val == null)
            throw new NullArgumentException();
        stackVar.set(sp + pos, val.subtract(stackElem));
    }
    
       
    
}
