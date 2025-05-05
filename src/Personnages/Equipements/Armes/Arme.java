package Personnages.Equipements.Armes;

import java.util.Random;

public class Arme {
    private String m_nom;
    private int m_amplitudeDegats;
    private int m_portee;

    public Arme(String nom, int amplitudeDegats, int portee){
        m_nom = nom;
        m_amplitudeDegats = amplitudeDegats;
        m_portee = portee;
    }

    public int attaque(){
        return new Random().nextInt(m_amplitudeDegats);
    }
}
