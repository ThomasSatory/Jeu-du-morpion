package Controlleur;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.io.*;


public class ControllerModeleia {

    @FXML
    private ListView<String> MLP = new ListView<String>();

    @FXML
    private Label Notif= new Label();

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
}
