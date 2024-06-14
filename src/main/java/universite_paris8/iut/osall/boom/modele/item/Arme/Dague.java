package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.Ennemie;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class Dague extends Arme{
    public Dague(Environnement environnement) {
        super(environnement, "Dague", 0, 0, 20, 10);
    }

    @Override
    public void utilise(Ennemie e) {
        e.setPv(e.getPv() - this.getDegat());
    }

    @Override
    public void equip(Joueur joueur) {
        joueur.setArme(this);
    }
}
