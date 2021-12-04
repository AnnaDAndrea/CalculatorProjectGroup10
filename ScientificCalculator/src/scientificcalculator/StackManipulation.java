package scientificcalculator;

import java.util.Deque;
import org.apache.commons.math3.complex.Complex;

/**
 * @author Group 10
 * @brief This is the Class used to manipulate the Stack
 */
public class StackManipulation {
    
    private Deque<Complex> stack;

    public StackManipulation(Deque<Complex> stack) {
        this.stack = stack;
    }
    
    /**
     * @brief This method removes all the elements from the stack
     */
    public void clear(){
        
        stack.clear();

    }
    
    /**
     * @brief This method removes the last element (i.e. the top) from the stack
     * It calls the NoSuchElementException if the stack is empty
     */
    public void drop() {
        
        stack.removeFirst();

    }

    /**
     * @brief This method pushes a copy of the last element onto the stack
     * It calls the NoSuchElementException if the stack is empty
     */
    public void dup() {
        
        Complex op1 = stack.removeFirst();
        
        stack.addFirst(op1);
        stack.addFirst(op1);
        
    }
    
    /**
     * @brief This method exchanges the last two elements of the stack
     * It calls the NoSuchElementException if the stack is empty or if there aren't enough elements
     */
    public void swap() {
        
        Complex op1 = stack.removeFirst();
        Complex op2 = stack.removeFirst();
        
        stack.addFirst(op1);
        stack.addFirst(op2);
        
    }
    
    /**
     * @brief This method pushes a copy of the second last element onto the stack
     * It calls the NoSuchElementException if the stack is empty or if there aren't enough elements
     */
    public void over() {
        
        Complex op1 = stack.removeFirst();
        Complex op2 = stack.removeFirst();
        
        stack.addFirst(op2);
        stack.addFirst(op1);
        stack.addFirst(op2);
        
    }
}
