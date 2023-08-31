package br.unesp.rc.shhc.panel;

import java.net.URL;
import java.util.ResourceBundle;

import br.unesp.rc.gson.utils.GsonUtils;
import br.unesp.rc.httpclient.utils.CustomHttpClientUtils;
import br.unesp.rc.shhc.SHHCModel.model.AirFlow;
import br.unesp.rc.shhc.SHHCModel.model.PulseOxygen;
import br.unesp.rc.shhc.SHHCModel.model.BloodPressure;
import br.unesp.rc.shhc.SHHCModel.model.Glucose;
import br.unesp.rc.shhc.SHHCModel.model.HeartRate;
import br.unesp.rc.shhc.SHHCModel.model.Temperature;

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

        spinnerTemperature.valueProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
                int temperature = spinnerTemperature.getValue();
                System.out.println("Temperature Value: " + temperature);
                String URL = "http://localhost:8084/shhc/Temperature/update";

                Temperature temp = new Temperature(temperature, "Online", "1");
                String json = GsonUtils.objetoToJson(temp);
                try {
                    CustomHttpClientUtils.setValueByHttpPut(URL, json);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        });

        SpinnerAir.valueProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
                int airflow = SpinnerAir.getValue();
                System.out.println(airflow);
                String URL = "http://localhost:8084/shhc/AirFlow/update";
                AirFlow air = new AirFlow(airflow, "Online", "4");
                String json = GsonUtils.objetoToJson(air);
                try {
                    CustomHttpClientUtils.setValueByHttpPut(URL, json);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        });

        SpinnerGlucose.valueProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
                int glucose = SpinnerGlucose.getValue();
                System.out.println(glucose);
                String URL = "http://localhost:8084/shhc/Glucose/update";
                Glucose glu = new Glucose(glucose, "Online", "2");
                String json = GsonUtils.objetoToJson(glu);
                try {
                    CustomHttpClientUtils.setValueByHttpPut(URL, json);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        });

        SpinnerHeart.valueProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
                int heartrate = SpinnerHeart.getValue();
                System.out.println(heartrate);
                String URL = "http://localhost:8084/shhc/HeartRate/update";
                HeartRate hr = new HeartRate(heartrate, "Online", "5");
                String json = GsonUtils.objetoToJson(hr);
                try {
                    CustomHttpClientUtils.setValueByHttpPut(URL, json);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        });

        SpinnerOxygen.valueProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
                int oxygenP = SpinnerOxygen.getValue();
                System.out.println(oxygenP);
                String URL = "http://localhost:8084/shhc/PulseOxygen/update";
                PulseOxygen oxyg = new PulseOxygen(oxygenP, "Online", "3");
                String json = GsonUtils.objetoToJson(oxyg);
                try {
                    CustomHttpClientUtils.setValueByHttpPut(URL, json);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        });

        spinnerSystolicBP.valueProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                int systolic = spinnerSystolicBP.getValue();
                int diastolic = spinnerDiastolicBP.getValue();
                String URL = "http://localhost:8084/shhc/BloodPressure/update";
                BloodPressure bloodP = new BloodPressure(diastolic, systolic, "Online", "6");
                String json = GsonUtils.objetoToJson(bloodP);
                try {
                    CustomHttpClientUtils.setValueByHttpPut(URL, json);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        });

        spinnerDiastolicBP.valueProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                int systolic = spinnerSystolicBP.getValue();
                int diastolic = spinnerDiastolicBP.getValue();
                String URL = "http://localhost:8084/shhc/BloodPressure/update";
                BloodPressure bloodP = new BloodPressure(diastolic, systolic, "Online", "6");
                String json = GsonUtils.objetoToJson(bloodP);
                try {
                    CustomHttpClientUtils.setValueByHttpPut(URL, json);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        buttonServer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                URL = URL.substring(0, URL.length() - 4);
                URL = URL.concat(textServerPort.getText());
                System.out.println(URL);
            }
        });
    }

}
