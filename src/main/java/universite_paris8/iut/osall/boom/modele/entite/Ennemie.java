package universite_paris8.iut.osall.boom.modele.entite;
import javafx.scene.layout.Pane;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Environnement.Map;
import universite_paris8.iut.osall.boom.modele.item.Arme.Arme;
import universite_paris8.iut.osall.boom.modele.item.Arme.EpeEnBois;
import universite_paris8.iut.osall.boom.modele.listeObservable.ListObsActeurs;

import java.util.Random;
public class Ennemie extends Acteur {
    private int nombreDeDegat;
    private int nombreDePixelDeplacer = 1; // Distance totale à parcourir en pixels
    private static final int DISTANCE_DETECTION = 999;
    private Arme arme;
    private int pv;
    private int pvMax;
    private Pane pane;

    public Ennemie(Environnement environnement, Pane pane) {
        super(environnement, 0, 0, 16, 16, 3, 1);
        this.nombreDeDegat = 1; // Par exemple, à ajuster selon vos besoins
        this.arme = new EpeEnBois(environnement);
        random();
        this.pvMax = 10; // Exemple de points de vie max
        this.pv = pvMax; // Commence avec les points de vie max
        this.pane = pane;
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
        Environnement environnement = getEnvironnement();
        Joueur joueur = environnement.getJoueur();
        // Calculer la distance entre l'ennemi et le joueur
        double distance = Math.sqrt(Math.pow(getX() - joueur.getX(), 2) + Math.pow(getY() - joueur.getY(), 2));
        // Vérifier si le joueur est dans la zone de détection
        if (distance <= DISTANCE_DETECTION) {
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
            if (arme != null && distance <= arme.getRange()) {
                attaque(joueur);
            }
        }
    }
    private boolean peutSeDeplacerVers(int newX, int newY) {
        Environnement environnement = getEnvironnement();
        Map map = environnement.getMap();


        // Vérifier chaque pixel de l'ennemi
        for (int i = 0; i < getLargeur(); i++) {
            for (int j = 0; j < getHauteur(); j++) {
                int indice = map.indice(newX + i, newY + j);
                if (map.estObstacle(indice)) {
                    return false; // Il y a un obstacle à cette position
                }
            }
        }

        return true; // Aucun obstacle trouvé, mouvement possible
    }

    private void attaque(Joueur joueur) {
        joueur.setPv(joueur.getPv() - arme.getDegat());
    }
    public void setArme(Arme arme) {
        this.arme = arme;
    }

    public int getPv() {
        return pv;
    }

    public int getPvMax() {
        return pvMax;
    }

    public void subitDegat(int degats) {
        pv -= degats;
        if (pv <= 0) {
            // L'ennemi est vaincu, peut-être le retirer de l'environnement
            getEnvironnement().getActeurs().remove(this);
            ListObsActeurs.updateBarreDeVie(this, pane);
            new Ennemie(getEnvironnement(), pane);
        }
        // Mettre à jour la barre de vie ou d'autres éléments visuels si nécessaire
        // (cette partie est spécifique à votre implémentation)
    }

}