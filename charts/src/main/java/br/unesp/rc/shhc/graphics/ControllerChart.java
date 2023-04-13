package br.unesp.rc.shhc.graphics;

import br.unesp.rc.gson.utils.GsonUtils;
import br.unesp.rc.httpclient.utils.CustomHttpClientUtils;
import br.unesp.rc.shhc.model.Temperature;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class ControllerChart implements Initializable {

    @FXML
    private LineChart<String, Number> chartTemperature;

    @FXML
    private LineChart<String, Number> chartHeartRate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        XYChart.Series<String, Number> TempSeries = new XYChart.Series<String, Number>();
        chartTemperature.getData().add(TempSeries);
        chartTemperature.setLegendVisible(false);

        // Cria o loop de atualização do gráfico
        AnimationTimer loop = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                // o loop executa uma vez a cada 2 segundos
                if (now - lastUpdate >= 2_000_000_000L) {

                    // Insere um novo ponto de dados na série de Temperatura
                    String url = "http://localhost:8084/shhc/Temperature/";
                    Temperature temperature;
                    String json = CustomHttpClientUtils.getValueByHttp(url);
                    temperature = (Temperature) GsonUtils.jsonToObject(json, Temperature.class);
                    updateChart(temperature.getValue(), TempSeries);

                    // Atualiza o lastUpdate para o tempo atual
                    lastUpdate = now;
                }
            }
        };
        loop.start();

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
