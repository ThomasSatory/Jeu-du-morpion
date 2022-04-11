package Controlleur;


import Vue.ViewjeuContreHumain;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;


public class ControllerModeleia {

    @FXML
    private ListView<String> MLP = new ListView<String>();

    @FXML
    private Label Notif= new Label();

    private Scene scene;
    private Stage stage;

    public void initialize(){
        Notif.setVisible(false);
        ShowMLPs();
    }

    @FXML
    protected void ShowMLPs(){
        File repertoire = new File("resources/models");
        String liste[] = repertoire.list();
        MLP.setItems(FXCollections.observableArrayList());
        MLP.getItems().addAll(liste);
        MLP.refresh();
    }

    @FXML
    protected void onSupprimer(){
        String Fichier=MLP.getSelectionModel().getSelectedItem();
        System.out.println(Fichier);
        File file = new File("resources/models/"+Fichier);
        if(file.delete()){
            Notif.setVisible(true);
            Notif.setText(file.getName() + " a été supprimé avec succés.");
        }else{
            Notif.setVisible(true);
            Notif.setTextFill(Color.GREEN);
            Notif.setText("Opération de suppression echouée");
        }
        ShowMLPs();
    }

    @FXML
    public void onRetour(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(ViewjeuContreHumain.class.getResource("../fxmls/menu.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
