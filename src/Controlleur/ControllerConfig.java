package Controlleur;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

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
    private Button changer = new Button();

    @FXML
    private RadioButton Facile = new RadioButton("Facile");

    @FXML
    private RadioButton Moyen = new RadioButton("Moyen");

    @FXML
    private RadioButton Difficile = new RadioButton("Difficile");

    @FXML
    private Label  Notif = new Label();

    @FXML
    private Label  ConfigFacile = new Label();

    @FXML
    private Label  ConfigMoyen = new Label();

    @FXML
    private Label  ConfigDifficile = new Label();


    @FXML
    protected void onChanger() throws IOException {
        String htext= h.getText();
        String ltext= l.getText();
        String lrtext=lr.getText();

        Path path = Paths.get("resources/config.txt");
        List<String> lines = Files.readAllLines(path);

        if(htext.length()!=0 && ltext.length()!=0 && lrtext.length()!=0){

            int hvalue = Integer.parseInt(htext);
            int lvalue = Integer.parseInt(ltext);
            float lrvalue = Float.parseFloat(lrtext);

            if (Facile.isSelected()){
                lines.add(0,"F:"+hvalue+":"+lrvalue+":"+lvalue+" ");
                lines.remove(1);
            }

            else if (Moyen.isSelected()){
                lines.add(1,"M:"+hvalue+":"+lrvalue+":"+lvalue+" ");
                lines.remove(2);
            }

            else if (Difficile.isSelected()){
                lines.add(2,"D:"+hvalue+":"+lrvalue+":"+lvalue+" ");
                lines.remove(3);
            }

            else{
                Notif.setVisible(true);
                Notif.setText("Veuillez choisir une difficulté");
            }

        } else {
            Notif.setVisible(true);
            Notif.setText("Veuillez renseigner tout les champs");
        }

        File file = new File("resources/config.txt");

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        for (int i =0 ;i< lines.size() ; i++){
            bw.write(lines.get(i));
            bw.newLine();
        }
        if((htext.length()!=0 && ltext.length()!=0 && lrtext.length()!=0) && (Facile.isSelected() || Moyen.isSelected() || Difficile.isSelected())){
            Notif.setVisible(true);
            Notif.setTextFill(Color.GREEN);
            Notif.setText("Changement effectué");
        }
        bw.close();
        showConfigs();
    }

    public void showConfigs() throws IOException {
        Path path = Paths.get("resources/config.txt");
        List<String> lines = Files.readAllLines(path);

        ConfigFacile.setText(lines.get(0));
        ConfigMoyen.setText(lines.get(1));
        ConfigDifficile.setText(lines.get(2));
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

