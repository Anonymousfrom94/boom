package universite_paris8.iut.osall.boom.modele.entite;

import javafx.collections.ListChangeListener;

public class ListObsActeurs implements ListChangeListener<Acteur> {
    @Override
    public void onChanged(Change<? extends Acteur> change) {
        System.out.println("Changement");
    }
}
