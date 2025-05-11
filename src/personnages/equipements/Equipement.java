package personnages.equipements;

public abstract class Equipement {
    private final String m_nom;

    public Equipement(String nom){
        m_nom = nom;
    }

    @Override
    public String toString() {
        return m_nom;
    }
}
