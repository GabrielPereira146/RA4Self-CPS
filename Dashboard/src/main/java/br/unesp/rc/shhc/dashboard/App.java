package br.unesp.rc.shhc.dashboard;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("MainPainel"), 1280, 600);
    stage.setMaximized(true);
    stage.setMinWidth(1360);
    stage.setMinHeight(600);

    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MainPainel.fxml"));
    Parent root = fxmlLoader.load();  // Carregar o FXML primeiro
    ControllerView controller = fxmlLoader.getController();  // Obter o controlador depois

    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
            // Exibe uma mensagem de confirmação
            event.consume(); // Consumir o evento para impedir o fechamento automático da janela

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Tem certeza que deseja fechar a aplicação? ",
                    ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            // Se o usuário clicar em "Sim", fecha a janela
            if (alert.getResult() == ButtonType.YES) {
                controller.cleanContainers();
                stage.close();
            }
        }
    });

    stage.setScene(new Scene(root));
    stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}