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

    private int indice(int newX, int newY){
        int ligne, colonne, indice;
        colonne = (int) newX/16;
        ligne = (int) newY/16;
        return ligne * 30 + colonne;
    }

    public boolean peutSeDeplacer(){
        int indice;
        if (this.haut){
            if (this.getY() - this.getVitesse() >= 0 ){
                indice = indice(this.getX(), this.getY() - this.getVitesse());
                if (this.getEnvironnement().getMap().getTableau()[indice] == 4){
                    System.out.println(this.toString() + "Indice : " + indice);
                    return false;
                }
            }

        }
        if (this.bas){
            if (this.getY() + this.getVitesse() <= 464){
                indice = indice(this.getX(), this.getY() + this.getVitesse());
                if (this.getEnvironnement().getMap().getTableau()[indice] == 4){
                    System.out.println(this.toString() + "Indice : " + indice);
                    return false;
                }
            }
        }
        if (this.gauche){
            if (this.getX() - this.getVitesse() >= 0){
                indice = indice(this.getX() - this.getVitesse(), this.getY());
                if (this.getEnvironnement().getMap().getTableau()[indice] == 4){
                    System.out.println(this.toString() + "Indice : " + indice);
                    return false;
                }
            }
        }
        if (this.droite){
            if (this.getX() + this.getVitesse() <= 464){
                indice = indice(this.getX() + this.getVitesse(), this.getY());
                if (this.getEnvironnement().getMap().getTableau()[indice] == 4){
                    System.out.println(this.toString() + "Indice : " + indice);
                    return false;
                }
            }
        }
        return true;
    }

    public void seDeplace(){
        if (peutSeDeplacer()){
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
        }
        this.haut = false;
        this.bas = false;
        this.droite = false;
        this.gauche = false;
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
                ", haut=" + haut +
                ", bas=" + bas +
                ", gauche=" + gauche +
                ", droite=" + droite +
                '}';
    }
}
