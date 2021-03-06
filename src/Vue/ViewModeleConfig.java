package Vue;

import Controlleur.ControllerConfig;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewModeleConfig extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewModeleConfig.class.getResource("../fxmls/config.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 380);
        stage.setTitle("Configuration du jeu");
        stage.getIcons().add(new Image(ViewModeleia.class.getResourceAsStream("/images/susEst.png")));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public static <String> void main(String[] args) {
        launch();
    }
}

