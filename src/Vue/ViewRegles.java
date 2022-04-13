package Vue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewRegles extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewRegles.class.getResource("../fxmls/regles.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("Les Regles");
        stage.getIcons().add(new Image(ViewRegles.class.getResourceAsStream("/images/susEst.png")));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public static <String> void main(String[] args) {
        launch();
    }
}
