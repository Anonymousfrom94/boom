package universite_paris8.iut.osall.boom.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class Clavier implements EventHandler<KeyEvent> {

    private Joueur joueur;

    public Clavier(Joueur joueur) {
        this.joueur = joueur;
    }

    @Override
    public void handle(KeyEvent event) {
        touche(event);
    }

    private void touche(KeyEvent event){
        int newX = joueur.getX();
        int newY = joueur.getY();

        switch (event.getCode()){
            case Z -> {
                newY -= joueur.getVitesse();
                joueur.setDirection("haut");
            }
            case S -> {
                newY += joueur.getVitesse();
                joueur.setDirection("bas");
            }
            case Q -> {
                newX -= joueur.getVitesse();
                joueur.setDirection("gauche");
            }
            case D -> {
                newX += joueur.getVitesse();
                joueur.setDirection("droite");
            }
        }

        if (newX >= 0 && newX <= 464 && newY >= 0 && newY <= 464){
            joueur.setX(newX);
            joueur.setY(newY);
        }
        System.out.println("x : " + joueur.getX() + ", y : " + joueur.getY());
    }

}
