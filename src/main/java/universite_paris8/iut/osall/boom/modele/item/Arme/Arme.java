package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.Ennemie;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;
import universite_paris8.iut.osall.boom.modele.item.Item;

public abstract class Arme extends Item {

    private int degat;
    private int range;

    public Arme(Environnement environnement, String nom, int x, int y, int degat, int range) {
        super(environnement, nom, x, y);
        this.degat = degat;
        this.range = range;
    }

    public Arme(Environnement environnement, String nom, int degat, int range) {
        super(environnement, nom);
        this.degat = degat;
        this.range = range;
    }

    public int getDegat() {
        return degat;
    }

    public void setDegat(int degat) {
        this.degat = degat;
    }

    public int getRange() {
        return range;
    }

    public abstract void utilise(Ennemie e);

    public abstract void equip(Joueur joueur);

}
