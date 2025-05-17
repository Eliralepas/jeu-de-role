package personnages;

import donjon.pion.Pion;
import personnages.equipements.armes.Arme;
import personnages.equipements.armures.Armure;
import utils.De;

import static utils.Demande.demandeEntier;

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
    protected final String m_symbol;
    protected final Pion m_pion;

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
        m_symbol = symbol;
        m_pion = new Pion(0, 0, symbol);
    }

    public void seDeplacer(int x, int y){
        //Se déplacer vers les coordonnées x, y
        m_pion.setPosition(x, y);
    }

    public void attaquer(Personnage perso){
        //Attaquer un personnage.
        System.out.println("Lancez un dé de 20 (appuyez sur 'ENTREE')\n");
        System.console().readLine();
        int resultatLance = De.lance(20);
        System.out.println("Vous avez fait " + resultatLance);
        int degats = getAttribut();
        int total = resultatLance + degats;
        System.out.println("Votre attaque est de " + resultatLance + "+" + degats + "(" + getNomAttribut() + ")" + " = " + total + ".");
        infligerDegats(perso, total);
    }

    private int getDegats(){
        //Renvoyer les dégâts de l'arme.
        return m_arme.attaque();
    }

    private void infligerDegats(Personnage perso, int degats){
        //Infliger les dégats à la cible si les dégâts sont supérieurs à la classe d'armure de la cible
        int classeArmureCible = perso.getClasseArmure();
        if (degats > classeArmureCible){
            System.out.println("Votre attaque perce l'armure de " + perso.m_nom + " (" + classeArmureCible + ").");
            System.out.println("Lancez un dé de " + getAmplitudeDegatsArme() + " pour infliger des dégâts (appuyez sur 'ENTREE')");
            System.console().readLine();
            int resultatLance = getDegats();
            perso.subirAttaque(resultatLance);
            System.out.println(perso.m_nom + " subit " + resultatLance + " dégâts !");
            System.out.println(perso.m_nom + " n'a plus que " + perso.getPv() + " points de vie restants !");
        }
        else {
            System.out.println("Votre attaque ne parvient pas à percer l'armure de " + perso.m_nom + "(" + classeArmureCible + ").");
        }
    }

    public int getAttribut(){
        //Renvoyer la dextérité si on utilise une arme à distance et la force sinon.
        return m_arme.estArmeDistance()? m_dexterite: m_force;
    }

    public String getNomAttribut(){
        //Renvoyer 'Dextérité' si on utilise une arme à distance et 'Force' sinon.
        return m_arme.estArmeDistance()? "Dextérité": "Force";
    }

    public void subirAttaque(int degats){
        //Déduire les dégâts reçus de la vie.
        m_pv -= degats;
    }

    public int getClasseArmure(){
        //Renvoyer la classe d'armure de l'armure équipée.
        return m_armure.getClasseArmure();
    }

    public int getAmplitudeDegatsArme(){
        //Renvoyer le nombre de faces du dé de l'arme.
        return m_arme.getAmplitudeDegats();
    }

    public Pion getPion(){
        //Renvoyer le pion associé au personnage.
        return m_pion;
    }

    public int getInitiative(){
        //Renvoyer l'initiative.
        return m_initiative;
    }

    public void setInitiative(int initiative){
        m_initiative = initiative;
    }

    public int getPortee(){
        //Renvoyer la portee.
        return m_arme.getPortee();
    }

    public int getVitesse(){
        //Renvoyer la vitesse.
        return m_vitesse;
    }

    public int getPv(){
        //Renvoyer les points de vie.
        return m_pv;
    }

    public boolean estJoueur(){
        return false;
    }

    public boolean estMort(){
        return m_pv <= 0;
    }

    public boolean peutAttaquer(){
        return !m_arme.pasDefinie();
    }

    public String getSymbol(){
        //Renvoyer le symbol.
        return m_symbol;
    }

    public String sePresenter(){
        //Renvoyer la formule de présentation pour l'affichage, a pour but d'être surchargé par la classe fille Joueur.
        return m_nom;
    }

    public String getInfos(){
        //Renvoyer les informations utiles sur le personnage pour l'affichage, a pour but d'être surchargé par la classe fille Joueur.
        return m_nom + " (" + m_pv + "/" + m_pvMax + ")";
    }

    public int getAction(){
        //Renvoyer l'entier correspondant à l'action choisie, a pour but d'être surchargé par la classe fille Joueur.
        String msgAction =
                m_nom + " il vous reste " + m_initiative + " actions, que souhaitez-vous faire ?\n" +
                """
                Attaquer:    1
                Se déplacer: 2
                
                """;
         return demandeEntier(1, 2, msgAction);
    }

    @Override
    public String toString() {
        return m_nom;
    }
}
