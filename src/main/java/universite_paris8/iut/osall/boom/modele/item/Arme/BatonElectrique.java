package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Ennemie;

public class BatonElectrique extends Arme{
    public BatonElectrique(Environnement environnement) {
        super(environnement, "Baton Electrique", 0, 0,8, 32);
    }

    @Override
    public void utilise(Ennemie e) {
        attackDeZone(e);
    }

    public void attackDeZone(Ennemie e){
        e.setPv(e.getPv() - this.getDegat());
        int rangeConnexion = 80;

        for(Acteur a : this.getEnvironnement().getActeurs()){
            if(((a.getX() <= e.getX() && a.getX() >= e.getX()-rangeConnexion)
                    || (a.getX() >= e.getX() && a.getX() <= e.getX()+rangeConnexion))
                    && ((a.getY() <= e.getY() && a.getY() >= e.getY()-rangeConnexion)
                    || (a.getY() >= e.getY() && a.getY() <= e.getY()+rangeConnexion))){
                a.setPv(a.getPv() - this.getDegat());
            }
        }
    }
}
