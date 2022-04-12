package Vue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewjeuContreIA extends Application {
    public static int Difficulte = 0;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewjeuContreHumain.class.getResource("../fxmls/jeuContreIA.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("Jeu Contre une IA");
        stage.getIcons().add(new Image(ViewjeuContreIA.class.getResourceAsStream("/images/susEst.png")));
        stage.setScene(scene);
        stage.show();
    }
    public static <String> void main(String[] args) {
        launch();
    }
}
