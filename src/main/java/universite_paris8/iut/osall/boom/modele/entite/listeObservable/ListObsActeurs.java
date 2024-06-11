package universite_paris8.iut.osall.boom.modele.entite.listeObservable;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Ennemie;
import universite_paris8.iut.osall.boom.modele.item.Consommable.Consommable;
import universite_paris8.iut.osall.boom.modele.item.Consommable.PotionHeal;

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
        pane.getChildren().add(imageView);
        imageView.translateXProperty().bind(ennemie.getXproperty());
        imageView.translateYProperty().bind(ennemie.getYproperty());
    }
}
