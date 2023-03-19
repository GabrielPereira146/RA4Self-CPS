package br.unesp.rc.shhc.panel;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.AnchorPane;


public class Controller implements Initializable  {

    @FXML
    private Spinner<Integer> SpinnerAir;

    @FXML
    private Spinner<Integer> SpinnerGlucose;

    @FXML
    private Spinner<Integer> SpinnerHeart;

    @FXML
    private Spinner<Integer> SpinnerOxygen;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Spinner<Integer> spinnerSystolicBP;

    @FXML
    private Spinner<Integer> spinnerTemperature;

    @FXML
    private Label teste;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        spinnerTemperature.valueProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
                int temperature = spinnerTemperature.getValue();
                System.out.println("Temperature Value: " + temperature);
                //String URL = "http://localhost:8084/shhc/Temperature/update";

                //Temperature temp = new Temperature(temperature, "Online", "1");
                //String json = GsonUtils.objetoToJson(temp);
                //CustomHttpClientUtils.setValueByHttpPut(URL, json);
            }
            
        });

        SpinnerAir.valueProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
               // value = spinnerTemperature.getValue();
              //  System.out.println(value);
                
            }
            
        });

        SpinnerGlucose.valueProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
              //  value = spinnerTemperature.getValue();
               // System.out.println(value);
                
            }
            
        });

        SpinnerHeart.valueProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
               // value = spinnerTemperature.getValue();
               // System.out.println(value);
                
            }
            
        });

        SpinnerOxygen.valueProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
               // value = spinnerTemperature.getValue();
               // System.out.println(value);
                
            }
            
        });
    }

}
