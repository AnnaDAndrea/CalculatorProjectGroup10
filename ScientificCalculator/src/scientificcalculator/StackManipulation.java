package scientificcalculator;

import java.util.Deque;
import java.util.NoSuchElementException;
import org.apache.commons.math3.complex.Complex;

/**
 * This is the Class used to manipulate the Stack containing the Complex Numbers
 *
 * @author Group 10
 *
 */
public class StackManipulation {

    private Deque<Complex> stack;

    public StackManipulation(Deque<Complex> stack) {
        this.stack = stack;
    }

    /**
     * This method removes all the elements from the stack
     */
    public void clear() {

        stack.clear();

    }

    /**
     * This method removes the last element (i.e. the top) from the stack It
     * calls the NoSuchElementException if the stack is empty
     */
    public void drop() {

        stack.removeFirst();

    }

    /**
     * This method pushes a copy of the last element onto the stack It calls the
     * NoSuchElementException if the stack is empty
     */
    public void dup() {

        Complex op1 = stack.removeFirst();

        stack.addFirst(op1);
        stack.addFirst(op1);

    }

    /**
     * This method exchanges the last two elements of the stack It calls the
     * NoSuchElementException if the stack is empty or if there aren't enough
     * elements
     */
    public void swap() {

        if (stack.size() >= 2) {
            Complex op1 = stack.removeFirst();
            Complex op2 = stack.removeFirst();

            stack.addFirst(op1);
            stack.addFirst(op2);
        } else {
            throw new NoSuchElementException();
        }

    }

    /**
     * This method pushes a copy of the second last element onto the stack It
     * calls the NoSuchElementException if the stack is empty or if there aren't
     * enough elements
     */
    public void over() {

        if (stack.size() >= 2) {
            Complex op1 = stack.removeFirst();
            Complex op2 = stack.removeFirst();

            stack.addFirst(op2);
            stack.addFirst(op1);
            stack.addFirst(op2);
        } else {
            throw new NoSuchElementException();
        }
    }

}
