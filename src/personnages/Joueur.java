package personnages;

import personnages.classes.Classe;
import personnages.equipements.armes.Arme;
import personnages.equipements.Equipement;
import personnages.equipements.armures.Armure;
import personnages.races.Race;

import java.util.ArrayList;
import java.util.Random;

import static affichage.Demande.demandeEntier;

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
        String symbol = nom;
        if (nom.length() > 3){
            symbol = nom.substring(0, 3);
        }
        super(nom, symbol, pv, force, dexterite, vitesse, initiative, new Arme("", 0, 0, false), new Armure("", 0, false));
    }

    public String getRace(){
        //Renvoyer le nom de la race.
        return m_race.toString();
    }

    public String getClasse(){
        //Renvoyer le nom de la classe.
        return m_classe.toString();
    }

    public String contenuInventaire(){
        String chaine = "";
        for (Equipement equipement : m_inventaire) {
            chaine += "\t › " + equipement.toString() + "\n";
        }
        return chaine;
    }

    @Override
    public String sePresenter() {
        return m_nom + " (" + m_race + " " + m_classe.toString().toLowerCase() + ")";
    }

    @Override
    public String getInfos() {
        return  m_nom + " (" + m_race + m_classe + ", " + m_pv + "/" + m_pvMax + ")";
    }

    @Override
    public int getAction() {
        String msgAction =
                m_nom + " il vous reste " + m_initiative + " actions, que souhaitez-vous faire ?\n" +
                """
                Attaquer:    1
                Se déplacer: 2
                S'équiper:   3
                
                """;
        return demandeEntier(1, 3, msgAction);
    }

    @Override
    public String toString() {
        String chaine = m_nom + " (" + m_race.toString() + ", " + m_classe.toString() + ")\n"
                + "\tVie: " + m_pv + "/" + m_pvMax + "\n"
                + "\tArmure: ";
        if (m_armure != null){
            chaine += m_armure + "\n";
        }
        else {
            chaine += "aucune\n";
        }
        chaine += "\tArme: ";
        if (m_arme != null){
            chaine += m_arme + "\n";
        }
        else {
            chaine += "aucune\n";
        }
        chaine += "\tInventaire: [" + m_inventaire.size() + "]\n" + contenuInventaire();
        chaine += "\tForce: " + m_force + "\n";
        chaine += "\tDextérité: " + m_dexterite + "\n";
        chaine += "\tVitesse: " + m_vitesse + "\n";
        return chaine;
    }

    @Override
    public boolean estJoueur(){
        return true;
    }
}
