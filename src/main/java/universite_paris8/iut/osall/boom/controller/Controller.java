package universite_paris8.iut.osall.boom.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import universite_paris8.iut.osall.boom.modele.Map;
import universite_paris8.iut.osall.boom.modele.entite.Ennemie;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;
import universite_paris8.iut.osall.boom.modele.vue.VueEnnemie;
import universite_paris8.iut.osall.boom.modele.vue.VueJoueur;
import universite_paris8.iut.osall.boom.modele.vue.VueMap;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private Pane pane;
    @FXML private TilePane tilePane;
    private Joueur joueur;
    private Map map;
    private VueMap vueMap;
    private VueJoueur vueJoueur;
    private Ennemie ennemie;
    private VueEnnemie vueEnnemie;
    private Timeline gameLoop;
    private int temps;

    @Override
    public void initialize(URL location, ResourceBundle resource) {
//        tilePane.setPrefTileWidth(16);
//        tilePane.setPrefTileHeight(16);
        this.map = new Map();
        this.joueur = new Joueur(pane);
        this.vueMap = new VueMap(tilePane, map);
        this.vueJoueur = new VueJoueur(pane, joueur);
        this.ennemie = new Ennemie();
        this.vueEnnemie = new VueEnnemie(pane, ennemie);
        initAnimation();
        gameLoop.play();
//        pane.requestFocus();
    }

    public void aff(MouseEvent mouseEvent) {
        pane.requestFocus();
    }

    public double getTilePaneHeight(){
        return tilePane.getPrefHeight();
    }

    public double getTilePaneWidth(){
        return tilePane.getTileWidth();
    }
    private void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.1),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    if(temps==100){
                        System.out.println("fini");
//                        gameLoop.stop();
                    }
                    else if (temps%5==0){
                        ennemie.setX((int) (Math.random() * 470));
                        ennemie.setY((int) (Math.random() * 470));
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }
}