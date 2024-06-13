package universite_paris8.iut.osall.boom.modele.entite;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.item.Arme.Arc;
import universite_paris8.iut.osall.boom.modele.item.Arme.Arme;
import universite_paris8.iut.osall.boom.modele.item.Arme.BatonElectrique;
import universite_paris8.iut.osall.boom.modele.item.Arme.EpeEnBois;
import universite_paris8.iut.osall.boom.modele.item.Equipement.Equipement;
import universite_paris8.iut.osall.boom.modele.item.Item;

import java.util.ArrayList;

public class Joueur extends Acteur {

    public final StringProperty direction;
    private final ObservableList<Item> inventaire;
    private Arme arme;
    private Equipement equipement;

    public Joueur(Environnement environnement) {
        super(environnement, 780, 485,14, 14, 5, 100);
        this.direction = new SimpleStringProperty("");
        this.inventaire = FXCollections.observableArrayList();
        this.arme = new EpeEnBois(environnement);
        this.equipement = null;
    }

    public ObservableList<Item> getInventaire() {
        return inventaire;
    }

    private int indice(int newX, int newY) {
        int ligne, colonne;
        colonne = newX / 16;
        ligne = newY / 16;
        return ligne * 100 + colonne;
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
        int indice1, indice2;
        int obstacle;
//
//        boolean botte = false;
//        for (int i = 0; i < inventaire.size(); i++){
//            if (inventaire.get(i).getNom() == "Botte de Lévitation"){
//                botte = true;
//            }
//        }
//        if (botte){
//            obstacle = 319;
//        }

        boolean bloquer = true;
        for(int i = 0; i < getEnvironnement().getObstacles().size() && bloquer; i++){
            obstacle = getEnvironnement().getObstacles().get(i);
            System.out.println("mrgfvb<losifvcb<seivg" + obstacle);
            if (this.direction.get().contains("haut")){
                indice1 = indice(this.getX(), this.getY() - this.getVitesse());
                indice2 = indice(this.getX() + getLargeur(), this.getY() - this.getVitesse());
                bloquer = obstacle(indice1, indice2, obstacle);
            }
            if (this.direction.get().contains("bas")){
                indice1 = indice(this.getX(), this.getY() + getHauteur() + this.getVitesse());
                indice2 = indice(this.getX() + getLargeur(), this.getY() + getHauteur() + this.getVitesse());
                bloquer =  obstacle(indice1, indice2, obstacle);
            }
            if (this.direction.get().contains("gauche")){
                indice1 = indice(this.getX() - this.getVitesse(), this.getY());
                indice2 = indice(this.getX() - this.getVitesse(), this.getY() + getHauteur());
                bloquer = obstacle(indice1, indice2, obstacle);
                System.out.println("obstaclejfhgvbrisgbnv " + obstacle);
            }
            if (this.direction.get().contains("droite")){
                indice1 = indice(this.getX() + getLargeur() + this.getVitesse(), this.getY());
                indice2 = indice(this.getX() + getLargeur() + this.getVitesse(), this.getY() + getHauteur());
                bloquer =  obstacle(indice1, indice2, obstacle);
            }
//            return bloquer;
        }
        System.out.println("Ce que renvoie bloquer : " + bloquer);
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
        System.out.println("X : " + getX() + " Y : " + getY());
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

    public void setArme(Arme arme) {
        this.arme = arme;
    }

    public Arme getArme(){
        return this.arme;
    }

    public void setEquipement(Equipement equipement) {
        this.equipement = equipement;
    }

    public Equipement getEquipement() {
        return equipement;
    }

    private Acteur estAttaquable(){
        for(Acteur e : this.getEnvironnement().getActeurs()){
            if(e instanceof Ennemie){
                if (
                        (this.getX() - getArme().getRange() <= e.getX() && this.getX() + 16 + getArme().getRange() >= e.getX()) &&
                                (this.getY() - getArme().getRange() <= e.getY() && this.getY() + 16 + getArme().getRange() >= e.getY())
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
            this.getArme().utilise((Ennemie) e);
        }
    }

    public Item peutRamasse(){
        for (Item item : this.getEnvironnement().getInventaireEnvironnement()){
            if (
                    (this.getX() - 10 <= item.getX() && this.getX() + 16 + 10 >= item.getX()) &&
                            (this.getY() - 10 <= item.getY() && this.getY() + 16 + 10 >= item.getY())
            ){
                return item;
            }

        }
        return null;
    }

    public void ramasse(){
        Item item = peutRamasse();
        if (item != null){
            this.inventaire.add(item);
            System.out.println(this.inventaire);
            getEnvironnement().getInventaireEnvironnement().remove(item);
        }

    }

}