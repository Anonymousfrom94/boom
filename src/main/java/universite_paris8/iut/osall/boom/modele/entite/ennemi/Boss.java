package universite_paris8.iut.osall.boom.modele.entite.ennemi;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;

public class Boss extends Ennemi{
    public Boss(Environnement environnement) {
        super(environnement, 32, 32, 5, 1);
        this.setX(700);
        this.setY(1150);
        invoque();
    }

    public void invoque(){
        for (int i = 0; i < 49; i++){
            new Squelette(getEnvironnement());
        }
    }
}
