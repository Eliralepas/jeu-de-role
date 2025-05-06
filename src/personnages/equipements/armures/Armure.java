package personnages.equipements.armures;

import personnages.equipements.Equipement;

public class Armure extends Equipement {
    private final int m_classeArmure;
    private final boolean m_estLourde;

    public Armure(String nom, int classeArmure, boolean estLourde){
        super(nom);
        m_classeArmure = classeArmure;
        m_estLourde = estLourde;
    }

    public boolean estLourde(){
        return m_estLourde;
    }
}
