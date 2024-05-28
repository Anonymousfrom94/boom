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

    private Pane pane;
    private boolean image1 = true;

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

//        Circle circle1 = new Circle(3);
//        circle1.setFill(Color.RED);
//        circle1.setTranslateX(joueur.getX());
//        circle1.setTranslateY(joueur.getY());
//        pane.getChildren().add(circle1);
//        circle1.translateXProperty().bind(joueur.getXproperty());
//        circle1.translateYProperty().bind(joueur.getYproperty());
//
//        Circle circle2 = new Circle(3);
//        circle2.setFill(Color.RED);
//        circle2.setTranslateX(joueur.getHitBox().getCoinHDX());
//        circle2.setTranslateY(joueur.getHitBox().getCoinHDY());
//        pane.getChildren().add(circle2);
//        circle1.translateXProperty().bind(joueur.getXproperty());
//        circle1.translateYProperty().bind(joueur.getYproperty());
//
//        Circle circle3 = new Circle(3);
//        circle3.setFill(Color.RED);
//        circle3.setTranslateX(joueur.getHitBox().getCoinBGX());
//        circle3.setTranslateY(joueur.getHitBox().getCoinBGY());
//        pane.getChildren().add(circle3);
//
//        Circle circle4 = new Circle(3);
//        circle4.setFill(Color.RED);
//        circle4.setTranslateX(joueur.getHitBox().getCoinBDX());
//        circle4.setTranslateY(joueur.getHitBox().getCoinBDY());
//        pane.getChildren().add(circle4);

        this.imageView = new ImageView();

        this.z1 = new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgPerso/joueur_haut_1.png");
        this.z2 = new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgPerso/joueur_haut_2.png");
        this.s1 = new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgPerso/joueur_bas_1.png");
        this.s2 = new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgPerso/joueur_bas_2.png");
        this.q1 = new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgPerso/joueur_gauche_1.png");
        this.q2 = new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgPerso/joueur_gauche_2.png");
        this.d1 = new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgPerso/joueur_droite_1.png");
        this.d2 = new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgPerso/joueur_droite_2.png");

        imageView.setImage(s1);
        imageView.setTranslateX(joueur.getX());
        imageView.setTranslateY(joueur.getY());

        pane.getChildren().add(imageView);
        Clavier x  = new Clavier(joueur);
        pane.addEventFilter(KeyEvent.KEY_PRESSED, x);
        imageView.translateXProperty().bind(joueur.getXproperty());
        imageView.translateYProperty().bind(joueur.getYproperty());

    }

    public void changementImg(Joueur joueur){
        switch (joueur.getDirection()){
            case "haut" -> this.imageView.setImage(z1);
            case "bas" -> this.imageView.setImage(s1);
            case "gauche" -> this.imageView.setImage(q1);
            case "droite" -> this.imageView.setImage(d1);
        }
    }
}
