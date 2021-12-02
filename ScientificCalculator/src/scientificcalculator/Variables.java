/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author Group 10
 */
public class Variables {
    
    private Deque<Complex> stack;
    private LinkedList<Complex> stackVar;
    private int sp;

    public Variables(Deque<Complex> stack) {
        this.stack = stack;
        stackVar = new LinkedList<>();
        sp = 0;
        for(int i=0;i<26;i++)
            stackVar.add(null);
        
    }
    
    private int charToCode(char var){
        if(var < 'a' && var > 'z')
            throw new VarOutOfRange();
        return (int) var - 97;
    }
    
    public List<Complex> getActualVariables(){
        return stackVar.subList(sp, sp + 26);
    }
        
    public void assignToVar(char var){
        int pos = charToCode(var);
        
        stackVar.set(sp + pos, stack.removeFirst());
    }
    
    public void pullFromVar(char var){
        int pos = charToCode(var);
        
        stack.addFirst(stackVar.get(sp + pos));
    }

    public void sumToVar(char var){
        int pos = charToCode(var);
        Complex stackElem = stack.removeFirst();
        stackVar.get(sp + pos).add(stackElem);
    }
    
    public void subtractToVar(char var){
        int pos = charToCode(var);
        Complex stackElem = stack.removeFirst();
        stackVar.get(sp + pos).subtract(stackElem);
    }
    
       
    
}
