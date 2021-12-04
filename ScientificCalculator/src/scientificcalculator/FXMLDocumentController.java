package scientificcalculator;

import exception.InterpreterException;
import exception.ZeroDivisionException;
import exception.VarOutOfRangeException;
import java.net.URL;
import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.apache.commons.math3.complex.*;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.Precision;

/**
 *
 * @author Group 10
 * @brief This is the Controller, so the Class that acts as an intermediary between the View and the Model
 */
public class FXMLDocumentController implements Initializable {

    private Deque<Complex> stack;
    private ObservableList<Complex> stackObs;
    private ObservableList<Character> variablesObs;
    private ObservableList<String> userDefObs;
    private Interpreter parser;
    private UserDefinedOperation userOperations;
    private Alert alert;

    @FXML
    private ListView<Complex> stackView;
    @FXML
    private Button sendButton;
    @FXML
    private TextField displayField;
    @FXML
    private ComboBox<Character> variablesList;
    @FXML
    private ComboBox<String> userDefList;

    /**
     * @brief method to initialize Model and GUI
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        alert = new Alert(AlertType.ERROR);
       
        stack = new LinkedList<>();

        stackObs = FXCollections.observableArrayList();
      
        userOperations = new UserDefinedOperation();
        
        parser = new Interpreter(stack, userOperations);

        stackView.setItems(stackObs);
        stackView.setCellFactory(lv -> {
            ListCell<Complex> cell = new ListCell<Complex>() {
                @Override
                protected void updateItem(Complex c, boolean empty) {
                    super.updateItem(c, empty);
                    if (empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        double img = Precision.round(c.getImaginary(), 8);
                        double re = Precision.round(c.getReal(), 8);
                        
                        if (img > 0) {
                            setText(re + "+" + img + "j");
                        } else if(img == 0 && re == 0){
                            setText("0.0+0.0j");
                        }  else if(img == 0){
                            setText(Precision.round(c.getReal() , 8) + "+0.0j");
                        }else if(re== 0){
                            setText("0.0"+ img +"j");
                        }else{
                            setText(re+ "" + img + "j");
                        }
                    }
                }
            };
            cell.setAlignment(Pos.CENTER);
            cell.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
            return cell;
        });
        ImageView sendIco = new ImageView("img/sendIco.png");
        
        sendButton.setGraphic(sendIco);
        sendIco.setFitHeight(25);
        sendIco.setFitWidth(25);
        sendButton.styleProperty().bind(Bindings.when(sendButton.armedProperty())
                                                .then("-fx-background-color: #ef5350; -fx-background-radius: 20px;")
                                                .otherwise("-fx-background-color: #ff867c; -fx-background-radius: 20px;"));
        
        
        variablesObs = FXCollections.observableArrayList();
        
        for(int i=0;i<26;i++){
            variablesObs.add((char) (i+97));
        } 

        variablesList.setItems(variablesObs);
        variablesList.setValue('a');
        variablesList.setStyle("-fx-font: 15px \"Arial\";" + "-fx-font-weight: bold;");
        
        userDefObs = FXCollections.observableArrayList(userOperations.getNameOperations());
        userDefList.setItems(userDefObs);
    }
/**
 * @brief the following methods are used to write in the text field a complex number
 * or an operator or the space character
 * @param event when one of the associated buttons is pushed
 */
    @FXML
    private void sevenAction(ActionEvent event) { displayField.setText(displayField.getText()+"7"); }

    @FXML
    private void eightAction(ActionEvent event) { displayField.setText(displayField.getText()+"8"); }

    @FXML
    private void nineAction(ActionEvent event) { displayField.setText(displayField.getText()+"9");  }

    @FXML
    private void fourAction(ActionEvent event) { displayField.setText(displayField.getText()+"4"); }

    @FXML
    private void fiveAction(ActionEvent event) { displayField.setText(displayField.getText()+"5"); }

    @FXML
    private void sixAction(ActionEvent event) { displayField.setText(displayField.getText()+"6"); }

    @FXML
    private void oneAction(ActionEvent event) { displayField.setText(displayField.getText()+"1"); }

    @FXML
    private void twoAction(ActionEvent event) { displayField.setText(displayField.getText()+"2"); }

    @FXML
    private void threeAction(ActionEvent event) { displayField.setText(displayField.getText()+"3"); }

    @FXML
    private void pointAction(ActionEvent event) { displayField.setText(displayField.getText()+"."); }

    @FXML
    private void zeroAction(ActionEvent event) { displayField.setText(displayField.getText()+"0"); }

    @FXML
    private void jAction(ActionEvent event) { displayField.setText(displayField.getText()+"j"); }

    @FXML
    private void spaceAction(ActionEvent event) { displayField.setText(displayField.getText()+" "); }

    @FXML
    private void sumAction(ActionEvent event) { displayField.setText(displayField.getText()+"+"); }

    @FXML
    private void subAction(ActionEvent event) { displayField.setText(displayField.getText()+"-"); }

    @FXML
    private void divAction(ActionEvent event) { displayField.setText(displayField.getText() +"/"); }

    @FXML
    private void sqrtAction(ActionEvent event) { displayField.setText(displayField.getText() +"sqrt"); }

    @FXML
    private void invAction(ActionEvent event) { displayField.setText(displayField.getText() +"+-"); }

    @FXML
    private void clearAction(ActionEvent event) { displayField.setText(displayField.getText() +"clear"); }

    @FXML
    private void dropAction(ActionEvent event) { displayField.setText(displayField.getText() +"drop"); }

    @FXML
    private void dupAction(ActionEvent event) { displayField.setText(displayField.getText() +"dup"); }

    @FXML
    private void swapAction(ActionEvent event) { displayField.setText(displayField.getText() +"swap"); }

    @FXML
    private void overAction(ActionEvent event) { displayField.setText(displayField.getText() +"over"); }

    @FXML
    private void prodAction(ActionEvent event) { displayField.setText(displayField.getText()+"*"); }
    
    @FXML
    private void copyVarAction(ActionEvent event) { displayField.setText(displayField.getText() + ">" + variablesList.getValue()); }

    @FXML
    private void assignVarAction(ActionEvent event) { displayField.setText(displayField.getText() + "<" + variablesList.getValue()); }

    @FXML
    private void sumToVarAction(ActionEvent event) { displayField.setText(displayField.getText() + "+" + variablesList.getValue()); }

    @FXML
    private void subtractToVarAction(ActionEvent event) { displayField.setText(displayField.getText() + "-" + variablesList.getValue()); }
    
    /**
     * @brief method called when the "new" button is pushed. It allows to create a new User-Defined Operation
     * @param event is the event when the new button is pushed
     */
    @FXML
    private void newAction(ActionEvent event) {
        String input=displayField.getText();
        
        if(input.length()!=0){
            TextInputDialog dialog = new TextInputDialog("name of this sequence");
            dialog.setHeaderText("Name User Operation");
            dialog.setContentText("Set a name of this sequence:\n" + input);
            Optional<String> ret=dialog.showAndWait();
            if(ret.isPresent() && !ret.get().contains(" ") && !ret.get().isEmpty() && !parser.check(ret.get()) && parser.check(input)){
                userOperations.newOperation(ret.get(), input);
                
                userDefObs.setAll(userOperations.getNameOperations());
                userDefList.setItems(userDefObs);
                
                alert.setAlertType(AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Created");
                alert.setContentText("New operation created successfully");
                alert.showAndWait(); 
                
                displayField.setText("");
            }else{
                alert.setAlertType(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Operation not allowed");
                alert.setContentText("The name of operation or the sequence contains error");
                alert.showAndWait(); 
            }
                
        }else{
            alert.setAlertType(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Insert one or more operations");
            alert.setContentText("You must insert one or more operations.");
            alert.showAndWait(); 
        }
        
    }

    /**
     * @brief cancAction method is used to delete from text field a character 
     * is there aren't space characters or a sequence until the previous space character
     * @param event is the event when the canc button is pushed
     */
    @FXML
    private void cancAction(ActionEvent event) {
        
        String text=displayField.getText();
        int index=text.lastIndexOf(" ");
        
        if(index!=-1){
           displayField.setText(text.substring(0,index));
        }
        else {
           displayField.setText("");
        }

    }
    /**
     * @brief sendAction method uses the interpreter to translate input in complex numbers and operators and to do any operations
     * This methos also handles exceptions by showing an alert
     * @param event is the event when the send button is clicked
     */
    @FXML
    private void sendAction(ActionEvent event) {
      showDialogAndCallParse();
    }

    @FXML
    private void keyPressedTextField(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)){
           showDialogAndCallParse();
        }
        
    }

    /**
     * @brief method to execute the sequence of operations inserted on the text field. 
     * It shows an error dialog according to the exception
     */
    private void showDialogAndCallParse(){
         String input=displayField.getText();
         if(input.length()!=0){

             try{    
                 parser.parse(input);
             } catch(InterpreterException ex){
                 alert.setAlertType(AlertType.ERROR);
                 alert.setTitle("Error");
                 alert.setHeaderText("Wrong command/s");
                 alert.setContentText("You inserted wrong command/s.\nEnter the operations to be performed again.");
                 alert.showAndWait();
             } catch(NoSuchElementException ex){
                 alert.setAlertType(AlertType.ERROR);
                 alert.setTitle("Error");
                 alert.setHeaderText("Not enough operands");
                 alert.setContentText("There are not enough operands.\nEnter the operations to be performed again");
                 alert.showAndWait();
             } catch (ZeroDivisionException ex) {
                 alert.setAlertType(AlertType.ERROR);
                 alert.setTitle("Error");
                 alert.setHeaderText("Divison with 0");
                 alert.setContentText("Division Error");
                 alert.showAndWait();
             } catch (VarOutOfRangeException ex) {
                 alert.setAlertType(AlertType.ERROR);
                 alert.setTitle("Error");
                 alert.setHeaderText("Variable Error");
                 alert.setContentText("Variable inserted not exist");
                 alert.showAndWait();
             } catch (NullArgumentException ex) {
                 alert.setAlertType(AlertType.ERROR);
                 alert.setTitle("Error");
                 alert.setHeaderText("Variable Error");
                 alert.setContentText("Variable inserted hasn't a value");
                 alert.showAndWait();
             } finally{
                 stackObs.setAll(stack);
             }
         }else{
             alert.setAlertType(AlertType.WARNING);
             alert.setTitle("Warning");
             alert.setHeaderText("Insert one or more operations");
             alert.setContentText("You must insert one or more operations.");
             alert.showAndWait(); 
         }
         displayField.setText("");
    }

}
