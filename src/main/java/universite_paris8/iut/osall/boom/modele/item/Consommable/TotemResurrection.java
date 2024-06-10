package universite_paris8.iut.osall.boom.modele.item.Consommable;

import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class TotemResurrection extends Consommable{

    private Joueur joueur;

    public TotemResurrection(Joueur joueur) {
        super(joueur.getEnvironnement(), "Totem de résurection", 0, 0);
        this.joueur = joueur;
    }

    public void utilise(){
        this.joueur.setPv(100);
    }
}
