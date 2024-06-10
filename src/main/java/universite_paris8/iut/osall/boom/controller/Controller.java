package universite_paris8.iut.osall.boom.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Ennemie;
import universite_paris8.iut.osall.boom.modele.entite.listeObservable.ListObsActeurs;
import universite_paris8.iut.osall.boom.modele.entite.listeObservable.ListObsItemEnv;
import universite_paris8.iut.osall.boom.modele.entite.listeObservable.ListObsItemJoueur;
import universite_paris8.iut.osall.boom.modele.item.Consommable.PotionHeal;
import universite_paris8.iut.osall.boom.modele.item.Consommable.TotemResurrection;
import universite_paris8.iut.osall.boom.modele.item.Item;
import universite_paris8.iut.osall.boom.vue.VueJoueur;
import universite_paris8.iut.osall.boom.vue.VueMap;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private Pane pane;
    @FXML private TilePane tilePane;
    @FXML
    private Pane equipementJoueur;
    @FXML
    private Pane inventaireJoueur;
    @FXML
    private Label nbrePotionHeal;
    @FXML
    private Label nbreTotem;
    @FXML
    private Label etatCouronne;
    @FXML
    private Label etatCollier;
    @FXML
    private Label etatCeinture;
    @FXML
    private Label etatBottes;
    @FXML
    private Label etatGant;
    @FXML
    private Label etatEpee;
    @FXML
    private Label etatArc;
    @FXML
    private Label etatDague;
    @FXML
    private Label etatSniper;
    @FXML
    private Label etatBaton;

    private Environnement environnement;
    private VueMap vueMap;
    private VueJoueur vueJoueur;
    private Timeline gameLoop;
    private int temps;

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        this.environnement = new Environnement();
        this.vueMap = new VueMap(tilePane, environnement.getMap());
        this.vueJoueur = new VueJoueur(pane, environnement.getJoueur());
        initAnimation();
        allListener();
        gameLoop.play();
        Clavier keyHandler = new Clavier(environnement.getJoueur());
        pane.addEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
        pane.addEventHandler(KeyEvent.KEY_RELEASED, keyHandler);
        allListObsListen();
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
                Duration.seconds(0.009 ),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    environnement.getJoueur().seDeplace();
                    //Test
//                    vueJoueur.changementImg(joueur);
                    temps++;
                    if (temps == 300){
                        for (int i = 0; i < 3; i++){
                            environnement.getActeurs().add(new Ennemie(environnement));
                            environnement.getInventaireEnvironnement().add(new PotionHeal(environnement.getJoueur()));
                            environnement.getInventaireEnvironnement().add(new TotemResurrection(environnement.getJoueur()));
                        }
                    }
                    environnement.unTour();
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    public void allListener(){
        environnement.getJoueur().getPropertyDirection().addListener(
                (obs,old,nouv) -> this.vueJoueur.changementImg()
        );
        environnement.getJoueur().getXproperty().addListener(
                (obs, old, nouv) -> this.vueJoueur.changementImg2()
        );
        environnement.getJoueur().getYproperty().addListener(
                (obs, old, nouv) -> this.vueJoueur.changementImg2()
        );
    }

    public void allListObsListen(){
        ListChangeListener<Acteur> listen= new ListObsActeurs(pane);
        environnement.getActeurs().addListener(listen);

        ListChangeListener<Item> listenItemInventaire = new ListObsItemEnv(pane);
        environnement.getInventaireEnvironnement().addListener(listenItemInventaire);

        ListChangeListener<Item> listenItemJoueur = new ListObsItemJoueur(equipementJoueur, inventaireJoueur, nbrePotionHeal, nbreTotem);
        environnement.getJoueur().getInventaire().addListener(listenItemJoueur);
    }
}