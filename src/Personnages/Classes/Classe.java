package Personnages.Classes;

import Personnages.Equipements.Equipement;

import java.util.ArrayList;

public abstract class Classe {
    private String m_nom;
    private int m_pv;
    private ArrayList<Equipement> m_equipements;

    public Classe(String nom, int pv, ArrayList<Equipement> equipements){
        m_nom = nom;
        m_pv = pv;
        m_equipements = new ArrayList<Equipement>();
        m_equipements.addAll(equipements);
    }

    public void setAttributs(int pv, ArrayList<Equipement> equipements){
        pv = m_pv;
        equipements.addAll(m_equipements);
    }
}
