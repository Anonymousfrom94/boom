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
       this.direction = "bas";
    }

    private int indice(int newX, int newY){
        int ligne, colonne, indice;
        colonne = (int) newX/16;
        ligne = (int) newY/16;
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
        int obstacle = 4;
        hauteur = 16;
        largeur = 16;
        boolean bloquer = true;
        if (this.haut){
            indice1 = indice(this.getX(), this.getY() - this.getVitesse());
            indice2 = indice(this.getX() + largeur, this.getY() - this.getVitesse());
            bloquer = obstacle(indice1, indice2, obstacle);
        }
        if (this.bas){
            indice1 = indice(this.getX(), this.getY() + hauteur + this.getVitesse());
            indice2 = indice(this.getX() + largeur, this.getY() + hauteur + this.getVitesse());
            bloquer =  obstacle(indice1, indice2, obstacle);
        }
        if (this.gauche){
            indice1 = indice(this.getX() - this.getVitesse(), this.getY());
            indice2 = indice(this.getX() - this.getVitesse(), this.getY() + hauteur);
            bloquer = obstacle(indice1, indice2, obstacle);
        }
        if (this.droite){
            indice1 = indice(this.getX() + largeur + this.getVitesse(), this.getY());
            indice2 = indice(this.getX() + largeur, this.getY() + hauteur - this.getVitesse());
            bloquer =  obstacle(indice1, indice2, obstacle);
        }
        return bloquer;
    }

    public void seDeplace(){
        if (peutSeDeplacer()){
            if (this.haut){
                if (this.getY() - this.getVitesse() >= 0 ){
                    this.setY(this.getY() - this.getVitesse());
                }
            }
            if (this.bas){
                if (this.getY() + this.getVitesse() <= 464){
                    this.setY(this.getY() + this.getVitesse());
                }
            }
            if (this.gauche){
                if (this.getX() - this.getVitesse() >= 0){
                    this.setX(this.getX() - this.getVitesse());
                }
            }
            if (this.droite){
                if (this.getX() + this.getVitesse() <= 464){
                    this.setX(this.getX() + this.getVitesse());
                }
            }
        }
        this.haut = false;
        this.bas = false;
        this.droite = false;
        this.gauche = false;
    }


    public boolean estHaut() {
        return haut;
    }

    public void setHaut(boolean haut) {
        this.haut = haut;
    }

    public boolean estBas() {
        return bas;
    }

    public void setBas(boolean bas) {
        this.bas = bas;
    }

    public boolean estGauche() {
        return gauche;
    }

    public void setGauche(boolean gauche) {
        this.gauche = gauche;
    }

    public boolean estDroite() {
        return droite;
    }

    public void setDroite(boolean droite) {
        this.droite = droite;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return super.toString() + "Joueur{" +
                ", haut=" + haut +
                ", bas=" + bas +
                ", gauche=" + gauche +
                ", droite=" + droite +
                '}';
    }
}
