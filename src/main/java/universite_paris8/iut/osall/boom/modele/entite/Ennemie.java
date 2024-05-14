package universite_paris8.iut.osall.boom.modele.entite;

import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.map.Environnement;

public class Ennemie extends Acteur {
    private String id;
    private static int compteur;

    public Ennemie(int x, int y, Environnement environnement, int pointDeVie, int pointAttaque, int moveSpeed, double atkSpeed) {
        super(x, y, environnement, pointDeVie, pointAttaque, moveSpeed, atkSpeed);
    }

    @Override
    public String getId() {
        return this.id;
    }
}
