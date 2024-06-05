package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.entite.Ennemie;

public class BatonElectrique extends Arme{
    public BatonElectrique() {
        super("Baton Electrique", 8, 32);
    }

    @Override
    public void utilise(Ennemie e) {

    }
}
