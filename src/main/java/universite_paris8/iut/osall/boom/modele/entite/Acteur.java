package universite_paris8.iut.osall.boom.modele.entite;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.osall.boom.modele.Map;

public abstract class Acteur {

    private IntegerProperty x,y;
    private Map map;
    private int moveSpeed;
    private int pointDeVie;
    private int pointAttaque;
    private double atkSpeed;
    public String direction;

    public Acteur(int x, int y, Map map, int pointDeVie, int pointAttaque, int moveSpeed, double atkSpeed) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.map = map;
        this.pointDeVie = pointDeVie;
        this.pointAttaque = pointAttaque;
        this.moveSpeed = moveSpeed;
        this.atkSpeed = atkSpeed;
        this.direction = "down";
    }

    public void setPointDeVie(int pointDeVie) {
        this.pointDeVie = pointDeVie;
    }

    public int getPointDeVie(){
        return this.pointDeVie;
    }

    public void attaque (Acteur a){
        if (a.getPointDeVie() - this.pointAttaque <= 0){
            a.setPointDeVie(0);
        }
        else {
            a.setPointDeVie(a.getPointDeVie() - this.pointAttaque);
        }
    }

    public abstract String getId();

    public IntegerProperty getX(){
        return x;
    }

    public IntegerProperty getY(){
        return y;
    }

    public int getMoveSpeed(){
        return this.moveSpeed;
    }

    public boolean estVivant(){
        return this.pointDeVie > 0;
    }
}
