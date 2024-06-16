package universite_paris8.iut.osall.boom.modele.entite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.item.Equipement.BotteLevitation;
import universite_paris8.iut.osall.boom.modele.item.Item;

import static org.junit.jupiter.api.Assertions.*;

class JoueurTest {

    private Joueur joueur;
    private Environnement environnement;

    @BeforeEach //ce qu'on init avant les test
    void setUp() {
        environnement = new Environnement();
        joueur = new Joueur(environnement);
    }

    @Test
    void testSeDeplace() {
        // déplacement normaux
        joueur.setDirection("haut");
        int yInitial = joueur.getY();
        joueur.seDeplace();
        assertEquals(yInitial - joueur.getVitesse(), joueur.getY());

        joueur.setDirection("bas");
        int yAfterUp = joueur.getY();
        joueur.seDeplace();
        assertEquals(yAfterUp + joueur.getVitesse(), joueur.getY());

        joueur.setDirection("gauche");
        int xInitial = joueur.getX();
        joueur.seDeplace();
        assertEquals(xInitial - joueur.getVitesse(), joueur.getX());

        joueur.setDirection("droite");
        int xAfterLeft = joueur.getX();
        joueur.seDeplace();
        assertEquals(xAfterLeft + joueur.getVitesse(), joueur.getX());

        //déplacement limite map
        joueur.setX(0);
        joueur.setY(0);
        joueur.setDirection("haut");
        joueur.seDeplace();
        assertEquals(0, joueur.getY());

        joueur.setDirection("gauche");
        joueur.seDeplace();
        assertEquals(0, joueur.getX());

        joueur.setX(environnement.getWidth() - joueur.getLargeur());
        joueur.setY(environnement.getHeight() - joueur.getHauteur());
        joueur.setDirection("bas");
        joueur.seDeplace();
        assertEquals(environnement.getHeight() - joueur.getHauteur(), joueur.getY());

        joueur.setDirection("droite");
        joueur.seDeplace();
        assertEquals(environnement.getWidth() - joueur.getLargeur(), joueur.getX());
    }

    @Test
    void testEstAttaquable() {
        // ennemie a coté
        Ennemie ennemie = new Ennemie(environnement);
        ennemie.setX(joueur.getX() + 10);
        ennemie.setY(joueur.getY() + 10);
        environnement.getActeurs().add(ennemie);

        assertNotNull(joueur.estAttaquable());

        //pas ennemies
        environnement.getActeurs().remove(ennemie);
        ennemie.setX(joueur.getX() + 300);
        ennemie.setY(joueur.getY() + 300);
        environnement.getActeurs().add(ennemie);

        assertNull(joueur.estAttaquable());
    }

    @Test
    void testAttaque() {
        //attaque
        Ennemie ennemie = new Ennemie(environnement);
        ennemie.setX(joueur.getX() + 10);
        ennemie.setY(joueur.getY() + 10);
        environnement.getActeurs().add(ennemie);

        int pvInitial = ennemie.getPv();
        joueur.attaque();
        assertTrue(ennemie.getPv() < pvInitial);

        //pas attaquer
        environnement.getActeurs().remove(ennemie);
        ennemie.setX(joueur.getX() + 300);
        ennemie.setY(joueur.getY() + 300);
        environnement.getActeurs().add(ennemie);

        pvInitial = ennemie.getPv();
        joueur.attaque();
        assertEquals(pvInitial, ennemie.getPv());
    }

    @Test
    void testPeutRamasse() {
        //item proche
        Item item = new Item(environnement, "TestItem", joueur.getX() + 10, joueur.getY() + 10);
        environnement.getInventaireEnvironnement().add(item);

        assertNotNull(joueur.peutRamasse());

        //item loin
        environnement.getInventaireEnvironnement().remove(item);
        item.setX(joueur.getX() + 300);
        item.setY(joueur.getY() + 300);
        environnement.getInventaireEnvironnement().add(item);

        assertNull(joueur.peutRamasse());
    }

    @Test
    void testRamasse() {
        //nouvel item dans l'inventaire
        Item item = new Item(environnement, "TestItem", joueur.getX() + 10, joueur.getY() + 10);
        environnement.getInventaireEnvironnement().add(item);

        joueur.ramasse();
        assertTrue(joueur.getInventaire().contains(item));
        assertFalse(environnement.getInventaireEnvironnement().contains(item));

        //aucun changement
        joueur.getInventaire().remove(item);
        environnement.getInventaireEnvironnement().add(item);
        item.setX(joueur.getX() + 300);
        item.setY(joueur.getY() + 300);

        joueur.ramasse();
        assertFalse(joueur.getInventaire().contains(item));
        assertTrue(environnement.getInventaireEnvironnement().contains(item));
    }

    @Test
    void testABottesDeLevitation() {
        //a des bottes
        assertFalse(joueur.aBottesDeLevitation());

        BotteLevitation bottes = new BotteLevitation(environnement);
        joueur.setEquipement(bottes);

        assertTrue(joueur.aBottesDeLevitation());

        //a pas de botte
        joueur.setEquipement(null);
        assertFalse(joueur.aBottesDeLevitation());
    }
}
