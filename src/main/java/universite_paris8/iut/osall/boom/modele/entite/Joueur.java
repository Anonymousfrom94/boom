package universite_paris8.iut.osall.boom.modele.entite;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.Pane;
import universite_paris8.iut.osall.boom.controller.Controller;
import universite_paris8.iut.osall.boom.modele.Environnement;
import universite_paris8.iut.osall.boom.modele.Map;

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
        this.direction = "";
    }

    private int indice(int newX, int newY) {
        int ligne, colonne, indice;
        colonne = (int) newX / 16;
        ligne = (int) newY / 16;
        return ligne * 30 + colonne;
    }

    public boolean peutSeDeplacer() {
        int indice;
        if (this.haut) {
            if (this.getY() - this.getVitesse() >= 0) {
                indice = indice(this.getX(), this.getY() - this.getVitesse());
                if (this.getEnvironnement().getMap().getTableau()[indice] == 4) {
                    System.out.println(this.toString() + "Indice : " + indice);
                    return false;
                }
            }

        }
        if (this.bas) {
            if (this.getY() + this.getVitesse() <= 464) {
                indice = indice(this.getX(), this.getY() + this.getVitesse());
                if (this.getEnvironnement().getMap().getTableau()[indice] == 4) {
                    System.out.println(this.toString() + "Indice : " + indice);
                    return false;
                }
            }
        }
        if (this.gauche) {
            if (this.getX() - this.getVitesse() >= 0) {
                indice = indice(this.getX() - this.getVitesse(), this.getY());
                if (this.getEnvironnement().getMap().getTableau()[indice] == 4) {
                    System.out.println(this.toString() + "Indice : " + indice);
                    return false;
                }
            }
        }
        if (this.droite) {
            if (this.getX() + this.getVitesse() <= 464) {
                indice = indice(this.getX() + this.getVitesse(), this.getY());
                if (this.getEnvironnement().getMap().getTableau()[indice] == 4) {
                    System.out.println(this.toString() + "Indice : " + indice);
                    return false;
                }
            }
        }
        return true;
    }

    public void seDeplace() {
        if (peutSeDeplacer()) {
            int dx = 0;
            int dy = 0;
            int vitesse = getVitesse();

            if (this.direction.contains("haut"))
                dy -= vitesse;
            if (this.direction.contains("bas"))
                dy += vitesse;
            if (this.direction.contains("gauche"))
                dx -= vitesse;
            if (this.direction.contains("droite"))
                dx += vitesse;

            setX(getX() + dx);
            setY(getY() + dy);
        }


}

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return this.direction;
    }
}