package br.unesp.rc.shhc.panel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.unesp.rc.httpclient.utils.CustomHttpClientUtils;
import br.unesp.rc.gson.utils.GsonUtils;
import br.unesp.rc.shhc.Temperature;
import br.unesp.rc.shhc.Glucose;
import br.unesp.rc.shhc.AirFlow;
import br.unesp.rc.shhc.PulseOxygen;
import br.unesp.rc.shhc.BloodPressure;
import br.unesp.rc.shhc.HeartRate;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Controller implements Initializable {

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
    private Spinner<Integer> spinnerDiastolicBP;

    @FXML
    private Spinner<Integer> spinnerTemperature;

    @FXML
    private Button buttonServer;

    @FXML
    private TextField textServerPort;

    String URL = "http://localhost:";
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        URL = URL.concat(textServerPort.getText());

        spinnerTemperature.valueProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
                int temperature = spinnerTemperature.getValue();
                System.out.println("Temperature Value: " + temperature);
                String URL_Temp = "/shhc/Temperature/update";
                URL_Temp = URL.concat(URL_Temp);

                Temperature temp = new Temperature(temperature, "Online", "1");
                String json = GsonUtils.objetoToJson(temp);
                try {
                    CustomHttpClientUtils.setValueByHttpPut(URL_Temp, json);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        SpinnerAir.valueProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
                int airflow = SpinnerAir.getValue();
                System.out.println(airflow);
                String URL_Air = "/shhc/AirFlow/update";
                URL_Air = URL.concat(URL_Air);
                AirFlow air = new AirFlow(airflow, "Online", "4");
                String json = GsonUtils.objetoToJson(air);
                try {
                    CustomHttpClientUtils.setValueByHttpPut(URL_Air, json);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

        SpinnerGlucose.valueProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
                int glucose = SpinnerGlucose.getValue();
                System.out.println(glucose);
                String URL_Glucose = "/shhc/Glucose/update";
                URL_Glucose = URL.concat(URL_Glucose);
                Glucose glu = new Glucose(glucose, "Online", "2");
                String json = GsonUtils.objetoToJson(glu);
                try {
                    CustomHttpClientUtils.setValueByHttpPut(URL_Glucose, json);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        SpinnerHeart.valueProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
                int heartrate = SpinnerHeart.getValue();
                System.out.println(heartrate);
                String URL_Heart = "/shhc/HeartRate/update";
                URL_Heart = URL.concat(URL_Heart);
                HeartRate hr = new HeartRate(heartrate, "Online", "5");
                String json = GsonUtils.objetoToJson(hr);
                try {
                    CustomHttpClientUtils.setValueByHttpPut(URL_Heart, json);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

        SpinnerOxygen.valueProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
                int oxygenP = SpinnerOxygen.getValue();
                System.out.println(oxygenP);
                String URL_Oxygen = "/shhc/PulseOxygen/update";
                URL_Oxygen = URL.concat(URL_Oxygen);
                PulseOxygen oxyg = new PulseOxygen(oxygenP, "Online", "3");
                String json = GsonUtils.objetoToJson(oxyg);
                try {
                    CustomHttpClientUtils.setValueByHttpPut(URL_Oxygen, json);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

        spinnerSystolicBP.valueProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                int systolic = spinnerSystolicBP.getValue();
                int diastolic = spinnerDiastolicBP.getValue();
                String URL_Blood = "/shhc/BloodPressure/update";
                URL_Blood = URL.concat(URL_Blood);
                BloodPressure bloodP = new BloodPressure(diastolic, systolic, "Online", "6");
                String json = GsonUtils.objetoToJson(bloodP);
                try {
                    CustomHttpClientUtils.setValueByHttpPut(URL_Blood, json);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        spinnerDiastolicBP.valueProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                int systolic = spinnerSystolicBP.getValue();
                int diastolic = spinnerDiastolicBP.getValue();
                String URL_Blood = "/shhc/BloodPressure/update";
                URL_Blood = URL.concat(URL_Blood);
                BloodPressure bloodP = new BloodPressure(diastolic, systolic, "Online", "6");
                String json = GsonUtils.objetoToJson(bloodP);
                try {
                    CustomHttpClientUtils.setValueByHttpPut(URL_Blood, json);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        buttonServer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                URL = URL.substring(0, URL.length()-4);
                URL = URL.concat(textServerPort.getText());
                System.out.println(URL);
            }
        });
    }

}
