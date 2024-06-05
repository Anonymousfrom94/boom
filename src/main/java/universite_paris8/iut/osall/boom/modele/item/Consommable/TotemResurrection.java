package universite_paris8.iut.osall.boom.modele.item.Consommable;

import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class TotemResurrection extends Consommable{

    private Joueur joueur;

    public TotemResurrection(Joueur joueur) {
        super("Totem de résurection");
        this.joueur = joueur;
    }

    public void utilise(){
        this.joueur.setPv(100);
    }
}
