package universite_paris8.iut.osall.boom.modele.item.Consommable;

import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class PotionHeal extends Consommable{

    private Joueur joueur;

    public PotionHeal(Joueur joueur) {
        super(joueur.getEnvironnement(), "Potion de Soin", 0, 0);
        this.joueur = joueur;
    }
    public void utilise(){
        seSoigne();
    }

    public void seSoigne(){
        if (joueur.getPv() + 30 <= 100){
            joueur.setPv(joueur.getPv() + 30);
            this.joueur.getInventaire().remove(this);
        }
    }
}
