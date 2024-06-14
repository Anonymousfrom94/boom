package universite_paris8.iut.osall.boom.modele.entite;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Environnement.Map;

import java.util.Random;

public class Ennemie extends Acteur {

    private int nombreDeDegat;
    private int nombreDePixelDeplacer = 1; // Distance totale à parcourir en pixels

    public Ennemie(Environnement environnement) {
        super(environnement, 0, 0, 16, 16, 2, 1);
        this.nombreDeDegat = 1; // Par exemple, à ajuster selon vos besoins
        random();
    }

    private void random() {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(getEnvironnement().getWidth());
            y = rand.nextInt(getEnvironnement().getHeight());
        } while (getEnvironnement().getMap().estObstacle(getEnvironnement().getMap().indice(x, y)));
        this.setX(x);
        this.setY(y);
    }

    public void seDeplace() {
        // Récupérer l'environnement
        Environnement environnement = getEnvironnement();

        // Récupérer la position du joueur
        Joueur joueur = environnement.getJoueur();
        int joueurX = joueur.getX();
        int joueurY = joueur.getY();

        // Calculer la direction du déplacement
        int deltaX = joueurX - getX();
        int deltaY = joueurY - getY();

        // Calculer les étapes pour le déplacement
        int stepX = (deltaX == 0) ? 0 : (deltaX > 0 ? 1 : -1);
        int stepY = (deltaY == 0) ? 0 : (deltaY > 0 ? 1 : -1);

        // Nouvelles positions potentielles
        int newX = getX() + stepX * getVitesse();
        int newY = getY() + stepY * getVitesse();

        // Vérifier si les nouvelles positions sont valides (i.e., pas d'obstacle)
        if (peutSeDeplacerVers(newX, newY)) {
            setX(newX);
            setY(newY);
        } else {
            // Si la direction directe est bloquée, essayer de contourner l'obstacle
            if (deltaX != 0) {
                newX = getX() + stepX * getVitesse();
                if (peutSeDeplacerVers(newX, getY())) {
                    setX(newX);
                }
            }
            if (deltaY != 0) {
                newY = getY() + stepY * getVitesse();
                if (peutSeDeplacerVers(getX(), newY)) {
                    setY(newY);
                }
            }
        }
    }

    private boolean peutSeDeplacerVers(int newX, int newY) {
        Environnement environnement = getEnvironnement();
        Map map = environnement.getMap();

        // Vérifier si les nouvelles positions sont valides (i.e., pas d'obstacle)
        int indice1 = map.indice(newX, newY);
        int indice2 = map.indice(newX + getLargeur() - 1, newY + getHauteur() - 1);
        return map.peutSeDeplacer(indice1, indice2);
    }

}
