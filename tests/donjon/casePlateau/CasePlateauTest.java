package donjon.casePlateau;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static utils.TypeCase.*;

class CasePlateauTest {

    private final String[][] plateau = new String[15][15];

    @BeforeEach
    void creerPlateau(){
        for (int i=0; i<15; i++){
            for (int j=0; j<15; j++) {
                plateau[i][j] = Symbols.CASE_VIDE;
            }
        }
        plateau[0][0] = Symbols.CASE_OBSTACLE;
        plateau[9][1] = Symbols.CASE_EQUIPEMENT;
    }

    @Test
    void testCaseDelpacement() {
        CasePlateau caseA1 = new CasePlateau("A1");
        assertFalse(caseA1.estValide(plateau, DEPLACEMENT));
        assertEquals(0, caseA1.getColonne()); // A=>0
        assertEquals(0, caseA1.getLigne());   // 1=>0

        CasePlateau caseB10 = new CasePlateau("B10");
        assertTrue(caseB10.estValide(plateau, DEPLACEMENT));
        assertEquals(1, caseB10.getColonne()); // B=>1
        assertEquals(9, caseB10.getLigne());  // 10=>9

        CasePlateau caseB1 = new CasePlateau("B1");
        assertTrue(caseB1.estValide(plateau, DEPLACEMENT));
        assertEquals(1, caseB1.getColonne()); // B=>1
        assertEquals(0, caseB1.getLigne());  // 1=>0
    }

    @Test
    void testCaseAjout() {
        CasePlateau caseA1 = new CasePlateau("A1");
        assertFalse(caseA1.estValide(plateau, AJOUT));

        CasePlateau caseB10 = new CasePlateau("B10");
        assertFalse(caseB10.estValide(plateau, AJOUT));

        CasePlateau caseB1 = new CasePlateau("B1");
        assertTrue(caseB1.estValide(plateau, AJOUT));
    }

    @Test
    void testCaseFormat() {
        CasePlateau caseMalFormee = new CasePlateau("1A");
        assertFalse(caseMalFormee.estValide(plateau, DEPLACEMENT));
    }
}