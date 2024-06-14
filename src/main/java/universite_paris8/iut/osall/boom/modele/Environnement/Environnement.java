package universite_paris8.iut.osall.boom.modele.Environnement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.osall.boom.modele.BFS;
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
    private BFS bfs;
    private int[] infoTuile;


    public Environnement() {
        largeurTuile = 16;
        hauteurTuile = 16;
        this.width = 100 * largeurTuile;
        this.heigth = 100 * hauteurTuile;
        this.acteurs = FXCollections.observableArrayList();
        this.inventaireEnvironnement = FXCollections.observableArrayList();
        this.obstacles = new ArrayList<>();
        this.compteurKill = 0;
        this.infoTuile = new int[3];
        this.infoTuile[0] = 16;
        this.infoTuile[1] = 100; //nombre de colonnes
        this.infoTuile[2] = 100; //nombre de lignes
        this.bfs = null;
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
            if (a instanceof Ennemie){
                ((Ennemie) a).seDeplace();
            }
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

    public int[] getInfoTuile(){
        return this.infoTuile;
    }

    public void setBfs(BFS bfs) {
        this.bfs = bfs;
    }

    public BFS getBfs() {
        return bfs;
    }

    public ArrayList<Integer> getObstacles() {
        return obstacles;
    }

    public void init(){
        this.map = new Map(this);
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
//        ajouterObstacle(316);
        ajouterObstacle(380);
        ajouterObstacle(455);
        ajouterObstacle(529);
        ajouterObstacle(530);
        ajouterObstacle(454);
        ajouterObstacle(379);
    }
}
