package universite_paris8.iut.osall.boom.modele.entite.listeObservable;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.osall.boom.modele.item.Consommable.Consommable;
import universite_paris8.iut.osall.boom.modele.item.Consommable.PotionHeal;
import universite_paris8.iut.osall.boom.modele.item.Consommable.TotemResurrection;
import universite_paris8.iut.osall.boom.modele.item.Equipement.Equipement;
import universite_paris8.iut.osall.boom.modele.item.Item;
import javafx.scene.control.Label;

public class ListObsItemJoueur implements ListChangeListener<Item> {

    private Pane equipementJoueur;
    private Pane inventaireJoueur;
    private Label nbrePotionHeal;
    private Label nbreTotem;
    private int compteurPotionHeal, compteurTotem;

    public ListObsItemJoueur(Pane equipementJoueur, Pane inventaireJoueur, Label nbrePotionHeal, Label nbreTotem) {
        this.equipementJoueur = equipementJoueur;
        this.inventaireJoueur = inventaireJoueur;
        this.nbrePotionHeal = nbrePotionHeal;
        this.nbreTotem = nbreTotem;
        compteurTotem = 0;
        compteurPotionHeal = 0;
    }

    @Override
    public void onChanged(Change<? extends Item> change) {
        System.out.println("changement");
        while (change.next()){
            System.out.println("est-ce des ajouts(inventaire du joueur) ? " + change.wasAdded());
            System.out.println("est-ce des suppressions(inventaire du joueur) ? " + change.wasRemoved());
            System.out.println("les ajouts(inventaire du joueur) : " + change.getAddedSubList());
            System.out.println("Les suppressions(inventaire du joueur) : " + change.getRemoved());
            for (Item i : change.getAddedSubList()) {
                if (i instanceof PotionHeal){
                    compteurPotionHeal++;
                }
                if (i instanceof TotemResurrection){
                    compteurTotem++;
                }
            }
            for (Item i : change.getRemoved()) {
                if (i instanceof Equipement){
                    equipementJoueur.getChildren().remove(equipementJoueur.lookup("#"+i.getId()));
                    if (i instanceof PotionHeal){
                        compteurPotionHeal--;
                    }
                    if (i instanceof TotemResurrection){
                        compteurTotem--;
                    }
                }
                if (i instanceof Consommable){
                    inventaireJoueur.getChildren().remove(inventaireJoueur.lookup("#"+i.getId()));
                }
            }
        }
        nbrePotionHeal.setText(""+compteurPotionHeal);
        nbreTotem.setText(""+compteurTotem);
    }
}
