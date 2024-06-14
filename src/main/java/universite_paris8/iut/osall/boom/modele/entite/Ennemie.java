package universite_paris8.iut.osall.boom.modele.entite;

import universite_paris8.iut.osall.boom.modele.BFS;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;

import java.util.Random;

public class Ennemie extends Acteur {

    private int nombreDeDegat;
    private int nombreDePixelDeplacer = 1; // Distance totale à parcourir en pixels

    public Ennemie(Environnement environnement) {
        super(environnement, 0, 0, 16, 16, 4, 1);
        this.nombreDeDegat = 1; // Par exemple, à ajuster selon vos besoins
        random();
    }

    private void random() {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(getEnvironnement().getWidth());
            y = rand.nextInt(getEnvironnement().getHeigth());
        } while (getEnvironnement().getMap().estObstacle(getEnvironnement().getMap().indice(x, y)));
        this.setX(x);
        this.setY(y);
    }

    public void seDeplace() {
        // Récupérer la position du joueur
        Joueur joueur = getEnvironnement().getJoueur();
        int joueurX = joueur.getX();
        int joueurY = joueur.getY();

        // Calculer la direction du déplacement
        int ennemiX = this.getX();
        int ennemiY = this.getY();
        int deltaX = joueurX - ennemiX;
        int deltaY = joueurY - ennemiY;

        // Calculer les étapes pour le déplacement
        int stepX = (deltaX == 0) ? 0 : (deltaX > 0 ? 1 : -1);
        int stepY = (deltaY == 0) ? 0 : (deltaY > 0 ? 1 : -1);

        // Vérifier si l'ennemi peut se déplacer dans la direction calculée
        int newX = ennemiX + stepX * nombreDePixelDeplacer;
        int newY = ennemiY + stepY * nombreDePixelDeplacer;

        // Vérifier si les nouvelles positions sont valides (i.e., pas d'obstacle)
        if (this.getEnvironnement().getMap().peutSeDeplacer(this)) {
            if (!this.getEnvironnement().getMap().estObstacle(this.getEnvironnement().getMap().indice(newX, newY))) {
                this.setX(newX);
                this.setY(newY);
            }
        }
    }
}
