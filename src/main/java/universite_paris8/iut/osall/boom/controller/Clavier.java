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
        switch (event.getCode()){
            case Z -> joueur.setY(joueur.getY()-joueur.getVitesse());
            case Q -> joueur.setX(joueur.getX()-joueur.getVitesse());
            case S -> joueur.setY(joueur.getY()+joueur.getVitesse());
            case D -> joueur.setX(joueur.getX()+joueur.getVitesse());
        }
        System.out.println("x : " + joueur.getX() + ", y : " + joueur.getY());
    }

}
