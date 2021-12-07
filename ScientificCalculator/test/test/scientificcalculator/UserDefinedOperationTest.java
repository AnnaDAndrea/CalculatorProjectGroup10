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
    
    
    @Test
    public void testDelete(){
        userDefinedOperation.newOperation("op1","1+2j 1-2j +");
        userDefinedOperation.newOperation("op2","1-2j 1-2j -");
        
        assertEquals(userDefinedOperation.delete("op1"),"1+2j 1-2j +");
    }
  
}
