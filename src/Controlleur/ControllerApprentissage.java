package Controlleur;

import Vue.ViewApprentissage;
import Vue.ViewjeuContreIA;
import ai.*;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
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

    public Task<Double> task;

    private Stage stage = new Stage();

    @FXML
    protected void onHelloButtonClick() throws IOException {
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
                return null;
            }
        };
        progress.progressProperty().bind(task.progressProperty());
        console.textProperty().bind(task.messageProperty());
        new Thread(task).start();
    }
}

