package universite_paris8.iut.osall.boom.modele.entite.ennemi;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;

public class Squelette extends Ennemi{

    public Squelette(Environnement environnement) {
        super(environnement, 16, 16, 3, 100);
    }
}
