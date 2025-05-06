package personnages;

import personnages.classes.Classe;
import personnages.equipements.armes.Arme;
import personnages.equipements.Equipement;
import personnages.equipements.armures.Armure;
import personnages.races.Race;

import java.util.ArrayList;
import java.util.Random;

public class Personnage {
    private final String m_nom;
    private final Race m_race;
    private final Classe m_classe;
    private int m_pv;
    private final int m_maxPv;
    private int m_force;
    private int m_dexterite;
    private int m_vitesse;
    private int m_initiative;
    private final ArrayList<Equipement> m_inventaire = new ArrayList<Equipement>();
    private Arme m_arme;
    private Armure m_armure;

    public Personnage(String nom, Race race, Classe classe){
        m_nom = nom;
        m_race = race;
        m_classe = classe;
        race.setAttributs(m_pv, m_force, m_dexterite, m_vitesse, m_initiative);
        classe.setAttributs(m_pv, m_inventaire);
        for(int i=0; i<4; i++){
            Random rnd = new Random();
            m_force += rnd.nextInt(4) + 1;
            m_dexterite += rnd.nextInt(4) + 1;
            m_vitesse += rnd.nextInt(4) + 1;
            m_initiative += rnd.nextInt(4) + 1;
        }
        m_force += 3;
        m_dexterite += 3;
        m_vitesse += 3;
        m_initiative += 3;
        m_maxPv = m_pv;
    }

    @Override
    public String toString() {
        String chaine = m_nom + "\n"
                + "Vie: " + m_pv + "/" + m_maxPv + "\n"
                + "Armure: ";
        if (m_armure != null){
            chaine += m_armure.toString() + "\n";
        }
        else {
            chaine += "aucune\n";
        }
        chaine += "Arme: ";
        if (m_arme != null){
            chaine += m_arme.toString() + "\n";
        }
        else {
            chaine += "aucune\n";
        }
        chaine += "Inventaire: [" + m_inventaire.size() + "]\n";
        chaine += "Force: " + m_force + "\n";
        chaine += "Dextérité: " + m_dexterite + "\n";
        chaine += "Vitesse: " + m_vitesse + "\n";
        return chaine;
    }
}
