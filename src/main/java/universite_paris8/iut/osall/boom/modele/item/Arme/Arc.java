package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.entite.Ennemie;
import universite_paris8.iut.osall.boom.modele.item.Arme.Arme;

public class Arc extends Arme {
    public Arc() {
        super("Arc", 0, 0, 10, 48);
    }

    public void utilise(Ennemie e){
        e.setPv(e.getPv() - this.getDegat());
    }
}
