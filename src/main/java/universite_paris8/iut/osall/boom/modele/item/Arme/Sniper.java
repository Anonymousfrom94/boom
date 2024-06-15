package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.entite.Ennemie;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class Sniper extends Arme{

    private Joueur joueur;

    public Sniper(Joueur joueur) {
        super(joueur.getEnvironnement(), "Sniper", 999999, 64);
        this.joueur = joueur;
    }

    @Override
    public void utilise(Ennemie e) {
        e.enleverPv(this.getDegat());
        this.joueur.enleverPv(10);
    }

    @Override
    public void equip(Joueur joueur) {
        joueur.setArme(this);
    }


}
