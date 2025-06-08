package personnages.equipements;

import donjon.pion.Pion;
import personnages.Personnage;
import personnages.equipements.effets.Effet;

import java.util.ArrayList;

public abstract class Equipement {
    private final String m_nom;
    private final Pion m_pion;
    protected final boolean m_estLourd;
    private final TypeEquipement m_type;
    private final ArrayList<Effet> m_effets;

    public Equipement(String nom, boolean estLourd, TypeEquipement type, ArrayList<Effet> effets) {
        m_nom = nom;
        m_pion = new Pion(0, 0, " * ");
        m_estLourd = estLourd;
        m_type = type;
        m_effets = new ArrayList<>(effets);
    }

    public Pion getPion(){
        //Renvoyer le pion associé
        return m_pion;
    }

    public void setPion(int x, int y){
        m_pion.setPosition(x, y);
    }

    public boolean pasDefinie(){
        return m_nom.isEmpty();
    }

    public TypeEquipement getType(){
        return m_type;
    }

    public void appliquerEffets(Personnage perso){
        for (Effet e : m_effets) {
            e.appliquer(perso);
        }
    }

    public void retirerEffets(Personnage perso){
        for (Effet e : m_effets) {
            e.retirer(perso);
        }
    }

    @Override
    public String toString() {
        return m_nom;
    }
}
