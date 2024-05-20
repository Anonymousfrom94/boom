package universite_paris8.iut.osall.boom.modele.vue;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.osall.boom.controller.Clavier;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;
import javafx.scene.image.Image;


import java.awt.*;

public class VueJoueur {

    private ImageView imageView;
    private Image z1;
    private Image z2;
    private Image s1;
    private Image s2;
    private Image q1;
    private Image q2;
    private Image d1;
    private Image d2;

    public VueJoueur(Pane pane, Joueur joueur) {

        this.z1 = new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgPerso/joueur_haut_1.png");
        this.z2 = new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgPerso/joueur_haut_2.png");
        this.s1 = new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgPerso/joueur_bas_1.png");
        this.s2 = new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgPerso/joueur_bas_2.png");
        this.q1 = new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgPerso/joueur_gauche_1.png");
        this.q2 = new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgPerso/joueur_gauche_2.png");
        this.d1 = new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgPerso/joueur_droite_1.png");
        this.d2 = new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgPerso/joueur_droite_2.png");

        Circle r = new Circle(10);
        r.setFill(Color.VIOLET);
        r.setId(joueur.getId());
        r.setTranslateX(joueur.getX());
        r.setTranslateY(joueur.getY());
        pane.getChildren().add(r);
        Clavier x  = new Clavier(joueur);
        pane.addEventFilter(KeyEvent.KEY_PRESSED, x);
        r.translateXProperty().bind(joueur.getXproperty());
        r.translateYProperty().bind(joueur.getYproperty());
    }
}
