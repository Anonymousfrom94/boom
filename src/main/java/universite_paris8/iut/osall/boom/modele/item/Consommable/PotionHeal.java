package universite_paris8.iut.osall.boom.modele.item.Consommable;

import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class PotionHeal extends Consommable{

    private Joueur joueur;

    private int indice(int newX, int newY) {
        int ligne, colonne;
        colonne = newX / 16;
        ligne = newY / 16;
        return ligne * 30 + colonne;
    }

    private void random(){
        int x = 0;
        int y = 0;
        do {
            x = (int) (Math.random() * 470);
            y = (int) (Math.random() * 470);
        }
        while(this.joueur.getEnvironnement().getMap().getTableau()[indice(x, y)] == 1);
        this.getXProperty().setValue(x);
        this.getYProperty().setValue(y);
    }

    public PotionHeal(Joueur joueur) {
        super(joueur.getEnvironnement(), "Potion de Soin", 0, 0);
        this.joueur = joueur;
        random();
    }

    public void utilise(){
        joueur.setPv(joueur.getPv() + 30);
    }
}
