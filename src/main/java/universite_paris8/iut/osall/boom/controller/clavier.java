package universite_paris8.iut.osall.boom.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class clavier implements EventHandler<KeyEvent> {

    private Joueur joueur;

    public clavier(Joueur joueur) {
        this.joueur = joueur;
    }

    @Override
    public void handle(KeyEvent event) {
        touche(event);
    }

    private void touche(KeyEvent event){
        switch (event.getCode()){
            case Z -> joueur.setY(joueur.getY()-5);
            case Q -> joueur.setX(joueur.getX()-5);
            case S -> joueur.setY(joueur.getY()+5);
            case D -> joueur.setX(joueur.getX()+5);
        }
    }

}
