package universite_paris8.iut.osall.boom.vue;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.osall.boom.modele.entite.Ennemie;

public class VueEnnemie {
    public VueEnnemie(Pane pane, Ennemie ennemie) {
        Circle r = new Circle(10);
        r.setFill(Color.RED);
        r.setId(ennemie.getId());
        r.setTranslateX(ennemie.getX());
        r.setTranslateY(ennemie.getY());
        pane.getChildren().add(r);
        r.translateXProperty().bind(ennemie.getXproperty());
        r.translateYProperty().bind(ennemie.getYproperty());
    }
}
