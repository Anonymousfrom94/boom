package universite_paris8.iut.osall.boom.modele.item;

public class Arme extends Item {

    private int degat;
    public Arme(String nom) {
        super(nom);
        this.degat = 0;
    }

    public void setDegat(int degat) {
        this.degat = degat;
    }
}
