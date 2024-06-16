package universite_paris8.iut.osall.boom.modele.item.Consommable;

import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class PotionHeal extends Consommable{

    private Joueur joueur;

    public PotionHeal(Joueur joueur) {
        super(joueur.getEnvironnement(), "Potion de Soin");
        this.joueur = joueur;
    }

    public PotionHeal(Joueur joueur, int x, int y) {
        super(joueur.getEnvironnement(), "Potion de Soin", x, y);
        this.joueur = joueur;
    }

    public void utilise(){
        seSoigne();
    }

    public void seSoigne(){
        if (joueur.getPv() + 90 <= 300){
            joueur.rajouterPv(90);
            this.joueur.getInventaire().remove(this);
        }
    }
}
