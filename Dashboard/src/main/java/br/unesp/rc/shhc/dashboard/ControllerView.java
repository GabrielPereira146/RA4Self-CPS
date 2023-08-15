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
import javafx.scene.layout.Pane;
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
    private AnchorPane anchorSensors;

    @FXML
    private Button button_Add;

    @FXML
    private Label label_add;

    @FXML
    private TabPane tabPaneSensors;

    @FXML
    private Tab tab_pac1;

    @FXML
    private Tab tab_sensors1;

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
    ArrayList<Button> buttonsList = new ArrayList<>();

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
                    ControllerChart controllerChart = new ControllerChart(ControllerView.this);
                    controllerChart.initialize(newPaciente);
                    // paneAir.setVisible(true);
                    // paneBlood.setVisible(true);
                    // paneGlucose.setVisible(true);
                    // paneHeart.setVisible(true);
                    // paneOxygen.setVisible(true);
                    // paneTemp.setVisible(true);
                }

            }

        });
    }

    private void createContainer(Patient newPaciente) {
        String port = newPaciente.getPort() + ":8080";
        String containerName = Integer.toString(newPaciente.getIdPaciente()) + newPaciente.getFirstName();
        System.out.println(port);
        try {
            String dockerCommand = "docker";
            String[] dockerArgs = { "run", "-p", port, "--name", containerName, "shhcapi" };

            // Criação do processo para executar o comando Docker
            ProcessBuilder processBuilder = new ProcessBuilder(dockerArgs);
            processBuilder.command().add(0, dockerCommand);
            processBuilder.inheritIO(); // Redireciona os streams de entrada e saída padrão do processo Java para o
                                        // processo Docker
            processBuilder.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void newButtonPaciente(Tab newTab, Patient paciente, Tab NewTabSensors) {
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
        button.setId(paciente.getIdPaciente() + paciente.getFirstName());
        Color color = Color.web("#007acc");
        Background background = new Background(new BackgroundFill(color, new CornerRadii(100), null));
        button.setBackground(background);
        buttonsList.add(button);
        Anchor_Pac.getChildren().add(button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tabPane.getSelectionModel().select(newTab);
                tabPaneSensors.getSelectionModel().select(NewTabSensors);
                labelHeight.setText(Float.toString(paciente.getHeight()));
                labelWeight.setText(Float.toString(paciente.getWeight()));
                labelAge.setText(Integer.toString(paciente.getAge()));

            }
        });

        // ajusta o botão de add para a posição correta
        button_Add.setLayoutY(button_Add.getLayoutY() + 100);
        label_add.setLayoutY(label_add.getLayoutY() + 100);
    }

    public void newTabPaciente(Tab newTab, Patient paciente, Tab newTabSensors) {

        int j = 0;
        tabPaneSensors.getTabs().add(newTabSensors);
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

        int i = 0;
        AnchorPane targetAnchorPane = new AnchorPane();
        for (javafx.scene.Node node : anchorSensors.getChildren()) {
            if (node instanceof Pane) {
                Pane clonedPane = new Pane();
                clonedPane.setId(titleList.get(i) + "Pane");
                i++;
                clonedPane.setPrefSize(((Pane) node).getWidth(), ((Pane) node).getHeight());
                clonedPane.setStyle(((Pane) node).getStyle());
                clonedPane.setLayoutX(((Pane) node).getLayoutX());
                clonedPane.setLayoutY(((Pane) node).getLayoutY());

                for (javafx.scene.Node paneNode : ((Pane) node).getChildren()) {
                    if (paneNode instanceof Label) {
                        Label clonedLabel = new Label(((Label) paneNode).getText());
                        clonedLabel.setLayoutX(((Label) paneNode).getLayoutX());
                        clonedLabel.setLayoutY(((Label) paneNode).getLayoutY());
                        clonedLabel.setId(((Label) paneNode).getId());
                        // clonedLabel.setTextFill(((Label) node).getTextFill());
                        clonedPane.getChildren().add(clonedLabel);
                    }
                }

                targetAnchorPane.getChildren().add(clonedPane);
                paciente.getListPane().add(clonedPane);
            }
        }
        newTabSensors.setContent(targetAnchorPane);

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
        Tab newTabSensors = new Tab(newPaciente.getIdPaciente() + namePatient.getText() + "Sensors");
        newTabPaciente(newTab, newPaciente, newTabSensors);

        // cria o botão do novo paciente
        newButtonPaciente(newTab, newPaciente, newTabSensors);

    }

    // metodo para receber as informações do paciente do ControllerDailog
    public static void paciente(Patient newPatient) {
        newPaciente = newPatient;
    }

    public void patientStats(Patient errorPaciente, int stats) {
        Color color;
        if (stats == 0) {
            color = Color.web("#007acc");
        } else {
            color = Color.web("#F0BE00");
        }
        Background background = new Background(new BackgroundFill(color, new CornerRadii(100), null));
        for (Button b : buttonsList) {
            if (b.getId().equals(errorPaciente.getIdPaciente() + errorPaciente.getFirstName())) {
                b.setBackground(background);
            }
        }
    }

    public void tempAnalysis(int value, Pane patient) {
        Color color;
        Label labelTempe = (Label) patient.lookup("#labelTemp");
        if (value >= 36 && value < 37) {
            color = Color.web("#e3fbe3");
            Background background = new Background(new BackgroundFill(color, new CornerRadii(5), null));
            patient.setBackground(background);
            labelTempe.setText("Normal");
            labelTempe.setTextFill(Color.web("#4fe40b"));
        } else if (value >= 37 && value < 38) {
            color = Color.web("#ffffe0");
            Background background = new Background(new BackgroundFill(color, new CornerRadii(5), null));
            patient.setBackground(background);
            labelTempe.setText("Febril");
            labelTempe.setTextFill(Color.web("#ffbf00"));
        } else if (value >= 38 && value < 39) {
            color = Color.web("#ffe0b5");
            Background background = new Background(new BackgroundFill(color, new CornerRadii(5), null));
            patient.setBackground(background);
            labelTempe.setText("Febre");
            labelTempe.setTextFill(Color.web("#ff8c00"));
        } else if (value >= 39 && value < 40) {
            color = Color.web("#ffd8d4");
            Background background = new Background(new BackgroundFill(color, new CornerRadii(5), null));
            patient.setBackground(background);
            labelTempe.setText("Febre alta");
            labelTempe.setTextFill(Color.web("#ff0000"));
        } else if (value >= 40 && value < 42) {
            color = Color.web("#e0bcdd");
            Background background = new Background(new BackgroundFill(color, new CornerRadii(5), null));
            patient.setBackground(background);
            labelTempe.setText("Febre altissima");
            labelTempe.setTextFill(Color.web("#993399"));
        } else {
            color = Color.web("#ffffff");
            Background background = new Background(new BackgroundFill(color, new CornerRadii(5), null));
            patient.setBackground(background);
            labelTempe.setText("ERRO");
            labelTempe.setTextFill(Color.web("#000000"));
        }
    }
}
