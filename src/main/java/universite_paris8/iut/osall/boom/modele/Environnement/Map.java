package universite_paris8.iut.osall.boom.modele.Environnement;

import universite_paris8.iut.osall.boom.modele.entite.Acteur;

public class Map {

    private int[] tableau;
    private Environnement environnement;

    public Map(Environnement environnement) {
        this.tableau = new int[10000]; // Exemple de taille du tableau, ajuster selon vos besoins
        this.environnement = environnement;
    }

    /**
     * Convertit les coordonnées (newX, newY) en un indice dans le tableau.
     *
     * @param newX Nouvelle position X
     * @param newY Nouvelle position Y
     * @return L'indice calculé dans le tableau
     */
    public int indice(int newX, int newY) {
        int colonne = newX / environnement.getLargeurTuile();
        int ligne = newY / environnement.getHauteurTuile();
        return ligne * environnement.getInfoTuile()[1] + colonne;
    }

    /**
     * Vérifie si un acteur peut se déplacer à une nouvelle position.
     *
     * @param acteur Acteur à vérifier
     * @return true si l'acteur peut se déplacer, false sinon
     */
    public boolean peutSeDeplacer(Acteur acteur) {
        int indice1, indice2;
        int obstacle;

        for (int i = 0; i < environnement.getObstacles().size(); i++) {
            obstacle = environnement.getObstacles().get(i);
            if (acteur.getDirection().contains("haut")) {
                indice1 = indice(acteur.getX(), acteur.getY() - acteur.getVitesse());
                indice2 = indice(acteur.getX() + acteur.getLargeur(), acteur.getY() - acteur.getVitesse());
                if (!obstacle(indice1, indice2, obstacle)) {
                    return false;
                }
            }
            if (acteur.getDirection().contains("bas")) {
                indice1 = indice(acteur.getX(), acteur.getY() + acteur.getHauteur() + acteur.getVitesse());
                indice2 = indice(acteur.getX() + acteur.getLargeur(), acteur.getY() + acteur.getHauteur() + acteur.getVitesse());
                if (!obstacle(indice1, indice2, obstacle)) {
                    return false;
                }
            }
            if (acteur.getDirection().contains("gauche")) {
                indice1 = indice(acteur.getX() - acteur.getVitesse(), acteur.getY());
                indice2 = indice(acteur.getX() - acteur.getVitesse(), acteur.getY() + acteur.getHauteur());
                if (!obstacle(indice1, indice2, obstacle)) {
                    return false;
                }
            }
            if (acteur.getDirection().contains("droite")) {
                indice1 = indice(acteur.getX() + acteur.getLargeur() + acteur.getVitesse(), acteur.getY());
                indice2 = indice(acteur.getX() + acteur.getLargeur() + acteur.getVitesse(), acteur.getY() + acteur.getHauteur());
                if (!obstacle(indice1, indice2, obstacle)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Vérifie si un obstacle est présent aux indices donnés dans le tableau.
     *
     * @param indice1 Premier indice à vérifier
     * @param indice2 Deuxième indice à vérifier
     * @param obstacle Valeur de l'obstacle à comparer
     * @return true si aucun obstacle n'est présent aux indices, false sinon
     */
    private boolean obstacle(int indice1, int indice2, int obstacle) {
        if (indice1 >= 0 && indice1 < tableau.length && indice2 >= 0 && indice2 < tableau.length) {
            if (tableau[indice1] == obstacle || tableau[indice2] == obstacle) {
                System.out.println("Indice 1 : " + indice1);
                System.out.println("Indice 2 : " + indice2);
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Vérifie si la valeur donnée est un obstacle dans le tableau.
     *
     * @param val Valeur à vérifier
     * @return true si la valeur est un obstacle, false sinon
     */
    public boolean estObstacle(int val) {
        for (int obstacle : environnement.getObstacles()) {
            if (tableau[val] == obstacle) {
                return true;
            }
        }
        return false;
    }

    public int[] getTableau() {
        return tableau;
    }

    public void setTableau(int[] tableau) {
        this.tableau = tableau;
    }

    public Environnement getEnvironnement() {
        return environnement;
    }
}
