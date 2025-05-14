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

    public int getClasseArmure(){
        //Renvoie la classe d'armure.
        return m_classeArmure;
    }

    public boolean estLourde(){
        //Renvoie vrai si l'armure est une armure lourde, faux sinon.
        return m_estLourde;
    }

    @Override
    public String toString() {
        String etat = "non";
        if (m_estLourde){
            etat = "oui";
        }
        return super.toString() + " (classe d'armure: " + m_classeArmure + ", lourde: " + etat + ")";
    }
}
