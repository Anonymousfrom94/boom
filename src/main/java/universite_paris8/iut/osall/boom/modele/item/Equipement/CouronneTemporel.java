package universite_paris8.iut.osall.boom.modele.item.Equipement;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Ennemie;

public class CouronneTemporel extends Equipement{
    public CouronneTemporel(Environnement environnement) {
        super(environnement, "Couronne Temporel");
    }

    public void utilise(){
        int x = getEnvironnement().getJoueur().getX();
        int y = getEnvironnement().getJoueur().getY();
        int rangeConnexion = 64;

        for(Acteur a : this.getEnvironnement().getActeurs()){
            if (a instanceof Ennemie){
                if(((a.getX() <= x && a.getX() >= x-rangeConnexion)
                        || (a.getX() >= x && a.getX() <= x+rangeConnexion))
                        && ((a.getY() <= y && a.getY() >= y-rangeConnexion)
                        || (a.getY() >= y && a.getY() <= y+rangeConnexion))){
                    if (a.getVitesse()-2 > 0){
                        a.setVitesse(a.getVitesse()-2);
                    }
                }
            }
        }
    }
}
