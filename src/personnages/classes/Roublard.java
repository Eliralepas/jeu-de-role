package personnages.classes;

import personnages.equipements.Equipement;
import personnages.equipements.armes.Arc;
import personnages.equipements.armes.Rapiere;
import affichage.Affichage;

import java.util.ArrayList;

public class Roublard extends Classe{
    private static final ArrayList<Equipement> m_equipements;
    static {
        m_equipements = new ArrayList<>();
        m_equipements.add(new Rapiere());
        m_equipements.add(new Arc());
    }

    public Roublard() {
        super(Affichage.nomRoublard(), 16, m_equipements);
    }
}
