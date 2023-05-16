package br.unesp.rc.shhc.principal;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import br.unesp.rc.shhc.model.Patient;

public class ControllerDialog {

    @FXML
    private TextField textAge;

    @FXML
    private TextField textFirstName;

    @FXML
    private TextField textHeight;

    @FXML
    private TextField textLastName;

    @FXML
    private TextField textWeight;

    @FXML
    void btnAddPersonClicked(ActionEvent event) {
        String firstName = textFirstName.getText();
        String lastName = textLastName.getText();
        float height = Float.valueOf(textHeight.getText());
        float weight = Float.valueOf(textWeight.getText());
        int age = Integer.valueOf(textAge.getText());

        Patient paciente = new Patient(firstName, lastName, height, weight, age);
       
        closeStage(event);
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}