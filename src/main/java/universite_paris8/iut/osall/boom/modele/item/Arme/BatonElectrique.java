package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Ennemi;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class BatonElectrique extends Arme{
    public BatonElectrique(Environnement environnement) {
        super(environnement, "Baton Electrique",8, 32);
    }

    public BatonElectrique(Environnement environnement, int x, int y) {
        super(environnement, "Baton Electrique", x, y, 8, 32);
    }

    @Override
    public void utilise(Ennemi e) {
        attackDeZone(e);
    }

    @Override
    public void equip(Joueur joueur) {
        joueur.setArme(this);
    }

    public void attackDeZone(Ennemi e){
        e.enleverPv(this.getDegat());
        int rangeConnexion = 80;

        for(Acteur a : this.getEnvironnement().getActeurs()){
            if (a instanceof Ennemi) {
                if(((a.getX() <= e.getX() && a.getX() >= e.getX()-rangeConnexion)
                        || (a.getX() >= e.getX() && a.getX() <= e.getX()+rangeConnexion))
                        && ((a.getY() <= e.getY() && a.getY() >= e.getY()-rangeConnexion)
                        || (a.getY() >= e.getY() && a.getY() <= e.getY()+rangeConnexion))){
                    a.enleverPv(this.getDegat());
                }
            }
        }
    }
}
