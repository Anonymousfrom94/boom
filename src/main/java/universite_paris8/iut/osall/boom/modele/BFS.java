package universite_paris8.iut.osall.boom.modele;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;

import java.util.ArrayDeque;
import java.util.Queue;

public class BFS {
    private Environnement environnement;
    private int[][] tableauDesDistances;
    private int xDebutTab;
    private int yDebutTab;

    public BFS(Environnement environnement) {
        this.environnement = environnement;
        this.tableauDesDistances = new int[environnement.getInfoTuile()[2]][environnement.getInfoTuile()[1]];
        lancementBFS();
    }

    public void lancementBFS() {
        // Initialiser toutes les distances à une valeur très grande (non accessible)
        for (int i = 0; i < environnement.getInfoTuile()[2]; i++) {
            for (int j = 0; j < environnement.getInfoTuile()[1]; j++) {
                tableauDesDistances[i][j] = Integer.MAX_VALUE;
            }
        }

        // File pour le BFS
        Queue<int[]> file = new ArrayDeque<>();

        // Position de départ du joueur
        int joueurX = environnement.getJoueur().getX() / environnement.getLargeurTuile();
        int joueurY = environnement.getJoueur().getY() / environnement.getHauteurTuile();

        // Initialisation du BFS à partir de la position du joueur
        file.add(new int[]{joueurY, joueurX});
        tableauDesDistances[joueurY][joueurX] = 0;

        // Directions possibles (haut, bas, gauche, droite)
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // Parcourir la file BFS
        while (!file.isEmpty()) {
            int[] position = file.poll();
            int currentDistance = tableauDesDistances[position[0]][position[1]];

            // Explorer les voisins dans toutes les directions
            for (int[] direction : directions) {
                int newX = position[0] + direction[0];
                int newY = position[1] + direction[1];

                // Vérifier les limites de la grille
                if (newX >= 0 && newX < environnement.getInfoTuile()[2] &&
                        newY >= 0 && newY < environnement.getInfoTuile()[1]) {

                    // Vérifier si la case est accessible et pas encore visitée
                    if (tableauDesDistances[newX][newY] == Integer.MAX_VALUE &&
                            !environnement.getMap().estObstacle(environnement.getMap().indice(newY * environnement.getLargeurTuile(), newX * environnement.getHauteurTuile()))) {

                        // Mettre à jour la distance et ajouter à la file
                        tableauDesDistances[newX][newY] = currentDistance + 1;
                        file.add(new int[]{newX, newY});
                    }
                }
            }
        }

        // Déterminer les limites du tableau de distances
        xDebutTab = Math.max(0, joueurX - 16);
        yDebutTab = Math.max(0, joueurY - 9);
    }

    public int[][] getTableauDesDistances() {
        return tableauDesDistances;
    }

    public int getxDebutTab() {
        return xDebutTab;
    }

    public int getyDebutTab() {
        return yDebutTab;
    }
}
