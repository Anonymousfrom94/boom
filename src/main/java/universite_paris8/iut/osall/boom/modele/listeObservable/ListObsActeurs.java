package universite_paris8.iut.osall.boom.modele.listeObservable;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Ennemie;

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
                creerSpriteEnnemie(pane, (Ennemie) a);
            }
            for (Acteur a : change.getRemoved()) {
                pane.getChildren().remove(pane.lookup("#" + a.getId()));
            }
        }
    }

    public void creerSpriteEnnemie(Pane pane, Ennemie ennemie) {
        ImageView imageView = new ImageView();
        imageView.setImage(new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgEnnemies/squelette.png"));
        imageView.setId(ennemie.getId());

        Rectangle vieBarre = new Rectangle(16, 2, Color.RED);
        vieBarre.setId("vieBarre_" + ennemie.getId());
        pane.getChildren().addAll(imageView, vieBarre); // Assurez-vous que imageView et vieBarre sont ajoutés au Pane

        // Liaisons et positions
        imageView.translateXProperty().bind(ennemie.getXproperty());
        imageView.translateYProperty().bind(ennemie.getYproperty());
        vieBarre.translateXProperty().bind(ennemie.getXproperty());
        vieBarre.translateYProperty().bind(ennemie.getYproperty().subtract(10)); // Placez la barre de vie au-dessus de l'image
    }

    public static void updateBarreDeVie(Ennemie ennemie, Pane pane) {
        double pourcentageVieRestante = (double) ennemie.getPv() / ennemie.getPvMax();
        Rectangle vieBarre = (Rectangle) pane.lookup("#vieBarre_" + ennemie.getId());

        if (vieBarre != null) {
            vieBarre.setWidth(16 * pourcentageVieRestante); // Largeur maximale est 16 pixels
        }
    }

    public Pane getPane() {
        return pane;
    }
}
