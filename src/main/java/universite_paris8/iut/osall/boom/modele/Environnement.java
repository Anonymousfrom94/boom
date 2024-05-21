package universite_paris8.iut.osall.boom.modele;

import javafx.collections.ObservableList;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;

public class Environnement {
    private Map map;
    private TilePane tilePane;
    private ObservableList<Acteur> acteurs;

    public Environnement(Map map, ObservableList<Acteur> listActeurs) {
        this.map = map;
        this.acteurs = listActeurs;
    }
}
