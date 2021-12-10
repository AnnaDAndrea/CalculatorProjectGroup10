package test.scientificcalculator;

import exception.KeyAlreadyExistException;
import exception.LoopException;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import scientificcalculator.UserDefinedOperation;

/**
 * UserDefinedOperationTest class is used to test UserDefinedOperation class
 *
 * @author Group 10
 */
public class UserDefinedOperationTest {

    private UserDefinedOperation userDefinedOperation;

    public UserDefinedOperationTest() {
    }

    @Before
    public void setUp() {
        userDefinedOperation = new UserDefinedOperation();
    }

    /**
     * testNewOperation method tests that the insertion of an operation with a
     * key already existing throws a KeyAlreadyExistException
     */
    @Test(expected = KeyAlreadyExistException.class)
    public void testNewOperation() {
        userDefinedOperation.newOperation("op1", "2j 4j drop");
        userDefinedOperation.newOperation("op1", "2+2j 4+4j swap");
    }

    /**
     * testNewOperation1 method tests the insertion of a new operation
     */
    @Test
    public void testNewOperation1() {
        userDefinedOperation.newOperation("op2", "3+2j 4+4j swap");
        userDefinedOperation.newOperation("op3", "2j 3j over");

        assertEquals("3+2j 4+4j swap", userDefinedOperation.getSequence("op2"));

    }

    /**
     * testDelete method tests the deletion of an operation
     */
    @Test
    public void testDelete() {
        assertNull(userDefinedOperation.delete("op1"));

        userDefinedOperation.newOperation("op1", "1+2j 1-2j +");
        userDefinedOperation.newOperation("op2", "1-2j 1-2j -");

        assertEquals("1+2j 1-2j +", userDefinedOperation.delete("op1"));
        assertNull(userDefinedOperation.delete("op1"));
    }

    /**
     * testSearchDependencies method verifies that all dependencies of a
     * UserDefined Operation are found
     */
    @Test
    public void testSearchDependencies() {
        Set<String> dep;

        dep = userDefinedOperation.searchDependencies("op1");
        assertEquals(0, dep.size());

        userDefinedOperation.newOperation("op1", "1 2 swap");
        dep = userDefinedOperation.searchDependencies("op1");
        assertEquals(0, dep.size());

        userDefinedOperation.newOperation("op2", "1 2 op1");
        dep = userDefinedOperation.searchDependencies("op1");
        assertEquals(1, dep.size());
        assertTrue(dep.contains("op2"));

        dep = userDefinedOperation.searchDependencies("op2");
        assertEquals(0, dep.size());

        userDefinedOperation.newOperation("op3", "1 2 op1");
        dep = userDefinedOperation.searchDependencies("op1");
        assertEquals(2, dep.size());
        assertTrue(dep.contains("op2"));
        assertTrue(dep.contains("op3"));

        userDefinedOperation.newOperation("op4", "op1 1 2 op2");
        dep = userDefinedOperation.searchDependencies("op1");
        assertEquals(3, dep.size());
        assertTrue(dep.contains("op2"));
        assertTrue(dep.contains("op3"));
        assertTrue(dep.contains("op4"));
        dep = userDefinedOperation.searchDependencies("op2");
        assertEquals(1, dep.size());
        assertTrue(dep.contains("op4"));

    }

    /**
     * testDeleteAllDependencies method verifies that a user defined operation
     * and all its dependences are removed
     */
    @Test
    public void testDeleteAllDependencies() {

        assertEquals("", userDefinedOperation.deleteAllDependencies("op1"));

        userDefinedOperation.newOperation("op1", "1 2 swap");
        userDefinedOperation.newOperation("op2", "1 2 op1");
        userDefinedOperation.newOperation("op3", "3 6 op1");
        userDefinedOperation.newOperation("op4", "6 7 op2");
        userDefinedOperation.newOperation("triplSum", "+ + +");
        userDefinedOperation.newOperation("quadSum", "+ triplSum");
        userDefinedOperation.newOperation("quintSum", "triplSum + +");
        userDefinedOperation.newOperation("op6", "1+2j 1+2j +");
        userDefinedOperation.newOperation("op7", "1+2j 1+2j *");

        userDefinedOperation.deleteAllDependencies("op1");
        assertNull(userDefinedOperation.getSequence("op1"));
        assertNull(userDefinedOperation.getSequence("op2"));
        assertNull(userDefinedOperation.getSequence("op3"));
        assertNull(userDefinedOperation.getSequence("op4"));
        assertEquals("1+2j 1+2j +", userDefinedOperation.getSequence("op6"));
        assertEquals("1+2j 1+2j *", userDefinedOperation.getSequence("op7"));
        assertEquals("+ + +", userDefinedOperation.getSequence("triplSum"));
        assertEquals("+ triplSum", userDefinedOperation.getSequence("quadSum"));
        assertEquals("triplSum + +", userDefinedOperation.getSequence("quintSum"));

        userDefinedOperation.deleteAllDependencies("quadSum");
        assertNull(userDefinedOperation.getSequence("quadSum"));
        assertEquals("1+2j 1+2j +", userDefinedOperation.getSequence("op6"));
        assertEquals("1+2j 1+2j *", userDefinedOperation.getSequence("op7"));
        assertEquals("+ + +", userDefinedOperation.getSequence("triplSum"));
        assertEquals("triplSum + +", userDefinedOperation.getSequence("quintSum"));

        userDefinedOperation.deleteAllDependencies("triplSum");
        assertEquals("1+2j 1+2j +", userDefinedOperation.getSequence("op6"));
        assertEquals("1+2j 1+2j *", userDefinedOperation.getSequence("op7"));
        assertNull(userDefinedOperation.getSequence("triplSum"));
        assertNull(userDefinedOperation.getSequence("quintSum"));

        userDefinedOperation.deleteAllDependencies("op6");
        assertNull(userDefinedOperation.getSequence("op6"));
        assertEquals("1+2j 1+2j *", userDefinedOperation.getSequence("op7"));

        userDefinedOperation.deleteAllDependencies("op10");
        assertNull(userDefinedOperation.getSequence("op10"));
        assertEquals("1+2j 1+2j *", userDefinedOperation.getSequence("op7"));

    }

    /**
     * testEditSequence1 method verifies the correct work of the editSequence
     * method
     */
    @Test
    public void testEditSequence1() {
        userDefinedOperation.newOperation("op1", "1 2 swap");
        userDefinedOperation.newOperation("op2", "op1");

        userDefinedOperation.editSequence("op1", "1 2");

        assertEquals("1 2", userDefinedOperation.getSequence("op1"));
        assertEquals("op1", userDefinedOperation.getSequence("op2"));

        userDefinedOperation.newOperation("op3", "1 2 op1 swap op2");
        userDefinedOperation.editSequence("op3", "1 2 op1 op1 op2 swap");

        assertEquals("1 2", userDefinedOperation.getSequence("op1"));
        assertEquals("op1", userDefinedOperation.getSequence("op2"));
        assertEquals("1 2 op1 op1 op2 swap", userDefinedOperation.getSequence("op3"));

        userDefinedOperation.editSequence("op4", "1 2 op1");

        assertNull(userDefinedOperation.getSequence("op4"));

    }

    /**
     * testEditSequence2 method verifies that the editSequence throw
     * LoopException correctly
     */
    @Test(expected = LoopException.class)
    public void testEditSequence2() {
        userDefinedOperation.newOperation("op1", "1 2 swap");
        userDefinedOperation.newOperation("op2", "op1");

        userDefinedOperation.editSequence("op1", "1 2 op2");

    }

    /**
     * testEditSequence3 method verifies that the editSequence throw
     * LoopException correctly
     */
    @Test(expected = LoopException.class)
    public void testEditSequence3() {
        userDefinedOperation.newOperation("op1", "1 2 swap");
        userDefinedOperation.newOperation("op2", "1 op1");
        userDefinedOperation.newOperation("op3", "1 op2 op2");
        userDefinedOperation.newOperation("op4", "1 op3");

        userDefinedOperation.editSequence("op1", "1 2 op4");

    }

    /**
     * testEditSequence4 method verifies that the editSequence throw
     * LoopException correctly
     */
    @Test(expected = LoopException.class)
    public void testEditSequence4() {
        userDefinedOperation.newOperation("op1", "1 2 swap");
        userDefinedOperation.newOperation("op2", "1");
        userDefinedOperation.newOperation("op3", "1 op2 op2");
        userDefinedOperation.newOperation("op4", "1 op3");

        userDefinedOperation.editSequence("op2", "1 2 op1");

        assertEquals("1 2 op1", userDefinedOperation.getSequence("op2"));

        userDefinedOperation.editSequence("op1", "1 2");

        assertEquals("1 2", userDefinedOperation.getSequence("op1"));

        userDefinedOperation.editSequence("op1", "1 2 op4");

    }

    /**
     * testSaveAndReload method verifies the correct operation of saving and
     * loading a file, the user defined operations are saved in a file and then
     * reloaded
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testSaveAndReload() throws IOException, ClassNotFoundException {
        userDefinedOperation.newOperation("op1", "1 2 swap");
        userDefinedOperation.newOperation("op2", "1 1+2j over");

        File f = new File("test.bin");
        f.delete();
        assertFalse(f.isFile());

        userDefinedOperation.save("test.bin");

        assertTrue(f.isFile());

        userDefinedOperation.delete("op1");
        userDefinedOperation.delete("op2");

        userDefinedOperation.reload("test.bin");

        assertEquals("1 2 swap", userDefinedOperation.getSequence("op1"));
        assertEquals("1 1+2j over", userDefinedOperation.getSequence("op2"));

    }

    /**
     * testSaveAndReload1 method verifies the save operation when there aren't
     * user defined operations and then the save operation using the same file
     * previoulsy used
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testSaveAndReload1() throws IOException, ClassNotFoundException {

        File f = new File("test1.bin");
        f.delete();
        assertFalse(f.isFile());

        File f2 = new File("test2.bin");
        f2.delete();
        assertFalse(f2.isFile());

        userDefinedOperation.save("test1.bin");

        assertTrue(f.isFile());

        userDefinedOperation.newOperation("op1", "1 2 swap");
        userDefinedOperation.newOperation("op2", "1 1+2j over");
        userDefinedOperation.newOperation("op3", "1+3j >x");

        userDefinedOperation.save("test2.bin");
        assertTrue(f2.isFile());

        userDefinedOperation.reload("test1.bin");

        assertNull(userDefinedOperation.getSequence("op1"));
        assertNull(userDefinedOperation.getSequence("op2"));
        assertNull(userDefinedOperation.getSequence("op3"));

        userDefinedOperation.reload("test2.bin");

        assertEquals("1 2 swap", userDefinedOperation.getSequence("op1"));
        assertEquals("1 1+2j over", userDefinedOperation.getSequence("op2"));
        assertEquals("1+3j >x", userDefinedOperation.getSequence("op3"));

        userDefinedOperation.delete("op1");
        userDefinedOperation.delete("op2");
        userDefinedOperation.delete("op3");

        userDefinedOperation.newOperation("op4", "1 1 1 1");
        userDefinedOperation.newOperation("op5", "2j 2j *");
        userDefinedOperation.newOperation("op6", "-4j 4j /");

        userDefinedOperation.save("test2.bin");
        userDefinedOperation.reload("test2.bin");

        assertNull(userDefinedOperation.getSequence("op1"));
        assertNull(userDefinedOperation.getSequence("op2"));
        assertNull(userDefinedOperation.getSequence("op3"));

        assertEquals("1 1 1 1", userDefinedOperation.getSequence("op4"));
        assertEquals("2j 2j *", userDefinedOperation.getSequence("op5"));
        assertEquals("-4j 4j /", userDefinedOperation.getSequence("op6"));

    }

    /**
     * testReloadFail method verifies that the opening of a file.bin that
     * doesn't exists an IOExcpetion is thrown
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test(expected = IOException.class)
    public void testReloadFail() throws IOException, ClassNotFoundException {
        userDefinedOperation.reload("userDefTest.bin");
    }

}
