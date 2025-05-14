package personnages.equipements.armes;

import personnages.equipements.Equipement;

import java.util.Random;

public class Arme extends Equipement {
    private final int m_amplitudeDegats;
    private final int m_portee;
    private final boolean m_estLourde;

    public Arme(String nom, int amplitudeDegats, int portee, boolean estLourde){
        super(nom);
        m_amplitudeDegats = amplitudeDegats;
        m_portee = portee;
        m_estLourde = estLourde;
    }

    public int attaque() {
        //Renvoyer les dégâts de l'arme
        return new Random().nextInt(m_amplitudeDegats) + 1;
    }

    public int getPortee(){
        //Renovie la portee
        return m_portee;
    }

    public int getAmplitudeDegats(){
        //Renvoie le nombre de faces du dé d'attaque.
        return m_amplitudeDegats;
    }

    public boolean estArmeDistance(){
        //Renvoie vrai si l'arme est une arme à distance, faux sinon.
        return m_portee > 1;
    }

    public boolean estLourde(){
        //Renvoie vrai si l'arme est lourde, faux sinon.
        return m_estLourde;
    }

    @Override
    public String toString() {
        return super.toString() + " (dégâts: 1d" + m_amplitudeDegats + ", portee: " + m_portee + ")";
    }
}
