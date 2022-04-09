package Controlleur;

import Vue.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class ControllerMenu {
    @FXML
    private Button JouerContreIA = new Button();

    @FXML
    private Button JouerContreHumain = new Button();

    @FXML
    private Button ModeleIA = new Button();

    @FXML
    private Button About = new Button();

    @FXML
    private RadioButton Facile = new RadioButton("Facile");

    @FXML
    private RadioButton Moyen = new RadioButton("Moyen");

    @FXML
    private RadioButton Difficile = new RadioButton("Difficile");

    @FXML
    private Label Difficulte = new Label();

    private Stage stage = new Stage();


    @FXML
    protected void onJouerContreIA() throws IOException {
        Path path = Paths.get("resources/config.txt");
        List<String> lines = Files.readAllLines(path);
        int h , l;
        double lr;


        if (Facile.isSelected()){
            String mode=lines.get(0);
            String modesplitted[]=mode.split(":");
            h=Integer.parseInt(modesplitted[1]);
            lr=Double.parseDouble(modesplitted[2]);
            System.out.println(modesplitted[3]);
            l=Integer.parseInt(modesplitted[3]);

            Difficulte.setVisible(false);
            System.out.println("facile");
            if (VerifConfig(h,lr,l)){
                ViewjeuContreIA viewjeuContreIA = new ViewjeuContreIA();
                viewjeuContreIA.start(stage);
            }else{
                ViewApprentissage viewApprentissage= new ViewApprentissage();
                viewApprentissage.Difficulté=1;
                System.out.println(viewApprentissage.Difficulté);
                viewApprentissage.start(stage);
            }
        }else if(Moyen.isSelected()){
            String mode=lines.get(1);
            String modesplitted[]=mode.split(":");
            h=Integer.parseInt(modesplitted[1]);
            lr=Double.parseDouble(modesplitted[2]);
            l=Integer.parseInt(modesplitted[3]);
            Difficulte.setVisible(false);
            System.out.println("moyen");
            if (VerifConfig(h,lr,l)){
                ViewjeuContreIA viewjeuContreIA = new ViewjeuContreIA();
                viewjeuContreIA.start(stage);
            } else {
                ViewApprentissage viewApprentissage= new ViewApprentissage();
                viewApprentissage.Difficulté=2;
                System.out.println(viewApprentissage.Difficulté);
                viewApprentissage.start(stage);
            }


        }else if (Difficile.isSelected()){
            String mode=lines.get(2);
            String modesplitted[]=mode.split(":");
            h=Integer.parseInt(modesplitted[1]);
            lr=Double.parseDouble(modesplitted[2]);
            l=Integer.parseInt(modesplitted[3]);
            Difficulte.setVisible(false);
            System.out.println("difficile");
            if (VerifConfig(h,lr,l)){
                ViewjeuContreIA viewjeuContreIA = new ViewjeuContreIA();
                viewjeuContreIA.start(stage);
            } else {
                ViewApprentissage viewApprentissage= new ViewApprentissage();
                viewApprentissage.Difficulté=3;
                System.out.println(viewApprentissage.Difficulté);
                viewApprentissage.start(stage);
            }
        }
        else {
            Difficulte.setVisible(true);
            Difficulte.setText("Veuillez choisir une difficulté");
        }
    }

    protected boolean VerifConfig(int h,double lr,int l) throws IOException {
        List ListeFichiers=FichiersConfig();

        int n=ListeFichiers.size();

        for (int i=0 ; i<n ;i++){
            String LigneFacile= (String) ListeFichiers.get(i);//recuperation des valeurs des differentes configurations
            String[] LigneFacileSplitted=LigneFacile.split("-");


            int hModel=Integer.parseInt(LigneFacileSplitted[1]);
            double lrModel=Double.parseDouble(LigneFacileSplitted[2]);
            String SlModel=LigneFacileSplitted[3];
            SlModel=SlModel.replace(".srl","");
            int lModel=Integer.parseInt(SlModel);

            if ((hModel==h)&&(lrModel==lr)&&(lModel==l)){
                return true;
            }
        }
        return false;
    }

    protected List FichiersConfig() throws IOException {
        File repertoire = new File("resources/models");
        String[] liste = repertoire.list();
        int n = liste.length;
        List<String> ListFichiers = null;
        if (n != 0) {
            ListFichiers = FXCollections.observableArrayList();
            for (int i = 0; i < n; i++) {
                ListFichiers.add(liste[i]);
            }
        }
        return ListFichiers;
    }


    @FXML
    protected void onJouerContreHumain() throws IOException{
        ViewjeuContreHumain viewjeuContreHumain = new ViewjeuContreHumain();
        viewjeuContreHumain.start(stage);
    }

    @FXML
    protected void onModeleIA() throws IOException  {
        ViewModeleia viewModeleia = new ViewModeleia();
        viewModeleia.start(stage);
    }

    @FXML
    protected void onConfiguration() throws IOException  {
        ViewModeleConfig viewModeleConfig = new ViewModeleConfig();
        viewModeleConfig.start(stage);
    }


    @FXML
    protected void onAbout() throws IOException {
        ViewAbout viewAbout= new ViewAbout();
        viewAbout.start(stage);
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

