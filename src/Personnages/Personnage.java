package Personnages;

import Personnages.Classes.Classe;
import Personnages.Equipements.Armes.Arme;
import Personnages.Equipements.Equipement;
import Personnages.Equipements.Armures.Armure;
import Personnages.Races.Race;

import java.util.ArrayList;
import java.util.Random;

public class Personnage {
    private String m_nom;
    private Race m_race;
    private Classe m_classe;
    private int m_pv;
    private int m_force;
    private int m_dexterite;
    private int m_vitesse;
    private int m_initiative;
    private ArrayList<Equipement> m_inventaire;
    private Arme m_arme;
    private Armure m_armure;

    public Personnage(String nom, Race race, Classe classe, int pv, int force, int dexterite, int vitesse, int initiative){
        m_nom = nom;
        m_race = race;
        m_classe = classe;
        race.setAttributs(m_pv, m_force, m_dexterite, m_vitesse, m_initiative);
        classe.setAttributs(m_pv, m_inventaire);
        for(int i=0; i<4; i++){
            Random rnd = new Random();
            m_force += rnd.nextInt(4);
            m_dexterite += rnd.nextInt(4);
            m_vitesse += rnd.nextInt(4);
            m_initiative += rnd.nextInt(4);
        }
        m_force += 3;
        m_dexterite += 3;
        m_vitesse += 3;
        m_initiative += 3;
    }
}
