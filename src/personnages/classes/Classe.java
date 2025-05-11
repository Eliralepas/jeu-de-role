package personnages.classes;

import personnages.equipements.Equipement;

import java.util.ArrayList;

public abstract class Classe {
    private final String m_nom;
    private final int m_pv;
    private final ArrayList<Equipement> m_equipements;

    public Classe(String nom, int pv, ArrayList<Equipement> equipements){
        m_nom = nom;
        m_pv = pv;
        m_equipements = new ArrayList<Equipement>();
        m_equipements.addAll(equipements);
    }

    public int getPv(){
        //Renvoie les points de vie.
        return m_pv;
    }

    public ArrayList<Equipement> getEquipements(){
        //Renvoie la liste des équipements.
        return m_equipements;
    }

    @Override
    public String toString() {
        return m_nom;
    }
}
