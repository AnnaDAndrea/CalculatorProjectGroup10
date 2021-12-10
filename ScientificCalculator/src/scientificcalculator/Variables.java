package scientificcalculator;

import exception.IllegalStackPointerException;
import exception.VarOutOfRangeException;
import java.util.Deque;
import java.util.LinkedList;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.NullArgumentException;

/**
 * This is the Class used to manage the 26 variables and its associated
 * operations
 *
 * @author Group 10
 *
 */
public class Variables {

    private Deque<Complex> stack;
    private LinkedList<Complex> stackVar;
    private int sp;

    /**
     * Constructor, stack pointer (sp) starting from 0 (so starting from 'a')
     *
     * @param stack stack containing the complex numbers
     */
    public Variables(Deque<Complex> stack) {
        this.stack = stack;
        stackVar = new LinkedList<>();
        sp = 0;
        for (int i = 0; i < 26; i++) {
            stackVar.add(null);
        }

    }

    /**
     * Method to convert the characters associated at the variables [a, b, ..., z]
     * in the numbers associated at the positions [0, 1, ..., 25]. 
     * It calls the VarOutOfRangeException if the variable is not contained in [a, b, ..., z]
     *
     * @param var variable [a, b, ..., z]
     * @return the position of the variable as a number
     */
    private int charToCode(char var) {
        if (var < 'a' || var > 'z') {
            throw new VarOutOfRangeException();
        }
        return (int) var - 97;
    }

    /**
     * Method to pull a complex number from the stack and assign it to a variable.
     * It calls the VarOutOfRangeException if the variable is not contained in [a, b, ..., z]
     * It calls the NoSuchElementException if the stack is empty
     *
     * @param var indicates the variable that will contain the assigned complex
     * number
     */
    public void assignToVar(char var) {
        int pos = charToCode(var);

        stackVar.set(sp + pos, stack.removeFirst());
    }

    /**
     * Method to copy the value of a variable and push it onto the stack. 
     * It calls the VarOutOfRangeException if the variable is not contained in [a, b, ..., z] 
     * It calls the NullArgumentException if the variable is empty
     *
     * @param var indicates the variable that will contain the value to copy
     * onto the stack
     */
    public void copyFromVar(char var) {
        int pos = charToCode(var);
        Complex val = stackVar.get(sp + pos);
        if (val == null) {
            throw new NullArgumentException();
        }
        stack.addFirst(new Complex(val.getReal(), val.getImaginary()));
    }

    /**
     * Method to pull a complex number from the stack and sum it to the variable
     * value and save the result into the variable itself. 
     * It calls the VarOutOfRangeException if the variable is not contained in [a, b, ..., z]
     * It calls the NoSuchElementException if the stack is empty
     * It calls the NullArgumentException if the variable is empty
     *
     * @param var indicates the variable that will contain the value of the sum
     */
    public void sumToVar(char var) {
        int pos = charToCode(var);

        Complex val = stackVar.get(sp + pos);
        if (val == null) {
            throw new NullArgumentException();
        }
        Complex stackElem = stack.removeFirst();
        stackVar.set(sp + pos, val.add(stackElem));

    }

    /**
     * Method to pull a complex number from the stack and subtract it to the
     * variable value and save the result into the variable itself. 
     * It calls the VarOutOfRangeException if the variable is not contained in [a, b, ..., z]
     * It calls the NoSuchElementException if the stack is empty
     * It calls the NullArgumentException if the variable is empty
     *
     * @param var indicates the variable that will contain the value of the
     * subtraction
     */
    public void subtractToVar(char var) {
        int pos = charToCode(var);

        Complex val = stackVar.get(sp + pos);
        if (val == null) {
            throw new NullArgumentException();
        }
        Complex stackElem = stack.removeFirst();
        stackVar.set(sp + pos, val.subtract(stackElem));

    }

    /**
     * Method to save a copy of all the 26 variables on a “variable stack”
     * (distinct from the stack used for the operands by the operations).
     * Applying “saveVar” several times it is possible to preserve several sets
     * of variable values
     */
    public void saveVar() {

        for (int i = 0; i < 26; i++) {
            Complex c = stackVar.get(sp + i);
            if (c != null) {
                stackVar.addLast(new Complex(c.getReal(), c.getImaginary()));
            } else {
                stackVar.addLast(null);
            }
        }
        sp = sp + 26;
    }

    /**
     * Method to restore for all the variables the last values that were saved
     * on the “variable stack” (removing them from that stack).
     * It calls the IllegalStackPointerException if there is a restoring attempt 
     * and the stack pointer is equal to 0 (so there aren't previous sets of variables
     * values to restore)
     */
    public void restoreVar() {

        if (sp == 0) {
            throw new IllegalStackPointerException();
        }

        for (int i = 0; i < 26; i++) {
            stackVar.removeLast();
        }

        sp = sp - 26;
    }

}
