package Vue;

import Controlleur.ControllerConfig;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewModeleConfig extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewModeleConfig.class.getResource("../fxmls/config.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 380);
        stage.setTitle("Configuration du jeu");
        Label Notif = (Label) scene.lookup("#Notif");
        Notif.setVisible(false);
        stage.setScene(scene);
        stage.show();
        ControllerConfig controllerConfig = new ControllerConfig();
        controllerConfig.showConfigs();
    }
    public static <String> void main(String[] args) {
        launch();
    }
}

