/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import java.text.NumberFormat;
import java.util.Deque;
import java.util.Locale;
import java.util.StringTokenizer;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexFormat;

/**
 * @brief Interpreter class implements a translation mechanism
 * @author Group 10
 */
public class Interpreter {
    
    private  ComplexNumOperation baseOp;
    private  StackManipulation stackManip;

    public Interpreter(Deque<Complex> stack) {
        baseOp = new ComplexNumOperation(stack);
        stackManip= new StackManipulation(stack);
    }
    /**
     * @brief isComplex returns true if the string is an operand
     * @param s is the input text
     * @return boolean
     */   
    private boolean isComplex(String s){
        return s.matches("^(?=[jJ.\\d+-])([+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)(?:[eE][+-]?\\d+)?(?![jJ.\\d]))([+-]?(?:(?:\\d+(?:\\.\\d*)?|\\.\\d+)(?:[eE][+-]?\\d+)?)?[jJ])?$");
    }
    /**
     * @brief parse method is used to separate the string input in tokens that are complex numbers(operators) or operators
     * This method calls each method of the class ComplexNumOperation to calculate basic operations and each method of the class StackManipulation
     * @param s is the content of the text field(display)
     * @throws InterpreterException if there are either wrong operators or wrong operands
     * @throws ZeroDivisionException 
     */
    public void parse(String s) throws InterpreterException, ZeroDivisionException{
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
