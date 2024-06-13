package universite_paris8.iut.osall.boom.modele.item.Equipement;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class CollierFantomatique extends Equipement{
    public CollierFantomatique(Environnement environnement) {
        super(environnement, "Collier Fantomatique");
    }

    @Override
    public void utilise() {

    }

    @Override
    public void equip(Joueur joueur) {
        joueur.setEquipement(this);
    }
}
