package universite_paris8.iut.osall.boom.vue;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.osall.boom.controller.Clavier;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;
import javafx.scene.image.Image;

public class VueJoueur {

    private Pane pane;
    private Pane equipementJoueur;
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

    public VueJoueur(Pane pane, Joueur joueur, Pane equipementJoueur) {

        this.pane = pane;
        this.equipementJoueur = equipementJoueur;
        this.imageView = new ImageView();
        this.joueur = joueur;

        //Barre de vie du joueur
        Rectangle vieBarre = new Rectangle(256, 10, Color.GREEN);
        vieBarre.setId("vieBarre_" + joueur.getId());
        equipementJoueur.getChildren().add(vieBarre);

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

        vieBarre.setTranslateX(46);
        vieBarre.setTranslateY(26);
    }

    public static void updateBarreDeVie(Acteur acteur, Pane pane) {
        int largeurBarre = 256;
        double pourcentageVieRestante = (double) acteur.getPv() / acteur.getPvMax();
        Rectangle vieBarre = (Rectangle) pane.lookup("#vieBarre_" + acteur.getId());
        System.out.println("Pourcentage de vie " + pourcentageVieRestante);

        if (vieBarre != null) {

            // Déterminer la couleur en fonction du pourcentage de vie restante
            if (pourcentageVieRestante > 0.75) {
                vieBarre.setFill(Color.GREEN);
            } else if (pourcentageVieRestante > 0.25) {
                vieBarre.setFill(Color.YELLOW);
            } else {
                vieBarre.setFill(Color.RED);
            }

            vieBarre.setWidth(largeurBarre * pourcentageVieRestante); // Ajuster la largeur en fonction du pourcentage de vie
        }
    }

    public void methodeSwitch(Image z, Image s, Image q, Image d){
        switch (joueur.getDirection()){
            case "haut" -> this.imageView.setImage(z);
            case "bas" -> this.imageView.setImage(s);
            case "gauche" -> this.imageView.setImage(q);
            case "droite" -> this.imageView.setImage(d);
        }
    }

    public void changementImg(){
        if (image1){
            methodeSwitch(z1, s1, q1, d1);
        }
        else {
            methodeSwitch(z2, s2, q2, d2);
        }
    }

    public void changementImg2() {
        this.image1 = !image1;
    }
}
