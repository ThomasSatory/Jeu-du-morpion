package Controlleur;

import Vue.ViewApprentissage;

import Vue.ViewjeuContreHumain;
import Vue.ViewjeuContreIA;
import ai.*;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.lang.invoke.SwitchPoint;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class ControllerApprentissage {
    @FXML
    private Button Boston = new Button();

    @FXML
    private Label startText = new Label();

    @FXML
    private ProgressBar progress = new ProgressBar();

    @FXML
    private TextField console = new TextField();

    @FXML
    private Button play = new Button();

    public Task<Double> task;

    private Stage stage;
    private Scene scene;


    /**
     * Function : permet de lancer des instructions à la création de la vue
     */
    public void initialize(){
        play.setVisible(false);
    }

    /**
     * Function : permet d'apprendre le modele et de le suavegarder au click sur le bouton
     * @param event
     * @throws IOException
     */
    @FXML
    protected void onHelloButtonClick(ActionEvent event) throws IOException {
        startText.setText("Apprentissage en cours");
        Boston.setText("Lancer l'apprentissage");
        Boston.setVisible(false);

        task=new Task(){
            @Override
            protected Double call() throws Exception {
                HashMap<Integer, Coup> mapTrain = Test.loadCoupsFromFile("./resources/train_dev_test/train.txt");
                double epochs = 10000 ;
                double error= 0.0;
                boolean verbose =true;
                int size = 9;
                int l;
                int h;
                double lr;

                int Difficulté = ViewApprentissage.getDifficulté();

                System.out.println(Difficulté);

                Path path = Paths.get("resources/config.txt");
                List<String> lines = Files.readAllLines(path);

                if (Difficulté==1){
                    String mode=lines.get(0);
                    String modesplitted[]=mode.split(":");
                    h=Integer.parseInt(modesplitted[1]);
                    lr=Double.parseDouble(modesplitted[2]);
                    l=Integer.parseInt(modesplitted[3]);
                }else if (Difficulté==2){
                    String mode=lines.get(1);
                    String modesplitted[]=mode.split(":");
                    h=Integer.parseInt(modesplitted[1]);
                    lr=Double.parseDouble(modesplitted[2]);
                    l=Integer.parseInt(modesplitted[3]);
                    System.out.println(l+" "+lr+" "+h);
                }else if (Difficulté==3){
                    String mode=lines.get(2);
                    String modesplitted[]=mode.split(":");
                    h=Integer.parseInt(modesplitted[1]);
                    lr=Double.parseDouble(modesplitted[2]);
                    l=Integer.parseInt(modesplitted[3]);
                }else{//mode facile par defaut
                    String mode=lines.get(0);
                    String modesplitted[]=mode.split(":");
                    h=Integer.parseInt(modesplitted[1]);
                    lr=Double.parseDouble(modesplitted[2]);
                    l=Integer.parseInt(modesplitted[3]);
                }
                int[] layers = new int[l+2];
                layers[0] = size ;
                for (int i = 0; i < l; i++) {
                    layers[i+1] = h ;
                }
                layers[layers.length-1] = size ;
                MultiLayerPerceptron net = new MultiLayerPerceptron(layers, lr, new SigmoidalTransferFunction());
                for(int i = 0; i < epochs; i++) {
                    Coup c = null;
                    while (c == null)
                        c = mapTrain.get((int) (Math.round(Math.random() * mapTrain.size())));
                    error += net.backPropagate(c.in, c.out);
                    if (i % 1000 == 0 && verbose) {
                        updateMessage("Error at step " + i +" is "+ (error/(double)i));
                    }
                    if (i % 100 == 0 && verbose){
                        updateProgress(i, epochs);
                    }
                }
                updateProgress(epochs,epochs);
                updateMessage("Apprentissage fini !");
                String file ="resources/models/MLP-"+h+"-"+lr+"-"+l+".srl";
                net.save(file);
                play.setVisible(true);
                return null;
            }
        };
        progress.progressProperty().bind(task.progressProperty());
        console.textProperty().bind(task.messageProperty());
        new Thread(task).start();
    }

    /**
     * Function : permet de switch entre la vue de l'apprentissage et celle du jeu au clic du bouton Jouer
     * @param event
     * @throws IOException
     */
    public void SwitchToGame(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(ViewjeuContreIA.class.getResource("../fxmls/jeuContreIA.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Jeu Contre IA");
        stage.show();
    }
}

