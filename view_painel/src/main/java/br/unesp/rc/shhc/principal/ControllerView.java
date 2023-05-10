
package br.unesp.rc.shhc.principal;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Modality;
import javafx.stage.Stage;




public class ControllerView implements Initializable {

    @FXML
    private AnchorPane Anchor_Pac;

    @FXML
    private AnchorPane MainAnchor_Pac;

    @FXML
    private Label Tab_name1;

    @FXML
    private Button button_Add;

    @FXML
    private Button button_Pac1;

    @FXML
    private Label label_Pac1;

    @FXML
    private Label label_add;

    @FXML
    private Tab tab_pac1;

    @FXML
    private TabPane tabPane;

    @FXML
    private DialogPane dialogPane;

    
    public void onOpenDialog() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewPatient.fxml"));
        Parent parent = fxmlLoader.load();
       
        Scene scene = new Scene(parent, 400, 350);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        Tab_name1.setText(label_Pac1.getText());
        button_Pac1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tabPane.getSelectionModel().select(tab_pac1);
            }
        });

        button_Add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { 

                try {
                    onOpenDialog();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                // Pane PatientDialogPane = fxmlLoader.load();
                // Cria a label do paciente
                Label label = new Label("PACIENTE");
                label.setLayoutX(label_add.getLayoutX());
                label.setLayoutY(label_add.getLayoutY());
                label.setAlignment(Pos.CENTER);
                label.setPrefWidth(93);
                label.setPrefHeight(17);
                Anchor_Pac.getChildren().add(label);

                // cria o novo guia com os graficos
                Tab newTab = new Tab(label.getText());
                tabPane.getTabs().add(newTab);

                // cria o botão do novo paciente
                newButtonPaciente(newTab);

            }
        });
    }

    public void newButtonPaciente(Tab newTab) {
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
            }
        });

        // ajusta o botão de add para a posição correta
        button_Add.setLayoutY(button_Add.getLayoutY() + 100);
        label_add.setLayoutY(label_add.getLayoutY() + 100);
    }

}
