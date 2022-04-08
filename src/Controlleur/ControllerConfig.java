package Controlleur;

import Vue.ViewModeleConfig;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.*;
import javax.swing.text.Element;
import javax.swing.text.html.ListView;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ControllerConfig {
    @FXML
    private TextField h = new TextField();

    @FXML
    private TextField l = new TextField();

    @FXML
    private TextField lr = new TextField();

    @FXML
    public ChoiceBox difficulte = new ChoiceBox(FXCollections.observableArrayList(
            "Facile", "Moyen", "Difficile"));

    @FXML
    private Button changer = new Button();

    @FXML
    private RadioButton Facile = new RadioButton("Facile");

    @FXML
    private RadioButton Moyen = new RadioButton("Moyen");

    @FXML
    private RadioButton Difficile = new RadioButton("Difficile");

    public ControllerConfig() throws IOException {
    }

    @FXML
    protected void onChanger() throws IOException {
        String htext= h.getText();
        String ltext= l.getText();
        String lrtext=lr.getText();
        if(htext.length()!=0 && ltext.length()!=0 && lrtext.length()!=0){
            int hvalue = Integer.parseInt(htext);
            int lvalue = Integer.parseInt(ltext);
            float lrvalue = Float.parseFloat(lrtext);
        }
        Path path = Paths.get("resources/config.txt");
        List<String> lines = Files.readAllLines(path);
        if (Facile.isSelected()){
            lines.add(0,"F:"+h+":"+lr+":"+l+" ");
            lines.remove(0);
        }
        else if (Moyen.isSelected()){
            lines.add(1,"M:"+h+":"+lr+":"+l+" ");
            lines.remove(1);
        }
        else if (Difficile.isSelected()){
            lines.add(2,"D:"+h+":"+lr+":"+l+" ");
            lines.remove(2);
        }

        File file = new File("resources/config.txt");

        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i =0 ;i< lines.size() ; i++){
            bw.write(lines.get(i));
            bw.write("\r");
        }
        bw.close();

    }

    protected ListView onStart() throws IOException {
        Path path = Paths.get("resources/config.txt");
        List<String> lines = Files.readAllLines(path);
        ListView Liste = new ListView((Element) lines);
        return Liste ;
    }

    @FXML
    protected void onFacile() throws IOException{ //pour disable les autres difficultées
        Moyen.setSelected(false);
        Difficile.setSelected(false);
    }

    @FXML
    protected void onMoyen() throws IOException{
        Facile.setSelected(false);
        Difficile.setSelected(false);
    }

    @FXML
    protected void onDifficile() throws IOException{
        Facile.setSelected(false);
        Moyen.setSelected(false);
    }
}

