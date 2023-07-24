package br.unesp.rc.shhc.dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.unesp.rc.shhc.model.Patient;
import br.unesp.rc.shhc.repository.PatientRepository;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControllerView implements Initializable {

    @FXML
    private AnchorPane Anchor_Pac;

    @FXML
    private AnchorPane MainAnchor_Pac;

    @FXML
    private Button button_Add;

    @FXML
    private Label label_add;

    @FXML
    private Tab tab_pac1;

    @FXML
    private TabPane tabPane;

    @FXML
    private DialogPane dialogPane;

    @FXML
    private Label labelAge;

    @FXML
    private Label labelHeight;

    @FXML
    private Label labelWeight;

    static Patient newPaciente = new Patient();
    ArrayList<String> titleList = new ArrayList<>();
 

    public void onOpenDialog() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewPatient.fxml"));
        Parent parent = fxmlLoader.load();

        Scene scene = new Scene(parent, 400, 350);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.showAndWait();

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        titleList.add("PulseOxygen");
        titleList.add("HeartRate");
        titleList.add("Temperature");
        titleList.add("AirFlow");
        titleList.add("Glucose");
        titleList.add("BloodPressure");

        button_Add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // Abre painel de novo paciente
                    onOpenDialog();
                    newPaciente.setIdPaciente(PatientRepository.patients.size());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (newPaciente != null) {
                    addPatient(newPaciente);
                    createContainer(newPaciente);
                    ControllerChart controllerChart = new ControllerChart();
                    controllerChart.initialize(newPaciente);
                }

            }

            
        });
    }

    private void createContainer(Patient newPaciente) {
        String port = newPaciente.getPort() + ":8080";
        
        try {
            String dockerCommand = "docker";
            String[] dockerArgs = { "run", "-p", port, "SHHCapi" };
            // Substitua "porta_interna" e "nome_da_sua_imagem" pelos valores desejados

            // Criação do processo para executar o comando Docker
            ProcessBuilder processBuilder = new ProcessBuilder(dockerArgs);
            processBuilder.command().add(0, dockerCommand);
            processBuilder.inheritIO(); // Redireciona os streams de entrada e saída padrão do processo Java para o processo Docker

            // Inicia o processo e aguarda a conclusão
            Process process = processBuilder.start();
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void newButtonPaciente(Tab newTab, Patient paciente) {
        Button button = new Button("");

        Image image = new Image(getClass().getResourceAsStream("icons/profile.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(39);
        imageView.setFitHeight(39);

        button.setGraphic(imageView);

        button.setLayoutX(23);
        button.setLayoutY(button_Add.getLayoutY());
        button.setPrefWidth(59);
        button.setPrefHeight(59);
        Color color = Color.web("#007acc");
        Background background = new Background(new BackgroundFill(color, new CornerRadii(100), null));
        button.setBackground(background);

        Anchor_Pac.getChildren().add(button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tabPane.getSelectionModel().select(newTab);
                labelHeight.setText(Float.toString(paciente.getHeight()));
                labelWeight.setText(Float.toString(paciente.getWeight()));
                labelAge.setText(Integer.toString(paciente.getAge()));

            }
        });

        // ajusta o botão de add para a posição correta
        button_Add.setLayoutY(button_Add.getLayoutY() + 100);
        label_add.setLayoutY(label_add.getLayoutY() + 100);
    }

    public void newTabPaciente(Tab newTab, Patient paciente) {

        int j = 0;
        tabPane.getTabs().add(newTab);
        AnchorPane anchorPane = new AnchorPane();
        // CRIAÇÃO DOS GRAFICOS
        anchorPane.setPrefWidth(Anchor_Pac.getPrefWidth());
        for (int i = 0; i < 6; i++) {
            // Define os Eixos
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();

            // Cria os graficos
            LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
            if (i > 2) {
                // cria os graficos inferiores
                lineChart.setLayoutX(15 + (j * 300));
                lineChart.setLayoutY(400);
                j++;
            } else {
                // cria os graficos superiores
                lineChart.setLayoutX(15 + (i * 300));
                lineChart.setLayoutY(100);
            }
            lineChart.setId(titleList.get(i) + "Chart");
            lineChart.setTitle(titleList.get(i));
            lineChart.setPrefSize(286, 220);
            lineChart.setMaxSize(286, 220);

            if (i != 5) {
                lineChart.setLegendVisible(false);
            }
            anchorPane.getChildren().addAll(lineChart);
            paciente.getListCharts().add(lineChart);
        }

        Label namePatient = new Label(paciente.getFirstName() + " " + paciente.getLastName());
        namePatient.setFont(new Font("System", 15));
        namePatient.setLayoutX(436);
        namePatient.setLayoutY(27);
        anchorPane.getChildren().addAll(namePatient);
        newTab.setContent(anchorPane);

    }

    public void addPatient(Patient newPaciente) {

        // Cria a label do paciente
        Label namePatient = new Label(newPaciente.getFirstName() + " " + newPaciente.getLastName());
        namePatient.setLayoutX(label_add.getLayoutX());
        namePatient.setLayoutY(label_add.getLayoutY());
        namePatient.setAlignment(Pos.CENTER);
        namePatient.setPrefWidth(93);
        namePatient.setPrefHeight(17);
        Anchor_Pac.getChildren().add(namePatient);

        // cria o novo guia com os graficos
        Tab newTab = new Tab(namePatient.getText());
        newTabPaciente(newTab, newPaciente);

        // cria o botão do novo paciente
        newButtonPaciente(newTab, newPaciente);

    }

    // metodo para receber as informações do paciente do ControllerDailog
    public static void paciente(Patient newPatient) {
        newPaciente = newPatient;
    }

}
