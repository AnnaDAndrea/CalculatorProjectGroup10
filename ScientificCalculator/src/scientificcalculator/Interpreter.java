/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import java.text.NumberFormat;
import java.util.Deque;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexFormat;

/**
 *
 * @author duino
 */
public class Interpreter {
    
    private ComplexNumOperation baseOp;
    private StackManipulation stackManip;

    public Interpreter(Deque<Complex> stack) {
        baseOp = new ComplexNumOperation(stack);
        stackManip= new StackManipulation(stack);
    }
       
    private boolean isComplex(String s){
        return s.matches("^(?=[jJ.\\d+-])([+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)(?:[eE][+-]?\\d+)?(?![jJ.\\d]))([+-]?(?:(?:\\d+(?:\\.\\d*)?|\\.\\d+)(?:[eE][+-]?\\d+)?)?[jJ])?$");
    }
    
    public void parse(String s) throws InterpreterException{
        StringTokenizer ops = new StringTokenizer(s, " ");
        
        while(ops.hasMoreTokens()){
            String op = ops.nextToken();

            if(isComplex(op)){
                ComplexFormat cf = new ComplexFormat("j", NumberFormat.getInstance(Locale.US));
                Complex c = cf.parse(op);
                
                baseOp.insertion(c);
            }
            else if(op.equals("+"))
                baseOp.sum();
            
            else if(op.equals("-"))
                baseOp.subtraction();
            else if(op.equals("/"))
                baseOp.division();
            else if(op.equals("*"))
                baseOp.product();
            else if(op.equals("+-"))
                baseOp.invertedSign();
            else if(op.equals("sqrt"))
                baseOp.squareRoot();
            else if(op.equals("clear"))
                stackManip.clear();
            else if(op.equals("dup"))
                stackManip.dup();
            else if(op.equals("drop"))
                stackManip.drop();
            else if(op.equals("over"))
                stackManip.over();
            else if(op.equals("swap"))
                stackManip.swap();
            else
                throw new InterpreterException();
        
        }
    
    }
    
}
