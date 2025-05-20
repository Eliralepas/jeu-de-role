package personnages.equipements.armes;

import personnages.equipements.Equipement;
import utils.De;

public class Arme extends Equipement {
    private final int m_amplitudeDegats;
    private final int m_portee;
    private final int m_nbLance;
    private int m_bonus;

    public Arme(String nom, int amplitudeDegats, int portee, boolean estLourde){
        super(nom, estLourde);
        m_amplitudeDegats = amplitudeDegats;
        m_portee = portee;
        m_nbLance = 1;
        m_bonus = 0;
    }

    public Arme(String nom, int amplitudeDegats, int portee, boolean estLourde, int nbLance){
        super(nom, estLourde);
        m_amplitudeDegats = amplitudeDegats;
        m_portee = portee;
        m_nbLance = nbLance;
        m_bonus = 0;
    }

    public int attaque() {
        //Renvoyer les dégâts de l'arme.
        int degats = 0;
        for(int i=0; i<m_nbLance; i++){
            degats += De.lance(m_amplitudeDegats + m_bonus);
        }
        return degats;
    }

    public int getPortee(){
        //Renovie la portee.
        return m_portee;
    }

    public int getAmplitudeDegats(){
        //Renvoie le nombre de faces du dé d'attaque.
        return m_amplitudeDegats;
    }

    public int getBonus(){
        //Renvoie le bonus.
        return m_bonus;
    }

    public void addBonus(int bonus){
        //Incrémente le bonus
        m_bonus += bonus;
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
