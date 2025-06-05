package personnages.equipements.armes;

import personnages.equipements.Equipement;
import personnages.equipements.effets.Effet;
import personnages.equipements.effets.EffetsStandards;
import affichage.Affichage;
import utils.De;
import utils.TypeEquipement;

import java.util.ArrayList;

public class Arme extends Equipement {
    private int m_amplitudeDegats;
    private final int m_portee;
    private final int m_nbLance;
    private int m_bonus;

    public Arme(String nom, int amplitudeDegats, int portee, boolean estLourde, int nbLance){
        ArrayList<Effet> effets = estLourde ? EffetsStandards.ARME_LOURDE : EffetsStandards.SANS_EFFET;
        super(nom, estLourde, TypeEquipement.ARME, effets);
        m_amplitudeDegats = amplitudeDegats;
        m_portee = portee;
        m_nbLance = nbLance;
        m_bonus = 0;
    }

    public Arme(String nom, int amplitudeDegats, int portee, boolean estLourde){
        this(nom, amplitudeDegats, portee, estLourde, 1);
    }

    public int attaque() {
        //Renvoyer les dégâts de l'arme.
        int degats = 0;
        for(int i=0; i<m_nbLance; i++){
            degats += De.lance(m_amplitudeDegats);
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
        m_amplitudeDegats += bonus;
    }

    public boolean estArmeDistance(){
        //Renvoie vrai si l'arme est une arme à distance, faux sinon.
        return m_portee > 1;
    }

    public static ArrayList<Arme> getArmes(){
        ArrayList<Arme> armes = new ArrayList<>();
        armes.add(new Arbalete());
        armes.add(new Arc());
        armes.add(new Baton());
        armes.add(new Epee2Mains());
        armes.add(new EpeeLongue());
        armes.add(new Fronde());
        armes.add(new Masse());
        armes.add(new Rapiere());
        return armes;
    }

    @Override
    public String toString() {
        return super.toString() + Affichage.toStringArme(m_nbLance, m_amplitudeDegats, m_portee, m_estLourd);
    }
}
