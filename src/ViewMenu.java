import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ViewMenu extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewApprentissage.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Menu principal");
        stage.setScene(scene);
        stage.show();
    }
    public static <String> void main(String[] args) {
        launch();
    }
}

