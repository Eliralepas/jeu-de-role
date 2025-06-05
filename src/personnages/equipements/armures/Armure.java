package personnages.equipements.armures;

import personnages.equipements.Equipement;
import personnages.equipements.effets.Effet;
import personnages.equipements.effets.EffetsStandards;
import affichage.Affichage;
import utils.TypeEquipement;

import java.util.ArrayList;

public class Armure extends Equipement {
    private final int m_classeArmure;

    public Armure(String nom, int classeArmure, boolean estLourde){
        ArrayList<Effet> effets = estLourde ? EffetsStandards.ARMURE_LOURDE : EffetsStandards.SANS_EFFET;
        super(nom, estLourde, TypeEquipement.ARMURE, effets);
        m_classeArmure = classeArmure;
    }

    public int getClasseArmure(){
        //Renvoie la classe d'armure.
        return m_classeArmure;
    }

    public static ArrayList<Armure> getArmures(){
        ArrayList<Armure> armures = new ArrayList<>();
        armures.add(new ArmureEcailles());
        armures.add(new CotteDeMailles());
        armures.add(new DemiPlate());
        armures.add(new Harnois());
        return armures;
    }

    @Override
    public String toString() {
        return super.toString() + Affichage.toStringArmure(m_classeArmure, m_estLourd);
    }
}
