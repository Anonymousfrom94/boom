package universite_paris8.iut.osall.boom.modele.item.Consommable;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.item.Item;

public abstract class Consommable extends Item {
    public Consommable(Environnement environnement, String nom, int x, int y) {
        super(environnement, nom, x, y);
    }

    public Consommable(Environnement environnement, String nom) {
        super(environnement, nom);
    }

    public abstract void utilise();
}
