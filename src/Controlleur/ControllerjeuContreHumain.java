package Controlleur;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

import javax.crypto.spec.PSource;

public class ControllerjeuContreHumain {
    @FXML
    public javafx.scene.control.Label Notification = new javafx.scene.control.Label();

    @FXML
    public Label Fin = new Label();

    @FXML
    public Label tourdecider = new Label();

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



    public boolean tour = true;

    public int GameBoard[][];

    public int width=10;
    public int height=30;

    public void initialize(){
        GameBoard=FillGameBoard();
        Retour.setVisible(false);
        Rejouer.setVisible(false);
        Fin.setVisible(false);
        Notification.setVisible(false);
        tourdecider.setText("C'est au tour du Joueur 1");
        VerifGame();
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

        while(egalite==false && winplayer2==false && winplayer1==false){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if ()
                }
            }
        }
    }
    @FXML
    public void onNordOuest() {
        if(tour==true && GameBoard[0][0]==0){
            tour=false;
            Image image= new Image(getClass().getResourceAsStream("/images/cross.png"),width-10,height-6,true,true);
            NordOuest.setGraphic(new ImageView(image));
            NordOuest.setStyle("-fx-background-color:#ff8000;");
            NordOuest.setDisable(false);
            tourdecider.setText("C'est au tour du Joueur 2");
            GameBoard[0][0]=1;
            AfficherGameBoard(GameBoard);
        } else if(tour==false && GameBoard[0][0]==0){
            tour=true;
            tourdecider.setText("C'est au tour du Joueur 1");
            Image image = new Image(getClass().getResourceAsStream("/images/circle.png"),width-10,height-6,true,true);
            NordOuest.setGraphic(new ImageView(image));
            NordOuest.setStyle("-fx-background-color: #000080;");
            NordOuest.setDisable(false);
            GameBoard[0][0]=-1;
            AfficherGameBoard(GameBoard);
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
            Nord.setStyle("-fx-background-color:#ff8000;");
            Nord.setDisable(false);
            tourdecider.setText("C'est au tour du Joueur 2");
            GameBoard[0][1]=1;
            AfficherGameBoard(GameBoard);
        } else if(tour==false && GameBoard[0][1]==0){
            tour=true;
            tourdecider.setText("C'est au tour du Joueur 1");
            Image image = new Image(getClass().getResourceAsStream("/images/circle.png"),width-10,height-6,true,true);
            Nord.setGraphic(new ImageView(image));
            Nord.setStyle("-fx-background-color: #0000B0;");
            Nord.setDisable(false);
            GameBoard[0][1]=-1;
            AfficherGameBoard(GameBoard);
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
            NordEst.setStyle("-fx-background-color:#ff8000;");
            NordEst.setDisable(false);
            tourdecider.setText("C'est au tour du Joueur 2");
            GameBoard[0][2]=1;
            AfficherGameBoard(GameBoard);
        } else if(tour==false && GameBoard[0][2]==0){
            tour=true;
            tourdecider.setText("C'est au tour du Joueur 1");
            Image image = new Image(getClass().getResourceAsStream("/images/circle.png"),width-10,height-6,true,true);
            NordEst.setGraphic(new ImageView(image));
            NordEst.setStyle("-fx-background-color: #000080;");
            NordEst.setDisable(false);
            GameBoard[0][2]=-1;
            AfficherGameBoard(GameBoard);
        } else {
            Notification.setVisible(true);
            Notification.setText("Tu ne peux pas placer ici");
        }
    }
    @FXML
    public void  onOuest(){
        if(tour==true && GameBoard[1][2]==0){
            tour=false;
            Image image= new Image(getClass().getResourceAsStream("/images/cross.png"),width-10,height-6,true,true);
            Ouest.setGraphic(new ImageView(image));
            Ouest.setStyle("-fx-background-color:#ff8000;");
            Ouest.setDisable(false);
            tourdecider.setText("C'est au tour du Joueur 2");
            GameBoard[1][2]=1;
            AfficherGameBoard(GameBoard);
        } else if(tour==false && GameBoard[1][2]==0){
            tour=true;
            tourdecider.setText("C'est au tour du Joueur 1");
            Image image = new Image(getClass().getResourceAsStream("/images/circle.png"),width-10,height-6,true,true);
            Ouest.setGraphic(new ImageView(image));
            Ouest.setStyle("-fx-background-color: #000080;");
            Ouest.setDisable(false);
            GameBoard[1][2]=-1;
            AfficherGameBoard(GameBoard);
        } else {
            Notification.setVisible(true);
            Notification.setText("Tu ne peux pas placer ici");
        }
    }
    @FXML
    public void onEst(){
        if(tour==true && GameBoard[1][0]==0){
            tour=false;
            Image image= new Image(getClass().getResourceAsStream("/images/cross.png"),width-10,height-6,true,true);
            Est.setGraphic(new ImageView(image));
            Est.setStyle("-fx-background-color:#ff8000;");
            Est.setDisable(false);
            tourdecider.setText("C'est au tour du Joueur 2");
            GameBoard[1][0]=1;
            AfficherGameBoard(GameBoard);
        } else if(tour==false && GameBoard[1][0]==0){
            tour=true;
            tourdecider.setText("C'est au tour du Joueur 1");
            Image image = new Image(getClass().getResourceAsStream("/images/circle.png"),width-10,height-6,true,true);
            Est.setGraphic(new ImageView(image));
            Est.setStyle("-fx-background-color: #000080;");
            Est.setDisable(false);
            GameBoard[1][0]=-1;
            AfficherGameBoard(GameBoard);
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
            SudEst.setStyle("-fx-background-color:#ff8000;");
            SudEst.setDisable(false);
            tourdecider.setText("C'est au tour du Joueur 2");
            GameBoard[2][2]=1;
            AfficherGameBoard(GameBoard);
        } else if(tour==false && GameBoard[2][2]==0){
            tour=true;
            tourdecider.setText("C'est au tour du Joueur 1");
            Image image = new Image(getClass().getResourceAsStream("/images/circle.png"),width-10,height-6,true,true);
            SudEst.setGraphic(new ImageView(image));
            SudEst.setStyle("-fx-background-color: #000080;");
            SudEst.setDisable(false);
            GameBoard[2][2]=-1;
            AfficherGameBoard(GameBoard);
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
            Sud.setStyle("-fx-background-color:#ff8000;");
            Sud.setDisable(false);
            tourdecider.setText("C'est au tour du Joueur 2");
            GameBoard[2][1]=1;
            AfficherGameBoard(GameBoard);
        } else if(tour==false && GameBoard[2][1]==0){
            tour=true;
            tourdecider.setText("C'est au tour du Joueur 1");
            Image image = new Image(getClass().getResourceAsStream("/images/circle.png"),width-10,height-6,true,true);
            Sud.setGraphic(new ImageView(image));
            Sud.setStyle("-fx-background-color: #000080;");
            Sud.setDisable(false);
            GameBoard[2][1]=-1;
            AfficherGameBoard(GameBoard);
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
            SudOuest.setStyle("-fx-background-color:#ff8000;");
            SudOuest.setDisable(false);
            tourdecider.setText("C'est au tour du Joueur 2");
            GameBoard[2][0]=1;
            AfficherGameBoard(GameBoard);
        } else if(tour==false && GameBoard[2][0]==0){
            tour=true;
            tourdecider.setText("C'est au tour du Joueur 1");
            Image image = new Image(getClass().getResourceAsStream("/images/circle.png"),width-10,height-6,true,true);
            SudOuest.setGraphic(new ImageView(image));
            SudOuest.setStyle("-fx-background-color: #000080;");
            SudOuest.setDisable(false);
            GameBoard[2][0]=-1;
            AfficherGameBoard(GameBoard);
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
            Centre.setStyle("-fx-background-color:#ff8000;");
            Centre.setDisable(false);
            tourdecider.setText("C'est au tour du Joueur 2");
            GameBoard[1][1]=1;
            AfficherGameBoard(GameBoard);
        } else if(tour==false && GameBoard[1][1]==0){
            tour=true;
            tourdecider.setText("C'est au tour du Joueur 1");
            Image image = new Image(getClass().getResourceAsStream("/images/circle.png"),width-10,height-6,true,true);
            Centre.setGraphic(new ImageView(image));
            Centre.setStyle("-fx-background-color: #000080;");
            Centre.setDisable(false);
            GameBoard[1][1]=-1;
            AfficherGameBoard(GameBoard);
        } else {
            Notification.setVisible(true);
            Notification.setText("Tu ne peux pas placer ici");
        }
    }



    @FXML
    public void onRejouer() {
    }

    @FXML
    public void onRetour() {
    }
}
