package scientificcalculator;

import exception.InterpreterException;
import exception.ZeroDivisionException;
import java.text.NumberFormat;
import java.util.Deque;
import java.util.Locale;
import java.util.StringTokenizer;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexFormat;

/**
 * @author Group 10
 * @brief Interpreter class implements a translation mechanism
 */
public class Interpreter {
    
    private  ComplexNumOperation baseOp;
    private  StackManipulation stackManip;
    private ComplexFormat cf;
    private Variables variablesStack;
    private UserDefinedOperation userOperations; 

    public Interpreter(Deque<Complex> stack, UserDefinedOperation userOperations) {
        baseOp = new ComplexNumOperation(stack);
        stackManip= new StackManipulation(stack);
        cf = new ComplexFormat("j", NumberFormat.getInstance(Locale.US));
        variablesStack=new Variables(stack);
        this.userOperations = userOperations;
        
    }
    /**
     * @brief isComplex method returns true if the string is an operand
     * @param s is the input text
     * @return boolean
     */   
    private boolean isComplex(String s){
        //return s.matches("^(?=[j.\\d+-])([+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)(?:[eE][+-]?\\d+)?(?![j.\\d]))([+-]?(?:(?:\\d+(?:\\.\\d*)?|\\.\\d+)(?:[e][+-]?\\d+)?)?[j])?$");
        return s.matches("^(?=[j.\\d+-])([+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)(?![j.\\d]))([+-]?(?:(?:\\d+(?:\\.\\d*)?|\\.\\d+))?[j])?$");
    }
    /**
     * @brief isImaginary method returns true if the string is a pure complex number
     * @param s
     * @return 
     */
    private boolean isImaginary(String s){
        //return s.matches("^(?=[j.\\d+-])([+-]?(?:(?:\\d+(?:\\.\\d*)?|\\.\\d+)(?:[e][+-]?\\d+)?)?[j])?$");
        return s.matches("^(?=[j.\\d+-])([+-]?(?:(?:\\d+(?:\\.\\d*)?|\\.\\d+))?[j])?$");
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
            Complex c;
            
            if(isComplex(op)){
                if(op.charAt(0)=='+')
                    c = cf.parse(op.substring(1));
                else
                    c = cf.parse(op);
                
                baseOp.insertion(c);
            }
            else if(isImaginary(op)){
                
                if(op.charAt(0)=='-')
                    c = cf.parse("0"+op);
                else if(op.charAt(0)=='+')
                    c = cf.parse("0"+op);
                else
                    c = cf.parse("0+"+op);
                
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
            else if(op.length()==2 && op.charAt(0)=='>')
                variablesStack.assignToVar(op.charAt(1));
            else if(op.length()==2 && op.charAt(0)=='<')
                variablesStack.copyFromVar(op.charAt(1));
            else if(op.length()==2 && op.charAt(0)=='+')
                variablesStack.sumToVar(op.charAt(1));
            else if(op.length()==2 && op.charAt(0)=='-')
                variablesStack.subtractToVar(op.charAt(1));
            else   
                 throw new InterpreterException();
        
        }
    
    }
    
    public boolean check(String s){
        StringTokenizer ops = new StringTokenizer(s, " ");
        boolean flag = true;
        
        while(ops.hasMoreTokens() && flag==true){
            String op = ops.nextToken();
            
            if(isComplex(op))
            {}
            else if(isImaginary(op))
            {}
            else if(op.equals("+"))
            {}
            else if(op.equals("-"))
            {}
            else if(op.equals("/"))
            {}
            else if(op.equals("*"))
            {}
            else if(op.equals("+-"))
            {}
            else if(op.equals("sqrt"))
            {}
            else if(op.equals("clear"))
            {}
            else if(op.equals("dup"))
            {}
            else if(op.equals("drop"))
            {}
            else if(op.equals("over"))
            {}
            else if(op.equals("swap"))
            {}
            else if(op.length()==2 && op.charAt(0)=='>')
            {}
            else if(op.length()==2 && op.charAt(0)=='<')
            {}
            else if(op.length()==2 && op.charAt(0)=='+')
            {}
            else if(op.length()==2 && op.charAt(0)=='-')
            {}
            else if(userOperations.getNameOperations().contains(op)) 
            {}
            else flag = false;
        }
        return flag;
    }
    
}
