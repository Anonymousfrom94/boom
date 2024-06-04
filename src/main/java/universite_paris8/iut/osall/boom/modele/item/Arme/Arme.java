package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.item.Item;

public abstract class Arme extends Item {

    private int degat;
    private int range;

    public Arme(String nom, int degat, int range) {
        super(nom);
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
}
