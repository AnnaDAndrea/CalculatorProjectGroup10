/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import java.util.Deque;
import java.util.NoSuchElementException;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author duino
 */
public class ComplexNumOperation {
    
    private Deque<Complex> stack;

    public ComplexNumOperation(Deque<Complex> stack) {
        this.stack = stack;
    }
    
    public void insert(Complex z){
        stack.addFirst(z);
    }
    
    
    
    
    
}
