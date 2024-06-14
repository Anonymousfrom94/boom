package universite_paris8.iut.osall.boom.modele.item.Equipement;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class GantDestructeur extends Equipement{
    public GantDestructeur(Environnement environnement) {
        super(environnement, "Gant Destructeur");
    }

    @Override
    public void utilise() {

    }

    @Override
    public void equip(Joueur joueur) {
        joueur.setEquipement(this);
    }


}
