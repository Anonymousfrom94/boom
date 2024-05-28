package universite_paris8.iut.osall.boom.modele.entite;
import universite_paris8.iut.osall.boom.modele.Environnement;

public class Joueur extends Acteur {

    private String direction;
    private boolean haut;
    private boolean bas;
    private boolean gauche;
    private boolean droite;

    public Joueur(Environnement environnement) {
        super(environnement, 240, 240, 4);
        this.haut = false;
        this.bas = false;
        this.droite = false;
        this.gauche = false;
        this.direction = "";
    }

    private int indice(int newX, int newY) {
        int ligne, colonne, indice;
        colonne = (int) newX / 16;
        ligne = (int) newY / 16;
        return ligne * 30 + colonne;
    }

    private boolean obstacle(int indice1, int indice2, int obstacle){
        if (this.getEnvironnement().getMap().getTableau()[indice1] == obstacle ||
                this.getEnvironnement().getMap().getTableau()[indice2] == obstacle){
            System.out.println(this.toString() + "Indice 1: " + indice1);
            System.out.println(this.toString() + "Indice 2: " + indice2);
            return false;
        }
        return true;
    }

    public boolean peutSeDeplacer(){
        int indice1, indice2, hauteur, largeur;
        int obstacle = 26;
        hauteur = 16;
        largeur = 16;
        boolean bloquer = true;
        if (this.direction.contains("haut")){
            indice1 = indice(this.getX(), this.getY() - this.getVitesse());
            indice2 = indice(this.getX() + largeur, this.getY() - this.getVitesse());
            bloquer = obstacle(indice1, indice2, obstacle);
        }
        if (this.direction.contains("bas")){
            indice1 = indice(this.getX(), this.getY() + hauteur + this.getVitesse());
            indice2 = indice(this.getX() + largeur, this.getY() + hauteur + this.getVitesse());
            bloquer =  obstacle(indice1, indice2, obstacle);
        }
        if (this.direction.contains("gauche")){
            indice1 = indice(this.getX() - this.getVitesse(), this.getY());
            indice2 = indice(this.getX() - this.getVitesse(), this.getY() + hauteur);
            bloquer = obstacle(indice1, indice2, obstacle);
        }
        if (this.direction.contains("droite")){
            indice1 = indice(this.getX() + largeur + this.getVitesse(), this.getY());
            indice2 = indice(this.getX() + largeur, this.getY() + hauteur - this.getVitesse());
            bloquer =  obstacle(indice1, indice2, obstacle);
        }
        return bloquer;
    }

    public void seDeplace() {
        if (peutSeDeplacer()) {
            int dx = 0;
            int dy = 0;
            int vitesse = getVitesse();

            if (this.direction.contains("haut"))
                dy -= vitesse;
            if (this.direction.contains("bas"))
                dy += vitesse;
            if (this.direction.contains("gauche"))
                dx -= vitesse;
            if (this.direction.contains("droite"))
                dx += vitesse;

            setX(getX() + dx);
            setY(getY() + dy);
        }
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return this.direction;
    }
}