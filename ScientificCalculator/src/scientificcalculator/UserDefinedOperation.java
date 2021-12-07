package scientificcalculator;

import exception.KeyAlreadyExistException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *@brief this class implements the logic needed to maintain a data structure that stores user-defined operations and methods to manage them
 * 
 The Map data structure is a key-value map, where the key is the name of the user-defined and the value is the sequence representing the operation
 * @author Group 10
 */

public class UserDefinedOperation{
    private Map<String, String> userOperations;

    public UserDefinedOperation() {
        userOperations = new HashMap<>();
    }

/**
 * @brief getNameOperations method returns a set containing all the keys
 * @return Set<String>
 */
    public Set<String> getNameOperations() {
        return userOperations.keySet();
    }

/**
 * @brief getSequence method returns the value related to the key
 * @param op is key
 * @return 
 */
    public String getSequence(String op) {
        return userOperations.get(op);
    }
/**
 * @brief newOperation method adds a new couple key-value and if the key already exists throws a KeyAlreadyExistException
 * @param name
 * @param value 
 */
    public void newOperation(String name, String value){
        if(userOperations.containsKey(name))
            throw new KeyAlreadyExistException();
        userOperations.put(name, value);
    }

    
/**
 * @brief delete method remove from the Map the user defined operation with key "name"
 * @param name 
 */    
    public String delete(String name){
        return userOperations.remove(name);
    }
    
    public void deleteDependencies(String name){
        
    }
    
  
}
