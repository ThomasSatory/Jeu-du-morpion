package Controlleur;

import Vue.ViewMenu;
import Vue.ViewjeuContreHumain;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.crypto.spec.PSource;
import java.io.IOException;

public class ControllerjeuContreHumain {
    @FXML
    public javafx.scene.control.Label Notification = new javafx.scene.control.Label();

    @FXML
    public Label Fin = new Label();

    @FXML
    public Label tourdecider = new Label();

    @FXML
    public Rectangle player1rectangle = new Rectangle();

    @FXML
    public Rectangle player2rectangle = new Rectangle();

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

    public int GameBoard[][];

    public int width=10;
    public int height=30;

    public FadeTransition fade1;
    public FadeTransition fade2;

    public void initialize(){
        GameBoard=FillGameBoard();
        Retour.setVisible(false);
        Rejouer.setVisible(false);
        Fin.setVisible(false);
        Notification.setVisible(false);
        tourdecider.setVisible(true);
        tourdecider.setText("C'est au tour du Joueur 1");
        Transitionplayer1();
    }

    public void Transitionplayer1(){
        fade1 = new FadeTransition(Duration.seconds(1),player1rectangle);
        fade1.setFromValue(.80);
        fade1.setToValue(.20);
        fade1.setCycleCount(FadeTransition.INDEFINITE);
        fade1.setAutoReverse(true);
        fade1.play();
    }

    public void Transitionplayer2(){
        fade2 = new FadeTransition(Duration.seconds(1),player2rectangle);
        fade2.setFromValue(.80);
        fade2.setToValue(.20);
        fade2.setCycleCount(FadeTransition.INDEFINITE);
        fade2.setAutoReverse(true);
        fade2.play();
    }


    public int[][] FillGameBoard(){
        GameBoard = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                GameBoard[i][j]=0;
            }
            System.out.println(" ");
        }
        return GameBoard;
    }

    public void AfficherGameBoard(int[][] GameBoard){
        System.out.println(" -------- ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <3; j++) {
                System.out.print(GameBoard[i][j]);
            }
            System.out.println(" ");
        }
    }

    public void VerifGame(){
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
            fade1.stop();
            fade2.stop();
            Fin.setText("Il y a égalité");
        }
        if (winplayer1 == true){
            Fin.setVisible(true);
            tourdecider.setVisible(false);
            Retour.setVisible(true);
            Rejouer.setVisible(true);
            fade2.stop();
            fade1.play();
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
        }
        if (winplayer2 == true){
            Fin.setVisible(true);
            tourdecider.setVisible(false);
            Retour.setVisible(true);
            Rejouer.setVisible(true);
            fade1.stop();
            fade1.play();
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
            Fin.setText("Le Joueur 2 a gagné");
        }
    }
    @FXML
    public void onNordOuest() {
        if(tour==true && GameBoard[0][0]==0){
            tour=false;
            Image image= new Image(getClass().getResourceAsStream("/images/cross.png"),width-10,height-6,true,true);
            NordOuest.setGraphic(new ImageView(image));
            NordOuest.setStyle("-fx-background-color:#ffb347;");
            NordOuest.setDisable(false);
            tourdecider.setText("C'est au tour du Joueur 2");
            GameBoard[0][0]=1;
            AfficherGameBoard(GameBoard);
            VerifGame();
            fade1.stop();
            Transitionplayer2();
        } else if(tour==false && GameBoard[0][0]==0){
            tour=true;
            tourdecider.setText("C'est au tour du Joueur 1");
            Image image = new Image(getClass().getResourceAsStream("/images/circle.png"),width-10,height-6,true,true);
            NordOuest.setGraphic(new ImageView(image));
            NordOuest.setStyle("-fx-background-color: #80cee1;");
            NordOuest.setDisable(false);
            GameBoard[0][0]=-1;
            AfficherGameBoard(GameBoard);
            VerifGame();
            fade2.stop();
            Transitionplayer1();
        } else {
            Notification.setVisible(true);
            Notification.setText("Tu ne peux pas placer ici");
        }
    }
    @FXML
    public void onNord(){
        if(tour==true && GameBoard[0][1]==0){
            tour=false;
            Image image= new Image(getClass().getResourceAsStream("/images/cross.png"),width-10,height-6,true,true);
            Nord.setGraphic(new ImageView(image));
            Nord.setStyle("-fx-background-color:#ffb347;");
            Nord.setDisable(false);
            tourdecider.setText("C'est au tour du Joueur 2");
            GameBoard[0][1]=1;
            AfficherGameBoard(GameBoard);
            VerifGame();
            fade1.stop();
            Transitionplayer2();
        } else if(tour==false && GameBoard[0][1]==0){
            tour=true;
            tourdecider.setText("C'est au tour du Joueur 1");
            Image image = new Image(getClass().getResourceAsStream("/images/circle.png"),width-10,height-6,true,true);
            Nord.setGraphic(new ImageView(image));
            Nord.setStyle("-fx-background-color: #80cee1;");
            Nord.setDisable(false);
            GameBoard[0][1]=-1;
            AfficherGameBoard(GameBoard);
            VerifGame();
            fade2.stop();
            Transitionplayer1();
        } else {
            Notification.setVisible(true);
            Notification.setText("Tu ne peux pas placer ici");
        }
    }
    @FXML
    public void onNordEst(){
        if(tour==true && GameBoard[0][2]==0){
            tour=false;
            Image image= new Image(getClass().getResourceAsStream("/images/cross.png"),width-10,height-6,true,true);
            NordEst.setGraphic(new ImageView(image));
            NordEst.setStyle("-fx-background-color:#ffb347;");
            NordEst.setDisable(false);
            tourdecider.setText("C'est au tour du Joueur 2");
            GameBoard[0][2]=1;
            AfficherGameBoard(GameBoard);
            VerifGame();
            fade1.stop();
            Transitionplayer2();
        } else if(tour==false && GameBoard[0][2]==0){
            tour=true;
            tourdecider.setText("C'est au tour du Joueur 1");
            Image image = new Image(getClass().getResourceAsStream("/images/circle.png"),width-10,height-6,true,true);
            NordEst.setGraphic(new ImageView(image));
            NordEst.setStyle("-fx-background-color: #80cee1;");
            NordEst.setDisable(false);
            GameBoard[0][2]=-1;
            AfficherGameBoard(GameBoard);
            VerifGame();
            fade2.stop();
            Transitionplayer1();
        } else {
            Notification.setVisible(true);
            Notification.setText("Tu ne peux pas placer ici");
        }
    }
    @FXML
    public void  onOuest(){
        if(tour==true && GameBoard[1][0]==0){
            tour=false;
            Image image= new Image(getClass().getResourceAsStream("/images/cross.png"),width-10,height-6,true,true);
            Ouest.setGraphic(new ImageView(image));
            Ouest.setStyle("-fx-background-color:#ffb347;");
            Ouest.setDisable(false);
            tourdecider.setText("C'est au tour du Joueur 2");
            GameBoard[1][0]=1;
            AfficherGameBoard(GameBoard);
            VerifGame();
            fade1.stop();
            Transitionplayer2();
        } else if(tour==false && GameBoard[1][0]==0){
            tour=true;
            tourdecider.setText("C'est au tour du Joueur 1");
            Image image = new Image(getClass().getResourceAsStream("/images/circle.png"),width-10,height-6,true,true);
            Ouest.setGraphic(new ImageView(image));
            Ouest.setStyle("-fx-background-color: #80cee1;");
            Ouest.setDisable(false);
            GameBoard[1][0]=-1;
            AfficherGameBoard(GameBoard);
            VerifGame();
            fade2.stop();
            Transitionplayer1();
        } else {
            Notification.setVisible(true);
            Notification.setText("Tu ne peux pas placer ici");
        }
    }
    @FXML
    public void onEst(){
        if(tour==true && GameBoard[1][2]==0){
            tour=false;
            Image image= new Image(getClass().getResourceAsStream("/images/cross.png"),width-10,height-6,true,true);
            Est.setGraphic(new ImageView(image));
            Est.setStyle("-fx-background-color:#ffb347;");
            Est.setDisable(false);
            tourdecider.setText("C'est au tour du Joueur 2");
            GameBoard[1][2]=1;
            AfficherGameBoard(GameBoard);
            VerifGame();
            fade1.stop();
            Transitionplayer2();
        } else if(tour==false && GameBoard[1][2]==0){
            tour=true;
            tourdecider.setText("C'est au tour du Joueur 1");
            Image image = new Image(getClass().getResourceAsStream("/images/circle.png"),width-10,height-6,true,true);
            Est.setGraphic(new ImageView(image));
            Est.setStyle("-fx-background-color: #80cee1;");
            Est.setDisable(false);
            GameBoard[1][2]=-1;
            AfficherGameBoard(GameBoard);
            VerifGame();
            fade2.stop();
            Transitionplayer1();
        } else {
            Notification.setVisible(true);
            Notification.setText("Tu ne peux pas placer ici");
        }
    }
    @FXML
    public void onSudEst(){
        if(tour==true && GameBoard[2][2]==0){
            tour=false;
            Image image= new Image(getClass().getResourceAsStream("/images/cross.png"),width-10,height-6,true,true);
            SudEst.setGraphic(new ImageView(image));
            SudEst.setStyle("-fx-background-color:#ffb347;");
            SudEst.setDisable(false);
            tourdecider.setText("C'est au tour du Joueur 2");
            GameBoard[2][2]=1;
            AfficherGameBoard(GameBoard);
            VerifGame();
            fade1.stop();
            Transitionplayer2();
        } else if(tour==false && GameBoard[2][2]==0){
            tour=true;
            tourdecider.setText("C'est au tour du Joueur 1");
            Image image = new Image(getClass().getResourceAsStream("/images/circle.png"),width-10,height-6,true,true);
            SudEst.setGraphic(new ImageView(image));
            SudEst.setStyle("-fx-background-color: #80cee1;");
            SudEst.setDisable(false);
            GameBoard[2][2]=-1;
            AfficherGameBoard(GameBoard);
            VerifGame();
            fade2.stop();
            Transitionplayer1();
        } else {
            Notification.setVisible(true);
            Notification.setText("Tu ne peux pas placer ici");
        }
    }
    @FXML
    public void onSud(){
        if(tour==true && GameBoard[2][1]==0){
            tour=false;
            Image image= new Image(getClass().getResourceAsStream("/images/cross.png"),width-10,height-6,true,true);
            Sud.setGraphic(new ImageView(image));
            Sud.setStyle("-fx-background-color:#ffb347;");
            Sud.setDisable(false);
            tourdecider.setText("C'est au tour du Joueur 2");
            GameBoard[2][1]=1;
            AfficherGameBoard(GameBoard);
            VerifGame();
            fade1.stop();
            Transitionplayer2();
        } else if(tour==false && GameBoard[2][1]==0){
            tour=true;
            tourdecider.setText("C'est au tour du Joueur 1");
            Image image = new Image(getClass().getResourceAsStream("/images/circle.png"),width-10,height-6,true,true);
            Sud.setGraphic(new ImageView(image));
            Sud.setStyle("-fx-background-color: #80cee1;");
            Sud.setDisable(false);
            GameBoard[2][1]=-1;
            AfficherGameBoard(GameBoard);
            VerifGame();
            fade2.stop();
            Transitionplayer1();
        } else {
            Notification.setVisible(true);
            Notification.setText("Tu ne peux pas placer ici");
        }
    }
    @FXML
    public void onSudOuest(){
        if(tour==true && GameBoard[2][0]==0){
            tour=false;
            Image image= new Image(getClass().getResourceAsStream("/images/cross.png"),width-10,height-6,true,true);
            SudOuest.setGraphic(new ImageView(image));
            SudOuest.setStyle("-fx-background-color:#ffb347;");
            SudOuest.setDisable(false);
            tourdecider.setText("C'est au tour du Joueur 2");
            GameBoard[2][0]=1;
            AfficherGameBoard(GameBoard);
            VerifGame();
            fade1.stop();
            Transitionplayer2();
        } else if(tour==false && GameBoard[2][0]==0){
            tour=true;
            tourdecider.setText("C'est au tour du Joueur 1");
            Image image = new Image(getClass().getResourceAsStream("/images/circle.png"),width-10,height-6,true,true);
            SudOuest.setGraphic(new ImageView(image));
            SudOuest.setStyle("-fx-background-color: #80cee1;");
            SudOuest.setDisable(false);
            GameBoard[2][0]=-1;
            AfficherGameBoard(GameBoard);
            VerifGame();
            fade2.stop();
            Transitionplayer1();
        } else {
            Notification.setVisible(true);
            Notification.setText("Tu ne peux pas placer ici");
        }
    }
    @FXML
    public void onCentre(){
        if(tour==true && GameBoard[1][1]==0){
            tour=false;
            Image image= new Image(getClass().getResourceAsStream("/images/cross.png"),width-10,height-6,true,true);
            Centre.setGraphic(new ImageView(image));
            Centre.setStyle("-fx-background-color:#ffb347;");
            Centre.setDisable(false);
            tourdecider.setText("C'est au tour du Joueur 2");
            GameBoard[1][1]=1;
            AfficherGameBoard(GameBoard);
            VerifGame();
            fade1.stop();
            Transitionplayer2();
        } else if(tour==false && GameBoard[1][1]==0){
            tour=true;
            tourdecider.setText("C'est au tour du Joueur 1");
            Image image = new Image(getClass().getResourceAsStream("/images/circle.png"),width-10,height-6,true,true);
            Centre.setGraphic(new ImageView(image));
            Centre.setStyle("-fx-background-color: #80cee1;");
            Centre.setDisable(false);
            GameBoard[1][1]=-1;
            AfficherGameBoard(GameBoard);
            VerifGame();
            fade2.stop();
            Transitionplayer1();
        } else {
            Notification.setVisible(true);
            Notification.setText("Tu ne peux pas placer ici");
        }
    }



    @FXML
    public void onRejouer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(ViewjeuContreHumain.class.getResource("../fxmls/jeuContreHumain.fxml"));
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
