package br.unesp.rc.shhc.principal;

import br.unesp.rc.gson.utils.GsonUtils;
import br.unesp.rc.httpclient.utils.CustomHttpClientUtils;
import br.unesp.rc.shhc.AirFlow;
import br.unesp.rc.shhc.BloodPressure;
import br.unesp.rc.shhc.Glucose;
import br.unesp.rc.shhc.HeartRate;
import br.unesp.rc.shhc.PulseOxygen;
import br.unesp.rc.shhc.Temperature;

import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.SourceDataLine;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn.SortType;

import br.unesp.rc.shhc.principal.ControllerView;

public class ControllerChart{

 
    public void initialize() {


        XYChart.Series<String, Number> TempSeries = new XYChart.Series<String, Number>();
        XYChart.Series<String, Number> AirSeries = new XYChart.Series<String, Number>();
        // arrumar 
        XYChart.Series<String, Number> BloodPSeries = new XYChart.Series<String, Number>();
        //
        XYChart.Series<String, Number> HeartRSeries = new XYChart.Series<String, Number>();
        XYChart.Series<String, Number> GlucoseSeries = new XYChart.Series<String, Number>();
        XYChart.Series<String, Number> PulseOSeries = new XYChart.Series<String, Number>();

        

        // Cria o loop de atualização do gráfico
        AnimationTimer loop = new AnimationTimer() {
            private long lastUpdate = 0;
            
            
            @Override
            public void handle(long now) {
                System.out.println("BATATA");
                // o loop executa uma vez a cada 2 segundos
                if (now - lastUpdate >= 2_000_000_000L) {

                    // Insere um novo ponto de dados na série de Temperatura
                    String url = "http://localhost:8084/shhc/Temperature/";
                    Temperature temperature;
                    String json = CustomHttpClientUtils.getValueByHttp(url);
                    temperature = (Temperature) GsonUtils.jsonToObject(json, Temperature.class);
                    //Atuliza o grafico
                    updateChartTemperature(temperature.getValue(), TempSeries);
                    System.out.println("DENTRO DO CHART:" + temperature.getValue());

                    // Insere um novo ponto de dados na série de AirFlow
                    url = "http://localhost:8084/shhc/AirFlow/";
                    AirFlow airFlow;
                    json = CustomHttpClientUtils.getValueByHttp(url);
                    airFlow = (AirFlow) GsonUtils.jsonToObject(json, AirFlow.class);
                    updateChart(airFlow.getValue(), AirSeries);

                    // Insere um novo ponto de dados na série de BloodPressure
                    url = "http://localhost:8084/shhc/BloodPressure/";
                    BloodPressure bloodPressure;
                    json = CustomHttpClientUtils.getValueByHttp(url);
                    bloodPressure = (BloodPressure) GsonUtils.jsonToObject(json, BloodPressure.class);
                    updateChart(bloodPressure.getSystolicValue(), BloodPSeries);
                    //COLOCAR A SEGUNDA PARTE DO BLOOD PRESSURE

                    // Insere um novo ponto de dados na série de Glucose
                    url = "http://localhost:8084/shhc/Glucose/";
                    Glucose glucose;
                    json = CustomHttpClientUtils.getValueByHttp(url);
                    glucose = (Glucose) GsonUtils.jsonToObject(json, Glucose.class);
                    updateChart(glucose.getValue(), GlucoseSeries);

                    // Insere um novo ponto de dados na série de HeartRate
                    url = "http://localhost:8084/shhc/HeartRate/";
                    HeartRate heartRate;
                    json = CustomHttpClientUtils.getValueByHttp(url);
                    heartRate = (HeartRate) GsonUtils.jsonToObject(json, HeartRate.class);
                    updateChart(heartRate.getValue(), HeartRSeries);

                    // Insere um novo ponto de dados na série de PulseOxygen
                    url = "http://localhost:8084/shhc/PulseOxygen/";
                    PulseOxygen pulseOxygen;
                    json = CustomHttpClientUtils.getValueByHttp(url);
                    pulseOxygen = (PulseOxygen) GsonUtils.jsonToObject(json, PulseOxygen.class);
                    updateChart(pulseOxygen.getValue(), PulseOSeries);

                    // Atualiza o lastUpdate para o tempo atual
                    lastUpdate = now;
                }
            }
        };
        loop.start();

    }

    public void updateChartTemperature(int value, XYChart.Series<String, Number> series) {

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        // Cria os graficos
        LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
        lineChart = ControllerView.listaCharts.get(2);
        int y = 0;
        if(series.getData().size()>0){
            y = Integer.parseInt(series.getData().get(series.getData().size() - 1).getXValue());
        }

        series.getData().add(new XYChart.Data<String, Number>(String.valueOf(y+1), value));
        if (series.getData().size() > 10) {
            series.getData().remove(0);
        }
       lineChart.getData().add(series);
       ControllerView.listaCharts.set(2, lineChart);
    }

    public void updateChart(int value, XYChart.Series<String, Number> series) {
        int y = 0;
        if(series.getData().size()>0){
            y = Integer.parseInt(series.getData().get(series.getData().size() - 1).getXValue());
        }

        series.getData().add(new XYChart.Data<String, Number>(String.valueOf(y+1), value));
        if (series.getData().size() > 10) {
            series.getData().remove(0);
        }
       
    }
}
