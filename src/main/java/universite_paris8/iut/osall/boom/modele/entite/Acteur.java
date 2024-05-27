package universite_paris8.iut.osall.boom.modele.entite;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.osall.boom.modele.Environnement;
import universite_paris8.iut.osall.boom.modele.HitBox;

public abstract class Acteur {

    private Environnement environnement;
    private String id;
    private int vitesse;
    private static int compteur = 0;
    private IntegerProperty x, y;
//    private HitBox hitBox;

    public Acteur(Environnement environnement, int x, int y, int vitesse) {
        this.environnement = environnement;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.vitesse = vitesse;
        this.id = "#" + compteur;
        compteur++;
        this.environnement.getActeurs().add(this);
//        this.hitBox = new HitBox(this);
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

//    public HitBox getHitBox() {
//        return hitBox;
//    }

    @Override
    public String toString() {
        return "Acteur{" +
//                "environnement=" + environnement +
//                ", id='" + id + '\'' +
                ", vitesse=" + vitesse +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
