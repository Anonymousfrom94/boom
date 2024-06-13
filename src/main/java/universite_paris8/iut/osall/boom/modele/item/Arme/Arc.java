package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.Ennemie;
import universite_paris8.iut.osall.boom.modele.item.Arme.projectile.Fleche;

import java.util.ArrayList;

public class Arc extends Arme {

    private ArrayList<Fleche> fleches;

    public Arc(Environnement environnement) {
        super(environnement, "Arc", 0, 0, 10, 48);
        this.fleches = new ArrayList<>();
    }

    public void utilise(Ennemie e){
//        for (Fleche fleche : fleches){
////            fleche.tire(e);
//        }
    }

    public ArrayList<Fleche> getFleches() {
        return fleches;
    }
}
