package Personnages.Races;

public abstract class Race {
    private String m_nom;
    private int m_pv;
    private int m_force;
    private int m_dexterite;
    private int m_vitesse;
    private int m_initiative;

    public Race(String nom, int pv, int force, int dexterite, int vitesse, int initiative){
        m_nom = nom;
        m_pv = pv;
        m_force = force;
        m_dexterite = dexterite;
        m_vitesse = vitesse;
        m_initiative = initiative;
    }

    public void setAttributs(int pv, int force, int dexterite, int vitesse, int initiative){
        pv = m_pv;
        force = m_force;
        dexterite = m_dexterite;
        vitesse = m_vitesse;
        initiative = m_initiative;
    }
}
