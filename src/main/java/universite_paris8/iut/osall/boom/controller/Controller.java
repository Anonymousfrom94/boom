package universite_paris8.iut.osall.boom.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import universite_paris8.iut.osall.boom.modele.Environnement;
import universite_paris8.iut.osall.boom.modele.Map;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Ennemie;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;
import universite_paris8.iut.osall.boom.modele.entite.ListObsActeurs;
import universite_paris8.iut.osall.boom.vue.VueEnnemie;
import universite_paris8.iut.osall.boom.vue.VueJoueur;
import universite_paris8.iut.osall.boom.vue.VueMap;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private Pane pane;
    @FXML private TilePane tilePane;
    private Environnement environnement;
    private VueMap vueMap;
    private VueJoueur vueJoueur;
    private VueEnnemie vueEnnemie;
    private Timeline gameLoop;
    private int temps;

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        this.environnement = new Environnement();
        this.vueMap = new VueMap(tilePane, environnement.getMap());
        this.vueJoueur = new VueJoueur(pane, environnement.getJoueur());
//        this.ennemie = new Ennemie(environnement);
//        this.vueEnnemie = new VueEnnemie(pane, ennemie);
        environnement.getJoueur().getPropertyDirection().addListener(
                (obs,old,nouv) -> this.vueJoueur.changementImg());
        initAnimation();
        gameLoop.play();
        Clavier keyHandler = new Clavier(environnement.getJoueur());
        pane.addEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
        pane.addEventHandler(KeyEvent.KEY_RELEASED, keyHandler);
        ListChangeListener<Acteur> listen= new ListObsActeurs(pane);
        environnement.getActeurs().addListener(listen);
    }

    public void aff(MouseEvent mouseEvent) {
        pane.requestFocus();
    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.05 ),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    environnement.getJoueur().seDeplace();
                    //Test
                    double chance = Math.random();
                    if (chance <= 0.2){
                        environnement.getActeurs().add(new Ennemie(environnement));
                    }
                    if (chance > 0.8){
                        environnement.getActeurs().remove(0);
                    }
                    //Test
//                    vueJoueur.changementImg(joueur);
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }
}