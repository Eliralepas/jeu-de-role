package personnages.classes;

import personnages.equipements.Equipement;
import personnages.equipements.armes.Arbalete;
import personnages.equipements.armes.EpeeLongue;
import personnages.equipements.armures.CotteDeMailles;

import java.util.ArrayList;

public class Guerrier extends Classe{
    private static final ArrayList<Equipement> m_equipements;
    static {
        m_equipements = new ArrayList<>();
        m_equipements.add(new CotteDeMailles());
        m_equipements.add(new EpeeLongue());
        m_equipements.add(new Arbalete());
    }

    public Guerrier() {
        super("Guerrier", 20, m_equipements);
    }
}
