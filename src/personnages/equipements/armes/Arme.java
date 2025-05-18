package personnages.equipements.armes;

import personnages.equipements.Equipement;


public class Arme extends Equipement {
    private final int m_amplitudeDegats;
    private final int m_portee;

    public Arme(String nom, int amplitudeDegats, int portee, boolean estLourde){
        super(nom, estLourde);
        m_amplitudeDegats = amplitudeDegats;
        m_portee = portee;
    }

    public boolean estArmeDistance(){
        //Renvoie vrai si l'arme est une arme à distance, faux sinon.
        return m_portee > 1;
    }
}
