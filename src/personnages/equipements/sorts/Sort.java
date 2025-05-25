package personnages.equipements.sorts;

import personnages.Personnage;
import java.util.ArrayList;


public abstract class Sort {
    protected String m_nom;

    public Sort(String nom){
        m_nom = nom;
    }

    public abstract boolean lancer(ArrayList<Personnage> personnages);

    @Override
    public String toString() {
        return m_nom;
    }
}
