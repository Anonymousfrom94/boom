package universite_paris8.iut.osall.boom.modele.entite;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.osall.boom.controller.Controller;

public class Joueur extends Acteur {

    private String direction;

    public Joueur() {
       super(240, 240, 20);
       this.direction = "bas";
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
