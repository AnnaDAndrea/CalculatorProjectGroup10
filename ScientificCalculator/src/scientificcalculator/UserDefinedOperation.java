package scientificcalculator;

import exception.KeyAlreadyExistException;
import exception.LoopException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * this class implements the logic needed to maintain a data structure that
 * stores user-defined operations and methods to manage them
 *
 * The Map data structure is a key-value map, where the key is the name of the
 * user-defined and the value is the sequence representing the operation
 *
 * @author Group 10
 */
public class UserDefinedOperation {

    private Map<String, String> userOperations;

    public UserDefinedOperation() {
        userOperations = new HashMap<>();
    }

    /**
     * getNameOperations method returns a set containing all the keys
     *
     * @return Set<String>
     */
    public Set<String> getNameOperations() {
        return userOperations.keySet();
    }

    /**
     * getSequence method returns the value related to the key or null if the
     * key doesn't exists
     *
     * @param op is key
     * @return String
     */
    public String getSequence(String op) {
        return userOperations.get(op);
    }

    /**
     * newOperation method adds a new couple key-value and if the key already
     * exists throws a KeyAlreadyExistException
     *
     * @param name
     * @param value
     */
    public void newOperation(String name, String value) {
        if (userOperations.containsKey(name)) {
            throw new KeyAlreadyExistException();
        }
        userOperations.put(name, value);
    }

    /**
     * delete method remove from the Map the user defined operation with key
     * "name" and returns the value related to the key
     *
     * @param name
     * @return String
     */
    public String delete(String name) {
        return userOperations.remove(name);
    }

    /**
     * searchDependencies method returns a Set<String> containing all the
     * dependencies of the name user defined operation
     *
     * @param name
     * @return Set<String>
     */
    public Set<String> searchDependencies(String name) {
        Set<String> dep = new HashSet<>();
        for (Map.Entry<String, String> kv : userOperations.entrySet()) {
            if (kv.getValue().contains(name)) {
                dep.add(kv.getKey());
            }
        }
        return dep;
    }

    /**
     * deleteAllDependencies method removes both the user-defined operation with
     * key "name" and all its dependencies and returns a String containing the
     * keys of all the user-defined operation deleted
     *
     * @param name
     * @return String
     */
    public String deleteAllDependencies(String name) {
        Set<String> dep = searchDependencies(name);
        String ret = "";

        for (String s : dep) {
            if (getSequence(s) != null) {
                ret += deleteAllDependencies(s) + " ";
            }
        }

        if (delete(name) != null) {
            ret += name + " ";
        }
        return ret;
    }

    /**
     *
     * save method saves the whole object userOperations in a binary file
     *
     * @param fileName
     */
    public void save(String fileName) throws IOException {

        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            out.writeObject(userOperations);
        }

    }

    /**
     * reload method reloads the object userOperations from a binary file
     *
     * @param fileName
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void reload(String fileName) throws IOException, ClassNotFoundException {
        System.out.println(fileName);

        HashMap<String, String> userDef = null;

        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {

            userDef = (HashMap<String, String>) in.readObject();
        }
        userOperations = userDef;
    }
    
    
    /**
     * checkLoop method allows to avoid any loops in user defined operations
     * @param value
     * @param init 
     */
    private void checkLoop(String value, String init){
        StringTokenizer ops = new StringTokenizer(value, " ");
        
        while(ops.hasMoreTokens()){
            String op = ops.nextToken();
            
            if(op.equals(init))
                throw new LoopException();
            else{
                String newValue = getSequence(op);
                if(newValue != null)
                {
                    checkLoop(newValue, init);
                }
            }
            
            
        }
        
    }
    
    
    /**
     * editSequence method allows the editing of a user defined operation
     * @param name
     * @param value 
     */
    public void editSequence(String name, String value){
            checkLoop(value, name);
            userOperations.replace(name, value);
        
    }

}
