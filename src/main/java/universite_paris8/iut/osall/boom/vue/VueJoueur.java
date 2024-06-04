package universite_paris8.iut.osall.boom.vue;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import universite_paris8.iut.osall.boom.controller.Clavier;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;
import javafx.scene.image.Image;

public class VueJoueur {

    private Pane pane;
    private boolean image1 = true;
    private Joueur joueur;

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

        this.imageView = new ImageView();
        this.joueur = joueur;

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

    public void changementImg(){
        if (image1 == true){
            switch (joueur.getDirection()){
                case "haut" -> this.imageView.setImage(z1);
                case "bas" -> this.imageView.setImage(s1);
                case "gauche" -> this.imageView.setImage(q1);
                case "droite" -> this.imageView.setImage(d1);
            }
        }
        else {
            switch (joueur.getDirection()){
                case "haut" -> this.imageView.setImage(z2);
                case "bas" -> this.imageView.setImage(s2);
                case "gauche" -> this.imageView.setImage(q2);
                case "droite" -> this.imageView.setImage(d2);
            }
        }
//        this.image1 = !image1;
    }

    public void changementImg2() {
        this.image1 = !image1;
    }
}
