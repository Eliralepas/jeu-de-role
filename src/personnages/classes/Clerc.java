package personnages.classes;

import personnages.equipements.Equipement;
import personnages.equipements.armes.Arbalete;
import personnages.equipements.armes.Masse;
import personnages.equipements.armures.ArmureEcailles;
import personnages.equipements.sorts.Guerison;
import personnages.equipements.sorts.Sort;

import java.util.ArrayList;

public class Clerc extends Classe{
    private static final ArrayList<Equipement> m_equipements;
    static {
        m_equipements = new ArrayList<>();
        m_equipements.add(new Masse());
        m_equipements.add(new ArmureEcailles());
        m_equipements.add(new Arbalete());
    }
    private static final ArrayList<Sort> m_sorts;
    static {
        m_sorts = new ArrayList<>();
        m_sorts.add(new Guerison());
    }

    public Clerc() {
        super("Clerc", 16, m_equipements, m_sorts);
    }
}
