/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import java.util.Deque;
import org.apache.commons.math3.complex.Complex;
import java.util.NoSuchElementException;

/**
 *
 * @author Group 10
 */
public class StackManipulation {
    
    private Deque<Complex> stack;

    public StackManipulation(Deque<Complex> stack) {
        this.stack = stack;
    }
    
    public void clear(){
        
        stack.clear();

    }
    
    public void drop() throws NoSuchElementException {
        
        stack.removeFirst();

    }

    public void dup() throws NoSuchElementException {
        
        Complex op1 = stack.removeFirst();
        
        stack.addFirst(op1);
        stack.addFirst(op1);
        
    }
    
    public void swap() throws NoSuchElementException {
        
        Complex op1 = stack.removeFirst();
        Complex op2 = stack.removeFirst();
        
        stack.addFirst(op1);
        stack.addFirst(op2);
        
    }
    
    public void over() throws NoSuchElementException {
        
        Complex op1 = stack.removeFirst();
        Complex op2 = stack.removeFirst();
        
        stack.addFirst(op2);
        stack.addFirst(op1);
        stack.addFirst(op2);
        
    }
}
