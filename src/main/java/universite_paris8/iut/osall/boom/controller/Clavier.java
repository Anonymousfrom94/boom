package universite_paris8.iut.osall.boom.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

import java.util.HashSet;
import java.util.Set;

import static javafx.scene.input.KeyCode.*;
import static javafx.scene.input.KeyCode.D;

public class Clavier implements EventHandler<KeyEvent> {

    private Joueur joueur;
    private final Set<KeyCode> pressedKeys;

    public Clavier(Joueur joueur) {
        this.joueur = joueur;
        this.pressedKeys = new HashSet<>();
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED)
            this.pressedKeys.add(keyEvent.getCode());
        else if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED)
            this.pressedKeys.remove(keyEvent.getCode());

        String direction = "";

        if (pressedKeys.contains(Z)){
            direction += "haut";
        }
        if (pressedKeys.contains(S)){
            direction += "bas";
        }
        if (pressedKeys.contains(Q)){
            direction += "gauche";
        }

        if (pressedKeys.contains(D)){
            direction += "droite";
        }
        this.joueur.setDirection(direction);
    }

//    private void touche(KeyEvent event){
////        switch (event.getCode()){
////            case Z -> joueur.setHaut(true);
////            case S -> joueur.setBas(true);
////            case Q -> joueur.setGauche(true);
////            case D -> joueur.setDroite(true);
////        }
//        if (event.getCode() == KeyCode.Z){
//            joueur.setHaut(true);
//        }
//        if (event.getCode() == KeyCode.S){
//            joueur.setBas(true);
//        }
//        if (event.getCode() == KeyCode.Q){
//            joueur.setGauche(true);
//        }
//        if (event.getCode() == KeyCode.D){
//            joueur.setDroite(true);
//        }
////        System.out.println("x : " + joueur.getX() + ", y : " + joueur.getY());
//    }

}
