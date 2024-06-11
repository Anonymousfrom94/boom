package universite_paris8.iut.osall.boom.modele.Environnement;

public class Map {

    private int[]tableau;

    public Map() {
        this.tableau = new int[10000];
    }



    public int[] getTableau() {
        return this.tableau;
    }

    public void setTableau(int[] tableau) {
        this.tableau = tableau;
    }
}
