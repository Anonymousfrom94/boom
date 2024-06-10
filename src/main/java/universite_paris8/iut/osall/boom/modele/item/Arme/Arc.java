package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.Ennemie;

public class Arc extends Arme {
    public Arc(Environnement environnement) {
        super(environnement, "Arc", 0, 0, 10, 48);
    }

    public void utilise(Ennemie e){
        e.setPv(e.getPv() - this.getDegat());
    }
}
