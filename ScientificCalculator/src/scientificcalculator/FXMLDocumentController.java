package scientificcalculator;

import java.net.URL;
import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.apache.commons.math3.complex.*;

/**
 *
 * @author Group 10
 */
public class FXMLDocumentController implements Initializable {

    private Deque<Complex> stack;
    private ObservableList<Complex> stackObs;
    private Interpreter parser;

    @FXML
    private ListView<Complex> stackView;
    @FXML
    private Button sendButton;
    @FXML
    private TextField displayField;

    /**
     * @brief initialize method is used to associate an observable list to a list view that contains complex numbers with the format a+bj
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        stack = new LinkedList<>();

        stackObs = FXCollections.observableArrayList();
      
        parser = new Interpreter(stack);

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
                        if (c.getImaginary() >= 0) {
                            setText(c.getReal() + "+" + c.getImaginary() + "j");
                        } else {
                            setText(c.getReal() + "" + c.getImaginary() + "j");
                        }
                    }
                }
            };
            cell.setAlignment(Pos.CENTER);
            return cell;
        });
        ImageView sendIco = new ImageView("img/sendIco.png");
        
        sendButton.setGraphic(sendIco);
        sendIco.setFitHeight(25);
        sendIco.setFitWidth(25);
        sendButton.styleProperty().bind(Bindings.when(sendButton.armedProperty())
                                                .then("-fx-background-color: #ef5350; -fx-background-radius: 20px;")
                                                .otherwise("-fx-background-color: #ff867c; -fx-background-radius: 20px;"));
   
    }
/**
 * @brief the following methods are used to write in the text field a complex number or an operator or the space character
 * @param event 
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

    /**
     * @brief  cancAction method is used to delete from text field a character is there aren't space characters or a sequence until the previous space character
     * @param event is the event when the canc button is clicked
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
     * @throws InterpreterException 
     */
    @FXML
    private void sendAction(ActionEvent event) throws InterpreterException {
        String input=displayField.getText();
        if(input.length()!=0){

            try{    
                parser.parse(input);
                displayField.setText("");
            }
            catch(InterpreterException ex){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Wrong command/s");
                alert.setContentText("You inserted wrong command/s.\nEnter the operations to be performed again.");
                alert.showAndWait();
                displayField.setText("");
            }catch(NoSuchElementException ex){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Not enough operands");
                alert.setContentText("There are not enough operands.\nEnter the operations to be performed again");
                alert.showAndWait();
                displayField.setText("");
            } catch (ZeroDivisionException ex) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Divison with 0");
                alert.setContentText("Division Error");
                alert.showAndWait();
                displayField.setText("");
            }finally{
                stackObs.setAll(stack);
            }
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Insert one or more operations");
            alert.setContentText("You must insert one or more operations.");
            alert.showAndWait();
            displayField.setText("");
        }
    }
}
