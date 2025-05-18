package donjon.casePlateau;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CasePlateauTest {

    @Test
    void testCaseValide() {
        CasePlateau caseA1 = new CasePlateau("A1");
        assertTrue(caseA1.estValide());
        assertEquals(0, caseA1.getColonne()); // A=0
        assertEquals(0, caseA1.getLigne());   // 1=0

        CasePlateau caseB10 = new CasePlateau("B10");
        assertTrue(caseB10.estValide());
        assertEquals(1, caseB10.getColonne()); // B=1
        assertEquals(9, caseB10.getLigne());  // 10=9
    }

    @Test
    void testCaseFormat() {
        CasePlateau caseMalFormee = new CasePlateau("1A");
        assertFalse(caseMalFormee.estValide());

        CasePlateau caseBonFormat = new CasePlateau("D13");
        assertTrue(caseBonFormat.estValide());
    }
}