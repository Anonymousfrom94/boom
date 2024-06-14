package universite_paris8.iut.osall.boom.modele.listeObservable;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import universite_paris8.iut.osall.boom.modele.item.Consommable.PotionHeal;
import universite_paris8.iut.osall.boom.modele.item.Consommable.TotemResurrection;
import universite_paris8.iut.osall.boom.modele.item.Equipement.BotteLevitation;
import universite_paris8.iut.osall.boom.modele.item.Arme.*;
import universite_paris8.iut.osall.boom.modele.item.Consommable.Consommable;
import universite_paris8.iut.osall.boom.modele.item.Equipement.CeintureTP;
import universite_paris8.iut.osall.boom.modele.item.Equipement.CouronneTemporel;
import universite_paris8.iut.osall.boom.modele.item.Equipement.Equipement;

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
        ImageView imageView = new ImageView();

        if (item instanceof Consommable){
            if (item instanceof PotionHeal){
                imageView.setImage(new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgConsommable/potionHeal.png"));
            }
            if (item instanceof TotemResurrection){
                imageView.setImage(new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgConsommable/totemResurection.png"));
            }
        }

        if (item instanceof Equipement){
            if (item instanceof CouronneTemporel){
                imageView.setImage(new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgEquipement/couronne.png"));
            }
            if (item instanceof CeintureTP){
                imageView.setImage(new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgEquipement/ceinture.png"));
            }
            if (item instanceof BotteLevitation){
                imageView.setImage(new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgEquipement/bottesLevitation.png"));
            }
        }

        if (item instanceof Arme){
            if (item instanceof EpeEnBois){
                imageView.setImage(new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgArme/epeeBois.png"));
            }
            if (item instanceof Dague){
                imageView.setImage(new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgArme/dague.png"));
            }
            if (item instanceof Arc){
                imageView.setImage(new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgArme/arc.png"));
            }
            if (item instanceof BatonElectrique){
                imageView.setImage(new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgArme/batonMagique.png"));
            }
            if (item instanceof Sniper){
                imageView.setImage(new Image("file:src/main/resources/universite_paris8/iut/osall/boom/imgArme/sniper.png"));
            }
        }

        imageView.setId(item.getId());
        pane.getChildren().add(imageView);
        imageView.translateXProperty().bind(item.getXProperty());
        imageView.translateYProperty().bind(item.getYProperty());
    }
}
