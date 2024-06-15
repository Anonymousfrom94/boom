package universite_paris8.iut.osall.boom.modele.entite;
import javafx.scene.layout.Pane;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Environnement.Map;
import universite_paris8.iut.osall.boom.modele.item.Arme.Arme;
import universite_paris8.iut.osall.boom.modele.item.Arme.EpeEnBois;

import java.util.Random;
public class Ennemie extends Acteur {

    private static final int rangeEnnemmi = 999;
    private Arme arme;
    private long derniereAttaque;
    private static final long intervalleAttack = 1000;

    public Ennemie(Environnement environnement, Pane pane) {
        super(environnement, 0, 0, 16, 16, 3);
        this.arme = new EpeEnBois(environnement);
        random();
    }
    private void random() {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(getEnvironnement().getWidth());
            y = rand.nextInt(getEnvironnement().getHeight());
        } while (getEnvironnement().getMap().estObstacle(getEnvironnement().getMap().indice(x, y)) || getEnvironnement().getMap().estNoSpawn(getEnvironnement().getMap().indice(x,y)));
        this.setX(x);
        this.setY(y);
    }
    public void seDeplace() {
        Environnement environnement = getEnvironnement();
        Joueur joueur = environnement.getJoueur();
        // Calculer la distance entre l'ennemi et le joueur
        double distance = Math.sqrt(Math.pow(getX() - joueur.getX(), 2) + Math.pow(getY() - joueur.getY(), 2));
        // Vérifier si le joueur est dans la zone de détection
        if (distance <= rangeEnnemmi) {
            // Calculer la direction du déplacement vers le joueur
            int deltaX = joueur.getX() - getX();
            int deltaY = joueur.getY() - getY();
            int stepX = (deltaX == 0) ? 0 : (deltaX > 0 ? 1 : -1);
            int stepY = (deltaY == 0) ? 0 : (deltaY > 0 ? 1 : -1);
            int newX = getX() + stepX * getVitesse();
            int newY = getY() + stepY * getVitesse();
            if (peutSeDeplacerVers(newX, newY)) {
                setX(newX);
                setY(newY);
            } else {
                // Essayer de contourner l'obstacle si la voie directe est bloquée
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
            if (arme != null && distance <= arme.getRange() && peutAttaquer()) {
                attaque(joueur);
            }

        }
    }

    private boolean peutAttaquer() {
        long tempsActuel = System.currentTimeMillis();
        return (tempsActuel - derniereAttaque) >= intervalleAttack;
    }


    private boolean peutSeDeplacerVers(int newX, int newY) {
        Environnement environnement = getEnvironnement();
        Map map = environnement.getMap();


        // Vérifier chaque pixel de l'ennemi
        for (int i = 0; i < getLargeur(); i++) {
            for (int j = 0; j < getHauteur(); j++) {
                int x = newX + i;
                int y = newY + j;
                if (x >= 0 && x < environnement.getWidth() && y >= 0 && y < environnement.getHeight()) {
                    int indice = map.indice(x, y);
                    if (map.estObstacle(indice)) {
                        return false; // Il y a un obstacle à cette position
                    }
                } else {
                    return false; // Les coordonnées sont hors limites de la carte
                }
            }
        }


        return true; // Aucun obstacle trouvé, mouvement possible
    }

    private void attaque(Joueur joueur) {
        joueur.enleverPv(arme.getDegat());
        derniereAttaque = System.currentTimeMillis();
    }
    public void setArme(Arme arme) {
        this.arme = arme;
    }


    public void subitDegat(int degats) {
        System.out.println("Début de subitDegat pour ennemi " + getId() + " : PV avant dégâts : " + getPv());
        System.out.println("Dégâts reçus : " + degats);
        enleverPv(degats);
        System.out.println("PV après dégâts : " + getPv());
        if (!this.estVivant()) {
            System.out.println("Ennemi " + getId() + " vaincu !");
        }
    }

}