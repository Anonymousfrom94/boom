package universite_paris8.iut.osall.boom.modele.entite;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Acteur {

    private String id;
    private int vitesse;
    private static int compteur = 0;
    private IntegerProperty x, y ;

    public Acteur(int x, int y, int vitesse) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.vitesse = vitesse;
        this.id = "#" + compteur;
        compteur++;
    }

    public int getX(){
        return this.x.getValue();
    }

    public int getY(){
        return this.y.getValue();
    }

    public IntegerProperty getXproperty(){
        return this.x;
    }

    public IntegerProperty getYproperty(){
        return this.y;
    }

    public void setX(int x) {
        this.x.setValue(x);
    }

    public void setY(int y) {
        this.y.setValue(y);
    }

    public String getId() {
        return id;
    }
}
