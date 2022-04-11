package Controlleur;

import Vue.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
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

    @FXML
    private AnchorPane anchorpane = new AnchorPane();

    private Stage stage ;
    private Scene scene ;

    public void initialize(){
        Difficulte.setVisible(false);
    }

    @FXML
    protected void onJouerContreIA(ActionEvent event) throws IOException {
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
                Parent root = FXMLLoader.load(ViewjeuContreIA.class.getResource("../fxmls/jeuContreIA.fxml"));
                stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene=new Scene(root);
                stage.setTitle("Jeu contre IA");
            }else{
                ViewApprentissage.Difficulté=1;
                Parent root = FXMLLoader.load(ViewApprentissage.class.getResource("../fxmls/apprentissage.fxml"));
                stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene=new Scene(root);
                stage.setTitle("Apprentissage");
            }
            stage.setScene(scene);
            stage.show();
        }

        else if(Moyen.isSelected()){
            String mode=lines.get(1);
            String modesplitted[]=mode.split(":");
            h=Integer.parseInt(modesplitted[1]);
            lr=Double.parseDouble(modesplitted[2]);
            l=Integer.parseInt(modesplitted[3]);
            Difficulte.setVisible(false);
            System.out.println("moyen");
            if (VerifConfig(h,lr,l)){
                Parent root = FXMLLoader.load(ViewjeuContreIA.class.getResource("../fxmls/jeuContreIA.fxml"));
                stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene=new Scene(root);
                stage.setTitle("Jeu contre IA");
            } else {
                ViewApprentissage.Difficulté=2;
                Parent root = FXMLLoader.load(ViewApprentissage.class.getResource("../fxmls/apprentissage.fxml"));
                stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene=new Scene(root);
                stage.setTitle("Apprentissage");
            }
            stage.setScene(scene);
            stage.show();
        }

        else if (Difficile.isSelected()){
            String mode=lines.get(2);
            String modesplitted[]=mode.split(":");
            h=Integer.parseInt(modesplitted[1]);
            lr=Double.parseDouble(modesplitted[2]);
            l=Integer.parseInt(modesplitted[3]);
            Difficulte.setVisible(false);
            System.out.println("difficile");
            if (VerifConfig(h,lr,l)){
                Parent root = FXMLLoader.load(ViewjeuContreIA.class.getResource("../fxmls/jeuContreIA.fxml"));
                stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene=new Scene(root);
                stage.setTitle("Jeu contre IA");
            } else {
                ViewApprentissage.Difficulté=3;
                Parent root = FXMLLoader.load(ViewApprentissage.class.getResource("../fxmls/apprentissage.fxml"));
                stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene=new Scene(root);
                stage.setTitle("Apprentissage");
            }
            stage.setScene(scene);
            stage.show();
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
    protected void onJouerContreHumain(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(ViewjeuContreHumain.class.getResource("../fxmls/jeuContreHumain.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setTitle("1v1");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onModeleIA(ActionEvent event) throws IOException  {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmls/modeleia.fxml"));
        Parent ReportManager = loader.load();
        Scene ReportManagerScene = new Scene(ReportManager);

        Stage window = (Stage)anchorpane.getScene().getWindow();

        window.setTitle("Modeles IA");
        window.setScene(ReportManagerScene);

        window.show();
    }

    @FXML
    protected void onConfiguration(ActionEvent event) throws IOException  {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmls/config.fxml"));
        Parent ReportManager = loader.load();
        Scene ReportManagerScene = new Scene(ReportManager);

        Stage window = (Stage)anchorpane.getScene().getWindow();

        window.setTitle("Configurations");
        window.setScene(ReportManagerScene);

        window.show();
    }

    @FXML
    protected void onRegles(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmls/regles.fxml"));
        Parent ReportManager = loader.load();
        Scene ReportManagerScene = new Scene(ReportManager);

        Stage window = (Stage)anchorpane.getScene().getWindow();

        window.setTitle("Regles");
        window.setScene(ReportManagerScene);

        window.show();
    }


    @FXML
    protected void onAbout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmls/about.fxml"));
        Parent ReportManager = loader.load();
        Scene ReportManagerScene = new Scene(ReportManager);

        Stage window = (Stage)anchorpane.getScene().getWindow();

        window.setTitle("About");
        window.setScene(ReportManagerScene);

        window.show();
    }

    @FXML
    protected void onHelp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmls/regles.fxml"));
        Parent ReportManager = loader.load();
        Scene ReportManagerScene = new Scene(ReportManager);

        Stage window = (Stage)anchorpane.getScene().getWindow();

        window.setTitle("Help");
        window.setScene(ReportManagerScene);

        window.show();
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

