package universite_paris8.iut.osall.boom.modele.entite;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.osall.boom.controller.Controller;

public class Joueur {

    private int id;
    private IntegerProperty x , y ;

    public Joueur() {
        this.x = new SimpleIntegerProperty(1224/2);
        this.y = new SimpleIntegerProperty(964/2);
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public int getX(){
        return this.x.getValue();
    }

    public int getY(){
        return this.y.getValue();
    }

    public IntegerProperty Xproperty(){
        return this.x;
    }

    public IntegerProperty Yproperty(){
        return this.y;
    }

    public void setX(int x){
        this.x.setValue(x);
    }

    public void setY(int y){
        this.y.setValue(y);
    }

}
