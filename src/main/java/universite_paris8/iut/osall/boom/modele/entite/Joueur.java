package universite_paris8.iut.osall.boom.modele.entite;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.Pane;
import universite_paris8.iut.osall.boom.controller.Controller;

public class Joueur extends Acteur {

    private String direction;
    private Pane pane;

    public Joueur(Pane pane) {
       super(240, 240, 4);
       this.direction = "bas";
       this.pane = pane;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
