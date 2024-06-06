package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.entite.Ennemie;

public class EpeEnBois extends Arme{
    public EpeEnBois() {
        super("Epée en Bois", 10, 15);
    }

    public void utilise(Ennemie e) {
        e.setPv(e.getPv() - this.getDegat());
    }
}
