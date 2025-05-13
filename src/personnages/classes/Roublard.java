package personnages.classes;

import personnages.equipements.Equipement;
import personnages.equipements.armes.Arc;
import personnages.equipements.armes.Rapiere;

import java.util.ArrayList;

public class Roublard extends Classe{
    private static final ArrayList<Equipement> m_equipements;
    static {
        m_equipements = new ArrayList<Equipement>();
        m_equipements.add(new Rapiere());
        m_equipements.add(new Arc());
    }

    public Roublard() {
        super("Roublard", 16, m_equipements);
    }
}
