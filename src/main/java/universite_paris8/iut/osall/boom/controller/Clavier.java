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
    private final Set<KeyCode> touchePress;

    public Clavier(Joueur joueur) {
        this.joueur = joueur;
        this.touchePress = new HashSet<>();
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED)
            this.touchePress.add(keyEvent.getCode());
        else if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED)
            this.touchePress.remove(keyEvent.getCode());


        String direction = "";

        if (touchePress.contains(Z)){
            direction += "haut";
        }
        if (touchePress.contains(S)){
            direction += "bas";
        }
        if (touchePress.contains(Q)){
            direction += "gauche";
        }
        if (touchePress.contains(D)){
            direction += "droite";
        }
        this.joueur.setDirection(direction);
        
        if (touchePress.contains(J)){
            this.joueur.attaque();
            touchePress.clear();
        }

        if (touchePress.contains(K) && joueur.getEquipement() != null){
            joueur.getEquipement().utilise();}}
}
