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

    @BeforeEach
    void setUp() {
        environnement = new Environnement();  // Initialiser un environnement réel
        joueur = new Joueur(environnement);
    }

    @Test
    void testSeDeplace() {
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
    }

    @Test
    void testEstAttaquable() {
        Ennemie ennemie = new Ennemie(environnement);
        ennemie.setX(joueur.getX() + 10);
        ennemie.setY(joueur.getY() + 10);
        environnement.getActeurs().add(ennemie);

        assertNotNull(joueur.estAttaquable());
    }

    @Test
    void testAttaque() {
        Ennemie ennemie = new Ennemie(environnement);
        ennemie.setX(joueur.getX() + 10);
        ennemie.setY(joueur.getY() + 10);
        environnement.getActeurs().add(ennemie);

        joueur.attaque();
        assertTrue(ennemie.getPv() < 100);  // Suppose que l'attaque inflige des dégâts
    }

    @Test
    void testPeutRamasse() {
        Item item = new Item(environnement, "TestItem", joueur.getX() + 10, joueur.getY() + 10);
        environnement.getInventaireEnvironnement().add(item);

        assertNotNull(joueur.peutRamasse());
    }

    @Test
    void testRamasse() {
        Item item = new Item(environnement, "TestItem", joueur.getX() + 10, joueur.getY() + 10);
        environnement.getInventaireEnvironnement().add(item);

        joueur.ramasse();
        assertTrue(joueur.getInventaire().contains(item));
        assertFalse(environnement.getInventaireEnvironnement().contains(item));
    }

    @Test
    void testABottesDeLevitation() {
        assertFalse(joueur.aBottesDeLevitation());

        BotteLevitation bottes = new BotteLevitation(environnement);
        joueur.setEquipement(bottes);

        assertTrue(joueur.aBottesDeLevitation());
    }
}
