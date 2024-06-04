package universite_paris8.iut.osall.boom.modele.item;

public class Arme extends Item {

    private int degat;
    public Arme(String nom, int degat) {
        super(nom);
        this.degat = degat;
    }

    public int getDegat(){
        return degat;
    }

}
