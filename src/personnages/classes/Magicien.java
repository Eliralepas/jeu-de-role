package personnages.classes;

import personnages.equipements.Equipement;
import personnages.equipements.armes.Arbalete;
import personnages.equipements.armes.Baton;
import personnages.equipements.armes.Fronde;
import personnages.equipements.armes.Masse;
import personnages.equipements.armures.ArmureEcailles;

import java.util.ArrayList;

public class Magicien extends Classe{
    private static final ArrayList<Equipement> m_equipements;
    static {
        m_equipements = new ArrayList<Equipement>();
        m_equipements.add(new Baton());
        m_equipements.add(new Fronde());
    }

    public Magicien() {
        super("Magicien", 16, m_equipements);
    }
}
