package personnages;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import personnages.Joueur;
import personnages.classes.Guerrier;
import personnages.equipements.armes.EpeeLongue;
import personnages.equipements.armures.CotteDeMailles;
import personnages.races.Humain;

class JoueurTest {
    private Joueur joueur;
    private Humain humain;
    private Guerrier guerrier;

    @BeforeEach
    void setUp() {
        joueur = new Joueur("TestHero", new Humain(), new Guerrier());
    }

    @Test
    void testConstructeur() {
        assertEquals("TestHero (Humain guerrier)", joueur.sePresenter());
        assertTrue(joueur.getPv() > 0);
        assertTrue(joueur.getVitesse() > 0);
        assertTrue(joueur.getForce() > 0);
        assertTrue(joueur.getDexterite() > 0);
        assertTrue(joueur.getInitiative() > 0);
    }

    @Test
    void testEstJoueur() {
        assertTrue(joueur.estJoueur());
    }

    @Test
    void testRecupererEquipement() {
        int tailleInitiale = joueur.getTailleInventaire();
        EpeeLongue epee = new EpeeLongue();
        joueur.recuperer(epee);
        assertEquals(tailleInitiale + 1, joueur.getTailleInventaire());
    }

    @Test
    void testRegagnerPv() {
        joueur.subirAttaque(10); // Inflige des dégâts
        int pvApresDegats = joueur.getPv();
        joueur.regagnePv();
        assertEquals(joueur.getPvMax(), joueur.getPv());
        assertTrue(joueur.getPv() > pvApresDegats);
    }

    @Test
    void testGetClasse() {
        assertEquals("Guerrier", joueur.getClasse());
    }

    @Test
    void testGetInfos() {
        String infos = joueur.getInfos();
        assertTrue(infos.contains(joueur.getPv() +"/" + joueur.getPvMax()));
    }

    @Test
    void testToString() {
        String toString = joueur.toString();
        assertTrue(toString.contains("TestHero"));
        assertTrue(toString.contains("Humain"));
        assertTrue(toString.contains("Guerrier"));
        assertTrue(toString.contains("Vie: "+joueur.getPv()+"/"+joueur.getPvMax()));
        assertTrue(toString.contains("Inventaire: [" + joueur.getTailleInventaire() + "]\n" + joueur.contenuInventaire()));
        assertTrue(toString.contains("Force: "+joueur.getForce()));
        assertTrue(toString.contains("Dextérité: "+joueur.getDexterite()));
        assertTrue(toString.contains("Vitesse: "+joueur.getVitesse()));
    }

}