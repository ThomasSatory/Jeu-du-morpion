package Vue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class ViewApprentissage extends Application {
    public static int Difficulté;
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene=getScene();
        stage.setTitle("Apprentissage MLP");
        stage.getIcons().add(new Image(ViewApprentissage.class.getResourceAsStream("/images/susEst.png")));
        stage.setScene(scene);
        stage.show();
    }

    public Scene getScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewApprentissage.class.getResource("../fxmls/apprentissage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        return scene;
    }

    public static int getDifficulté() {
        return Difficulté;
    }

    public static <String> void main(String[] args) {
        launch();
    }
}

