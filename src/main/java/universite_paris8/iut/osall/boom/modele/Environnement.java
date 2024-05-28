package universite_paris8.iut.osall.boom.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;

public class Environnement {
    private Map map;
    private int width;
    private int heigth;
    private ObservableList<Acteur> acteurs;
    private int largeurTuile;
    private int hauteurTuile;

    public Environnement(Map map) {
        largeurTuile = 16;
        hauteurTuile = 16;
        this.width = 30 * 16;
        this.heigth = 30 * 16;
        this.map = map;
        this.acteurs = FXCollections.observableArrayList();
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
}
