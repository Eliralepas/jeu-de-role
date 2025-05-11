package personnages.races;

public abstract class Race {
    private final String m_nom;
    private final int m_pv;
    private final int m_force;
    private final int m_dexterite;
    private final int m_vitesse;
    private final int m_initiative;

    public Race(String nom, int pv, int force, int dexterite, int vitesse, int initiative){
        m_nom = nom;
        m_pv = pv;
        m_force = force;
        m_dexterite = dexterite;
        m_vitesse = vitesse;
        m_initiative = initiative;
    }

    public void setAttributs(int pv, int force, int dexterite, int vitesse, int initiative){
        //Met à jour les attributs du personnage qui est de cette race.
        pv = m_pv;
        force = m_force;
        dexterite = m_dexterite;
        vitesse = m_vitesse;
        initiative = m_initiative;
    }

    @Override
    public String toString() {
        return m_nom;
    }
}
