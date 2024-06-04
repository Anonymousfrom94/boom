package universite_paris8.iut.osall.boom.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Ennemie;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class Environnement {
    private Map map;
    private int width;
    private int heigth;
    private Joueur joueur;
    private ObservableList<Acteur> acteurs;
    private int largeurTuile;
    private int hauteurTuile;

    public Environnement() {
        largeurTuile = 16;
        hauteurTuile = 16;
        this.width = 30 * largeurTuile;
        this.heigth = 30 * hauteurTuile;
        this.acteurs = FXCollections.observableArrayList();
        init();
    }

    public ObservableList<Acteur> getActeurs() {
        return acteurs;
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

    public void unTour(){
        // cela ne peut etre un foreach a cause des naissances
        // modification de acteurs.
        //System.out.println("tour " + this.nbTours);
        for(int i=acteurs.size()-1; i>=0;i--){
            Acteur a = acteurs.get(i);
            if(!a.estVivant()){
                System.out.println("mort de : " + a);
                acteurs.remove(i);
            }
        }
    }

    public void init(){
        this.map = new Map();
        this.joueur = new Joueur(this);
    }
}
