/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.apache.commons.math3.complex.*;

/**
 *
 * @author Group 10
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    private Complex num;
    private ComplexFormat cf;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        
        label.setText(cf.format(num));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        num = new Complex(1.0, 3.0);
        cf = new ComplexFormat();
        
        
    }    
    
}
