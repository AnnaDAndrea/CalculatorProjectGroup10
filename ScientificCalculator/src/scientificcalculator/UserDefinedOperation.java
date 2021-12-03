package scientificcalculator;

import exception.KeyAlreadyExistException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Group 10
 */
public class UserDefinedOperation{
    private Map<String, String> userOperations;

    public UserDefinedOperation() {
        userOperations = new HashMap<>();
    }


    public Set<String> getNameOperations() {
        return userOperations.keySet();
    }


    public String getSequence(String op) {
        return userOperations.get(op);
    }
    
    public void newOperation(String name, String value){
        if(userOperations.containsKey(name))
            throw new KeyAlreadyExistException();
        userOperations.put(name, value);
    }
    
  
}
