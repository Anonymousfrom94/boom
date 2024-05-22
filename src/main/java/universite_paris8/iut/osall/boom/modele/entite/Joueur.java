package universite_paris8.iut.osall.boom.modele.entite;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.Pane;
import universite_paris8.iut.osall.boom.controller.Controller;
import universite_paris8.iut.osall.boom.modele.Environnement;

public class Joueur extends Acteur {

    private String direction;
    private boolean haut;
    private boolean bas;
    private boolean gauche;
    private boolean droite;

    public Joueur(Environnement environnement) {
       super(environnement, 240, 240, 4);
       this.haut = false;
       this.bas = false;
       this.droite = false;
       this.gauche = false;
       this.direction = "bas";
    }

//    public boolean peutSeDeplacer(){
//        /*Regarder la prochaine position du joueur,
//        * regarder si la prochaine tuile est normale ou non,
//        * Si oui return true, si non return false.
//        * */
//        // A faire : Changer les conditions
//        // programmer le code
//        if (this.haut){
//            if (this.getY() - this.getVitesse() >= 0 ){
//                /*...code...*/
//            }
//        }
//        if (this.bas){
//            if (this.getY() + this.getVitesse() <= 464){
//                /*...code...*/
//            }
//        }
//        if (this.gauche){
//            if (this.getX() - this.getVitesse() >= 0){
//                /*...code...*/
//            }
//        }
//        if (this.droite){
//            if (this.getX() + this.getVitesse() <= 464){
//                /*...code...*/
//            }
//        }
//    }

    public void seDeplace(){
        System.out.println("Avant" + this.toString());
        if (this.haut){
            if (this.getY() - this.getVitesse() >= 0 ){
                this.setY(this.getY() - this.getVitesse());
            }
        }
        if (this.bas){
            if (this.getY() + this.getVitesse() <= 464){
                this.setY(this.getY() + this.getVitesse());
            }
        }
        if (this.gauche){
            if (this.getX() - this.getVitesse() >= 0){
                this.setX(this.getX() - this.getVitesse());
            }
        }
        if (this.droite){
            if (this.getX() + this.getVitesse() <= 464){
                this.setX(this.getX() + this.getVitesse());
            }
        }
        this.haut = false;
        this.bas = false;
        this.droite = false;
        this.gauche = false;
        System.out.println("Après" +this.toString());
    }


    public boolean estHaut() {
        return haut;
    }

    public void setHaut(boolean haut) {
        this.haut = haut;
    }

    public boolean estBas() {
        return bas;
    }

    public void setBas(boolean bas) {
        this.bas = bas;
    }

    public boolean estGauche() {
        return gauche;
    }

    public void setGauche(boolean gauche) {
        this.gauche = gauche;
    }

    public boolean estDroite() {
        return droite;
    }

    public void setDroite(boolean droite) {
        this.droite = droite;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return super.toString() + "Joueur{" +
                "direction='" + direction + '\'' +
                ", haut=" + haut +
                ", bas=" + bas +
                ", gauche=" + gauche +
                ", droite=" + droite +
                '}';
    }
}
