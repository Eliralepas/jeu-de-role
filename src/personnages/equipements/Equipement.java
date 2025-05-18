package personnages.equipements;

import donjon.pion.Pion;

public abstract class Equipement {
    private final String m_nom;
    private final Pion m_pion;
    protected final boolean m_estLourd;

    public Equipement(String nom, boolean estLourd) {
        m_nom = nom;
        m_pion = new Pion(0, 0, " * ");
        m_estLourd = estLourd;
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

    public boolean estArmure(){
        return true;
    }

    public boolean estLourd(){
        //Renvoie vrai si l'équipement est lourd, faux sinon.
        return m_estLourd;
    }

    @Override
    public String toString() {
        return m_nom;
    }
}
