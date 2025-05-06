package personnages.equipements.armes;

import personnages.equipements.Equipement;

import java.util.Random;

public class Arme extends Equipement {
    private final int m_amplitudeDegats;
    private final int m_portee;

    public Arme(String nom, int amplitudeDegats, int portee){
        super(nom);
        m_amplitudeDegats = amplitudeDegats;
        m_portee = portee;
    }

    public int attaque(int degatsBase)
    {
        return new Random().nextInt(m_amplitudeDegats) + 1 + degatsBase;
    }

    public int getPortee(){
        return m_portee;
    }

    public boolean estArmeDistance(){
        return m_portee > 1;
    }
}
