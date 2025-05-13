package personnages;

import personnages.classes.Classe;
import personnages.equipements.armes.Arme;
import personnages.equipements.Equipement;
import personnages.equipements.armures.Armure;
import personnages.races.Race;

import java.util.ArrayList;
import java.util.Random;

public class Joueur extends Personnage{
    private final Race m_race;
    private final Classe m_classe;
    private final ArrayList<Equipement> m_inventaire;

    public Joueur(String nom, Race race, Classe classe){
        m_race = race;
        m_classe = classe;
        m_inventaire = classe.getEquipements();
        int pv = classe.getPv();
        int force = 0;
        int dexterite = 0;
        int vitesse = 0;
        int initiative = 0;
        race.setAttributs(pv, force, dexterite, vitesse, initiative);
        for(int i=0; i<4; i++){
            Random rnd = new Random();
            force += rnd.nextInt(4) + 1;
            dexterite += rnd.nextInt(4) + 1;
            vitesse += rnd.nextInt(4) + 1;
            initiative += rnd.nextInt(4) + 1;
        }
        force += 3;
        dexterite += 3;
        vitesse += 3;
        initiative += 3;
        super(nom, nom.substring(0, 3), pv, force, dexterite, vitesse, initiative, new Arme("", 0, 0, false), new Armure("", 0, false));
    }

    public String getRace(){
        //Renvoyer le nom de la race.
        return m_race.toString();
    }

    public String getClasse(){
        //Renvoyer le nom de la classe.
        return m_classe.toString();
    }

    @Override
    public String toString() {
        String chaine = m_nom + "\n"
                + "Vie: " + m_pv + "/" + m_pvMax + "\n"
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
