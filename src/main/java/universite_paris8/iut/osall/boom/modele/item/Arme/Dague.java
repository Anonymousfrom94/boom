package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Ennemi;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class Dague extends Arme{
    public Dague(Environnement environnement) {
        super(environnement, "Dague", 20, 12);
    }

    public Dague(Environnement environnement, int x, int y) {
        super(environnement, "Dague", x, y, 20, 12);
    }

    @Override
    public void utilise(Ennemi e) {
        e.enleverPv(this.getDegat());
    }

    @Override
    public void equip(Joueur joueur) {
        joueur.setArme(this);
    }
}
