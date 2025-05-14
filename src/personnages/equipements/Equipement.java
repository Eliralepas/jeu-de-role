package personnages.equipements;

import donjon.pion.Pion;

public abstract class Equipement {
    private final String m_nom;
    private final Pion m_pion;

    public Equipement(String nom){
        m_nom = nom;
        m_pion = new Pion(0, 0, " * ");
    }


    public Pion getPion(){
        //Renvoyer le pion associé
        return m_pion;
    }

    @Override
    public String toString() {
        return m_nom;
    }
}
