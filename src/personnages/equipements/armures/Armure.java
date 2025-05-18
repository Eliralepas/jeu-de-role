package personnages.equipements.armures;

import personnages.equipements.Equipement;

public class Armure extends Equipement {
    private final int m_classeArmure;

    public Armure(String nom, int classeArmure, boolean estLourde){
        super(nom, estLourde);
        m_classeArmure = classeArmure;
    }

    public int getClasseArmure(){
        //Renvoie la classe d'armure.
        return m_classeArmure;
    }
}
