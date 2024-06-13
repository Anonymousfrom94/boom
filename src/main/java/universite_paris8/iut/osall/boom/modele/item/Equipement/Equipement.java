package universite_paris8.iut.osall.boom.modele.item.Equipement;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.item.Item;

public abstract class Equipement extends Item {

    public Equipement(Environnement environnement, String nom) {
        super(environnement, nom, 595, 670);
    }

    public abstract void utilise();

    public abstract void equip();
}
