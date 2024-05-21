package universite_paris8.iut.osall.boom.modele.entite;

public class Ennemie extends Acteur {

    public Ennemie(){
        super((int) (Math.random() * 470), (int) (Math.random() * 470), 4);
    }
}
//(int) Math.random() * (480 - 1)git