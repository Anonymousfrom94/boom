package universite_paris8.iut.osall.boom.modele.entite;

import javafx.collections.ListChangeListener;

public class ListObsActeurs implements ListChangeListener<Acteur> {
    @Override
    public void onChanged(Change<? extends Acteur> change) {
        System.out.println("Changement");
//        while (change.next()){
//            System.out.println("est-ce des ajouts ? " + change.wasAdded());
//            System.out.println("est-ce des suppressions ? " + change.wasRemoved());
//            System.out.println("les ajouts : " + change.getAddedSubList());
//            for (Acteur a : c.getAddedSubList()) {
//                creerSprite(a);
//                taille_ListeVivants++;
//                if (a instanceof Loup){
//                }
//            }
//        }
    }
}
