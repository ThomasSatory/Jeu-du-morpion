package Controlleur;

import Vue.*;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class ControllerMenu {
    @FXML
    public Button stop;


    @FXML
    public Rectangle Humain;



    @FXML
    public Button play;

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

    @FXML
    private ImageView ghost = new ImageView();

    @FXML
    private ImageView ghost2 = new ImageView();

    @FXML
    private ImageView ghost3 = new ImageView();

    @FXML
    private ImageView ghost4 = new ImageView();

    private Stage stage ;
    private Scene scene ;

    public TranslateTransition translate ;
    public TranslateTransition translate2 ;
    public TranslateTransition translate3 ;
    public TranslateTransition translate4 ;

    public MediaPlayer mediaPlayer;

    public int width=10;
    public int height=30;


    public void initialize(){
        Difficulte.setVisible(false);
        Translation();
        stop.setVisible(false);
        Image image2= new Image(getClass().getResourceAsStream("/images/mute.png"),width-10,height-6,true,true);
        Image image= new Image(getClass().getResourceAsStream("/images/sound.png"),width-10,height-6,true,true);
        play.setGraphic(new ImageView(image2));
        stop.setGraphic(new ImageView(image));
    }

    public void setButtons(ActionEvent event){

    }


    public void Translation(){
        translate=new TranslateTransition(Duration.millis(2000), ghost);
        translate.setByX(160.00);
        translate.setAutoReverse(true);
        translate.setCycleCount(100);
        translate.play();

        translate2=new TranslateTransition(Duration.millis(2000), ghost2);
        translate2.setByX(-160.00);
        translate2.setAutoReverse(true);
        translate2.setCycleCount(100);
        translate2.play();

        translate3=new TranslateTransition(Duration.millis(2000), ghost3);
        translate3.setByX(-160.00);
        translate3.setAutoReverse(true);
        translate3.setCycleCount(100);
        translate3.play();

        translate4=new TranslateTransition(Duration.millis(2000), ghost4);
        translate4.setByX(160.00);
        translate4.setAutoReverse(true);
        translate4.setCycleCount(100);
        translate4.play();
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

    @FXML
    public void playMusique(ActionEvent event) {
        String s = "resources/musique.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(0.1);
        mediaPlayer.play();
        play.setVisible(false);
        stop.setVisible(true);
    }

    @FXML
    public void stopMusique(ActionEvent event) {
        mediaPlayer.setMute(true);
        play.setVisible(true);
        stop.setVisible(false);
    }
}

