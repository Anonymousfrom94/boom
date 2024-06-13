package universite_paris8.iut.osall.boom.modele.entite;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;

public class Ennemie extends Acteur {

    public Ennemie(Environnement environnement){
        super(environnement, 0, 0, 16, 16, 4, 1);
        random();
    }

    private int indice(int newX, int newY) {
        int ligne, colonne;
        colonne = newX / 16;
        ligne = newY / 16;
        return ligne * 100 + colonne;
    }

    private void random(){
        int x = 0;
        int y = 0;
        do {
            x = (int) (Math.random() * 125);
            y = (int) (Math.random() * 125);
        }
        while(this.getEnvironnement().getMap().getTableau()[indice(x, y)] == 1);
        this.setX(x);
        this.setY(y);
    }
}
//(int) Math.random() * (480 - 1)git