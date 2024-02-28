package br.unesp.rc.shhc.dashboardphone;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.unesp.rc.shhc.SHHCPacientModel.model.Patient;
import br.unesp.rc.shhc.SHHCPacientModel.repository.PatientRepository;
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

import br.unesp.rc.shhc.SHHCModel.model.Analyzable;
import static br.unesp.rc.shhc.SHHCPacientModel.repository.PatientRepository.patients;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class ControllerView implements Initializable {

    @FXML
    private AnchorPane Anchor_Pac;
    
    @FXML
    private Accordion Accordion_Pac;
    
    @FXML
    private AnchorPane MainAnchor_Pac;

    @FXML
    private AnchorPane anchorSensors;

    @FXML
    private Button button_Add;

    @FXML
    private Label label_add;

    @FXML
    private Button button_Close;

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
    ArrayList<String> idContainersList = new ArrayList<>();
    ArrayList<Button> buttonsList = new ArrayList<>();
    ArrayList<String> labelList = new ArrayList<>();

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
        labelList.add("Name");
        labelList.add("Age");
        labelList.add("Height");
        labelList.add("Weight");
        for (Patient p : patients) {
            Patient patient = p;
            newTabPaciente(patient);
        }
       
    }

    public void newTabPaciente(Patient paciente) {
        Color color;
        Background background;
        int j = 0;
        /*TabPaneSensors.getTabs().add(newTabSensors);
        tabPane.getTabs().add(newTab);*/
       
        TitledPane titledPane = new TitledPane();
        titledPane.setText(paciente.getFirstName() + " " + paciente.getLastName());
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(335,374);
        
        Label lb = new Label();
        Label lbdatapac = new Label();
        for (int i = 0; i < 4; i++) {
            lb.setText(labelList.get(i));
            lb.setFont(new Font("System", 12));
            lb.setLayoutX(13);
            lb.setLayoutY(15 + (15 * i));
            lb.setPrefSize(45, 17);
            lb.setAlignment(Pos.CENTER_RIGHT);
            lbdatapac.setLayoutX(64);
            lbdatapac.setFont(new Font("System", 12));
            lbdatapac.setLayoutY(15 + (15 * i));
            anchorPane.getChildren().addAll(lbdatapac);
            anchorPane.getChildren().addAll(lb); 
        }
        lbdatapac = (Label)anchorPane.getChildren().get(1);
        lbdatapac.setText(paciente.getFirstName() + " " + paciente.getLastName());
        lbdatapac = (Label)anchorPane.getChildren().get(3);
        lbdatapac.setText(String.valueOf(paciente.getAge()));
        lbdatapac = (Label)anchorPane.getChildren().get(5);
        lbdatapac.setText(String.valueOf(paciente.getHeight()));
        lbdatapac = (Label)anchorPane.getChildren().get(7);
        lbdatapac.setText(String.valueOf(paciente.getWeight()));
        for (int i = 0; i < 6; i++) {
            // Cria os graficos
            Pane pane = new Pane();
            pane.setPrefSize(100, 48);
            color = Color.web("#dddddd");
            background = new Background(new BackgroundFill(color, new CornerRadii(5), null));
            pane.setBackground(background);
            lb.setText(titleList.get(i));
            lb.setFont(new Font("System", 12));
            lb.setLayoutX(5);
            lb.setLayoutY(0);
            lb.setPrefSize(92, 18);
            lb.setAlignment(Pos.CENTER);
            pane.getChildren().addAll(lb);
            lb.setFont(new Font("System", 12));
            lb.setLayoutX(13);
            lb.setLayoutY(24);
            lb.setPrefSize(76, 18);
            lb.setAlignment(Pos.CENTER);
            pane.getChildren().addAll(lb);
            if (i > 2) {
                // cria os graficos inferiores
                pane.setLayoutX(6 + (j * 110));
                pane.setLayoutY(126);
                j++;
            } else {
                // cria os graficos superiores
                pane.setLayoutX(6 + (i * 110));
                pane.setLayoutY(190);
            }
            anchorPane.getChildren().addAll(pane);
        }
        Button btn = new Button();
        btn.setText("Iniciar Procedimento" );
        btn.setPrefSize(138,28);
        btn.setLayoutX(98);
        btn.setLayoutY(274);
        anchorPane.getChildren().addAll(btn);
        int i = 0;
        Accordion_Pac.getPanes().add(titledPane);
    }

    /*public void addPatient(Patient Paciente) {

        // Cria a label do paciente
        Label namePatient = new Label(Paciente.getFirstName() + " " + Paciente.getLastName());
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

    }*/

    public void analysis(Analyzable analyzable, Pane patient, String sensor, String label) {
        Color color;
        Label labelResult = (Label) patient.lookup(label);

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("ksession-rules" + sensor);

        try {
            analyzable.applyRules(kSession);
        } catch (Throwable t) {
            System.out.println("Mensagem: " + t.getMessage());
        }

        labelResult.setText(analyzable.getClazz());

        Background background;
        switch (analyzable.getIdClazz()) {
            case 1:
                color = Color.web("#e3fbe3");
                background = new Background(new BackgroundFill(color, new CornerRadii(5), null));
                patient.setBackground(background);
                labelResult.setTextFill(Color.web("#4fe40b"));
                break;
            case 2:
                color = Color.web("#ffffe0");
                background = new Background(new BackgroundFill(color, new CornerRadii(5), null));
                patient.setBackground(background);
                labelResult.setTextFill(Color.web("#ffbf00"));
                break;
            case 3:
                color = Color.web("#ffe0b5");
                background = new Background(new BackgroundFill(color, new CornerRadii(5), null));
                patient.setBackground(background);
                labelResult.setTextFill(Color.web("#ff8c00"));
                break;
            case 4:
                color = Color.web("#ffd8d4");
                background = new Background(new BackgroundFill(color, new CornerRadii(5), null));
                patient.setBackground(background);
                labelResult.setTextFill(Color.web("#ff0000"));
                break;
            case 5:
                color = Color.web("#e0bcdd");
                background = new Background(new BackgroundFill(color, new CornerRadii(5), null));
                patient.setBackground(background);
                labelResult.setTextFill(Color.web("#993399"));
                break;
            default:
                color = Color.web("#ffffff");
                background = new Background(new BackgroundFill(color, new CornerRadii(5), null));
                patient.setBackground(background);
                labelResult.setText("ERRO");
                labelResult.setTextFill(Color.web("#000000"));
                break;
        }

    }

}