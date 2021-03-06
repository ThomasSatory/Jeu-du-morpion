package Controlleur;

import Vue.ViewjeuContreHumain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerAbout {

    private Stage stage;
    private Scene scene;

    /**
     * Function : Retour au menu lors de l'appui du bouton Retour
     * @param event
     * @throws IOException
     */
    @FXML
    public void onRetour(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(ViewjeuContreHumain.class.getResource("../fxmls/menu.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Menu Pricipal");
        stage.show();
    }
}
