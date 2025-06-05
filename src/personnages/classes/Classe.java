package personnages.classes;

import personnages.equipements.Equipement;
import personnages.sorts.Sort;

import java.util.ArrayList;

public abstract class Classe {
    private final String m_nom;
    private final int m_pv;
    private final ArrayList<Equipement> m_equipements;
    private final ArrayList<Sort> m_sorts;

    public Classe(String nom, int pv, ArrayList<Equipement> equipements){
        m_nom = nom;
        m_pv = pv;
        m_equipements = new ArrayList<>(equipements);
        m_sorts = new ArrayList<>();
    }

    public Classe(String nom, int pv, ArrayList<Equipement> equipements, ArrayList<Sort> sorts){
        m_nom = nom;
        m_pv = pv;
        m_equipements = equipements;
        m_sorts = sorts;
    }

    public int getPv(){
        //Renvoie les points de vie.
        return m_pv;
    }

    public ArrayList<Equipement> getEquipements(){
        //Renvoie la liste des équipements.
        return new ArrayList<>(m_equipements);
    }

    public ArrayList<Sort> getSorts(){
        //Renvoie la liste des sorts.
        return new ArrayList<>(m_sorts);
    }

    public static ArrayList<Classe> getClasses(){
        ArrayList<Classe> classes = new ArrayList<>();
        classes.add(new Clerc());
        classes.add(new Guerrier());
        classes.add(new Magicien());
        classes.add(new Roublard());
        return classes;
    }

    @Override
    public String toString() {
        return m_nom;
    }
}
