package br.unesp.rc.shhc.dashboard;

import br.unesp.rc.gson.utils.GsonUtils;
import br.unesp.rc.httpclient.utils.CustomHttpClientUtils;
import br.unesp.rc.shhc.AirFlow;
import br.unesp.rc.shhc.BloodPressure;
import br.unesp.rc.shhc.Glucose;
import br.unesp.rc.shhc.HeartRate;
import br.unesp.rc.shhc.PulseOxygen;
import br.unesp.rc.shhc.Temperature;
import br.unesp.rc.shhc.model.Patient;
import javafx.animation.AnimationTimer;
import javafx.scene.chart.XYChart;

public class ControllerChart {

    public void initialize(Patient paciente) {
        String URL = "http://localhost:" + paciente.getPort();
        //Criação das sereis para os graficos
        XYChart.Series<String, Number> TempSeries = new XYChart.Series<String, Number>();
        XYChart.Series<String, Number> AirSeries = new XYChart.Series<String, Number>();

        XYChart.Series<String, Number> BloodPDSeries = new XYChart.Series<String, Number>();
        BloodPDSeries.setName("Diastolic");
        XYChart.Series<String, Number> BloodPSSeries = new XYChart.Series<String, Number>();
        BloodPSSeries.setName("Systolic");

        XYChart.Series<String, Number> HeartRSeries = new XYChart.Series<String, Number>();
        XYChart.Series<String, Number> GlucoseSeries = new XYChart.Series<String, Number>();
        XYChart.Series<String, Number> PulseOSeries = new XYChart.Series<String, Number>();
        // Inicialização dos Graficos com a series
        paciente.getListCharts().get(0).getData().add(PulseOSeries);
        paciente.getListCharts().get(1).getData().add(HeartRSeries);
        paciente.getListCharts().get(2).getData().add(TempSeries);
        paciente.getListCharts().get(3).getData().add(AirSeries);
        paciente.getListCharts().get(4).getData().add(GlucoseSeries);
        paciente.getListCharts().get(5).getData().add(BloodPDSeries);
        paciente.getListCharts().get(5).getData().add(BloodPSSeries);

        // Cria o loop de atualização do gráfico
        AnimationTimer loop = new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {

                // o loop executa uma vez a cada 2 segundos
                if (now - lastUpdate >= 2_000_000_000L) {

                    // Insere um novo ponto de dados na série de PulseOxygen
                    String url = URL + "/shhc/PulseOxygen/";
                    PulseOxygen pulseOxygen;
                    String json = CustomHttpClientUtils.getValueByHttp(url);
                    pulseOxygen = (PulseOxygen) GsonUtils.jsonToObject(json, PulseOxygen.class);
                    updateChart(pulseOxygen.getValue(), PulseOSeries);

                    // Insere um novo ponto de dados na série de HeartRate
                    url =  URL + "/shhc/HeartRate/";
                    HeartRate heartRate;
                    json = CustomHttpClientUtils.getValueByHttp(url);
                    heartRate = (HeartRate) GsonUtils.jsonToObject(json, HeartRate.class);
                    updateChart(heartRate.getValue(), HeartRSeries);

                    // Insere um novo ponto de dados na série de Temperatura
                    url =  URL + "/shhc/Temperature/";
                    Temperature temperature;
                    json = CustomHttpClientUtils.getValueByHttp(url);
                    temperature = (Temperature) GsonUtils.jsonToObject(json, Temperature.class);
                    // Atuliza o grafico
                    updateChart(temperature.getValue(), TempSeries);

                    // Insere um novo ponto de dados na série de AirFlow
                    url =  URL + "/shhc/AirFlow/";
                    AirFlow airFlow;
                    json = CustomHttpClientUtils.getValueByHttp(url);
                    airFlow = (AirFlow) GsonUtils.jsonToObject(json, AirFlow.class);
                    updateChart(airFlow.getValue(), AirSeries);

                    // Insere um novo ponto de dados na série de Glucose
                    url =  URL + "/shhc/Glucose/";
                    Glucose glucose;
                    json = CustomHttpClientUtils.getValueByHttp(url);
                    glucose = (Glucose) GsonUtils.jsonToObject(json, Glucose.class);
                    updateChart(glucose.getValue(), GlucoseSeries);

                    // Insere um novo ponto de dados na série de BloodPressure
                    url = URL + "/shhc/BloodPressure/";
                    BloodPressure bloodPressure;
                    json = CustomHttpClientUtils.getValueByHttp(url);
                    bloodPressure = (BloodPressure) GsonUtils.jsonToObject(json, BloodPressure.class);
                    updateChart(bloodPressure.getDiastolicValue(), BloodPDSeries);
                    updateChart(bloodPressure.getSystolicValue(), BloodPSSeries);

                    // Atualiza o lastUpdate para o tempo atual
                    lastUpdate = now;
                }
            }
        };
        loop.start();

    }

    public void updateChart(int value, XYChart.Series<String, Number> series) {
        int y = 0;
        if (series.getData().size() > 0) {
            y = Integer.parseInt(series.getData().get(series.getData().size() - 1).getXValue());
        }

        series.getData().add(new XYChart.Data<String, Number>(String.valueOf(y + 1), value));
        if (series.getData().size() > 10) {
            series.getData().remove(0);
        }
    }
}
