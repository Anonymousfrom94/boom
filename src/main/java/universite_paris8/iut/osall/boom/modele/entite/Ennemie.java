package universite_paris8.iut.osall.boom.modele.entite;

import universite_paris8.iut.osall.boom.modele.Environnement;

public class Ennemie extends Acteur {

    public Ennemie(Environnement environnement){
        super(environnement, (int) (Math.random() * 470), (int) (Math.random() * 470), 4, 1);
    }
}
//(int) Math.random() * (480 - 1)git