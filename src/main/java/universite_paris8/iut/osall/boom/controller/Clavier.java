package universite_paris8.iut.osall.boom.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
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
//        switch (event.getCode()){
//            case Z -> joueur.setHaut(true);
//            case S -> joueur.setBas(true);
//            case Q -> joueur.setGauche(true);
//            case D -> joueur.setDroite(true);
//        }
        if (event.getCode() == KeyCode.Z){
            joueur.setHaut(true);
        }
        if (event.getCode() == KeyCode.S){
            joueur.setBas(true);
        }
        if (event.getCode() == KeyCode.Q){
            joueur.setGauche(true);
        }
        if (event.getCode() == KeyCode.D){
            joueur.setDroite(true);
        }
//        System.out.println("x : " + joueur.getX() + ", y : " + joueur.getY());
    }

}
