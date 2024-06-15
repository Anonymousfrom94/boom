package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.Ennemie;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class EpeEnBois extends Arme{
    public EpeEnBois(Environnement environnement) {
        super(environnement,"Epée en Bois", 10, 15);
    }

    public void utilise(Ennemie e) {
        e.subitDegat(getDegat());
    }

    @Override
    public void equip(Joueur joueur) {
        joueur.setArme(this);
    }
}
