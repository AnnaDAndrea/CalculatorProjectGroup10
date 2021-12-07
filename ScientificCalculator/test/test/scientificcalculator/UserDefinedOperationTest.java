/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.scientificcalculator;

import exception.KeyAlreadyExistException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import scientificcalculator.UserDefinedOperation;

/**
 *@brief UserDefinedOperationTest class is used to test UserDefinedOperation class
 * @author Group 10
 */
public class UserDefinedOperationTest 
{
    
    private UserDefinedOperation userDefinedOperation;

    public UserDefinedOperationTest() {
    }

    @Before
    public void setUp() {
        userDefinedOperation = new UserDefinedOperation();
    }

    /**
     * @brief testNewOperation method tests that the insertion of an operation with a key already existing throws a KeyAlreadyExistException
     */
    @Test(expected = KeyAlreadyExistException.class)
    public void testNewOperation() {
        userDefinedOperation.newOperation("op1", "2j 4j drop");
        userDefinedOperation.newOperation("op1", "2+2j 4+4j swap");
    }
    
    /**
     * @brief testNewOperation1 method tests the insertion of a new operation
     */
    @Test
    public void testNewOperation1() {
        userDefinedOperation.newOperation("op2", "3+2j 4+4j swap");
        userDefinedOperation.newOperation("op3", "2j 3j over");
 
        assertEquals("3+2j 4+4j swap", userDefinedOperation.getSequence("op2"));

    }
    
    /**
     * @brief testDelete method tests the deletion of an operation
     */
    @Test
    public void testDelete(){
        userDefinedOperation.newOperation("op1","1+2j 1-2j +");
        userDefinedOperation.newOperation("op2","1-2j 1-2j -");
        
        assertEquals("1+2j 1-2j +",userDefinedOperation.delete("op1"));
        assertNull(userDefinedOperation.delete("op1"));
    }
    
    /**
     * @brief 
     */
    @Test
    public void testDeleteAllDependencies(){
       userDefinedOperation.newOperation("op1","1 2 swap"); 
       userDefinedOperation.newOperation("op2","1 2 op1");
       userDefinedOperation.newOperation("op3","3 6 op1");
       userDefinedOperation.newOperation("op4","6 7 op2");
       userDefinedOperation.newOperation("triplSum","+ + +");
       userDefinedOperation.newOperation("quadSum","+ triplSum");
       userDefinedOperation.newOperation("quintSum","triplSum + +");
       userDefinedOperation.newOperation("op6","1+2j 1+2j +");
       userDefinedOperation.newOperation("op7","1+2j 1+2j *");
       
       
       userDefinedOperation.deleteAllDependencies("op1");
       assertNull(userDefinedOperation.getSequence("op1"));
       assertNull(userDefinedOperation.getSequence("op2"));
       assertNull(userDefinedOperation.getSequence("op3"));
       assertNull(userDefinedOperation.getSequence("op4"));
       assertEquals("1+2j 1+2j +",userDefinedOperation.getSequence("op6"));
       assertEquals("1+2j 1+2j *",userDefinedOperation.getSequence("op7"));
       assertEquals("+ + +",userDefinedOperation.getSequence("triplSum"));
       assertEquals("+ triplSum",userDefinedOperation.getSequence("quadSum"));
       assertEquals("triplSum + +",userDefinedOperation.getSequence("quintSum"));
        
       userDefinedOperation.deleteAllDependencies("quadSum");
       assertEquals("1+2j 1+2j +",userDefinedOperation.getSequence("op6"));
       assertEquals("1+2j 1+2j *",userDefinedOperation.getSequence("op7"));
       assertEquals("+ + +",userDefinedOperation.getSequence("triplSum"));
       assertEquals("triplSum + +",userDefinedOperation.getSequence("quintSum"));
       
       userDefinedOperation.deleteAllDependencies("triplSum");
       assertEquals("1+2j 1+2j +",userDefinedOperation.getSequence("op6"));
       assertEquals("1+2j 1+2j *",userDefinedOperation.getSequence("op7"));
       assertNull(userDefinedOperation.getSequence("triplSum"));
       assertNull(userDefinedOperation.getSequence("quintSum"));
       
       userDefinedOperation.deleteAllDependencies("op6");
       assertNull(userDefinedOperation.getSequence("op6"));
       assertEquals("1+2j 1+2j *",userDefinedOperation.getSequence("op7"));
       
 
    }
  
}
