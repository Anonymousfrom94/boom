package universite_paris8.iut.osall.boom.modele.item.Arme.projectile;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.Ennemie;
import universite_paris8.iut.osall.boom.modele.item.Arme.Arc;

public class Fleche {
    private Arc arc;
    private IntegerProperty x, y;
    private int vitesse, largeur, hauteur;
    private Environnement environnement;

    public Fleche(Arc arc, Environnement environnement) {
        this.arc = arc;
        vitesse = 5;
        largeur = 10;
        hauteur = 3;
        this.x = new SimpleIntegerProperty(environnement.getJoueur().getX() + 14);
        this.y = new SimpleIntegerProperty(environnement.getJoueur().getY() + 6);
        this.environnement = environnement;
        init();
    }

    public void init(){
        for (int i = 0; i < 500000; i++){
            arc.getFleches().add(new Fleche(arc, environnement));
        }
    }

    public void tire(Ennemie e) {
        System.out.println("à coder fleche pas fait");
    }
}
