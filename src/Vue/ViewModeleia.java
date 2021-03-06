package Vue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewModeleia extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewModeleia.class.getResource("../fxmls/modeleia.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        Label Notif = (Label) scene.lookup("#Notif");
        Notif.setVisible(false);
        stage.getIcons().add(new Image(ViewModeleia.class.getResourceAsStream("/images/susEst.png")));
        stage.setResizable(false);
        stage.setTitle("Modele IA");
        stage.setScene(scene);
        stage.show();
    }
    public static <String> void main(String[] args) {
        launch();
    }
}
