package br.unesp.rc.shhc.graphics;

import br.unesp.rc.gson.utils.GsonUtils;
import br.unesp.rc.httpclient.utils.CustomHttpClientUtils;
import br.unesp.rc.shhc.model.Temperature;

import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class ControllerChart implements Initializable {

    Queue<Integer> queue = new LinkedList<>();

    @FXML
    private LineChart<String, Number> chartTemperature;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String url = "http://localhost:8084/shhc/Temperature/";
        AnimationTimer loop = new AnimationTimer() {
            Temperature temperature;

            @Override
            public void handle(long now) {
                try {
                    String json = CustomHttpClientUtils.getValueByHttp(url);
                    temperature = (Temperature) GsonUtils.jsonToObject(json, Temperature.class);
                    updateChart(temperature.getValue());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        loop.start();

    }

    public void updateChart(int temperature) {
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        if (queue.size() < 10) {
            queue.offer(temperature);
        } else {

            queue.poll();
            queue.offer(temperature);
        }
        chartTemperature.getData().clear();

        int position = 0;
        for (Integer elemento : queue) {
            series.getData().add(new XYChart.Data<String, Number>(String.valueOf(position), elemento));
            position++;
        }
        chartTemperature.getData().add(series);

    }
}
