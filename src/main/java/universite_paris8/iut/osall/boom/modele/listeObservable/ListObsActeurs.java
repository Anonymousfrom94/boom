package universite_paris8.iut.osall.boom.modele.listeObservable;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Boss;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Ennemi;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Squelette;

public class ListObsActeurs implements ListChangeListener<Acteur> {

    private Pane pane;

    public ListObsActeurs(Pane pane) {
        this.pane = pane;
    }

    @Override
    public void onChanged(Change<? extends Acteur> change) {
        System.out.println("Changement");
        while (change.next()){
            System.out.println("est-ce des ajouts ? " + change.wasAdded());
            System.out.println("est-ce des suppressions ? " + change.wasRemoved());
            System.out.println("les ajouts : " + change.getAddedSubList());
            for (Acteur a : change.getAddedSubList()) {
                creerSpriteEnnemie(pane, (Ennemi) a);
                //Ajout des listener à chaque ennemi après qu'ils soient créer
                a.pvProperty().addListener(
                        (obs, old, nouv) -> ListObsActeurs.updateBarreDeVie(a, pane)
                );
            }
            for (Acteur a : change.getRemoved()) {
                pane.getChildren().remove(pane.lookup("#" + a.getId()));
                pane.getChildren().remove(pane.lookup("#vieBarre_" + a.getId()));
            }
        }
    }

    public void creerSpriteEnnemie(Pane pane, Ennemi ennemi) {

        ImageView imageView = new ImageView();
        if (ennemi instanceof Squelette){
            imageView.setImage(new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgEnnemies/squelette.png"));
        }
        if (ennemi instanceof Boss){
            imageView.setImage(new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgEnnemies/boss.png"));
        }
        imageView.setId(ennemi.getId());

        Rectangle vieBarre = new Rectangle(16, 2, Color.GREEN);
        vieBarre.setId("vieBarre_" + ennemi.getId());
        pane.getChildren().addAll(imageView, vieBarre);

        imageView.translateXProperty().bind(ennemi.getXproperty());
        imageView.translateYProperty().bind(ennemi.getYproperty());
        vieBarre.translateXProperty().bind(ennemi.getXproperty());
        vieBarre.translateYProperty().bind(ennemi.getYproperty().subtract(10)); // Placez la barre de vie au-dessus de l'image
    }

    public static void updateBarreDeVie(Acteur acteur, Pane pane) {
        int largeurBarre = 16;
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

    public Pane getPane() {
        return pane;
    }
}