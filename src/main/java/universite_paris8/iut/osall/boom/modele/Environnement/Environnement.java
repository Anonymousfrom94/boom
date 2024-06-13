package universite_paris8.iut.osall.boom.modele.Environnement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Ennemie;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;
import universite_paris8.iut.osall.boom.modele.item.Item;

import java.util.ArrayList;

public class Environnement {
    private Map map;
    private int width;
    private int heigth;
    private Joueur joueur;
    private ObservableList<Acteur> acteurs;
    private ObservableList<Item> inventaireEnvironnement;
    private int largeurTuile;
    private int hauteurTuile;
    private ArrayList<Integer> obstacles;
    private int compteurKill;

    public Environnement() {
        largeurTuile = 16;
        hauteurTuile = 16;
        this.width = 100 * largeurTuile;
        this.heigth = 100 * hauteurTuile;
        this.acteurs = FXCollections.observableArrayList();
        this.inventaireEnvironnement = FXCollections.observableArrayList();
        this.obstacles = new ArrayList<>();
        this.compteurKill = 0;
        init();
    }

    public ObservableList<Acteur> getActeurs() {
        return acteurs;
    }

    public ObservableList<Item> getInventaireEnvironnement() {
        return inventaireEnvironnement;
    }

    public int getWidth() {
        return width;
    }

    public int getHeigth() {
        return heigth;
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

    public void unTour() {
        // cela ne peut etre un foreach a cause des naissances
        // modification de acteurs.
        //System.out.println("tour " + this.nbTours);&
        joueur.ramasse();
        for (int i = acteurs.size() - 1; i >= 0; i--) {
            Acteur a = acteurs.get(i);
            if (!a.estVivant()) {
                System.out.println("mort de : " + a);
                acteurs.remove(i);
                compteurKill++;
                new Ennemie(this);
            }
        }
        System.out.println(compteurKill);
    }

    public void ajouterObstacle(int obstacle){
        obstacles.add(obstacle);
    }

    public boolean estObstacle(int obstacle){
        for (int i : obstacles){
            if (i == obstacle){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Integer> getObstacles() {
        return obstacles;
    }

    public void init(){
        this.map = new Map();
        this.joueur = new Joueur(this);
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
        ajouterObstacle(316);
    }
}
