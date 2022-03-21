import ai.*;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

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
            ViewApprentissage viewapprentissage= new ViewApprentissage();
            viewapprentissage.start(stage);
        }else if(Moyen.isSelected()){
            Difficulte.setVisible(false);
            ViewApprentissage viewapprentissage= new ViewApprentissage();
            viewapprentissage.start(stage);
        }else if (Difficile.isSelected()){
            Difficulte.setVisible(false);
            ViewApprentissage viewapprentissage= new ViewApprentissage();
            viewapprentissage.start(stage);
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

