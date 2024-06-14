package universite_paris8.iut.osall.boom.modele.listeObservable;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import universite_paris8.iut.osall.boom.modele.item.Arme.BatonElectrique;
import universite_paris8.iut.osall.boom.modele.item.Arme.Dague;
import universite_paris8.iut.osall.boom.modele.item.Arme.EpeEnBois;
import universite_paris8.iut.osall.boom.modele.item.Arme.Sniper;
import universite_paris8.iut.osall.boom.modele.item.Consommable.Consommable;
import universite_paris8.iut.osall.boom.modele.item.Consommable.PotionHeal;
import universite_paris8.iut.osall.boom.modele.item.Consommable.TotemResurrection;
import universite_paris8.iut.osall.boom.modele.item.Equipement.BotteLevitation;
import universite_paris8.iut.osall.boom.modele.item.Equipement.CeintureTP;
import universite_paris8.iut.osall.boom.modele.item.Equipement.CouronneTemporel;
import universite_paris8.iut.osall.boom.modele.item.Equipement.Equipement;
import universite_paris8.iut.osall.boom.modele.item.Item;
import javafx.scene.control.Label;

public class ListObsItemJoueur implements ListChangeListener<Item> {

    private Pane equipementJoueur;
    private Pane inventaireJoueur;
    private Label nbrePotionHeal;
    private Label nbreTotem;
    private int compteurPotionHeal, compteurTotem;
    private Label etatBaton;
    private Label etatSniper;
    private Label etatEpee;
    private Label etatDague;
    private Label etatCouronne;
    private Label etatCeinture;
    private Label etatBottes;
    private Label etatArc;
    private Label etatCollier;
    private Label etatGant;

    public ListObsItemJoueur(Pane equipementJoueur, Pane inventaireJoueur, Label nbrePotionHeal, Label nbreTotem
            , Label etatBaton, Label etatSniper, Label etatEpee, Label etatDague, Label etatCouronne, Label etatCeinture,
                             Label etatBottes, Label etatArc, Label etatCollier, Label etatGant) {
        this.equipementJoueur = equipementJoueur;
        this.inventaireJoueur = inventaireJoueur;
        this.nbrePotionHeal = nbrePotionHeal;
        this.nbreTotem = nbreTotem;
        compteurTotem = 0;
        compteurPotionHeal = 0;
        this.etatBaton = etatBaton;
        this.etatSniper = etatSniper;
        this.etatEpee = etatEpee;
        this.etatDague = etatDague;
        this.etatCouronne = etatCouronne;
        this.etatCeinture = etatCeinture;
        this.etatBottes = etatBottes;
        this.etatArc = etatArc;
        this.etatCollier = etatCollier;
        this.etatGant = etatGant;
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
                if (i instanceof BatonElectrique){
                    etatBaton.setText("Unlock");
                }
                if (i instanceof BotteLevitation){
                    etatBottes.setText("Unlock");
                }
                if (i instanceof CeintureTP){
                    etatCeinture.setText("Unlock");
                }
                if (i instanceof CouronneTemporel){
                    etatCouronne.setText("Unlock");
                }
                if (i instanceof Dague){
                    etatDague.setText("Unlock");
                }
                if (i instanceof EpeEnBois){
                    etatEpee.setText("Unlock");
                }
                if (i instanceof Sniper){
                    etatSniper.setText("Unlock");
                }
            }
            for (Item i : change.getRemoved()) {
                if (i instanceof Consommable){
                    equipementJoueur.getChildren().remove(equipementJoueur.lookup("#"+i.getId()));
                    if (i instanceof PotionHeal){
                        compteurPotionHeal--;
                    }
                    if (i instanceof TotemResurrection){
                        compteurTotem--;
                    }
                }
//                if (i instanceof Consommable){
//                    inventaireJoueur.getChildren().remove(inventaireJoueur.lookup("#"+i.getId()));
//                }
            }
        }
        nbrePotionHeal.setText(""+compteurPotionHeal);
        nbreTotem.setText(""+compteurTotem);
    }
}
