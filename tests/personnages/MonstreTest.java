package personnages;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import personnages.equipements.armes.Arme;
import personnages.equipements.armures.Armure;

class MonstreTest {
    private Monstre monstre1;
    private Monstre monstre2;

    @BeforeEach
    void setUp() {
        monstre1 = new Monstre("Dragon", "D", 1, 10, 5, 5, 5, 6, 1, 12);
        monstre2 = new Monstre("Dragon", "D", 2, 10, 5, 5, 5, 6, 1, 12);
    }

    @Test
    void testConstructeur() {
        assertEquals("Dragon", monstre1.sePresenter());
        assertEquals(10, monstre1.getPv());
        assertEquals(5, monstre1.getForce());
        assertEquals(5, monstre1.getDexterite());
        assertEquals(5, monstre1.getVitesse());
        assertEquals(0, monstre1.getInitiative());
        assertEquals(1, monstre1.getPortee());
        assertEquals(12, monstre1.getClasseArmure());
        assertEquals(6, monstre1.getAmplitudeDegatsArme());
    }

    @Test
    void testEstJoueur() {
        assertFalse(monstre1.estJoueur());
    }

    @Test
    void testToStringPremierMonstre() {
        assertEquals("Dragon", monstre1.toString());
    }

    @Test
    void testToStringMonstreSuivant() {
        assertEquals("Dragon 2", monstre2.toString());
    }

    @Test
    void testSubirAttaque() {
        int pvInitiaux = monstre1.getPv();
        monstre1.subirAttaque(3);
        assertEquals(pvInitiaux - 3, monstre1.getPv());
    }

    @Test
    void testEstMort() {
        assertFalse(monstre1.estMort());
        monstre1.subirAttaque(monstre1.getPv());
        assertTrue(monstre1.estMort());
    }

    @Test
    void testPeutAttaquer() {
        //Le monstre est créé avec une arme par défaut
        assertTrue(monstre1.peutAttaquer());
    }

    @Test
    void testGetInfos() {
        String infos = monstre1.getInfos();
        assertTrue(infos.contains("Dragon"));
        assertTrue(infos.contains(monstre1.getPv()+"/"+monstre1.getPvMax())); // PV actuels/max
    }

    @Test
    void testSePresenter() {
        assertEquals("Dragon", monstre1.sePresenter());
    }
}