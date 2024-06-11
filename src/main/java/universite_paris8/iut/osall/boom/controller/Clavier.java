package universite_paris8.iut.osall.boom.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;
import universite_paris8.iut.osall.boom.modele.item.Equipement.CeintureTP;
import universite_paris8.iut.osall.boom.modele.item.Equipement.CouronneTemporel;
import universite_paris8.iut.osall.boom.modele.item.Item;

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
        
        if (pressedKeys.contains(J)){
            this.joueur.attaque();
            pressedKeys.clear();
            System.out.println("joueur attaque ennemie");
        }
        
        if (pressedKeys.contains(T)){
            boolean ceinture = false;
            CeintureTP ceintureTP = null;
            for (int i = 0; i < joueur.getInventaire().size(); i++){
                if (joueur.getInventaire().get(i).getNom() == "Ceinture de Téléportation"){
                    ceinture = true;
                    ceintureTP = (CeintureTP) joueur.getInventaire().get(i);
                }
            }
            if (ceinture){
                pressedKeys.clear();
                ceintureTP.utilise();
            }
        }

        if (pressedKeys.contains(K)){
            boolean couronne = false;
            CouronneTemporel couronneTemporel = null;
            for (int i = 0; i < joueur.getInventaire().size(); i++){
                if (joueur.getInventaire().get(i).getNom() == "Couronne Temporel"){
                    couronne = true;
                    couronneTemporel = (CouronneTemporel) joueur.getInventaire().get(i);
                }
            }
            if (couronne){
                pressedKeys.clear();
                couronneTemporel.utilise();
            }
        }
    }
}
