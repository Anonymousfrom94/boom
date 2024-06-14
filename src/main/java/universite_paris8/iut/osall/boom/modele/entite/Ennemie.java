package universite_paris8.iut.osall.boom.modele.entite;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class Ennemie extends Acteur {

    private int nombreDeDegat;
    private int attentePourDeplacement = 0;
    private int nombreDePixelDeplacer = 1; // Distance totale à parcourir en pixels
    private int dx = 0;
    private int dy = 0;
    private int deplacementRestant = 0;
    private int porteeDeVue;

    public Ennemie(Environnement environnement) {
        super(environnement, 0, 0, 16, 16, 4, 1);
        this.porteeDeVue = porteeDeVue;
        this.nombreDeDegat = nombreDeDegat;
        random();
    }

    private int indice(int newX, int newY) {
        int ligne, colonne;
        colonne = newX / 16;
        ligne = newY / 16;
        return ligne * 100 + colonne;
    }

    private void random() {
        int x = 0;
        int y = 0;
        do {
            x = (int) (Math.random() * 125);
            y = (int) (Math.random() * 125);
        } while (this.getEnvironnement().getMap().getTableau()[indice(x, y)] == 1);
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
        int stepX = 0;
        if (deltaX > 0) {
            stepX = 1;
        } else if (deltaX < 0) {
            stepX = -1;
        }

        int stepY = 0;
        if (deltaY > 0) {
            stepY = 1;
        } else if (deltaY < 0) {
            stepY = -1;
        }

        // Calculer les nouvelles positions
        int newX = ennemiX + stepX * nombreDePixelDeplacer;
        int newY = ennemiY + stepY * nombreDePixelDeplacer;

        // Vérifier si l'ennemi peut se déplacer dans la direction calculée
        if (this.getEnvironnement().getMap().peutSeDeplacer(this)) {
            // Vérifier si les nouvelles positions sont valides (i.e., pas d'obstacle)
            if (!this.getEnvironnement().getMap().estObstacle(indice(newX, newY))) {
                this.setX(newX);
                this.setY(newY);
            }
        }
    }

}
