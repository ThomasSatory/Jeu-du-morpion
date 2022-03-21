import ai.*;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.HashMap;

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

    @FXML
    protected void onHelloButtonClick() throws IOException {
        startText.setText("Apprentissage en cours");
        Boston.setText("Lancer l'apprentissage");
        Boston.setVisible(false);

        int[] layers = new int[]{ 2, 5, 1 };
        MultiLayerPerceptron net = new MultiLayerPerceptron(layers, 0.1, new SigmoidalTransferFunction());

        task=new Task(){
            @Override
            protected Double call() throws Exception {
                HashMap<Integer, Coup> mapTrain = Test.loadCoupsFromFile("./resources/train_dev_test/train.txt");
                double epochs = 10000000 ;
                double error= 0.0;
                boolean verbose =true;

                for(int i = 0; i < epochs; i++) {
                    double[] inputs = new double[]{Math.round(Math.random()), Math.round(Math.random())};
                    double[] output = new double[1];

                    if((inputs[0] == 1.0) || (inputs[1] == 1.0))
                        output[0] = 1.0;
                    else
                        output[0] = 0.0;

                    Coup c = null;
                    while (c == null)
                        c = mapTrain.get((int) (Math.round(Math.random() * mapTrain.size())));
                    error += net.backPropagate(inputs, output);
                    if (i % 10000 == 0 && verbose) {
                        updateMessage("Error at step " + i +" is "+ (error/(double)i));
                        updateProgress(i, epochs);
                    }
                }
                updateProgress(epochs,epochs);
                updateMessage("Apprentissage fini !");
                String file ="./ressources/models/MLP:256:0.1:2.srl";
                net.save(file);
                return null;
            }
        };

        progress.progressProperty().bind(task.progressProperty());
        console.textProperty().bind(task.messageProperty());
        new Thread(task).start();
    }
}

