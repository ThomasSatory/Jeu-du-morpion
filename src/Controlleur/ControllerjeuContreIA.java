package Controlleur;

import Vue.ViewjeuContreHumain;
import Vue.ViewjeuContreIA;
import ai.MultiLayerPerceptron;
import ai.SigmoidalTransferFunction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ControllerjeuContreIA {
    @FXML
    public javafx.scene.control.Label Notification = new javafx.scene.control.Label();

    @FXML
    public Label Fin = new Label();

    @FXML
    public Label tourdecider = new Label();

    @FXML
    public ImageView vs = new ImageView();

    @FXML
    protected Button NordOuest = new Button();

    @FXML
    protected Button Nord = new Button();

    @FXML
    protected Button NordEst = new Button();

    @FXML
    protected Button Ouest = new Button();

    @FXML
    protected Button Centre = new Button();

    @FXML
    protected Button Est = new Button();

    @FXML
    protected Button  SudEst = new Button();

    @FXML
    protected Button SudOuest = new Button();

    @FXML
    protected Button Sud = new Button();

    @FXML
    protected Button Rejouer = new Button();

    @FXML
    protected Button Retour = new Button();

    private Stage stage;
    private Scene scene;

    public boolean tour = true;

    public double GameBoard[][];

    public int width=10;
    public int height=30;

    private MultiLayerPerceptron MLP ;

    public double[] output;

    public double[] sortieIA ;


    public void initialize() throws IOException {
        GameBoard=FillGameBoard();
        Retour.setVisible(false);
        Rejouer.setVisible(false);
        Fin.setVisible(false);
        Notification.setVisible(false);
        tourdecider.setVisible(true);
        MLP= LoadMLP();
        output=transformBoard();
        sortieIA=IAplay();
        AfficherSortieIA();
        tourdecider.setText("C'est au tour du Joueur 1");
    }

    public double[] transformBoard(){
        double[] output = null;
        output = new double[9];

        output[0]=GameBoard[0][0];
        output[1]=GameBoard[0][1];
        output[2]=GameBoard[0][2];
        output[3]=GameBoard[1][0];
        output[4]=GameBoard[1][1];
        output[5]=GameBoard[1][2];
        output[6]=GameBoard[2][0];
        output[7]=GameBoard[2][1];
        output[8]=GameBoard[2][2];

        return output;
    }

    public void AfficherSortieIA(){
        for (int i = 0; i < 9; i++) {
            System.out.println(sortieIA[i]);
        }
    }

    public double[] IAplay(){
        double[] sortie= MLP.forwardPropagation(output);
        return sortie;
    }


    private MultiLayerPerceptron LoadMLP() throws IOException {
        int h;
        int l;
        double lr;
        int Difficulté = ViewjeuContreIA.Difficulte;
        int size=9;

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
        MLP = new MultiLayerPerceptron(layers, lr, new SigmoidalTransferFunction());
        MLP.load("resources/models/MLP-"+h+"-"+lr+"-"+l+".srl");
        return MLP;
    }

    public int getMax(){
        int imax = 0;
        double max=0;
        output=transformBoard();
        for (int i = 0; i < 9; i++) {
            if (max < sortieIA[i] && output[i]==0){
                max=sortieIA[i];
                imax=i;
                System.out.println(sortieIA[imax]);
            }
        }
        return imax;
    }

    public double[][] FillGameBoard(){
        GameBoard = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                GameBoard[i][j]=0;
            }
            System.out.println(" ");
        }
        return GameBoard;
    }

    public void AfficherGameBoard(double[][] GameBoard){
        System.out.println(" -------- ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <3; j++) {
                System.out.print(GameBoard[i][j]);
            }
            System.out.println(" ");
        }
    }

    public boolean VerifGame(){
        boolean egalite=false;
        boolean winplayer1=false;
        boolean winplayer2=false;
        int verifegalite=0;


        for (int i = 0; i < 3; i++) {
            if (GameBoard[i][0] == 1 && GameBoard[i][1] == 1 && GameBoard[i][2] == 1) {//faire les lignes
                winplayer1 = true;

                if (i == 0) { //coloration de la grille pour montrer la combinaison gagnante
                    NordOuest.setStyle("-fx-background-color: #ff0000");
                    Nord.setStyle("-fx-background-color: #ff0000");
                    NordEst.setStyle("-fx-background-color: #ff0000");
                } else if (i == 1) { //coloration de la grille pour montrer la combinaison gagnante
                    Ouest.setStyle("-fx-background-color: #ff0000");
                    Centre.setStyle("-fx-background-color: #ff0000");
                    Est.setStyle("-fx-background-color: #ff0000");
                } else if (i == 02) { //coloration de la grille pour montrer la combinaison gagnante
                    SudOuest.setStyle("-fx-background-color: #ff0000");
                    Sud.setStyle("-fx-background-color: #ff0000");
                    SudEst.setStyle("-fx-background-color: #ff0000");
                }
            } else if (GameBoard[i][0] == -1 && GameBoard[i][1] == -1 && GameBoard[i][2] == -1) {
                winplayer2 = true;

                if (i == 0) { //coloration de la grille pour montrer la combinaison gagnante
                    NordOuest.setStyle("-fx-background-color: #ff0000");
                    Nord.setStyle("-fx-background-color: #ff0000");
                    NordEst.setStyle("-fx-background-color: #ff0000");
                } else if (i == 1) { //coloration de la grille pour montrer la combinaison gagnante
                    Ouest.setStyle("-fx-background-color: #ff0000");
                    Centre.setStyle("-fx-background-color: #ff0000");
                    Est.setStyle("-fx-background-color: #ff0000");
                } else if (i == 02) { //coloration de la grille pour montrer la combinaison gagnante
                    SudOuest.setStyle("-fx-background-color: #ff0000");
                    Sud.setStyle("-fx-background-color: #ff0000");
                    SudEst.setStyle("-fx-background-color: #ff0000");
                }
            } else if (GameBoard[0][i] == 1 && GameBoard[1][i] == 1 && GameBoard[2][i] == 1) { //verification des colonnes
                winplayer1 = true;

                if (i == 0) { //coloration de la grille pour montrer la combinaison gagnante
                    NordOuest.setStyle("-fx-background-color: #ff0000");
                    Ouest.setStyle("-fx-background-color: #ff0000");
                    SudOuest.setStyle("-fx-background-color: #ff0000");
                } else if (i == 1) { //coloration de la grille pour montrer la combinaison gagnante
                    Nord.setStyle("-fx-background-color: #ff0000");
                    Centre.setStyle("-fx-background-color: #ff0000");
                    Sud.setStyle("-fx-background-color: #ff0000");
                } else if (i == 2) { //coloration de la grille pour montrer la combinaison gagnante
                    NordEst.setStyle("-fx-background-color: #ff0000");
                    Est.setStyle("-fx-background-color: #ff0000");
                    SudEst.setStyle("-fx-background-color: #ff0000");
                }
            } else if (GameBoard[0][i] == -1 && GameBoard[1][i] == -1 && GameBoard[2][i] == -1) {
                winplayer2 = true;

                if (i == 0) { //coloration de la grille pour montrer la combinaison gagnante
                    NordOuest.setStyle("-fx-background-color: #ff0000");
                    Ouest.setStyle("-fx-background-color: #ff0000");
                    SudOuest.setStyle("-fx-background-color: #ff0000");
                } else if (i == 1) { //coloration de la grille pour montrer la combinaison gagnante
                    Nord.setStyle("-fx-background-color: #ff0000");
                    Centre.setStyle("-fx-background-color: #ff0000");
                    Sud.setStyle("-fx-background-color: #ff0000");
                } else if (i == 2) { //coloration de la grille pour montrer la combinaison gagnante
                    NordEst.setStyle("-fx-background-color: #ff0000");
                    Est.setStyle("-fx-background-color: #ff0000");
                    SudEst.setStyle("-fx-background-color: #ff0000");
                }
            }
        }

        if( GameBoard[0][0]==1 && GameBoard[1][1]==1 && GameBoard[2][2]==1){//verification des diagonales
            winplayer1=true;
            NordOuest.setStyle("-fx-background-color: #ff0000");
            Centre.setStyle("-fx-background-color: #ff0000");
            SudEst.setStyle("-fx-background-color: #ff0000");
        }else if ( GameBoard[0][0]==-1 && GameBoard[1][1]==-1 && GameBoard[2][2]==-1){
            winplayer2=true;
            NordOuest.setStyle("-fx-background-color: #ff0000");
            Centre.setStyle("-fx-background-color: #ff0000");
            SudEst.setStyle("-fx-background-color: #ff0000");
        }else if (GameBoard[0][2]==1 && GameBoard[1][1]==1 && GameBoard[2][0]==1){
            winplayer1=true;
            NordEst.setStyle("-fx-background-color: #ff0000");
            Centre.setStyle("-fx-background-color: #ff0000");
            SudOuest.setStyle("-fx-background-color: #ff0000");
        }else if ( GameBoard[0][2]==-1 && GameBoard[1][1]==-1 && GameBoard[2][0]==-1) {
            winplayer2 = true;
            NordEst.setStyle("-fx-background-color: #ff0000");
            Centre.setStyle("-fx-background-color: #ff0000");
            SudOuest.setStyle("-fx-background-color: #ff0000");
        }

        if (winplayer1 == true){
            Fin.setVisible(true);
            tourdecider.setVisible(false);
            Retour.setVisible(true);
            Rejouer.setVisible(true);
            NordOuest.setDisable(true);
            Nord.setDisable(true);
            NordEst.setDisable(true);
            Ouest.setDisable(true);
            Centre.setDisable(true);
            Est.setDisable(true);
            SudOuest.setDisable(true);
            Sud.setDisable(true);
            SudEst.setDisable(true);
            vs.setVisible(false);
            Fin.setText("Le Joueur 1 a gagné");
            return true;
        }
        if (winplayer2 == true){
            Fin.setVisible(true);
            tourdecider.setVisible(false);
            Retour.setVisible(true);
            Rejouer.setVisible(true);
            NordOuest.setDisable(true);
            Nord.setDisable(true);
            NordEst.setDisable(true);
            Ouest.setDisable(true);
            Centre.setDisable(true);
            Est.setDisable(true);
            SudOuest.setDisable(true);
            Sud.setDisable(true);
            SudEst.setDisable(true);
            vs.setVisible(false);
            Fin.setText("L'IA a gagné");
            return true;
        }

        for (int i = 0; i < 3; i++) { //verification égalité
            for (int j = 0; j < 3; j++) {
                if (GameBoard[i][j] != 0) {
                    verifegalite += 1;
                }
            }
        }
        if(verifegalite==9){
            egalite=true;
        }

        if (egalite == true){
            Fin.setVisible(true);
            tourdecider.setVisible(false);
            Retour.setVisible(true);
            Rejouer.setVisible(true);
            vs.setVisible(false);
            Fin.setText("Il y a égalité");
            return true;
        }

        return false;
    }

    public void IACoup() throws InterruptedException {
        int max = getMax();

        if (tour==false && VerifGame() == false){
            if (max==0){
                tour=true;
                Image image= new Image(getClass().getResourceAsStream("/images/circle.png"),width-10,height-6,true,true);
                NordOuest.setGraphic(new ImageView(image));
                NordOuest.setStyle("-fx-background-color:#0000B0;");
                NordOuest.setDisable(false);
                tourdecider.setText("C'est au tour du Joueur 1");
                GameBoard[0][0]=-1;
                AfficherGameBoard(GameBoard);
                VerifGame();
            }else if (max==1){
                tour=true;
                Image image= new Image(getClass().getResourceAsStream("/images/circle.png"),width-10,height-6,true,true);
                Nord.setGraphic(new ImageView(image));
                Nord.setStyle("-fx-background-color:#0000B0;");
                Nord.setDisable(false);
                tourdecider.setText("C'est au tour du Joueur 1");
                GameBoard[0][1]=-1;
                AfficherGameBoard(GameBoard);
                VerifGame();
            }else if (max==2){
                tour=true;
                Image image= new Image(getClass().getResourceAsStream("/images/circle.png"),width-10,height-6,true,true);
                NordEst.setGraphic(new ImageView(image));
                NordEst.setStyle("-fx-background-color:#0000B0;");
                NordEst.setDisable(false);
                tourdecider.setText("C'est au tour du Joueur 1");
                GameBoard[0][2]=-1;
                AfficherGameBoard(GameBoard);
                VerifGame();
            }else if (max==3){
                tour=true;
                Image image= new Image(getClass().getResourceAsStream("/images/circle.png"),width-10,height-6,true,true);
                Ouest.setGraphic(new ImageView(image));
                Ouest.setStyle("-fx-background-color:#0000B0;");
                Ouest.setDisable(false);
                tourdecider.setText("C'est au tour du Joueur 1");
                GameBoard[1][0]=-1;
                AfficherGameBoard(GameBoard);
                VerifGame();
            }else if (max==4){
                tour=true;
                Image image= new Image(getClass().getResourceAsStream("/images/circle.png"),width-10,height-6,true,true);
                Centre.setGraphic(new ImageView(image));
                Centre.setStyle("-fx-background-color:#0000B0;");
                Centre.setDisable(false);
                tourdecider.setText("C'est au tour du Joueur 1");
                GameBoard[1][1]=-1;
                AfficherGameBoard(GameBoard);
                VerifGame();
            }else if (max==5){
                tour=true;
                Image image= new Image(getClass().getResourceAsStream("/images/circle.png"),width-10,height-6,true,true);
                Est.setGraphic(new ImageView(image));
                Est.setStyle("-fx-background-color:#0000B0;");
                Est.setDisable(false);
                tourdecider.setText("C'est au tour du Joueur 1");
                GameBoard[1][2]=-1;
                AfficherGameBoard(GameBoard);
                VerifGame();
            }else if (max==6){
                tour=true;
                Image image= new Image(getClass().getResourceAsStream("/images/circle.png"),width-10,height-6,true,true);
                SudOuest.setGraphic(new ImageView(image));
                SudOuest.setStyle("-fx-background-color:#0000B0;");
                SudOuest.setDisable(false);
                tourdecider.setText("C'est au tour du Joueur 1");
                GameBoard[2][0]=-1;
                AfficherGameBoard(GameBoard);
                VerifGame();
            }else if (max==7){
                tour=true;
                Image image= new Image(getClass().getResourceAsStream("/images/circle.png"),width-10,height-6,true,true);
                Sud.setGraphic(new ImageView(image));
                Sud.setStyle("-fx-background-color:#0000B0;");
                Sud.setDisable(false);
                tourdecider.setText("C'est au tour du Joueur 1");
                GameBoard[2][1]=-1;
                AfficherGameBoard(GameBoard);
                VerifGame();
            }else if (max==8){
                tour=true;
                Image image= new Image(getClass().getResourceAsStream("/images/circle.png"),width-10,height-6,true,true);
                SudEst.setGraphic(new ImageView(image));
                SudEst.setStyle("-fx-background-color:#0000B0;");
                SudEst.setDisable(false);
                tourdecider.setText("C'est au tour du Joueur 1");
                GameBoard[2][2]=-1;
                AfficherGameBoard(GameBoard);
                VerifGame();
            }
        }
    }


    @FXML
    public void onNordOuest() throws InterruptedException {
        if(tour==true && GameBoard[0][0]==0){
            tour=false;
            Image image= new Image(getClass().getResourceAsStream("/images/cross.png"),width-10,height-6,true,true);
            NordOuest.setGraphic(new ImageView(image));
            NordOuest.setStyle("-fx-background-color:#ff8000;");
            NordOuest.setDisable(false);
            tourdecider.setText("C'est au tour de l'IA");
            GameBoard[0][0]=1;
            AfficherGameBoard(GameBoard);
            VerifGame();
            IACoup();
        } else {
            Notification.setVisible(true);
            Notification.setText("Tu ne peux pas placer ici");
        }
    }
    @FXML
    public void onNord() throws InterruptedException {
        if(tour==true && GameBoard[0][1]==0){
            tour=false;
            Image image= new Image(getClass().getResourceAsStream("/images/cross.png"),width-10,height-6,true,true);
            Nord.setGraphic(new ImageView(image));
            Nord.setStyle("-fx-background-color:#ff8000;");
            Nord.setDisable(false);
            tourdecider.setText("C'est au tour de l'IA");
            GameBoard[0][1]=1;
            AfficherGameBoard(GameBoard);
            VerifGame();
            IACoup();
        } else {
            Notification.setVisible(true);
            Notification.setText("Tu ne peux pas placer ici");
        }
    }
    @FXML
    public void onNordEst() throws InterruptedException {
        if(tour==true && GameBoard[0][2]==0){
            tour=false;
            Image image= new Image(getClass().getResourceAsStream("/images/cross.png"),width-10,height-6,true,true);
            NordEst.setGraphic(new ImageView(image));
            NordEst.setStyle("-fx-background-color:#ff8000;");
            NordEst.setDisable(false);
            GameBoard[0][2]=1;
            tourdecider.setText("C'est au tour de l'IA");
            AfficherGameBoard(GameBoard);
            VerifGame();
            IACoup();
        } else {
            Notification.setVisible(true);
            Notification.setText("Tu ne peux pas placer ici");
        }
    }
    @FXML
    public void  onOuest() throws InterruptedException {
        if(tour==true && GameBoard[1][0]==0){
            tour=false;
            Image image= new Image(getClass().getResourceAsStream("/images/cross.png"),width-10,height-6,true,true);
            Ouest.setGraphic(new ImageView(image));
            Ouest.setStyle("-fx-background-color:#ff8000;");
            Ouest.setDisable(false);
            tourdecider.setText("C'est au tour de l'IA");
            GameBoard[1][0]=1;
            AfficherGameBoard(GameBoard);
            VerifGame();
            IACoup();
        } else {
            Notification.setVisible(true);
            Notification.setText("Tu ne peux pas placer ici");
        }
    }
    @FXML
    public void onEst() throws InterruptedException {
        if(tour==true && GameBoard[1][2]==0){
            tour=false;
            Image image= new Image(getClass().getResourceAsStream("/images/cross.png"),width-10,height-6,true,true);
            Est.setGraphic(new ImageView(image));
            Est.setStyle("-fx-background-color:#ff8000;");
            Est.setDisable(false);
            tourdecider.setText("C'est au tour de l'IA");
            GameBoard[1][2]=1;
            AfficherGameBoard(GameBoard);
            VerifGame();
            IACoup();
        } else {
            Notification.setVisible(true);
            Notification.setText("Tu ne peux pas placer ici");
        }
    }
    @FXML
    public void onSudEst() throws InterruptedException {
        if(tour==true && GameBoard[2][2]==0){
            tour=false;
            Image image= new Image(getClass().getResourceAsStream("/images/cross.png"),width-10,height-6,true,true);
            SudEst.setGraphic(new ImageView(image));
            SudEst.setStyle("-fx-background-color:#ff8000;");
            SudEst.setDisable(false);
            tourdecider.setText("C'est au tour de l'IA");
            GameBoard[2][2]=1;
            AfficherGameBoard(GameBoard);
            VerifGame();
            IACoup();
        } else {
            Notification.setVisible(true);
            Notification.setText("Tu ne peux pas placer ici");
        }
    }
    @FXML
    public void onSud() throws InterruptedException {
        if(tour==true && GameBoard[2][1]==0){
            tour=false;
            Image image= new Image(getClass().getResourceAsStream("/images/cross.png"),width-10,height-6,true,true);
            Sud.setGraphic(new ImageView(image));
            Sud.setStyle("-fx-background-color:#ff8000;");
            Sud.setDisable(false);
            tourdecider.setText("C'est au tour de l'IA");
            GameBoard[2][1]=1;
            AfficherGameBoard(GameBoard);
            VerifGame();
            IACoup();
        } else {
            Notification.setVisible(true);
            Notification.setText("Tu ne peux pas placer ici");
        }
    }
    @FXML
    public void onSudOuest() throws InterruptedException {
        if(tour==true && GameBoard[2][0]==0){
            tour=false;
            Image image= new Image(getClass().getResourceAsStream("/images/cross.png"),width-10,height-6,true,true);
            SudOuest.setGraphic(new ImageView(image));
            SudOuest.setStyle("-fx-background-color:#ff8000;");
            SudOuest.setDisable(false);
            tourdecider.setText("C'est au tour de l'IA");
            GameBoard[2][0]=1;
            AfficherGameBoard(GameBoard);
            VerifGame();
            IACoup();
        } else {
            Notification.setVisible(true);
            Notification.setText("Tu ne peux pas placer ici");
        }
    }
    @FXML
    public void onCentre() throws InterruptedException {
        if(tour==true && GameBoard[1][1]==0){
            tour=false;
            Image image= new Image(getClass().getResourceAsStream("/images/cross.png"),width-10,height-6,true,true);
            Centre.setGraphic(new ImageView(image));
            Centre.setStyle("-fx-background-color:#ff8000;");
            Centre.setDisable(false);
            tourdecider.setText("C'est au tour de l'IA");
            GameBoard[1][1]=1;
            AfficherGameBoard(GameBoard);
            VerifGame();
            IACoup();
        } else {
            Notification.setVisible(true);
            Notification.setText("Tu ne peux pas placer ici");
        }
    }



    @FXML
    public void onRejouer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(ViewjeuContreHumain.class.getResource("../fxmls/jeuContreIA.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Jeu contre IA");
        stage.show();
    }


    @FXML
    public void onRetour(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(ViewjeuContreHumain.class.getResource("../fxmls/menu.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Menu Pricipal");
        stage.show();
    }
}
