package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;

import javax.swing.text.Element;
import javax.swing.text.html.ListView;
import java.awt.*;
import java.io.*;
import java.util.List;

public class ControllerModeleia {
    @FXML
    Label nbfichiers = new Label();

    protected void onStart() {
        File repertoire = new File("resources/models");
        String liste[] = repertoire.list();
        int n = liste.length;
        nbfichiers.setText("Il y a "+n+" Fichiers");
        ObservableList<String> ListFichiers = null;
        if (n != 0) {
            ListFichiers = FXCollections.observableArrayList();
            for (int i = 0; i < n; i++) {
                ListFichiers.add(liste[i]);
            }
        }
        ListView listView=new ListView((Element) ListFichiers);
    }

    protected void onSupprimer(){

    }
}
