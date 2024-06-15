package universite_paris8.iut.osall.boom.modele.entite;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.item.Arme.Arme;
import universite_paris8.iut.osall.boom.modele.item.Arme.EpeEnBois;
import universite_paris8.iut.osall.boom.modele.item.Equipement.Equipement;
import universite_paris8.iut.osall.boom.modele.item.Item;

public class Joueur extends Acteur {

    private final ObservableList<Item> inventaire;
    private Arme arme;
    private Equipement equipement;

    public Joueur(Environnement environnement) {
        super(environnement, 780, 485,14, 14, 5, 99999);
        this.inventaire = FXCollections.observableArrayList();
        this.arme = new EpeEnBois(environnement);
        inventaire.add(this.arme);
        this.equipement = null;
    }

    public ObservableList<Item> getInventaire() {
        return inventaire;
    }

    public void seDeplace() {
        if (getEnvironnement().getMap().peutSeDeplacer(this)) {
            int dx = 0;
            int dy = 0;
            int vitesse = getVitesse();

            if (this.direction.get().contains("haut")){
                if (this.getY() - getVitesse() > 0){
                    dy -= vitesse;
                }
            }
            if (this.direction.get().contains("bas")){
                if (this.getY() + 16 + getVitesse() < this.getEnvironnement().getHeight()){
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

    public void attaque() {
        Acteur e = estAttaquable();
        if (e != null) {
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