package universite_paris8.iut.osall.boom.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
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
import universite_paris8.iut.osall.boom.modele.item.Arme.*;
import universite_paris8.iut.osall.boom.modele.item.Equipement.*;
import universite_paris8.iut.osall.boom.modele.listeObservable.ListObsActeurs;
import universite_paris8.iut.osall.boom.modele.listeObservable.ListObsItemEnv;
import universite_paris8.iut.osall.boom.modele.listeObservable.ListObsItemJoueur;
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
    @FXML private Pane equipementJoueur;
    @FXML private Pane inventaireJoueur;
    @FXML private Label nbrePotionHeal;
    @FXML private Label nbreTotem;
    @FXML private Label etatCouronne;
    @FXML private Label etatCeinture;
    @FXML private Label etatBottes;
    @FXML private Label etatEpee;
    @FXML private Label etatDague;
    @FXML private Label etatSniper;
    @FXML private Label etatBaton;

    private Environnement environnement;
    private VueMap vueMap;
    private VueJoueur vueJoueur;
    private Timeline gameLoop;
    private int temps;

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        this.environnement = new Environnement();
        this.vueMap = new VueMap(tilePane, environnement.getMap());
        this.vueJoueur = new VueJoueur(pane, environnement.getJoueur(), equipementJoueur);
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
                Duration.seconds(0.025 ),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    environnement.getJoueur().seDeplace();
                    if (temps == 10){
                        environnement.spawnItemEtEnnemie();
                    }
                    environnement.unTour(temps);
                    temps++;
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
        this.environnement.getJoueur().getXproperty().addListener((observable, oldValue, newValue) -> {
            this.pane.setTranslateX( pane.getPrefWidth() / 4 - environnement.getJoueur().getX()-(environnement.getJoueur().getLargeur()/2));
        });
        this.environnement.getJoueur().getYproperty().addListener((observable, oldValue, newValue) -> {
            this.pane.setTranslateY( pane.getPrefHeight() / 4 - environnement.getJoueur().getY()-(environnement.getJoueur().getHauteur()/2));
        });
        this.pane.setTranslateX(pane.getPrefWidth() / 4 - environnement.getJoueur().getX()-(environnement.getJoueur().getLargeur()/2));
        this.pane.setTranslateY(pane.getPrefHeight() / 4 - environnement.getJoueur().getY()-(environnement.getJoueur().getHauteur()/2));
        // Ajout du listener au pv du joueur
        environnement.getJoueur().pvProperty().addListener(
                (obs, old, nouv) -> VueJoueur.updateBarreDeVie(environnement.getJoueur(), equipementJoueur)
        );
    }

    public void allListObsListen(){
        ListChangeListener<Acteur> listen= new ListObsActeurs(pane);
        environnement.getActeurs().addListener(listen);

        ListChangeListener<Item> listenItemInventaire = new ListObsItemEnv(pane);
        environnement.getInventaireEnvironnement().addListener(listenItemInventaire);

        ListChangeListener<Item> listenItemJoueur = new ListObsItemJoueur(equipementJoueur, inventaireJoueur, nbrePotionHeal, nbreTotem
                , etatBaton, etatSniper, etatEpee, etatDague, etatCouronne, etatCeinture, etatBottes);
        environnement.getJoueur().getInventaire().addListener(listenItemJoueur);
    }

    private void unlockArme(){
        Arme arme = environnement.getJoueur().getArme();
        if (arme instanceof BatonElectrique){
            etatBaton.setText("Unlock");
        }
        if (arme instanceof Dague){
            etatDague.setText("Unlock");
        }
        if (arme instanceof EpeEnBois){
            etatEpee.setText("Unlock");
        }
        if (arme instanceof Sniper){
            etatSniper.setText("Unlock");
        }
    }

    private void unlockEquip(){
        Equipement equipement = environnement.getJoueur().getEquipement();
        if(equipement instanceof BotteLevitation){
            etatBottes.setText("Unlock");
        }
        if (equipement instanceof CeintureTP){
            etatCeinture.setText("Unlock");
        }
        if (equipement instanceof CouronneTemporel){
            etatCouronne.setText("Unlock");
        }
    }

    @FXML
    void clickOnBaton(ActionEvent event) {
        unlockArme();
        for (Item i : environnement.getJoueur().getInventaire()){
            if (i instanceof BatonElectrique){
                ((BatonElectrique) i).equip(environnement.getJoueur());
                etatBaton.setText("équipé");
            }
        }
        System.out.println(" \n\narme du joueur : "+environnement.getJoueur().getArme());
    }

    @FXML
    void clickOnBottes(ActionEvent event) {
        unlockEquip();
        for (Item i : environnement.getJoueur().getInventaire()){
            if (i instanceof BotteLevitation){
                ((BotteLevitation) i).equip(environnement.getJoueur());
                etatBottes.setText("équipé");
            }
        }
        System.out.println(" \n\nEquipement du joueur : "+environnement.getJoueur().getEquipement());
    }

    @FXML
    void clickOnCeinture(ActionEvent event) {
        unlockEquip();
        for (Item i : environnement.getJoueur().getInventaire()){
            if (i instanceof CeintureTP){
                ((CeintureTP) i).equip(environnement.getJoueur());
                etatCeinture.setText("équipé");
            }
        }
        System.out.println(" \n\nEquipement du joueur : "+environnement.getJoueur().getEquipement());
    }

    @FXML
    void clickOnCouronne(ActionEvent event) {
        unlockEquip();
        for (Item i : environnement.getJoueur().getInventaire()){
            if (i instanceof CouronneTemporel){
                ((CouronneTemporel) i).equip(environnement.getJoueur());
                etatCouronne.setText("équipé");
            }
        }
        System.out.println(" \n\nEquipement du joueur : "+environnement.getJoueur().getEquipement());
    }

    @FXML
    void clickOnDague(ActionEvent event) {
        unlockArme();
        for (Item i : environnement.getJoueur().getInventaire()){
            if (i instanceof Dague){
                ((Dague) i).equip(environnement.getJoueur());
                etatDague.setText("équipé");
            }
        }
        System.out.println(" \n\narme du joueur : "+environnement.getJoueur().getArme());
    }

    @FXML
    void clickOnEpee(ActionEvent event) {
        unlockArme();
        for (Item i : environnement.getJoueur().getInventaire()){
            if (i instanceof EpeEnBois){
                ((EpeEnBois) i).equip(environnement.getJoueur());
                etatEpee.setText("équipé");
            }
        }
        System.out.println(" \n\narme du joueur : "+environnement.getJoueur().getArme());
    }

    @FXML
    void clickOnPotionHeal(ActionEvent event) {
        for (Item i : environnement.getJoueur().getInventaire()) {
            if (i instanceof PotionHeal) {
                ((PotionHeal) i).utilise();
                break;
            }
        }
        System.out.println(" \n\npv du joueur : " + environnement.getJoueur().getPv());
    }


    @FXML
    void clickOnSniper(ActionEvent event) {
        unlockArme();
        for (Item i : environnement.getJoueur().getInventaire()){
            System.out.println("bds<livgbvmo<ivbofib vterc_qtgibufdyubfogqvbiusssssssfqinfqgdhhghhqdhtqht");
            if (i instanceof Sniper){
                ((Sniper) i).equip(environnement.getJoueur());
                etatSniper.setText("équipé");
            }
            System.out.println(" \n\narme du joueur : "+environnement.getJoueur().getArme());
        }
    }

    @FXML
    void clickOnTotem(ActionEvent event) {
        for (Item i : environnement.getJoueur().getInventaire()) {
            if (i instanceof TotemResurrection) {
                ((TotemResurrection) i).utilise();
                break;
            }
        }
        System.out.println(" \n\npv du joueur : " + environnement.getJoueur().getPv());
    }


}
