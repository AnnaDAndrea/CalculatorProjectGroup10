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
import org.apache.commons.math3.exception.NullArgumentException;

/**
 * This is a tester class to test the behavior of the class Interpreter. It
 * tests if the interpreter works correctly
 *
 * @author Group 10
 *
 */
public class InterpreterTest {

    private Interpreter interpreter;
    private Deque<Complex> stack;
    private UserDefinedOperation userOperations;

    public InterpreterTest() {
    }

    /**
     * The setUp method is used to create a fixture
     */
    @Before
    public void setUp() {
        stack = new LinkedList<>();
        userOperations = new UserDefinedOperation();
        interpreter = new Interpreter(stack, userOperations);

    }

    /**
     * The testParseInsertion method verifies if a complex number inserted in the
     * text field is inserted onto the stack.
     */
    @Test
    public void testParseInsertion() {

        interpreter.parse("01");
        assertEquals(new Complex(1, 0), stack.getFirst());

        interpreter.parse("-01");
        assertEquals(new Complex(-1, 0), stack.getFirst());

        interpreter.parse("-0j");
        assertEquals(new Complex(0.0, -0.0), stack.getFirst());

        interpreter.parse("-03j");
        assertEquals(new Complex(0.0, -3.0), stack.getFirst());

        interpreter.parse("+0j");
        assertEquals(new Complex(0.0, 0.0), stack.getFirst());

        interpreter.parse("03j");
        assertEquals(new Complex(0.0, 3.0), stack.getFirst());

        interpreter.parse("1+5j");
        assertEquals(new Complex(1, 5), stack.getFirst());

        interpreter.parse("42");
        assertEquals(new Complex(42, 0), stack.getFirst());

        interpreter.parse("42j");
        assertEquals(new Complex(0, 42), stack.getFirst());

        interpreter.parse("-4-2j");
        assertEquals(new Complex(-4, -2), stack.getFirst());

        interpreter.parse("0");
        assertEquals(new Complex(0, 0), stack.getFirst());

        interpreter.parse("+0");
        assertEquals(new Complex(0, 0), stack.getFirst());

        interpreter.parse("-0");
        assertEquals(new Complex(-0.0, 0.0), stack.getFirst());

        interpreter.parse("0j");
        assertEquals(new Complex(0, 0), stack.getFirst());

        interpreter.parse(".2+2j");
        assertEquals(new Complex(0.2, 2), stack.getFirst());

        interpreter.parse("9+.2j");
        assertEquals(new Complex(9, 0.2), stack.getFirst());

        interpreter.parse("0.0001+3j");
        assertEquals(new Complex(0.0001, 3), stack.getFirst());

        interpreter.parse("1-0.00001j");
        assertEquals(new Complex(1, -0.00001), stack.getFirst());

        interpreter.parse("-9999999-9999999j");
        assertEquals(new Complex(-9999999, -9999999), stack.getFirst());

        interpreter.parse("1234567-1234567j");
        assertEquals(new Complex(1234567, -1234567), stack.getFirst());

        interpreter.parse("j");
        assertEquals(new Complex(0, 1), stack.getFirst());

        interpreter.parse("1+j");
        assertEquals(new Complex(1, 1), stack.getFirst());

        interpreter.parse("1-j");
        assertEquals(new Complex(1, -1), stack.getFirst());

    }

    /**
     * The following testParseOnlyJ methods verify that "+j" and "-j" are
     * recognized as variables and not as imaginary part.
     */
    @Test(expected = NullArgumentException.class)
    public void testParseOnlyJ() {
        interpreter.parse("+j");

    }

    @Test(expected = NullArgumentException.class)
    public void testParseOnlyJ1() {
        interpreter.parse("-j");

    }

    /**
     * The testParseSaveAndRestore method verifies that the interpreter calls
     * correctly the save and restore methods. 
     */
    @Test
    public void testParseSaveAndRestore() {
        interpreter.parse("1+1j 2+2j 3+3j");
        interpreter.parse(">a >b >c");
        interpreter.parse("save");

        interpreter.parse("4+4j 5+5j 6+6j");
        interpreter.parse("+a +b +c");
        interpreter.parse("restore");

        interpreter.parse("<a <b <c");
        assertEquals(new Complex(3, 3), stack.removeLast());
        assertEquals(new Complex(2, 2), stack.removeLast());
        assertEquals(new Complex(1, 1), stack.removeLast());
    }

    /**
     * The testParseInsertion1 method checks if there is a wrong operand (jb is
     * invalid, bj is valid) or a wrong operation calling the InterpreterException
     */
    @Test(expected = InterpreterException.class)
    public void testParseInsertion1() {

        interpreter.parse("j1");
    }

    /**
     * The following methods verify if the interpreter recognizes and
     * separates the input to calculate the specified operation
     */
    @Test
    public void testParseSum() {
        interpreter.parse("1+1j 2+2j +");
        assertEquals(new Complex(3, 3), stack.getFirst());

    }

    @Test
    public void testParseProduct() {
        interpreter.parse("1+1j 2+2j *");
        assertEquals(new Complex(0, 4), stack.getFirst());

    }

    @Test
    public void testParseDivision() {
        interpreter.parse("1+1j 2+2j /");
        assertEquals(new Complex(0.5, 0), stack.getFirst());
    }

    @Test
    public void testParseSubctration() {
        interpreter.parse("1+1j 2+2j -");
        assertEquals(new Complex(-1, -1), stack.getFirst());
    }

    @Test
    public void testParseSquareRoot() {
        interpreter.parse("-4 sqrt");
        assertEquals(new Complex(0, 2), stack.getFirst());
    }

    @Test
    public void testParseInvertSign() {
        interpreter.parse("1+1j +-");
        assertEquals(new Complex(-1, -1), stack.getFirst());
    }

    @Test
    public void testParseClear() {
        interpreter.parse("1+1j clear");
        assertEquals(0, stack.size());
    }

    @Test
    public void testParseDup() {
        interpreter.parse("1+1j 2+2j dup");
        assertEquals(new Complex(2, 2), stack.getFirst());
    }

    @Test
    public void testParseDrop() {
        interpreter.parse("1+1j 2+2j drop");
        assertEquals(new Complex(1, 1), stack.getFirst());
    }

    @Test
    public void testParseOver() {
        interpreter.parse("1+1j 2+2j over");
        assertEquals(new Complex(1, 1), stack.getFirst());
    }

    @Test
    public void testParseSwap() {
        interpreter.parse("1+1j 2+2j swap");
        assertEquals(new Complex(1, 1), stack.getFirst());
    }

    /**
     * The testParseSequentiallyOperations method verifies if the interpreter
     * recognizes and separates the input to calculate the operations
     * sequentially
     */
    @Test
    public void testParseSequentiallyOperations() {
        interpreter.parse("5 9 - sqrt");
        assertEquals(new Complex(0, 2), stack.getFirst());
    }

    /**
     * The testParseAssignToVar method verifies if the interpreter recognizes
     * that it has to call the assignToVar method of the class Variables. To test
     * this operation, the content of the variable is pushed onto the stack 
     */
    @Test
    public void testParseAssignToVar() {
        interpreter.parse("10+5j");
        interpreter.parse(">x");

        interpreter.parse("<x");

        assertEquals(new Complex(10, 5), stack.getFirst());

    }

    /**
     * The testParseAssignToVar1 method verifies that an InterpreterException is
     * thrown if there is a wrong insertion of the operation by keyboard 
     */
    @Test(expected = InterpreterException.class)
    public void testParseAssignToVar1() {
        interpreter.parse(">xSE");

    }

    @Test(expected = InterpreterException.class)
    public void testParseAssignToVar2() {
        interpreter.parse("SE>x");
    }

    /**
     * The testParseSumToVar method verifies if there is "+var" operation in
     * the text field, then the interpter calls the sumToVar method of the class
     * Variable
     */
    @Test
    public void testParseSumToVar() {
        interpreter.parse("1+1j 2+2j");
        interpreter.parse(">x"); //x->2+2j
        interpreter.parse("+x");
        interpreter.parse("<x");

        assertEquals(new Complex(3, 3), stack.getFirst());

    }

    /**
     * The following two methods verify that with a wrong insertion an
     * InterpreterException is thrown 
     */
    @Test(expected = InterpreterException.class)
    public void testParseSumToVar1() {
        interpreter.parse("+xSE");
    }

    @Test(expected = InterpreterException.class)
    public void testParseSumToVar2() {
        interpreter.parse("SE+x");
    }

    /**
     * The testParseSubctractionToVar method verifies if there is "-var"
     * operation in the text field, then the interpreter calls the subctractionToVar
     * method of the class Variable 
     */
    @Test
    public void testParseSubctractionToVar() {
        interpreter.parse("2+2j 1+1j");
        interpreter.parse(">x"); //x->1+1j
        interpreter.parse("-x");
        interpreter.parse("<x");

        assertEquals(new Complex(-1, -1), stack.getFirst());

    }

    /**
     * The following two methods verify that with a wrong insertion an
     * InterpreterException is thrown
     */
    @Test(expected = InterpreterException.class)
    public void testParseSubctractionToVar1() {
        interpreter.parse("-xSE");

    }

    @Test(expected = InterpreterException.class)
    public void testParseSubctractionToVar2() {
        interpreter.parse("SE-x");

    }

    /**
     * The testParseUserDef method verifies if the interpreter recognizes the user
     * defined operations 
     */
    @Test
    public void testParseUserDef() {
        interpreter.parse("2 4 5 6");
        userOperations.newOperation("tripleSum", "+ + +");

        interpreter.parse("tripleSum");

        assertEquals(stack.size(), 1);
        assertEquals(new Complex(17), stack.removeFirst());

        //empty stack
        interpreter.parse("2 4 5 6 7");

        interpreter.parse("- tripleSum +-");

        assertEquals(stack.size(), 1);
        assertEquals(new Complex(-10, -0.0), stack.removeFirst());

        //empty stack
        userOperations.newOperation("quadSum", "+ tripleSum");
        interpreter.parse("2 4 5 6 7");

        interpreter.parse("quadSum +-");

        assertEquals(stack.size(), 1);
        assertEquals(new Complex(-24, -0.0), stack.removeFirst());

        //empty stack
    }

    /**
     * The following two methods verify that the interpreter doesn't recognize
     * the user-defined operations that don't exist. 
     * It calls the InterpreterException 
     */
    @Test(expected = InterpreterException.class)
    public void testParseUserDefExc() {
        interpreter.parse("tripleSum");
    }

    @Test(expected = InterpreterException.class)
    public void testParseUserDefExc1() {
        interpreter.parse("2 4");
        interpreter.parse(" + tripleSum +-");
    }

    /**
     * The testCheckBaseOp method verifies that all the basic operations are allowed
     * and it checks if the wrong basic operations aren't allowed
     */
    @Test
    public void testCheckBaseOp() {

        assertEquals(true, interpreter.check("0"));
        assertEquals(true, interpreter.check("0j"));
        assertEquals(true, interpreter.check(".2"));
        assertEquals(true, interpreter.check(".2j"));
        assertEquals(true, interpreter.check("2"));
        assertEquals(true, interpreter.check("2j"));
        assertEquals(true, interpreter.check("+2j"));
        assertEquals(true, interpreter.check("-2j"));
        assertEquals(true, interpreter.check("+2"));
        assertEquals(true, interpreter.check("-2.1"));
        assertEquals(true, interpreter.check("2+j"));
        assertEquals(true, interpreter.check("2-j"));
        assertEquals(true, interpreter.check("+j"));
        assertEquals(true, interpreter.check("-j"));
        assertEquals(true, interpreter.check("j"));
        assertEquals(true, interpreter.check("2.2-1j"));
        assertEquals(true, interpreter.check("2.2+2.1j"));
        assertEquals(true, interpreter.check("+"));
        assertEquals(true, interpreter.check("-"));
        assertEquals(true, interpreter.check("/"));
        assertEquals(true, interpreter.check("*"));
        assertEquals(true, interpreter.check("+-"));
        assertEquals(true, interpreter.check("mod"));
        assertEquals(true, interpreter.check("sqrt"));
        assertEquals(true, interpreter.check("clear"));
        assertEquals(true, interpreter.check("dup"));
        assertEquals(true, interpreter.check("drop"));
        assertEquals(true, interpreter.check("over"));
        assertEquals(true, interpreter.check("swap"));

        assertEquals(false, interpreter.check("/s"));
        assertEquals(false, interpreter.check("*s"));
        assertEquals(false, interpreter.check("+-d"));
        assertEquals(false, interpreter.check("sqrtc"));
        assertEquals(false, interpreter.check("clears"));
        assertEquals(false, interpreter.check("sdup"));
        assertEquals(false, interpreter.check("dropd"));
        assertEquals(false, interpreter.check("overd"));
        assertEquals(false, interpreter.check("swapd"));
        assertEquals(false, interpreter.check("abs"));
        assertEquals(false, interpreter.check("mods"));
        assertEquals(false, interpreter.check("modulus"));

    }

    /**
     * The testCheckVar method verifies that all the variables operations are
     * allowed and it checks if the wrong variables operations aren't allowed
     */
    @Test
    public void testCheckVar() {
        assertEquals(false, interpreter.check(">"));
        assertEquals(true, interpreter.check(">a"));
        assertEquals(false, interpreter.check(">ab"));
        assertEquals(false, interpreter.check("<"));
        assertEquals(true, interpreter.check("<a"));
        assertEquals(false, interpreter.check("<ab"));
        assertEquals(true, interpreter.check("+a"));
        assertEquals(false, interpreter.check("+ab"));
        assertEquals(true, interpreter.check("-a"));
        assertEquals(false, interpreter.check("-ab"));

    }

    /**
     * The testCheckUserOp method verifies that all the User-defined operations are
     * allowed and it checks if the wrong User-defined operations aren't allowed
     */
    @Test
    public void testCheckUserOp() {
        assertEquals(false, interpreter.check("op1"));
        assertEquals(false, interpreter.check("op2"));
        userOperations.newOperation("op1", "+ - /");
        assertEquals(true, interpreter.check("op1"));
        assertEquals(false, interpreter.check("op2"));
        userOperations.newOperation("op2", "+ + /");
        assertEquals(true, interpreter.check("op1"));
        assertEquals(true, interpreter.check("op2"));

    }

    /**
     * The testCheckSequence method verifies that correct sequence of operations are
     * allowed and it checks if the wrong sequence of operations aren't allowed
     */
    @Test
    public void testCheckSequence() {
        assertEquals(false, interpreter.check(" "));
        assertEquals(false, interpreter.check("     "));
        assertEquals(true, interpreter.check("+ -"));
        assertEquals(true, interpreter.check("+ - / * +- sqrt"));
        assertEquals(true, interpreter.check("+ - / * +- sqrt   "));
        assertEquals(true, interpreter.check("   + - / * +- sqrt"));
        assertEquals(true, interpreter.check("+  - / * +-  sqrt"));
        assertEquals(true, interpreter.check("+ - / * +- sqrt <a"));
        assertEquals(true, interpreter.check("+ >a / * +- sqrt"));
        assertEquals(false, interpreter.check("+ > a / * +- sqrt"));
        assertEquals(true, interpreter.check("+ <a / * +- sqrt"));
        assertEquals(false, interpreter.check("+ < a / * +- sqrt"));
        userOperations.newOperation("op1", "+ - /");
        assertEquals(true, interpreter.check("+c <a op1"));
        assertEquals(false, interpreter.check("+c <a op3"));
        assertEquals(false, interpreter.check("+c op1 op3"));
        assertEquals(true, interpreter.check("2+5j <a / * +- sqrt"));
        assertEquals(true, interpreter.check("2+.1j <a / * +- sqrt"));
        assertEquals(true, interpreter.check("2+0.1j <a / * +- sqrt"));
        assertEquals(true, interpreter.check("0+0.1j <a / * +- sqrt"));
        assertEquals(true, interpreter.check("0j <a / * +- sqrt"));
        assertEquals(true, interpreter.check("0 <a / * +- sqrt"));
        assertEquals(true, interpreter.check("+0.1-5j <a / * +- sqrt"));
        assertEquals(true, interpreter.check("+0.1 <a / * +- sqrt"));
        assertEquals(true, interpreter.check("-0.1 <a / * +- sqrt"));

        assertEquals(true, interpreter.check("j <a / * +- sqrt"));
        assertEquals(true, interpreter.check("+j <a / * +- sqrt"));
        assertEquals(true, interpreter.check("-j <a / * +- sqrt"));
        assertEquals(true, interpreter.check("2-j <a / * +- sqrt"));
        assertEquals(true, interpreter.check("2+j <a / * +- sqrt"));
    }
    
    @Test
    public void testParseMod() {
        interpreter.parse("4+0j mod");
        assertEquals(new Complex(4, 0), stack.getFirst());
    }

}
