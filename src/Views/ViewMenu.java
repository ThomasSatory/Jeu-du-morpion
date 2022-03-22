package Views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class ViewMenu extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewMenu.class.getResource("fxmls/menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        Label Difficulte = (Label) scene.lookup("#Difficulte");
        Difficulte.setVisible(false);
        stage.setTitle("Menu principal");
        stage.setScene(scene);
        stage.show();
    }
    public static <String> void main(String[] args) {
        launch();
    }
}

