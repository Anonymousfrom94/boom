package universite_paris8.iut.osall.boom.modele.item.Arme.projectile;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.Ennemie;
import universite_paris8.iut.osall.boom.modele.item.Arme.Arc;

public class Fleche {
    private Arc arc;
    private IntegerProperty x, y;
    private int vitesse, largeur, hauteur;
    private Environnement environnement;

    public Fleche(Arc arc, Environnement environnement) {
        this.arc = arc;
        vitesse = 5;
        largeur = 10;
        hauteur = 3;
        this.x = new SimpleIntegerProperty(environnement.getJoueur().getX() + 14);
        this.y = new SimpleIntegerProperty(environnement.getJoueur().getY() + 6);
        this.environnement = environnement;
        init();
    }

    private int indice(int newX, int newY) {
        int ligne, colonne;
        colonne = newX / 16;
        ligne = newY / 16;
        return ligne * 100 + colonne;
    }

    private boolean obstacle(int indice1, int indice2, int obstacle){
        if (indice1 > environnement.getMap().getTableau().length ||
                indice2 > environnement.getMap().getTableau().length){
            return false;
        }
        if (environnement.getMap().getTableau()[indice1] == obstacle ||
                environnement.getMap().getTableau()[indice2] == obstacle){
            System.out.println(this.toString() + "Indice 1: " + indice1);
            System.out.println(this.toString() + "Indice 2: " + indice2);
            return false;
        }
        return true;
    }

    public boolean peutSeDeplacer(){
        int indice1, indice2;
        int obstacle = 0;

        boolean bloquer = true;
        if (environnement.getJoueur().getDirection().contains("haut")){
            indice1 = indice(this.x.getValue(), this.y.getValue() - vitesse);
            indice2 = indice(this.x.getValue() + largeur, this.y.getValue() - vitesse);
            bloquer = obstacle(indice1, indice2, obstacle);
        }
        if (environnement.getJoueur().getDirection().contains("bas")){
            indice1 = indice(this.x.getValue(), this.y.getValue() + hauteur + vitesse);
            indice2 = indice(this.x.getValue() + largeur, this.y.getValue() + hauteur + vitesse);
            bloquer =  obstacle(indice1, indice2, obstacle);
        }
        if (environnement.getJoueur().getDirection().contains("gauche")){
            indice1 = indice(this.x.getValue() - vitesse, this.y.getValue());
            indice2 = indice(this.x.getValue() - vitesse, this.y.getValue() + hauteur);
            bloquer = obstacle(indice1, indice2, obstacle);
        }
        if (environnement.getJoueur().getDirection().contains("droite")){
            indice1 = indice(this.x.getValue() + largeur + vitesse, this.y.getValue());
            indice2 = indice(this.x.getValue() + largeur, this.y.getValue() + hauteur);
            bloquer =  obstacle(indice1, indice2, obstacle);
        }
        return bloquer;
    }

    public void tire(Ennemie ennemie){
        int distance = arc.getRange();
        int newX = this.x.getValue();
        int newY = this.y.getValue();
        while (distance > 0 && peutSeDeplacer()){
            if (environnement.getJoueur().getDirection().contains("haut")){
                this.y.setValue(this.y.getValue() - 5);
            }
            if (environnement.getJoueur().getDirection().contains("bas")){
                this.y.setValue(this.y.getValue() + 5);
            }
            if (environnement.getJoueur().getDirection().contains("gauche")){
                this.x.setValue(this.x.getValue() - 5);
            }
            if (environnement.getJoueur().getDirection().contains("droite")){
                this.x.setValue(this.x.getValue() + 5);
            }
            distance--;
        }
        if (this.estDansHitbox(ennemie)){
            ennemie.setPv(ennemie.getPv() - arc.getDegat());
        }
    }

    public boolean estDansHitbox(Ennemie ennemie){
        if ((this.x.getValue() - 5 <= ennemie.getX() && this.x.getValue() + 10 + 5 >= ennemie.getX()) &&
                (this.y.getValue() - 10 <= ennemie.getY() && this.y.getValue() + 16 + 10 >= ennemie.getY())){
            return true;
        }
        return false;
    }


    public void init(){
        for (int i = 0; i < 500000; i++){
            arc.getFleches().add(new Fleche(arc, environnement));
        }
    }
}
