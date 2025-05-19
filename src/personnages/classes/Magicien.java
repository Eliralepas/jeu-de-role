package personnages.classes;

import personnages.equipements.Equipement;
import personnages.equipements.armes.Baton;
import personnages.equipements.armes.Fronde;
import personnages.equipements.sorts.ArmeMagique;
import personnages.equipements.sorts.BoogieWoogie;
import personnages.equipements.sorts.Guerison;
import personnages.equipements.sorts.Sort;

import java.util.ArrayList;

public class Magicien extends Classe{
    private static final ArrayList<Equipement> m_equipements;
    static {
        m_equipements = new ArrayList<>();
        m_equipements.add(new Baton());
        m_equipements.add(new Fronde());
    }
    private static final ArrayList<Sort> m_sorts;
    static {
        m_sorts = new ArrayList<>();
        m_sorts.add(new Guerison());
        m_sorts.add(new BoogieWoogie());
        m_sorts.add(new ArmeMagique());
    }

    public Magicien() {
        super("Magicien", 16, m_equipements, m_sorts);
    }
}
