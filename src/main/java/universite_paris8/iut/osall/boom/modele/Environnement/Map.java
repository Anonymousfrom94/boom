package universite_paris8.iut.osall.boom.modele.Environnement;

import universite_paris8.iut.osall.boom.modele.entite.Acteur;

public class Map {

    private int[]tableau;
    private Environnement environnement;

    public Map() {
        this.tableau = new int[10000];
    }

    private int indice(int newX, int newY) {
        int ligne, colonne;
        colonne = newX / 16;
        ligne = newY / 16;
        return ligne * 100 + colonne;
    }

    private boolean obstacle(int indice1, int indice2, int obstacle){
        if (indice1 > this.getEnvironnement().getMap().getTableau().length ||
                indice2 > this.getEnvironnement().getMap().getTableau().length){
            return false;
        }
        if (this.getEnvironnement().getMap().getTableau()[indice1] == obstacle ||
                this.getEnvironnement().getMap().getTableau()[indice2] == obstacle){
            System.out.println("Indice 1 : " + indice1);
            System.out.println("Indice 2 : " + indice2);
            return false;
        }
        return true;
    }

    public boolean peutSeDeplacer(Acteur acteur){
        int indice1, indice2;
        int obstacle;

        boolean bloquer = true;
        for(int i = 0; i < getEnvironnement().getObstacles().size() && bloquer; i++){
            obstacle = getEnvironnement().getObstacles().get(i);
            if (acteur.direction.get().contains("haut")){
                indice1 = indice(acteur.getX(), acteur.getY() - acteur.getVitesse());
                indice2 = indice(acteur.getX() + acteur.getLargeur(), acteur.getY() - acteur.getVitesse());
                bloquer = obstacle(indice1, indice2, obstacle);
            }
            if (acteur.direction.get().contains("bas")){
                indice1 = indice(acteur.getX(), acteur.getY() + acteur.getHauteur() + acteur.getVitesse());
                indice2 = indice(acteur.getX() + acteur.getLargeur(), acteur.getY() + acteur.getHauteur() + acteur.getVitesse());
                bloquer =  obstacle(indice1, indice2, obstacle);
            }
            if (acteur.direction.get().contains("gauche")){
                indice1 = indice(acteur.getX() - acteur.getVitesse(), acteur.getY());
                indice2 = indice(acteur.getX() - acteur.getVitesse(), acteur.getY() + acteur.getHauteur());
                bloquer = obstacle(indice1, indice2, obstacle);
            }
            if (acteur.direction.get().contains("droite")){
                indice1 = indice(acteur.getX() + acteur.getLargeur() + acteur.getVitesse(), acteur.getY());
                indice2 = indice(acteur.getX() + acteur.getLargeur() + acteur.getVitesse(), acteur.getY() + acteur.getHauteur());
                bloquer =  obstacle(indice1, indice2, obstacle);
            }
//            return bloquer;
        }
        System.out.println("Ce que renvoie bloquer : " + bloquer);
        return bloquer;
    }

    public boolean estObstacle(int val){
        boolean bloquer = true;
        for(int i = 0; i < getEnvironnement().getObstacles().size() && bloquer; i++){
            if (this.getTableau()[val]==getEnvironnement().getObstacles().get(i)){
                bloquer = false;
            }
        }
        return bloquer;
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
