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
 *
 * @author duino
 */
public class Interpreter {
    
    private ComplexNumOperation baseOp;

    public Interpreter(Deque<Complex> stack) {
        baseOp = new ComplexNumOperation(stack);
    }
       
    private boolean isComplex(String s){
        return s.matches("^(?=[jJ.\\d+-])([+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)(?:[eE][+-]?\\d+)?(?![jJ.\\d]))([+-]?(?:(?:\\d+(?:\\.\\d*)?|\\.\\d+)(?:[eE][+-]?\\d+)?)?[jJ])?$");
    }
    
    public void parse(String s){
        StringTokenizer ops = new StringTokenizer(s, " ");
        
        while(ops.hasMoreTokens()){
            String op = ops.nextToken();

            if(isComplex(op)){
                ComplexFormat cf = new ComplexFormat("j", NumberFormat.getInstance(Locale.US));
                Complex c = cf.parse(op);
                
                baseOp.insert(c);
            }
            else if(op.equals("+"))
                baseOp.sum();
            else
                System.out.println("Errore " + op);
        }
    
    }
    
}
