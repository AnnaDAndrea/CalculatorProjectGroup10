package scientificcalculator;

import exception.IllegalStackPointerException;
import exception.InterpreterException;
import exception.LoopException;
import exception.ZeroDivisionException;
import exception.VarOutOfRangeException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
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
import javafx.stage.FileChooser;
import org.apache.commons.math3.complex.*;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.Precision;

/**
 * This is the Controller, so the Class that acts as an intermediary between the
 * View and the Model
 *
 * @author Group 10
 *
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
     * Method to initialize Model and GUI
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        alert = new Alert(AlertType.ERROR);

        stack = new LinkedList<>();

        stackObs = FXCollections.observableArrayList();

        userOperations = new UserDefinedOperation();

        parser = new Interpreter(stack,(UserDefinedOperationReadOnly) userOperations);

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

                        if(re == 0 && img == 0)
                            setText("0.0+0.0j");
                        else if(re == 0 && img > 0)
                            setText("0.0+" + img + "j");
                        else if(re == 0 && img < 0)
                            setText("0.0" + img + "j");
                        else if(re > 0 && img == 0)
                            setText(re + "+0.0j");
                        else if(re > 0 && img > 0)
                            setText(re + "+" + img + "j");
                        else if(re > 0 && img < 0)
                            setText(re + "" + img + "j");
                        else if(re < 0 && img == 0)
                            setText(re + "+0.0j");
                        else if(re < 0 && img > 0)
                            setText(re + "+" + img + "j");
                        else
                            setText(re + "" + img + "j");
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

        for (int i = 0; i < 26; i++) {
            variablesObs.add((char) (i + 97));
        }

        variablesList.setItems(variablesObs);
        variablesList.setValue('a');
        variablesList.setStyle("-fx-font: 15px \"Arial\";" + "-fx-font-weight: bold;");

        userDefObs = FXCollections.observableArrayList(userOperations.getNameOperations());
        userDefList.setItems(userDefObs);

        displayField.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                displayField.end();
            }
        });
    }

    /**
     * The following methods are used to write a complex number or
     * an operator or the space character in the text field
     *
     * @param event when one of the associated buttons is pushed
     */
    @FXML
    private void sevenAction(ActionEvent event) {
        displayField.setText(displayField.getText() + "7");
    }

    @FXML
    private void eightAction(ActionEvent event) {
        displayField.setText(displayField.getText() + "8");
    }

    @FXML
    private void nineAction(ActionEvent event) {
        displayField.setText(displayField.getText() + "9");
    }

    @FXML
    private void fourAction(ActionEvent event) {
        displayField.setText(displayField.getText() + "4");
    }

    @FXML
    private void fiveAction(ActionEvent event) {
        displayField.setText(displayField.getText() + "5");
    }

    @FXML
    private void sixAction(ActionEvent event) {
        displayField.setText(displayField.getText() + "6");
    }

    @FXML
    private void oneAction(ActionEvent event) {
        displayField.setText(displayField.getText() + "1");
    }

    @FXML
    private void twoAction(ActionEvent event) {
        displayField.setText(displayField.getText() + "2");
    }

    @FXML
    private void threeAction(ActionEvent event) {
        displayField.setText(displayField.getText() + "3");
    }

    @FXML
    private void pointAction(ActionEvent event) {
        displayField.setText(displayField.getText() + ".");
    }

    @FXML
    private void zeroAction(ActionEvent event) {
        displayField.setText(displayField.getText() + "0");
    }

    @FXML
    private void jAction(ActionEvent event) {
        displayField.setText(displayField.getText() + "j");
    }

    @FXML
    private void spaceAction(ActionEvent event) {
        displayField.setText(displayField.getText() + " ");
    }

    @FXML
    private void sumAction(ActionEvent event) {
        displayField.setText(displayField.getText() + "+");
    }

    @FXML
    private void subAction(ActionEvent event) {
        displayField.setText(displayField.getText() + "-");
    }

    @FXML
    private void divAction(ActionEvent event) {
        displayField.setText(displayField.getText() + "/");
    }

    @FXML
    private void sqrtAction(ActionEvent event) {
        displayField.setText(displayField.getText() + "sqrt");
    }

    @FXML
    private void invAction(ActionEvent event) {
        displayField.setText(displayField.getText() + "+-");
    }

    @FXML
    private void clearAction(ActionEvent event) {
        displayField.setText(displayField.getText() + "clear");
    }

    @FXML
    private void dropAction(ActionEvent event) {
        displayField.setText(displayField.getText() + "drop");
    }

    @FXML
    private void dupAction(ActionEvent event) {
        displayField.setText(displayField.getText() + "dup");
    }

    @FXML
    private void swapAction(ActionEvent event) {
        displayField.setText(displayField.getText() + "swap");
    }

    @FXML
    private void overAction(ActionEvent event) {
        displayField.setText(displayField.getText() + "over");
    }

    @FXML
    private void prodAction(ActionEvent event) {
        displayField.setText(displayField.getText() + "*");
    }

    @FXML
    private void copyVarAction(ActionEvent event) {
        displayField.setText(displayField.getText() + ">" + variablesList.getValue());
    }

    @FXML
    private void assignVarAction(ActionEvent event) {
        displayField.setText(displayField.getText() + "<" + variablesList.getValue());
    }

    @FXML
    private void sumToVarAction(ActionEvent event) {
        displayField.setText(displayField.getText() + "+" + variablesList.getValue());
    }

    @FXML
    private void subtractToVarAction(ActionEvent event) {
        displayField.setText(displayField.getText() + "-" + variablesList.getValue());
    }

    @FXML
    private void saveVarAction(ActionEvent event) {
        displayField.setText(displayField.getText() + "save");
    }

    @FXML
    private void restoreVarAction(ActionEvent event) {
        displayField.setText(displayField.getText() + "restore");
    }

    /**
     * The newAction method is called when the "new" button is pushed. 
     * It allows to create a new User-Defined Operation
     *
     * @param event is the event when the "new" button is pushed
     */
    @FXML
    private void newAction(ActionEvent event) {
        String input = displayField.getText();

        if (input.length() != 0) {
            TextInputDialog dialog = new TextInputDialog("sequence name");
            dialog.setHeaderText("User-defined Operation Name");
            dialog.setContentText("Set a name for this sequence:\n" + input);
            Optional<String> ret = dialog.showAndWait();
            if (ret.isPresent() && !ret.get().contains(" ") && !ret.get().isEmpty() && !parser.check(ret.get()) && parser.check(input)) {
                userOperations.newOperation(ret.get(), input);

                userDefObs.setAll(userOperations.getNameOperations());
                userDefList.setItems(userDefObs);

                informationDialog("Created", "New operation created successfully");

                displayField.setText("");
            } else {
                errorDialog("Operation not allowed", "The name of the operation or the sequence is\\ninvalid");
            }

        } else {
            warningDialog("Insert one or more operations", "You must insert one or more operations");
        }

    }

    /**
     * The callAction method sends the content of a user-defined operation to the text field 
     *
     * @param event
     */
    @FXML
    private void callAction(ActionEvent event) {
        if (userDefList.getValue() != null) {
            displayField.setText(displayField.getText() + userDefList.getValue());
        } else 
            warningDialog("Select a User-defined Operation", "You must select a User-defined operation");
        
    }

    /**
     * The cancAction method is used to delete 
     * an entire string up to the previous space character (using the "canc" button)
     *
     * @param event is the event when the canc button is pushed
     */
    @FXML
    private void cancAction(ActionEvent event) {

        String text = displayField.getText();
        int index = text.lastIndexOf(" ");

        if (index != -1) {
            displayField.setText(text.substring(0, index));
        } else {
            displayField.setText("");
        }

    }

    /**
     * The deleteAction method is used to delete a user-defined operation. 
     * The user chooses whether to apply a cascade or no action policy
     * 
     * @param event is the event when the delete button is pushed
     */
    @FXML
    private void deleteAction(ActionEvent event) {
        if (userDefList.getValue() != null) {
            ButtonType cascade = new ButtonType("Cascade");
            ButtonType noAction = new ButtonType("No action");
            ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            Alert chooseAlert = new Alert(AlertType.WARNING, "", cascade, noAction, cancel);

            chooseAlert.setTitle("Warning");
            chooseAlert.setHeaderText("Cascade or No action");
            chooseAlert.setContentText("Do you want to apply the cascade policy or the no action policy?");
            String choose = chooseAlert.showAndWait().get().getText();

            if (choose.equals("Cascade")) {
                String ret = userOperations.deleteAllDependencies(userDefList.getValue());
                informationDialog("Deleted User Defined Operations", "The deleted user defined operations are:\n" + ret);
            } else if (choose.equals("No action")) {
                Set<String> depSet = userOperations.searchDependencies(userDefList.getValue());

                if (depSet.isEmpty()) {
                    userOperations.delete(userDefList.getValue());
                    informationDialog("Deleted User Defined Operation", userDefList.getValue() + " has been deleted");
                } else {
                    informationDialog("Dependencies found", userDefList.getValue() + " has dependencies,\nso it hasn't been deleted");
                }
            }

            userDefObs.setAll(userOperations.getNameOperations());
            userDefList.setItems(userDefObs);
            userDefList.getSelectionModel().select(0);

        } else 
            warningDialog("Select a User-defined Operation", "You must select a User-defined operation");
        

    }

    /**
     * The editAction method is used to edit a sequence of operations
     * using a dialog, when the button is pushed
     *
     * @param event is the event when the edit button is pushed
     */
    @FXML
    private void editAction(ActionEvent event) {

        if (userDefList.getValue() != null) {
            TextInputDialog dialog = new TextInputDialog(userOperations.getSequence(userDefList.getValue()));
            dialog.setHeaderText("Edit User-defined Operation");
            dialog.setContentText("Set a new sequence for " + userDefList.getValue());
            Optional<String> ret = dialog.showAndWait();

            if (ret.isPresent() && !ret.get().isEmpty() && parser.check(ret.get())) {
                try {
                    userOperations.editSequence(userDefList.getValue(), ret.get());
                    
                    informationDialog("Edited", userDefList.getValue() + " has been edited");
                } catch (LoopException ex) {
                    errorDialog("Loop found", "The sequence of the operation generates a loop. Editing Aborted.");
                }
            } else {
                errorDialog("Operation not allowed", "The sequence of the operation is invalid. Editing Aborted.");
            }

        } else 
            warningDialog("Select a User-defined Operation", "You must select a User-defined operation");
        
    }

    /**
     * The sendAction method uses the interpreter to translate the input in complex
     * numbers (operands) and operators and to do any operations. This method also handles
     * exceptions by showing an alert
     *
     * @param event is the event when the send button is clicked
     */
    @FXML
    private void sendAction(ActionEvent event) {
        showDialogAndCallParse();
    }

    /**
     * The keyPressedTextField method allows to use the enter button on the keyboard
     *
     * @param event is the event when the enter button is clicked
     */
    @FXML
    private void keyPressedTextField(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            showDialogAndCallParse();
        }

    }

    /**
     * The showDialogAndCallParse method is used to execute the sequence of the operations
     * inserted on the text field. It shows an error dialog according to the
     * exception
     */
    private void showDialogAndCallParse() {
        String input = displayField.getText();
        if (input.length() != 0) {

            try {
                parser.parse(input);
            } catch (InterpreterException ex) {
                errorDialog("Wrong command/s", "You inserted wrong command/s.\nEnter the operations to be performed again.");
            } catch (NoSuchElementException ex) {
                errorDialog("Not enough operands", "There are not enough operands.\nEnter the operations to be performed again.");
            } catch (ZeroDivisionException ex) {
                errorDialog("Division by 0", "Division Error");
            } catch (VarOutOfRangeException ex) {
                errorDialog("Variable out of range", "The inserted variable doesn't exist");
            } catch (NullArgumentException ex) {
                errorDialog("Variable Value Error", "The inserted variable hasn't a value");
            } catch (IllegalStackPointerException ex) {
                errorDialog("Variables Restore Error", "There aren't available saves of the variables values");
            } finally {
                stackObs.setAll(stack);
            }
        } else {
            warningDialog("Insert one or more operations", "You must insert one or more operations");
        }
        displayField.setText("");
    }
    
    /**
     * The warningDialog method is used to show a warning dialog
     */
    private void warningDialog(String header, String content){
        alert.setAlertType(AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    /**
     * The errorDialog method is used to show an error dialog 
     */
    private void errorDialog(String header, String content){
        alert.setAlertType(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    /**
     * The informationDialog method is used to show an information dialog 
     */
    private void informationDialog(String header, String content){
        alert.setAlertType(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }


    /**
     * The saveUserAction method is used to save the user-defined operations 
     * in a binary file chosen by the user 
     *
     * @param event is the event when the save button is clicked
     */
    @FXML
    private void saveUserAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Bin Files", "*.bin"));

        fileChooser.setTitle("Save your User-defined Operations in a .bin file");

        File f = fileChooser.showSaveDialog(stackView.getScene().getWindow());
        if (f == null) {
            return;
        }

        try {
            userOperations.save(f.getPath());
            informationDialog("User-defined Operations saved", "Successful saving procedure");
        } catch (IOException ex) {
            errorDialog("Save Error", "Unsuccessful saving procedure");
        }

    }

    /**
     * The reloadUserAction method is used to reload the user-defined operations
     * previously saved from a binary file
     *
     * @param event is the event when the reload button is clicked
     */
    @FXML
    private void reloadUserAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Reload your User-defined Operations from a .bin file");
        File f = fileChooser.showOpenDialog(stackView.getScene().getWindow());

        if (f == null) {
            return;
        }

        try {
            userOperations.reload(f.getPath());
            informationDialog("User-defined Operations reloaded", "Successful reload procedure");
            userDefObs.setAll(userOperations.getNameOperations());
            userDefList.setItems(userDefObs);
            userDefList.getSelectionModel().select(0);
        } catch (IOException | ClassNotFoundException ex) {
            errorDialog("Reload Error", "Unsuccessful reload procedure");
        }

    }

}
