package universite_paris8.iut.osall.boom.modele.Environnement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Ennemie;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;
import universite_paris8.iut.osall.boom.modele.item.Item;

import java.util.ArrayList;

public class Environnement {
    private Map map;
    private int width;
    private int height;
    private Joueur joueur;
    private ObservableList<Acteur> acteurs;
    private ObservableList<Item> inventaireEnvironnement;
    private int largeurTuile;
    private int hauteurTuile;
    private ArrayList<Integer> obstacles;
    private ArrayList<Integer> blocNoSpawn;
    private int compteurKill;
    private int[] infoTuile;

    public Environnement() {
        largeurTuile = 16;
        hauteurTuile = 16;
        this.width = 100 * largeurTuile;
        this.height = 100 * hauteurTuile;
        this.acteurs = FXCollections.observableArrayList();
        this.inventaireEnvironnement = FXCollections.observableArrayList();
        this.obstacles = new ArrayList<>();
        this.blocNoSpawn = new ArrayList<>();
        this.compteurKill = 0;
        this.infoTuile = new int[3];
        this.infoTuile[0] = 16;  // Taille d'une tuile
        this.infoTuile[1] = 100; // Nombre de colonnes
        this.infoTuile[2] = 100; // Nombre de lignes
        init();
    }

    public void unTour(Pane pane) {
        joueur.ramasse();

        // Déplacement des acteurs (ennemis)
        for (int i = acteurs.size() - 1; i >= 0; i--) {
            Acteur acteur = acteurs.get(i);
            if (!acteur.estVivant()) {
                System.out.println("Mort de : " + acteur);
                acteurs.remove(i);
                if (acteur instanceof Ennemie) {
                    compteurKill++;
                }
                // Génération d'un nouvel ennemi après la mort
                new Ennemie(this, pane);
                System.out.println("Nombre d'ennemis tués : " + compteurKill);
            }
            if (acteur instanceof Ennemie){
                acteur.seDeplace();
            }
        }
    }

    public void init() {
        this.map = new Map(this);
        this.joueur = new Joueur(this);

        allAddObs();
        allAddNoSpawn();
    }

/* *********************************************************************************************************************
                                             ADD
********************************************************************************************************************* */
    public void ajouterActeur(Acteur acteur) {
        acteurs.add(acteur);
    }

    public void ajouterObstacle(int obstacle) {
        obstacles.add(obstacle);
    }

    public void ajouterNoSpawn(int obstacle) {
        blocNoSpawn.add(obstacle);
    }

/* *********************************************************************************************************************
                                           GETTER & SETTER & BOOLEAN
********************************************************************************************************************* */
    public ObservableList<Acteur> getActeurs() {
        return acteurs;
    }

    public ObservableList<Item> getInventaireEnvironnement() {
        return inventaireEnvironnement;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLargeurTuile() {
        return largeurTuile;
    }

    public int getHauteurTuile() {
        return hauteurTuile;
    }

    public Map getMap() {
        return map;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public int[] getInfoTuile() {
        return this.infoTuile;
    }

    public ArrayList<Integer> getObstacles() {
        return obstacles;
    }

    public ArrayList<Integer> getBlocNoSpawn() {
        return blocNoSpawn;
    }

    public boolean estObstacle(int obstacle) {
        return obstacles.contains(obstacle);
    }

    public boolean estNoSpawn(int obstacle) {
        return blocNoSpawn.contains(obstacle);
    }

/* *********************************************************************************************************************

********************************************************************************************************************* */
    public void allAddObs(){
        ajouterObstacle(316);
        ajouterObstacle(319);
        ajouterObstacle(676);
        ajouterObstacle(677);
        ajouterObstacle(678);
        ajouterObstacle(679);
        ajouterObstacle(751);
        ajouterObstacle(752);
        ajouterObstacle(753);
        ajouterObstacle(754);
        ajouterObstacle(826);
        ajouterObstacle(827);
        ajouterObstacle(828);
        ajouterObstacle(829);
        ajouterObstacle(380);
        ajouterObstacle(455);
        ajouterObstacle(529);
        ajouterObstacle(530);
        ajouterObstacle(454);
        ajouterObstacle(379);
    }

    public void allAddNoSpawn(){
        ajouterNoSpawn(316);
        ajouterNoSpawn(376);
        ajouterNoSpawn(527);
        ajouterNoSpawn(1052);
        ajouterNoSpawn(377);
        ajouterNoSpawn(378);
    }
}
