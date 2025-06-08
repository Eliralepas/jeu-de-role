package personnages.races;

import java.util.ArrayList;

public abstract class Race {
    private final String m_nom;
    private final int m_pv;
    private final int m_force;
    private final int m_dexterite;
    private final int m_vitesse;

    public Race(String nom, int pv, int force, int dexterite, int vitesse){
        m_nom = nom;
        m_pv = pv;
        m_force = force;
        m_dexterite = dexterite;
        m_vitesse = vitesse;
    }

    public int getPv(){
        return m_pv;
    }

    public int getForce(){
        return m_force;
    }

    public int getDexterite(){
        return m_dexterite;
    }

    public int getVitesse(){
        return m_vitesse;
    }

    public static ArrayList<Race> getRaces(){
        ArrayList<Race> races = new ArrayList<>();
        races.add(new Elf());
        races.add(new Halfelin());
        races.add(new Humain());
        races.add(new Nain());
        return races;
    }

    @Override
    public String toString() {
        return m_nom;
    }
}
