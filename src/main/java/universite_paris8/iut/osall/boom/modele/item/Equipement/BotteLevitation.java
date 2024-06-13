package universite_paris8.iut.osall.boom.modele.item.Equipement;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class BotteLevitation extends Equipement{
    public BotteLevitation(Environnement environnement) {
        super(environnement, "Botte de Lévitation");
    }

    @Override
    public void utilise() {

    }

    @Override
    public void equip(Joueur joueur) {
        joueur.setEquipement(this);
    }
}
