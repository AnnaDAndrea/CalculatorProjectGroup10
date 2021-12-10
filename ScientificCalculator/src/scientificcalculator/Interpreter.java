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
     * @brief isComplex method returns true if the string is a complex operand
     * @param s is the input text
     * @return boolean
     */   
    private boolean isComplex(String s){
        return s.matches("^(?=[j.\\d+-])([+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)(?![j.\\d]))([+-]?(?:(?:\\d+(?:\\.\\d*)?|\\.\\d+))?[j])?$");
    }
    /**
     * @brief isImaginary method returns true if the string is a pure complex number
     * @param s
     * @return 
     */
    private boolean isImaginary(String s){
        return s.matches("^(?=[j.\\d+-])([+-]?(?:(?:\\d+(?:\\.\\d*)?|\\.\\d+))?[j])?$");
    }
    
    public boolean isOnlyJPart(String s){
        return s.matches("^(?=[j.\\d+-])([+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)(?![j.\\d]))([+-]?[j])$");
    }
    /**
     * @brief parse method is used to separate the string input in tokens that are complex numbers(operators) or operators
     * This method calls each method of the class ComplexNumOperation to calculate basic operations,each method of the class StackManipulation,each method of the class Variables
     * @param s is the content of the text field(display)
     * @throws InterpreterException if there are either wrong operators or wrong operands
     * @throws ZeroDivisionException 
     */
    public void parse(String s) throws InterpreterException, ZeroDivisionException{
        StringTokenizer ops = new StringTokenizer(s, " ");
        
        while(ops.hasMoreTokens()){
            String op = ops.nextToken();
            Complex c = null;
            if(op.equals("j")){
                c= new Complex(0,1);
                baseOp.insertion(c);
            } 
            else if(isOnlyJPart(op)){
                if(op.charAt(0)=='+')
                    op = op.substring(1);
                
                op = op.replace("j", "1j");
                c = cf.parse(op);
                
                baseOp.insertion(c);
            }
            else if(isComplex(op)){
                if(op.charAt(0)=='+')
                    c = cf.parse(op.substring(1));
                else
                    c = cf.parse(op);
                
                baseOp.insertion(c);
            }
            else if(isImaginary(op) && !op.equals("+j") && !op.equals("-j")){
             
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
            else if(op.equals("save")){
                variablesStack.saveVar();
            }
            else if(op.equals("restore")){
                variablesStack.restoreVar();
            }
            else {  
                String userDef= userOperations.getSequence(op);
                if(userDef == null)
                    throw new InterpreterException();
                else{
                    parse(userDef);
                }
            }
        
        }
    
    }
 /**
  * @brief check method returns true if a sequence is allowed,correct and all the containing operations exist
  * @param s is a sequence to be checked
  * @return boolean
  */
    public boolean check(String s){
        StringTokenizer ops = new StringTokenizer(s, " ");
        boolean flag = true, flag1 = false;
        
        while(ops.hasMoreTokens() && flag==true){
            String op = ops.nextToken();
            flag1 = true;
            if(op.equals("j"))
            {} 
            else if(isOnlyJPart(op))
            {}
            else if(isComplex(op))
            {}
            else if(isImaginary(op) && !op.equals("+j") && !op.equals("-j"))
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
            else if(op.equals("save"))
            {}
            else if(op.equals("restore"))
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
        return flag && flag1;
    }
    
}
