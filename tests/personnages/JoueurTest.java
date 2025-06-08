package personnages;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static affichage.Affichage.contenuInventaire;
import static org.junit.jupiter.api.Assertions.*;
import static personnages.TypePersonnage.JOUEUR;

import personnages.classes.Guerrier;
import personnages.equipements.Equipement;
import personnages.equipements.armes.EpeeLongue;
import personnages.races.Humain;

import java.util.ArrayList;

class JoueurTest {
    private Joueur joueur;

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
        assertEquals(JOUEUR , joueur.getType());
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
        joueur.subirAttaque(2, "MOI"); // Inflige des dégâts
        int pvApresDegats = joueur.getPv();
        joueur.guerir(2);
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
    void testSePresenter(){
        assertEquals("TestHero (Humain guerrier)", joueur.sePresenter());
    }

    @Test
    void testToString() {
        String toString = joueur.toString();
        assertTrue(toString.contains("TestHero"));
        assertTrue(toString.contains("Humain"));
        assertTrue(toString.contains("Guerrier"));
        assertTrue(toString.contains("Vie: "+joueur.getPv()+"/"+joueur.getPvMax()));
        assertTrue(toString.contains("Inventaire: [" + joueur.getTailleInventaire() + "]\n" + contenuInventaire(joueur.getInventaire())));
        assertTrue(toString.contains("Force: "+joueur.getForce()));
        assertTrue(toString.contains("Dextérité: "+joueur.getDexterite()));
        assertTrue(toString.contains("Vitesse: "+joueur.getVitesse()));
    }

    @Test
    void testRecuperer(){
        Equipement arme = new EpeeLongue();
        int tailleInitial = joueur.getTailleInventaire();
        joueur.recuperer(arme);

        assertEquals(tailleInitial+1, joueur.getTailleInventaire());

        ArrayList<Equipement> inventaire = joueur.getInventaire();
        Equipement equip = inventaire.get(tailleInitial);

        assertEquals(equip, arme);
    }

    @Test
    void estMort(){
        joueur.subirAttaque(1000, "MOI");
        assertTrue(joueur.estMort());
    }

}