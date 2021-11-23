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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        Complex num = new Complex(1.0, 3.0);
        stack = new LinkedList<>();

        stackObs = FXCollections.observableList((List) stack);
        
        parser = new Interpreter(stack);

        stackView.setItems(stackObs);
        stackView.setCellFactory(lv -> new ListCell<Complex>() {
            @Override
            protected void updateItem(Complex c, boolean empty) {
                super.updateItem(c, empty);
                if (empty) {
                    setText(null);
                    setStyle("");
                } else {
                    if(c.getImaginary()>=0)
                        setText(c.getReal() + "+" + c.getImaginary()+"j");
                    else
                        setText(c.getReal() + "-" + c.getImaginary()+"j");
                }
            }
        });
        

        
        parser.parse("1.11+1.22j 1.10 +");

        

    }

}
