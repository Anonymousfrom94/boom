package universite_paris8.iut.osall.boom.modele.listeObservable;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Ennemie;
import universite_paris8.iut.osall.boom.modele.item.Arme.BatonElectrique;
import universite_paris8.iut.osall.boom.modele.item.Arme.EpeEnBois;

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

    public void creerSpriteEnnemie(Pane pane, Ennemie ennemie) {
        ImageView imageView = new ImageView();
        imageView.setImage(new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgEnnemies/squelette.png"));
        imageView.setId(ennemie.getId());

        Rectangle vieBarre = new Rectangle(16, 2, Color.GREEN);
        vieBarre.setId("vieBarre_" + ennemie.getId());
        pane.getChildren().addAll(imageView, vieBarre);

        imageView.translateXProperty().bind(ennemie.getXproperty());
        imageView.translateYProperty().bind(ennemie.getYproperty());
        vieBarre.translateXProperty().bind(ennemie.getXproperty());
        vieBarre.translateYProperty().bind(ennemie.getYproperty().subtract(10));
    }

    public static void updateBarreDeVie(Acteur acteur, Pane pane) {
        int largeurBarre = 16;
        double pourcentageVieRestante = (double) acteur.getPv() / acteur.getPvMax();
        Rectangle vieBarre = (Rectangle) pane.lookup("#vieBarre_" + acteur.getId());
        System.out.println("Pourcentage de vie " + pourcentageVieRestante);

        if (vieBarre != null) {

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