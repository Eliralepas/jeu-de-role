package personnages.equipements.armes;

import personnages.equipements.Equipement;
import utils.De;

public class Arme extends Equipement {
    private final int m_amplitudeDegats;
    private final int m_portee;

    public Arme(String nom, int amplitudeDegats, int portee, boolean estLourde){
        super(nom, estLourde);
        m_amplitudeDegats = amplitudeDegats;
        m_portee = portee;
    }

    public int attaque() {
        //Renvoyer les dégâts de l'arme
        return De.lance(m_amplitudeDegats);
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

    @Override
    public boolean estArmure() {
        return false;
    }

    @Override
    public String toString() {
        String etat = "non";
        if (m_estLourd){
            etat = "oui";
        }
        return super.toString() + " (dégâts: 1d" + m_amplitudeDegats + ", portee: " + m_portee + ", lourde: " + etat + ")";
    }
}
