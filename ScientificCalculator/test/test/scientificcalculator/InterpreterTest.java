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
     * setUp method is used to create a fixture
     */
    @Before
    public void setUp() {
        stack = new LinkedList<>();
        userOperations = new UserDefinedOperation();
        interpreter = new Interpreter(stack, userOperations);

    }

    /**
     * testParseInsertion method verifies that a complex number inserted in the
     * text field is inserted onto the stack It calls InterpreterException It
     * calls ZeroDivisionException
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
     * recognized as variables and not as imaginary part It calls
     * InterpreterException It calls ZeroDivisionException
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
     * testParseSaveAndRestore method verifies that the interpreter calls
     * correctly save and restore methods It calls InterpreterException It calls
     * ZeroDivisionException
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
     * testParseInsertion1 method checks that if there is a wrong operand (jb is
     * invalid, bj is valid) or wrong operation then InterpterExcpetion is
     * thrown It calls InterpreterException It calls ZeroDivisionException
     */
    @Test(expected = InterpreterException.class)
    public void testParseInsertion1() {

        interpreter.parse("j1");
    }

    /**
     * the following methods verify that the interpreter recognizes and
     * separates the input to calculate the specified operation
     *
     * It calls InterpreterException It calls ZeroDivisionException
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
     * testParseSequentiallyOperations method verifies that the interpreter
     * recognizes and separates the input to calculate the operations
     * sequentially It calls InterpreterException It calls ZeroDivisionException
     */
    @Test
    public void testParseSequentiallyOperations() {
        interpreter.parse("5 9 - sqrt");
        assertEquals(new Complex(0, 2), stack.getFirst());
    }

    /**
     * testParseAssignToVar method verifies that the interptreter recognizes
     * that it has to call assignToVar method of the class Variables To test
     * this operation the content of the variable is pushed onto the stack It
     * calls InterpreterException It calls ZeroDivisionException
     */
    @Test
    public void testParseAssignToVar() {
        interpreter.parse("10+5j");
        interpreter.parse(">x");

        interpreter.parse("<x");

        assertEquals(new Complex(10, 5), stack.getFirst());

    }

    /**
     * testParseAssignToVar1 method verifies that an InterpreterException is
     * thrown if there is a wrong insertion of the operation by keyboard It
     * calls InterpreterException It calls ZeroDivisionException
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
     * testParseSumToVar method verifies that if there is "+var" operation in
     * the text field, then the interpter calls sumToVar method of the class
     * Variable It calls InterpreterException It calls ZeroDivisionException
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
     * the following two methods verify that with a wrong insertion an
     * InterpreterException is thrown It calls InterpreterException It calls
     * ZeroDivisionException
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
     * testParseSubctractionToVar method verifies that if there is "-var"
     * operation in the text field, then the interpter calls subctractionToVar
     * method of the class Variable It calls InterpreterException It calls
     * ZeroDivisionException
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
     * the following two methods verify that with a wrong insertion an
     * InterpreterException is thrown It calls InterpreterException It calls
     * ZeroDivisionException
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
     * testParseUserDef method verifies that the interpreter recognizes user
     * defined operations It calls InterpreterException It calls
     * ZeroDivisionException
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
     * the following two methods verify that the interpreter doesn't recognize
     * user defined operations that don't exist It calls InterpreterException It
     * calls ZeroDivisionException
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
     * testCheckBaseOp method verifies that all the basic operations are allowed
     * and check if the wrong basic operations aren't allowed
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

    }

    /**
     * testCheckVar method verifies that all the variables operations are
     * allowed and check if the wrong variables operations aren't allowed
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
     * testCheckUserOp method verifies that all the UserDefined operations are
     * allowed and check if the wrong UserDefined operations aren't allowed
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
     * testCheckSequence method verifies that correct sequence of operations are
     * allowed and check if the wrong sequence of operations aren't allowed
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

}
