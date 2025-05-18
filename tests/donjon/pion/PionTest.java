package donjon.pion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PionTest {
    private Pion p1;
    private Pion p2;
    private Pion p3;

    @BeforeEach
    void setUp(){
        p1 = new Pion(1, 3, "ABC");
        p2 = new Pion(1,3, "DEF");
        p3 = new Pion(10,10,"AAA");
    }

    @Test
    void testConstructeur(){
        assertEquals(1, p1.getX());
        assertEquals(3, p1.getY());
        assertEquals("ABC", p1.toString());
    }

    @Test
    void testEquals(){
        assertTrue(p1.equals(p2));
    }

    @Test
    void testGetDistance(){
        int distance = p2.getDistance(5, 2); //Math.max(Math.abs(1 - 5),Math.abs(3 - 2)
        assertEquals(4, distance);
    }

    @Test
    void testSetPosition(){
        assertEquals(10, p3.getX());
        assertEquals(10, p3.getY());

        p3.setPosition(5,9);

        assertEquals(5, p3.getX());
        assertEquals(9, p3.getY());
    }
}
