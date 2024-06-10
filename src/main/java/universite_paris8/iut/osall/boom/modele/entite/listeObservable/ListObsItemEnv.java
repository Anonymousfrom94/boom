package universite_paris8.iut.osall.boom.modele.entite.listeObservable;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.osall.boom.modele.item.Consommable.PotionHeal;
import universite_paris8.iut.osall.boom.modele.item.Consommable.TotemResurrection;
import universite_paris8.iut.osall.boom.modele.item.Equipement.BotteLevitation;
import universite_paris8.iut.osall.boom.modele.item.Item;

public class ListObsItemEnv implements ListChangeListener<Item> {

    private Pane pane;

    public ListObsItemEnv(Pane pane) {
        this.pane = pane;
    }

    @Override
    public void onChanged(Change<? extends Item> change) {
        System.out.println("changement");
        while (change.next()){
            System.out.println("est-ce des ajouts ? " + change.wasAdded());
            System.out.println("est-ce des suppressions ? " + change.wasRemoved());
            System.out.println("les ajouts : " + change.getAddedSubList());
            System.out.println("Les suppressions : " + change.getRemoved());
            for (Item i : change.getAddedSubList()) {
                creerSpriteItem(pane, i);
            }
            for (Item i : change.getRemoved()) {
                pane.getChildren().remove(pane.lookup("#"+i.getId()));
            }
        }
    }

    public void creerSpriteItem(Pane pane, Item item) {
        Circle r = new Circle(8);
        if (item instanceof PotionHeal){
            r.setFill(Color.GREEN);
        }
        if (item instanceof TotemResurrection){
            r.setFill(Color.YELLOW);
        }
        r.setId(item.getId());
        pane.getChildren().add(r);
        r.translateXProperty().bind(item.getXProperty());
        r.translateYProperty().bind(item.getYProperty());
        if (item instanceof BotteLevitation){
            r.setFill(Color.PURPLE);
            r.setId(item.getId());
            pane.getChildren().add(r);
            r.translateXProperty().bind(item.getXProperty());
            r.translateYProperty().bind(item.getYProperty());
        }
    }
}
