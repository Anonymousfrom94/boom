package universite_paris8.iut.osall.boom.modele.Environnement;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;

public class Map {

    private int[] tableau;
    private Environnement environnement;

    public Map(Environnement environnement) {
        this.tableau = new int[10000];
        this.environnement = environnement;
    }

    private int indice(int newX, int newY) {
        int ligne, colonne;
        colonne = newX / 16;
        ligne = newY / 16;
        return ligne * 100 + colonne;
    }

    private boolean obstacle(int indice1, int indice2, int obstacle) {
        if (indice1 >= this.getTableau().length ||
                indice2 >= this.getTableau().length ||
                indice1 < 0 || indice2 < 0) {
            return true; // Out of bounds is considered an obstacle
        }
        return this.getTableau()[indice1] == obstacle || this.getTableau()[indice2] == obstacle;
    }

    public boolean peutSeDeplacer(Acteur acteur) {
        int indice1, indice2;

        for (int obstacle : getEnvironnement().getObstacles()) {
            if (acteur.direction.get().contains("haut")) {
                indice1 = indice(acteur.getX(), acteur.getY() - acteur.getVitesse());
                indice2 = indice(acteur.getX() + acteur.getLargeur(), acteur.getY() - acteur.getVitesse());
                if (obstacle(indice1, indice2, obstacle)) {
                    return false;
                }
            }
            if (acteur.direction.get().contains("bas")) {
                indice1 = indice(acteur.getX(), acteur.getY() + acteur.getHauteur() + acteur.getVitesse());
                indice2 = indice(acteur.getX() + acteur.getLargeur(), acteur.getY() + acteur.getHauteur() + acteur.getVitesse());
                if (obstacle(indice1, indice2, obstacle)) {
                    return false;
                }
            }
            if (acteur.direction.get().contains("gauche")) {
                indice1 = indice(acteur.getX() - acteur.getVitesse(), acteur.getY());
                indice2 = indice(acteur.getX() - acteur.getVitesse(), acteur.getY() + acteur.getHauteur());
                if (obstacle(indice1, indice2, obstacle)) {
                    return false;
                }
            }
            if (acteur.direction.get().contains("droite")) {
                indice1 = indice(acteur.getX() + acteur.getLargeur() + acteur.getVitesse(), acteur.getY());
                indice2 = indice(acteur.getX() + acteur.getLargeur() + acteur.getVitesse(), acteur.getY() + acteur.getHauteur());
                if (obstacle(indice1, indice2, obstacle)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean estObstacle(int val) {
        for (int obstacle : getEnvironnement().getObstacles()) {
            if (this.getTableau()[val] == obstacle) {
                return true;
            }
        }
        return false;
    }

    public Environnement getEnvironnement() {
        return environnement;
    }

    public int[] getTableau() {
        return this.tableau;
    }

    public void setTableau(int[] tableau) {
        this.tableau = tableau;
    }
}
