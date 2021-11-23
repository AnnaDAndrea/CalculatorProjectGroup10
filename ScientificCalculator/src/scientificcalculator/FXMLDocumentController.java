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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Complex num = new Complex(1.0, 3.0);
        stack = new LinkedList<>();

        stackObs = FXCollections.observableList((List) stack);

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
        sendButton.styleProperty().bind(Bindings.when(sendButton.armedProperty()).then("-fx-background-color: green;").otherwise("-fx-background-color: red;"));
        for(int i = 0; i<100;i++){
            try {
                parser.parse("1.11-1.22j");
            } catch (InterpreterException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

}
