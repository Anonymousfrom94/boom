package universite_paris8.iut.osall.boom.modele.entite;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;

public abstract class Acteur {

    public final StringProperty direction;
    private Environnement environnement;
    private String id;
    private int vitesse;
    private static int compteur = 0;
    private IntegerProperty x, y;
    private int largeur, hauteur;
    private int pvMax;
    private IntegerProperty pv;

    public Acteur(Environnement environnement, int x, int y, int largeur, int hauteur, int vitesse) {
        this.environnement = environnement;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.direction = new SimpleStringProperty("");
        this.vitesse = vitesse;
        this.pvMax = 100; // Exemple de points de vie max
        this.pv = new SimpleIntegerProperty(pvMax);
        this.id = "#" + compteur;
        compteur++;
        this.environnement.getActeurs().add(this);
        this.largeur = largeur;
        this.hauteur = hauteur;
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

    public int getVitesse() {
        return vitesse;
    }

    public Environnement getEnvironnement() {
        return environnement;
    }

    public boolean estVivant(){
        return pv.getValue() > 0;
    }


    public int getPvMax() {
        return pvMax;
    }

    public int getPv() {
        return pv.get();
    }

    public IntegerProperty pvProperty() {
        return pv;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setPv(int pv) {
        this.pv.setValue(pv);
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public void setDirection(String direction) {
        this.direction.set(direction);
    }

    public String getDirection() {
        return this.direction.get();
    }

    public StringProperty getPropertyDirection(){
        return this.direction;
    }

    @Override
    public String toString() {
        return "Acteur{" +
                ", vitesse=" + vitesse +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
