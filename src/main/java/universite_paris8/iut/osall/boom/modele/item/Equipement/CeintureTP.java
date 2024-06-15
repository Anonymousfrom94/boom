package universite_paris8.iut.osall.boom.modele.item.Equipement;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class CeintureTP extends Equipement{

    public CeintureTP(Environnement environnement) {
        super(environnement, "Ceinture de Téléportation", 650, 670);
    }

    public void utilise(){
        seTeleporte();
    }

    public void seTeleporte(){
        int rangeTP = 80;
        int dx = 0;
        int dy = 0;

        if (getEnvironnement().getJoueur().direction.get().contains("haut")){
            if (getEnvironnement().getJoueur().getY() - rangeTP > 0){
                dy -= rangeTP;
            }
        }
        if (getEnvironnement().getJoueur().direction.get().contains("bas")){
            if (getEnvironnement().getJoueur().getY() + 16 + rangeTP < this.getEnvironnement().getHeight()){
                dy += rangeTP;
            }
        }
        if (getEnvironnement().getJoueur().direction.get().contains("gauche")){
            if (getEnvironnement().getJoueur().getX() - rangeTP > 0){
                dx -= rangeTP;
            }
        }
        if (getEnvironnement().getJoueur().direction.get().contains("droite")){
            if (getEnvironnement().getJoueur().getX() + 16 + rangeTP < this.getEnvironnement().getWidth()){
                dx += rangeTP;
            }
        }
        getEnvironnement().getJoueur().setX(getEnvironnement().getJoueur().getX() + dx);
        getEnvironnement().getJoueur().setY(getEnvironnement().getJoueur().getY() + dy);
    }

    @Override
    public void equip(Joueur joueur) {
        joueur.setEquipement(this);
    }
}
