package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Ennemie;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class BatonElectrique extends Arme{
    public BatonElectrique(Environnement environnement) {
        super(environnement, "Baton Electrique",8, 64);
    }

    @Override
    public void utilise(Ennemie e) {
        attackDeZone(e);
    }

    @Override
    public void equip(Joueur joueur) {
        joueur.setArme(this);
    }

    public void attackDeZone(Ennemie e){
        e.enleverPv(this.getDegat());
        int rangeConnexion = 80;

        for(Acteur a : this.getEnvironnement().getActeurs()){
            if(((a.getX() <= e.getX() && a.getX() >= e.getX()-rangeConnexion)
                    || (a.getX() >= e.getX() && a.getX() <= e.getX()+rangeConnexion))
                    && ((a.getY() <= e.getY() && a.getY() >= e.getY()-rangeConnexion)
                    || (a.getY() >= e.getY() && a.getY() <= e.getY()+rangeConnexion))){
                a.enleverPv(this.getDegat());
            }
        }
    }
}
