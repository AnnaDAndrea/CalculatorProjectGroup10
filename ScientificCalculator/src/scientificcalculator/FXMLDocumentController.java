/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import java.net.URL;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
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
    private Button pointButton;
    @FXML
    private Button sendButton;
    @FXML
    private TextField displayField;
    @FXML
    private Button sevenButton;
    @FXML
    private Button eightButton;
    @FXML
    private Button nineButton;
    @FXML
    private Button fourButton;
    @FXML
    private Button fiveButton;
    @FXML
    private Button sixButton;
    @FXML
    private Button oneButton;
    @FXML
    private Button twoButton;
    @FXML
    private Button threeButton;
    @FXML
    private Button zeroButton;
    @FXML
    private Button jButton;
    @FXML
    private Button cancButton;
    @FXML
    private Button spaceButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Complex num = new Complex(1.0, 3.0);
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
        sendIco.setFitHeight(35);
        sendIco.setFitWidth(35);
        sendButton.styleProperty().bind(Bindings.when(sendButton.armedProperty()).then("-fx-background-color: #ef5350;").otherwise("-fx-background-color: #ff867c;"));
       
       
        
    }

    @FXML
    private void sevenAction(ActionEvent event) { displayField.setText(displayField.getText()+"7"); }

    @FXML
    private void eightAction(ActionEvent event) { displayField.setText(displayField.getText()+"8"); }

    @FXML
    private void nineAction(ActionEvent event) { displayField.setText(displayField.getText()+"9");  }

    @FXML
    private void fourAction(ActionEvent event) { displayField.setText(displayField.getText()+"4"); }

    @FXML
    private void fiveAction(ActionEvent event) {  displayField.setText(displayField.getText()+"5"); }

    @FXML
    private void sixAction(ActionEvent event) {   displayField.setText(displayField.getText()+"6"); }

    @FXML
    private void oneAction(ActionEvent event) { displayField.setText(displayField.getText()+"1"); }

    @FXML
    private void twoAction(ActionEvent event) {   displayField.setText(displayField.getText()+"2"); }

    @FXML
    private void threeAction(ActionEvent event) {  displayField.setText(displayField.getText()+"3"); }

    @FXML
    private void pointAction(ActionEvent event) {  displayField.setText(displayField.getText()+"."); }

    @FXML
    private void zeroAction(ActionEvent event) { displayField.setText(displayField.getText()+"0"); }

    @FXML
    private void jAction(ActionEvent event) { displayField.setText(displayField.getText()+"j"); }

    @FXML
    private void cancAction(ActionEvent event) {
        
        String text=displayField.getText();
        String newText;
      //  String newText=text.substring(0,text.length()-1);
        //displayField.setText(newText);
     /*   int len=text.length();
        int index=-1;
                
        if(len!=0 && text.contains(" "))
            for (int i = len - 1; i >= 0; i--) {
                if (text.charAt(i) == ' ') {
                    index = i;
                }
                break;
            }
        if(index!=-1){
            newText=text.substring(0, index);
            displayField.setText(newText);
        }
        else{
            newText=text.substring(0,text.length()-1);
            displayField.setText(newText);
       }
      */
     int index=text.lastIndexOf(" ");
     if(index!=-1){
        String result = text.substring(0,index);
        displayField.setText(result);
     }
     else {
        String result = text.substring(0,text.length()-1);
        displayField.setText(result);
     }
     
     
    }

    @FXML
    private void sendAction(ActionEvent event) throws InterpreterException {
        String input=displayField.getText();
        if(input.length()!=0){
            
        try{    
        parser.parse(input);
        displayField.setText("");
        stackObs.setAll(stack);
        }
        catch(Exception ex){
            
        }finally{
            stackObs.setAll(stack);
        }
        }
    }

    @FXML
    private void spaceAction(ActionEvent event) { displayField.setText(displayField.getText()+" ");   }

    @FXML
    private void sumAction(ActionEvent event) {   displayField.setText(displayField.getText()+"+"); }

    @FXML
    private void subAction(ActionEvent event) { displayField.setText(displayField.getText()+"-");}

    @FXML
    private void mulAction(ActionEvent event) { displayField.setText(displayField.getText()+"*");   } //cambiare il nome in prodAction

    @FXML
    private void divAction(ActionEvent event) { displayField.setText(displayField.getText() +"\\"); }

    @FXML
    private void sqrtAction(ActionEvent event) { displayField.setText(displayField.getText() +"sqrt");    }

    @FXML
    private void invAction(ActionEvent event) { displayField.setText(displayField.getText() +"+-");
    }

    @FXML
    private void clearAction(ActionEvent event) { displayField.setText(displayField.getText() +"clear"); }

    @FXML
    private void dropAction(ActionEvent event) { displayField.setText(displayField.getText() +"drop"); }

    @FXML
    private void dupAction(ActionEvent event) { displayField.setText(displayField.getText() +"dup"); }

    @FXML
    private void swapAction(ActionEvent event) { displayField.setText(displayField.getText() +"swap");  }

    @FXML
    private void overAction(ActionEvent event) { displayField.setText(displayField.getText() +"over");  }

}
