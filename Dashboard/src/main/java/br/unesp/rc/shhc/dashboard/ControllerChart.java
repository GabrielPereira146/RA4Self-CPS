package br.unesp.rc.shhc.dashboard;

import br.unesp.rc.gson.utils.GsonUtils;
import br.unesp.rc.httpclient.utils.CustomHttpClientUtils;
import br.unesp.rc.shhc.SHHCModel.model.AirFlow;
import br.unesp.rc.shhc.SHHCModel.model.BloodPressure;
import br.unesp.rc.shhc.SHHCModel.model.Glucose;
import br.unesp.rc.shhc.SHHCModel.model.HeartRate;
import br.unesp.rc.shhc.SHHCModel.model.PulseOxygen;
import br.unesp.rc.shhc.SHHCModel.model.Temperature;
import br.unesp.rc.shhc.SHHCPacientModel.model.Patient;
import javafx.animation.AnimationTimer;
import javafx.scene.chart.XYChart;


public class ControllerChart {

    ControllerView controller;
    public ControllerChart(ControllerView controller){
        this.controller = controller;
    }
    int status = 1;
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
                    String json = CustomHttpClientUtils.getValueByHttp(url);//Pega o valor da API
                    if(json != null){
                        pulseOxygen = (PulseOxygen) GsonUtils.jsonToObject(json, PulseOxygen.class);
                        updateChart(pulseOxygen.getValue(), PulseOSeries);
                        controller.pulseOxygenAnalysis(pulseOxygen.getValue(), paciente.getListPane().get(3));
                        status = 0;
                    }
                    
                    // Insere um novo ponto de dados na série de HeartRate
                    url =  URL + "/shhc/HeartRate/";
                    HeartRate heartRate;
                    json = CustomHttpClientUtils.getValueByHttp(url);
                    if(json != null){
                        heartRate = (HeartRate) GsonUtils.jsonToObject(json, HeartRate.class);
                        updateChart(heartRate.getValue(), HeartRSeries);
                        controller.heartRateAnalysis(heartRate.getValue(), paciente.getListPane().get(1));
                        status = 0;
                    }
                    
                    // Insere um novo ponto de dados na série de Temperatura
                    url =  URL + "/shhc/Temperature/";
                    Temperature temperature;
                    json = CustomHttpClientUtils.getValueByHttp(url);
                    if(json != null){
                        temperature = (Temperature) GsonUtils.jsonToObject(json, Temperature.class);
                        updateChart(temperature.getValue(), TempSeries);
                        controller.tempAnalysis(temperature.getValue(), paciente.getListPane().get(0));
                        status = 0;
                    }

                    // Insere um novo ponto de dados na série de AirFlow
                    url =  URL + "/shhc/AirFlow/";
                    AirFlow airFlow;
                    json = CustomHttpClientUtils.getValueByHttp(url);
                    if(json != null){
                       airFlow = (AirFlow) GsonUtils.jsonToObject(json, AirFlow.class);
                       updateChart(airFlow.getValue(), AirSeries);
                       controller.airFlowAnalysis(airFlow.getValue(), paciente.getListPane().get(4));
                       status = 0;
                    }

                    // Insere um novo ponto de dados na série de Glucose
                    url =  URL + "/shhc/Glucose/";
                    Glucose glucose;
                    json = CustomHttpClientUtils.getValueByHttp(url);
                     if(json != null){
                        glucose = (Glucose) GsonUtils.jsonToObject(json, Glucose.class);
                        updateChart(glucose.getValue(), GlucoseSeries);
                        controller.glucoseAnalysis(glucose.getValue(), paciente.getListPane().get(2));
                        status = 0;
                    }

                    // Insere um novo ponto de dados na série de BloodPressure
                    url = URL + "/shhc/BloodPressure/";
                    BloodPressure bloodPressure;
                    json = CustomHttpClientUtils.getValueByHttp(url);
                    if(json != null){
                        bloodPressure = (BloodPressure) GsonUtils.jsonToObject(json, BloodPressure.class);
                        updateChart(bloodPressure.getDiastolicValue(), BloodPDSeries);
                        updateChart(bloodPressure.getSystolicValue(), BloodPSSeries); 
                        status = 0;
                    }
                    
                    // Atualiza o stats do paciente(Ativo, inativo)
                    controller.patientStats(paciente, status);
                    status = 1;

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
