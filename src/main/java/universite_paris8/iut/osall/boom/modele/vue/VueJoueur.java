package universite_paris8.iut.osall.boom.modele.vue;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.osall.boom.controller.clavier;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class VueJoueur {

    public VueJoueur(Pane pane, Joueur joueur) {
        Circle r = new Circle(10);
        r.setFill(Color.VIOLET);
        r.setId(String.valueOf(joueur.getId()));
        pane.getChildren().add(r);
        clavier x  = new clavier(joueur);
        pane.addEventFilter(KeyEvent.KEY_PRESSED, x);
        r.translateXProperty().bind(joueur.Xproperty());
        r.translateYProperty().bind(joueur.Yproperty());
    }
}
