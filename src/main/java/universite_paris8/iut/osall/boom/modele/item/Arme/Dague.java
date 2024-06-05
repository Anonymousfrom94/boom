package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.entite.Ennemie;

public class Dague extends Arme{
    public Dague() {
        super("Dague", 15, 10);
    }

    @Override
    public void utilise(Ennemie e) {
        e.setPv(e.getPv() - this.getDegat());
    }
}
