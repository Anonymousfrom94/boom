package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.Ennemie;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class Sniper extends Arme{

    private Joueur joueur;

    public Sniper(Environnement environnement) {
        super(environnement, "Sniper", 999999, 64);
        this.joueur = environnement.getJoueur();
    }

    public Sniper(Environnement environnement, int x, int y) {
        super(environnement, "Sniper", x, y, 999999, 64);
        this.joueur = environnement.getJoueur();
    }

    @Override
    public void utilise(Ennemie e) {
        e.enleverPv(this.getDegat());
        joueur.setPv(joueur.getPv()-10);
    }

    @Override
    public void equip(Joueur joueur) {
        joueur.setArme(this);
    }


}
