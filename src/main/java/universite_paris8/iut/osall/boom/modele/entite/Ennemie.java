package universite_paris8.iut.osall.boom.modele.entite;
import javafx.scene.layout.Pane;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Environnement.Map;
import universite_paris8.iut.osall.boom.modele.item.Arme.Arme;
import universite_paris8.iut.osall.boom.modele.item.Arme.BatonElectrique;
import universite_paris8.iut.osall.boom.modele.item.Arme.EpeEnBois;

import java.util.Random;
public class Ennemie extends Acteur {

    private static final int rangeEnnemmi = 200;
    private Arme arme;
    private long derniereAttaque;
    private static final long intervalleAttack = 1500;

    public Ennemie(Environnement environnement) {
        super(environnement, 0, 0, 16, 16, 3);
        this.arme = new BatonElectrique(environnement);
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

        int distanceEnX = joueur.getX() - getX();
        int distanceEnY = joueur.getY() - getY();
        double distance = Math.sqrt(distanceEnX * distanceEnX + distanceEnY * distanceEnY);

        if (distance <= rangeEnnemmi) {
            if (arme instanceof BatonElectrique && distance <= arme.getRange() && peutAttaquer()) {
                joueur.setPv(joueur.getPv() - arme.getDegat());
                derniereAttaque = System.currentTimeMillis();
            } else {
                int dx = 0;
                if (distanceEnX > 0) {
                    dx = 1;  //la droite
                } else if (distanceEnX < 0) {
                    dx = -1; //la gauche
                }

                int dy = 0;
                if (distanceEnY > 0) {
                    dy = 1;  //le bas
                } else if (distanceEnY < 0) {
                    dy = -1; //vers le haut
                }

                int newX = getX() + dx * getVitesse();
                int newY = getY() + dy * getVitesse();

                if (peutSeDeplacerVers(newX, newY)) {
                    setX(newX);
                    setY(newY);
                } else {
                    if (distanceEnX != 0) {
                        newX = getX() + dx * getVitesse();
                        if (peutSeDeplacerVers(newX, getY())) {
                            setX(newX);
                        }
                    }
                    if (distanceEnY != 0) {
                        newY = getY() + dy * getVitesse();
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
    }

    private boolean peutAttaquer() {
        long tempsActuel = System.currentTimeMillis();
        return (tempsActuel - derniereAttaque) >= intervalleAttack;
    }

    private boolean peutSeDeplacerVers(int newX, int newY) {
        Environnement environnement = getEnvironnement();
        Map map = environnement.getMap();

        for (int i = 0; i < getLargeur(); i++) {
            for (int j = 0; j < getHauteur(); j++) {
                int x = newX + i;
                int y = newY + j;
                if (x >= 0 && x < environnement.getWidth() && y >= 0 && y < environnement.getHeight()) {
                    int indice = map.indice(x, y);
                    if (map.estObstacle(indice)) {
                        return false;
                    }
                } else {
                    return false;
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
        enleverPv(degats);
        if (!this.estVivant()) {
            System.out.println("Ennemi " + getId() + " vaincu !");
        }
    }

}