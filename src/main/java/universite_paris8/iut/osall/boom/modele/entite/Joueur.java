package universite_paris8.iut.osall.boom.modele.entite;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.item.Arme.Arme;
import universite_paris8.iut.osall.boom.modele.item.Arme.EpeEnBois;
import universite_paris8.iut.osall.boom.modele.item.Item;

import java.util.ArrayList;

public class Joueur extends Acteur {

    private final StringProperty direction;
    private final ArrayList<Item> inventaire;
    private Arme arme;

    public Joueur(Environnement environnement) {
        super(environnement, 240, 240, 1, 100);
        this.direction = new SimpleStringProperty("");
        this.inventaire = new ArrayList<Item>();
        this.arme = new EpeEnBois();// à remplacer par new EpéeDeBois()
    }

    public void ajouterAInventaire(Item item){
        this.inventaire.add(item);
    }

    public void retirerDeInventaire(Item item){
        this.inventaire.remove(item);
    }

    private int indice(int newX, int newY) {
        int ligne, colonne;
        colonne = newX / 16;
        ligne = newY / 16;
        return ligne * 30 + colonne;
    }

    private boolean obstacle(int indice1, int indice2, int obstacle){
        if (indice1 > this.getEnvironnement().getMap().getTableau().length ||
                indice2 > this.getEnvironnement().getMap().getTableau().length){
            return false;
        }
        if (this.getEnvironnement().getMap().getTableau()[indice1] == obstacle ||
                this.getEnvironnement().getMap().getTableau()[indice2] == obstacle){
            System.out.println(this.toString() + "Indice 1: " + indice1);
            System.out.println(this.toString() + "Indice 2: " + indice2);
            return false;
        }
        return true;
    }

    public boolean peutSeDeplacer(){
        int indice1, indice2, hauteur, largeur;
        int obstacle = 1;
        hauteur = 16;
        largeur = 16;
        boolean bloquer = true;
        if (this.direction.get().contains("haut")){
            indice1 = indice(this.getX(), this.getY() - this.getVitesse());
            indice2 = indice(this.getX() + largeur, this.getY() - this.getVitesse());
            bloquer = obstacle(indice1, indice2, obstacle);
        }
        if (this.direction.get().contains("bas")){
            indice1 = indice(this.getX(), this.getY() + hauteur + this.getVitesse());
            indice2 = indice(this.getX() + largeur, this.getY() + hauteur + this.getVitesse());
            bloquer =  obstacle(indice1, indice2, obstacle);
        }
        if (this.direction.get().contains("gauche")){
            indice1 = indice(this.getX() - this.getVitesse(), this.getY());
            indice2 = indice(this.getX() - this.getVitesse(), this.getY() + hauteur);
            bloquer = obstacle(indice1, indice2, obstacle);
        }
        if (this.direction.get().contains("droite")){
            indice1 = indice(this.getX() + largeur + this.getVitesse(), this.getY());
            indice2 = indice(this.getX() + largeur, this.getY() + hauteur - this.getVitesse());
            bloquer =  obstacle(indice1, indice2, obstacle);
        }
        return bloquer;
    }

    public void seDeplace() {
        if (peutSeDeplacer()) {
            int dx = 0;
            int dy = 0;
            int vitesse = getVitesse();

            if (this.direction.get().contains("haut")){
                if (this.getY() - getVitesse() > 0){
                    dy -= vitesse;
                }
            }
            if (this.direction.get().contains("bas")){
                if (this.getY() + 16 + getVitesse() < this.getEnvironnement().getHeigth()){
                    dy += vitesse;
                }
            }
            if (this.direction.get().contains("gauche")){
                if (this.getX() - getVitesse() > 0){
                    dx -= vitesse;
                }
            }
            if (this.direction.get().contains("droite")){
                if (this.getX() + 16 + getVitesse() < this.getEnvironnement().getWidth()){
                    dx += vitesse;
                }
            }
            setX(getX() + dx);
            setY(getY() + dy);
//            setDirection("");
        }
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

    public Arme getArme(){
        return this.arme;
    }

    private Acteur estAttaquable(){
        for(Acteur e : this.getEnvironnement().getActeurs()){
            if(e instanceof Ennemie){
                if (
                        (this.getX() - 10 <= e.getX() && this.getX() + 16 + 10 >= e.getX()) &&
                                (this.getY() - 10 <= e.getY() && this.getY() + 16 + 10 >= e.getY())
                ){
                    System.out.println("ennemie proche");
                    return e;
                }

            }
        }
        System.out.println("Pas d'ennemie");
        return null;
    }

    public void attaque(){
        Acteur e = estAttaquable();
        if (e != null){
            e.setPv(e.getPv() - this.getArme().getDegat());
        }
    }

}