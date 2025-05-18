package personnages;

import personnages.equipements.armes.Arme;
import personnages.equipements.armures.Armure;
import utils.De;

public abstract class Personnage {
    protected final String m_nom;
    protected int m_pv;
    protected final int m_pvMax;
    protected int m_force;
    protected int m_dexterite;
    protected int m_vitesse;
    protected int m_initiative;
    protected Arme m_arme;
    protected Armure m_armure;

    public Personnage(String nom, String symbol, int pv, int force, int dexterite, int vitesse, int initiative, Arme arme, Armure armure){
        m_nom = nom;
        m_pv = pv;
        m_pvMax = pv;
        m_force = force;
        m_dexterite = dexterite;
        m_vitesse = vitesse;
        m_initiative = initiative;
        m_arme = arme;
        m_armure = armure;
    }

    public int getAttribut(){
        //Renvoyer la dextérité si on utilise une arme à distance et la force sinon.
        return m_arme.estArmeDistance()? m_dexterite: m_force;
    }

    public void subirAttaque(int degats){
        //Déduire les dégâts reçus de la vie.
        m_pv -= degats;
    }

    public int getClasseArmure(){
        //Renvoyer la classe d'armure de l'armure équipée.
        return m_armure.getClasseArmure();
    }
}
