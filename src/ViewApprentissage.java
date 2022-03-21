import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ViewApprentissage extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene=getScene();
        stage.setTitle("Apprentissage MLP");
        stage.setScene(scene);
        stage.show();
    }

    public Scene getScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewApprentissage.class.getResource("apprentissage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        return scene;
    }

    public static <String> void main(String[] args) {
        launch();
    }
}

