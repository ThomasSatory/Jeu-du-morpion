import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import java.io.IOException;

public class ViewMenu extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewApprentissage.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        ToggleGroup toggleGroup= new ToggleGroup();
        stage.setTitle("Menu principal");
        stage.setScene(scene);
        stage.show();
    }
    public static <String> void main(String[] args) {
        launch();
    }
}

