package scientificcalculator;

import java.util.Set;

/**
 * This interface is used to read-only the user defined operations
 * 
 * @author Group 10
 */
public interface UserDefinedOperationReadOnly {
    
    /**
     * The getNameOperations method returns a set containing all the keys
     *
     * @return Set<String>
     */
    public Set<String> getNameOperations();

    
    /**
     * The getSequence method returns the value related to the key or null if the
     * key doesn't exist
     *
     * @param op is key
     * @return String
     */
    public String getSequence(String op);
}
