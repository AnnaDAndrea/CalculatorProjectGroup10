package test.scientificcalculator;

import java.util.Deque;
import java.util.LinkedList;
import org.apache.commons.math3.complex.Complex;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import scientificcalculator.Interpreter;
import exception.InterpreterException;
import scientificcalculator.UserDefinedOperation;
import exception.ZeroDivisionException;

/**
 * @author Group 10
 * @brief This is a tester class to test the behavior of the class Interpreter.
 * It tests if the interpreter works correctly
 */
public class InterpreterTest {

    private Interpreter interpreter;
    private Deque<Complex> stack;
    private UserDefinedOperation userOperations;

    public InterpreterTest() {
    }

    /**
     * @biref setUp method is used to create a fixture
     */
    @Before
    public void setUp() {
        stack = new LinkedList<>();
        userOperations = new UserDefinedOperation();
        interpreter = new Interpreter(stack, userOperations);

    }

    /**
     * @brief testParseInsertion method verifies that a complex number inserted
     * in the text field is inserted onto the stack
     * @throws InterpreterException
     * @throws ZeroDivisionException
     */
    @Test
    public void testParseInsertion() throws InterpreterException, ZeroDivisionException {

        interpreter.parse("1+5j");
        assertEquals(stack.getFirst(), new Complex(1, 5));

        interpreter.parse("42");
        assertEquals(stack.getFirst(), new Complex(42, 0));

        interpreter.parse("42j");
        assertEquals(stack.getFirst(), new Complex(0, 42));

        interpreter.parse("-4-2j");
        assertEquals(stack.getFirst(),new Complex(-4,-2));
        
        interpreter.parse("0");
        assertEquals(stack.getFirst(),new Complex(0,0));

        interpreter.parse("+0");
        assertEquals(stack.getFirst(),new Complex(0,0));

	interpreter.parse("-0");
        assertEquals(stack.getFirst(),new Complex(-0.0,0.0));

	interpreter.parse("0j");
	assertEquals(stack.getFirst(),new Complex(0,0));

	interpreter.parse(".2+2j");
        assertEquals(stack.getFirst(),new Complex(0.2,2));

	interpreter.parse("9+.2j");
        assertEquals(stack.getFirst(),new Complex(9,0.2));

	
	interpreter.parse("0.0001+3j");
        assertEquals(stack.getFirst(),new Complex(0.0001,3));
        
        interpreter.parse("1-0.00001j");
        assertEquals(stack.getFirst(),new Complex(1,-0.00001));

		
	interpreter.parse("-9999999-9999999j");
        assertEquals(stack.getFirst(),new Complex(-9999999,-9999999));

	interpreter.parse("1234567-1234567j");
        assertEquals(stack.getFirst(),new Complex(1234567,-1234567));
        
       // interpreter.parse("+j"); BUG TO FIX
        //interpreter.parse("-j"); BUG TO FIX
        //interpreter.parse("j");  BUG TO FIX
        //interpreter.parse("1+j"); BUG TO FIX
        //interpreter.parse("1-j"); BUG TO FIX
        
        
        
        
    }

    /**
     * @brief testParseInsertion1 method checks that if there is a wrong operand (jb is invalid, bj is valid)
     * or wrong operation then InterpterExcpetion is thrown
     * @throws InterpreterException
     * @throws ZeroDivisionException
     */
    @Test(expected = InterpreterException.class)
    public void testParseInsertion1() throws InterpreterException, ZeroDivisionException {

        interpreter.parse("j1");
    }

    /**
     * @brief the following methods verify that the interpreter recognizes and
     * separates the input to calculate the specified operation
     *
     * @throws InterpreterException
     * @throws ZeroDivisionException
     */
    @Test
    public void testParseSum() throws InterpreterException, ZeroDivisionException {
        interpreter.parse("1+1j 2+2j +");
        assertEquals(stack.getFirst(), new Complex(3, 3));

    }

    @Test
    public void testParseProduct() throws InterpreterException, ZeroDivisionException {
        interpreter.parse("1+1j 2+2j *");
        assertEquals(stack.getFirst(), new Complex(0, 4));

    }

    @Test
    public void testParseDivision() throws InterpreterException, ZeroDivisionException {
        interpreter.parse("1+1j 2+2j /");
        assertEquals(stack.getFirst(), new Complex(0.5, 0));
    }

    @Test
    public void testParseSubctration() throws InterpreterException, ZeroDivisionException {
        interpreter.parse("1+1j 2+2j -");
        assertEquals(stack.getFirst(), new Complex(-1, -1));
    }

    @Test
    public void testParseSquareRoot() throws InterpreterException, ZeroDivisionException {
        interpreter.parse("-4 sqrt");
        assertEquals(stack.getFirst(), new Complex(0, 2));
    }

    @Test
    public void testParseInvertSign() throws InterpreterException, ZeroDivisionException {
        interpreter.parse("1+1j +-");
        assertEquals(stack.getFirst(), new Complex(-1, -1));
    }

    @Test
    public void testParseClear() throws InterpreterException, ZeroDivisionException {
        interpreter.parse("1+1j clear");
        assertEquals(stack.size(), 0);
    }

    @Test
    public void testParseDup() throws InterpreterException, ZeroDivisionException {
        interpreter.parse("1+1j 2+2j dup");
        assertEquals(stack.getFirst(), new Complex(2, 2));
    }

    @Test
    public void testParseDrop() throws InterpreterException, ZeroDivisionException {
        interpreter.parse("1+1j 2+2j drop");
        assertEquals(stack.getFirst(), new Complex(1, 1));
    }

    @Test
    public void testParseOver() throws InterpreterException, ZeroDivisionException {
        interpreter.parse("1+1j 2+2j over");
        assertEquals(stack.getFirst(), new Complex(1, 1));
    }

    @Test
    public void testParseSwap() throws InterpreterException, ZeroDivisionException {
        interpreter.parse("1+1j 2+2j swap");
        assertEquals(stack.getFirst(), new Complex(1, 1));
    }

    /**
     * @brief testParseSequentiallyOperations method verifies that the
     * interpreter recognizes and separates the input to calculate the
     * operations sequentially
     * @throws InterpreterException
     * @throws ZeroDivisionException
     */
    @Test
    public void testParseSequentiallyOperations() throws InterpreterException, ZeroDivisionException {
        interpreter.parse("5 9 - sqrt");
        assertEquals(stack.getFirst(), new Complex(0, 2));
    }

    /**
     * @brief testParseAssignToVar method verifies that the interptreter
     * recognizes that it has to call assignToVar method of the class Variables
     * To test this operation the content of the variable is pushed onto the
     * stack
     * @throws InterpreterException
     * @throws ZeroDivisionException
     */
    @Test
    public void testParseAssignToVar() throws InterpreterException, ZeroDivisionException {
        interpreter.parse("10+5j");
        interpreter.parse(">x");

        interpreter.parse("<x");

        assertEquals(stack.getFirst(), new Complex(10, 5));

    }

    /**
     * @brief testParseAssignToVar1 method verifies that an InterpreterException
     * is thrown if there is a wrong insertion of the operation by keyboard
     * @throws InterpreterException
     * @throws ZeroDivisionException
     */
    @Test(expected = InterpreterException.class)
    public void testParseAssignToVar1() throws InterpreterException, ZeroDivisionException {
        interpreter.parse(">xSE");

    }
    
    @Test(expected = InterpreterException.class)
    public void testParseAssignToVar2() throws InterpreterException, ZeroDivisionException {
        interpreter.parse("SE>x");
    }

    /**
     * @brief testParseSumToVar method verifies that if there is "+var"
     * operation in the text field, then the interpter calls sumToVar method of
     * the class Variable
     * @throws InterpreterException
     * @throws ZeroDivisionException
     */
    @Test
    public void testParseSumToVar() throws InterpreterException, ZeroDivisionException {
        interpreter.parse("1+1j 2+2j");
        interpreter.parse(">x"); //x->2+2j
        interpreter.parse("+x");
        interpreter.parse("<x");

        assertEquals(stack.getFirst(), new Complex(3, 3));

    }
    
    /**
     * @brief the following two methods verify that with a wrong insertion an InterpreterException is thrown
     * @throws InterpreterException
     * @throws ZeroDivisionException 
     */
    @Test(expected = InterpreterException.class)
    public void testParseSumToVar1() throws InterpreterException, ZeroDivisionException {
        interpreter.parse("+xSE");
    }
    
    @Test(expected = InterpreterException.class)
    public void testParseSumToVar2() throws InterpreterException, ZeroDivisionException {
        interpreter.parse("SE+x");
    }

    /**
     * @brief testParseSubctractionToVar method verifies that if there is "-var"
     * operation in the text field, then the interpter calls subctractionToVar
     * method of the class Variable
     * @throws InterpreterException
     * @throws ZeroDivisionException
     */
    @Test
    public void testParseSubctractionToVar() throws InterpreterException, ZeroDivisionException {
        interpreter.parse("2+2j 1+1j");
        interpreter.parse(">x"); //x->1+1j
        interpreter.parse("-x");
        interpreter.parse("<x");

        assertEquals(stack.getFirst(), new Complex(-1, -1));

    }
    /**
     * @brief the following two methods verify that with a wrong insertion an InterpreterException is thrown
     * @throws InterpreterException
     * @throws ZeroDivisionException 
     */
    @Test(expected = InterpreterException.class)
    public void testParseSubctractionToVar1() throws InterpreterException, ZeroDivisionException {
        interpreter.parse("-xSE");

    }
    
    @Test(expected = InterpreterException.class)
    public void testParseSubctractionToVar2() throws InterpreterException, ZeroDivisionException {
        interpreter.parse("SE-x");

    }

    /**
     * @brief testCheckBaseOp method verifies that all the basic operations are allowed 
     * and check if the wrong basic operations aren't allowed
     */
    @Test
    public void testCheckBaseOp() {

        assertEquals(interpreter.check("0"), true);
        assertEquals(interpreter.check("0j"), true);
        assertEquals(interpreter.check(".2"), true);
        assertEquals(interpreter.check(".2j"), true);
        assertEquals(interpreter.check("2"), true);
        assertEquals(interpreter.check("2j"), true);
        assertEquals(interpreter.check("+2j"), true);
        assertEquals(interpreter.check("-2j"), true);
        assertEquals(interpreter.check("+2"), true);
        assertEquals(interpreter.check("-2.1"), true);
        //assertEquals(interpreter.check("2+j"), true); BUG
        //assertEquals(interpreter.check("2-j"), true); BUG
        //assertEquals(interpreter.check("+j"), true); BUG
        //assertEquals(interpreter.check("-j"), true); BUG
        //assertEquals(interpreter.check("j"), true); BUG
        assertEquals(interpreter.check("2.2-1j"), true);
        assertEquals(interpreter.check("2.2+2.1j"), true);
        assertEquals(interpreter.check("+"), true);
        assertEquals(interpreter.check("-"), true);
        assertEquals(interpreter.check("/"), true);
        assertEquals(interpreter.check("*"), true);
        assertEquals(interpreter.check("+-"), true);
        assertEquals(interpreter.check("sqrt"), true);
        assertEquals(interpreter.check("clear"), true);
        assertEquals(interpreter.check("dup"), true);
        assertEquals(interpreter.check("drop"), true);
        assertEquals(interpreter.check("over"), true);
        assertEquals(interpreter.check("swap"), true);
        assertEquals(interpreter.check("/s"), false);
        assertEquals(interpreter.check("*s"), false);
        assertEquals(interpreter.check("+-d"), false);
        assertEquals(interpreter.check("sqrtc"), false);
        assertEquals(interpreter.check("clears"), false);
        assertEquals(interpreter.check("sdup"), false);
        assertEquals(interpreter.check("dropd"), false);
        assertEquals(interpreter.check("overd"), false);
        assertEquals(interpreter.check("swapd"), false);

    }

    /**
     * @brief testCheckVar method verifies that all the variables operations are allowed 
     * and check if the wrong variables operations aren't allowed
     */
    @Test
    public void testCheckVar() {
        assertEquals(interpreter.check(">"), false);
        assertEquals(interpreter.check(">a"), true);
        assertEquals(interpreter.check(">ab"), false);
        assertEquals(interpreter.check("<"), false);
        assertEquals(interpreter.check("<a"), true);
        assertEquals(interpreter.check("<ab"), false);
        assertEquals(interpreter.check("+a"), true);
        assertEquals(interpreter.check("+ab"), false);
        assertEquals(interpreter.check("-a"), true);
        assertEquals(interpreter.check("-ab"), false);

    }

    /**
     * @brief testCheckUserOp method verifies that all the UserDefined operations are allowed 
     * and check if the wrong UserDefined operations aren't allowed
     */
    @Test
    public void testCheckUserOp() {
        assertEquals(interpreter.check("op1"), false);
        assertEquals(interpreter.check("op2"), false);
        userOperations.newOperation("op1", "+ - /");
        assertEquals(interpreter.check("op1"), true);
        assertEquals(interpreter.check("op2"), false);
        userOperations.newOperation("op2", "+ + /");
        assertEquals(interpreter.check("op1"), true);
        assertEquals(interpreter.check("op2"), true);
        
        
    }

    /**
     * @brief testCheckSequence method verifies that correct sequence of operations are allowed 
     * and check if the wrong sequence of operations aren't allowed
     */
    @Test
    public void testCheckSequence() {
        assertEquals(interpreter.check("+ -"), true);
        assertEquals(interpreter.check("+ - / * +- sqrt"), true);
        assertEquals(interpreter.check("+ - / * +- sqrt   "), true);
        assertEquals(interpreter.check("   + - / * +- sqrt"), true);
        assertEquals(interpreter.check("+  - / * +-  sqrt"), true);
        assertEquals(interpreter.check("+ - / * +- sqrt <a"), true);
        assertEquals(interpreter.check("+ >a / * +- sqrt"), true);
        assertEquals(interpreter.check("+ > a / * +- sqrt"), false);
        assertEquals(interpreter.check("+ <a / * +- sqrt"), true);
        assertEquals(interpreter.check("+ < a / * +- sqrt"), false);
        userOperations.newOperation("op1", "+ - /");
        assertEquals(interpreter.check("+c <a op1"), true);
        assertEquals(interpreter.check("+c <a op3"), false);
        assertEquals(interpreter.check("+c op1 op3"), false);
        assertEquals(interpreter.check("2+5j <a / * +- sqrt"), true);
        assertEquals(interpreter.check("2+.1j <a / * +- sqrt"), true);
        assertEquals(interpreter.check("2+0.1j <a / * +- sqrt"), true);
        assertEquals(interpreter.check("0+0.1j <a / * +- sqrt"), true);
        assertEquals(interpreter.check("0j <a / * +- sqrt"), true);
        assertEquals(interpreter.check("0 <a / * +- sqrt"), true);
        assertEquals(interpreter.check("+0.1-5j <a / * +- sqrt"), true);
        assertEquals(interpreter.check("+0.1 <a / * +- sqrt"), true);
        assertEquals(interpreter.check("-0.1 <a / * +- sqrt"), true);
        
        //assertEquals(interpreter.check("j <a / * +- sqrt"), true); BUG   
        //assertEquals(interpreter.check("+j <a / * +- sqrt"), true); BUG  
        //assertEquals(interpreter.check("-j <a / * +- sqrt"), true); BUG
        //assertEquals(interpreter.check("2-j <a / * +- sqrt"), true); BUG  
        //assertEquals(interpreter.check("2+j <a / * +- sqrt"), true); BUG  
    }

}
