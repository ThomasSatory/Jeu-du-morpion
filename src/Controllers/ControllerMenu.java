package Controllers;

import Views.ViewAbout;
import Views.ViewApprentissage;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;


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
        if (Facile.isSelected()){
            Difficulte.setVisible(false);
            System.out.println("facile");
            ViewApprentissage viewApprentissage= new ViewApprentissage();
            viewApprentissage.Difficulté=1;
            System.out.println(viewApprentissage.Difficulté);
            viewApprentissage.start(stage);
        }else if(Moyen.isSelected()){
            Difficulte.setVisible(false);
            System.out.println("moyen");
            ViewApprentissage viewApprentissage= new ViewApprentissage();
            viewApprentissage.Difficulté=2;
            System.out.println(viewApprentissage.Difficulté);
            viewApprentissage.start(stage);
        }else if (Difficile.isSelected()){
            Difficulte.setVisible(false);
            System.out.println("difficile");
            ViewApprentissage viewApprentissage= new ViewApprentissage();
            viewApprentissage.Difficulté=3;
            System.out.println(viewApprentissage.Difficulté);
            viewApprentissage.start(stage);
        }
        else {
            Difficulte.setVisible(true);
            Difficulte.setText("Veuillez choisir une difficulté");
        }
    }

    @FXML
    protected void onJouerContreHumain() throws IOException{

    }

    @FXML
    protected void onModeleIA() throws IOException  {

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

