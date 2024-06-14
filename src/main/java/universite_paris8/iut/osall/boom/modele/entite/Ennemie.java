package universite_paris8.iut.osall.boom.modele.entite;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;

import java.util.ArrayList;

public class Ennemie extends Acteur {

    private int nombreDeDegat;

    private int attentePourDeplacement = 0;
    private int nombreDePixelDeplacer = 1; // Distance totale à parcourir en pixels
    private int dx = 0;
    private int dy = 0;
    private int deplacementRestant = 0;
    private int porteeDeVue;

    public Ennemie(Environnement environnement){
        super(environnement, 0, 0, 16, 16, 4, 1);
        this.porteeDeVue = porteeDeVue;
        this.nombreDeDegat=nombreDeDegat;
        random();
    }

    private int indice(int newX, int newY) {
        int ligne, colonne;
        colonne = newX / 16;
        ligne = newY / 16;
        return ligne * 100 + colonne;
    }

    private void random(){
        int x = 0;
        int y = 0;
        do {
            x = (int) (Math.random() * 125);
            y = (int) (Math.random() * 125);
        }
        while(this.getEnvironnement().getMap().getTableau()[indice(x, y)] == 1);
        this.setX(x);
        this.setY(y);
    }

    public void seDeplace(){
        // Récupérer la position du joueur
        if (getEnvironnement().getMap().peutSeDeplacer(this)){
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

            if (this.getEnvironnement().getMap().getTableau()[indice(newX, newY)] != 1) {
                this.setX(newX);
                this.setY(newY);
            }
        }

    }
}
//(int) Math.random() * (480 - 1)git