package personnages;

import donjon.pion.Pion;
import personnages.equipements.Equipement;
import personnages.equipements.armes.Arme;
import personnages.equipements.armures.Armure;
import personnages.equipements.slots.SlotArme;
import personnages.equipements.slots.SlotArmure;
import personnages.equipements.slots.SlotEquipable;
import personnages.sorts.Sort;
import affichage.Affichage;
import utils.De;
import utils.TypeEquipement;
import utils.TypePersonnage;

import java.util.ArrayList;

import static utils.Demande.demandeEntier;

public abstract class Personnage {
    private final String m_nom;
    private int m_pv;
    private final int m_pvMax;
    private int m_force;
    private int m_dexterite;
    private int m_vitesse;
    private int m_initiative;
    private Arme m_arme;
    private Armure m_armure;
    private final String m_symbol;
    private final Pion m_pion;
    private final ArrayList<Equipement> m_inventaire;
    private final ArrayList<Sort> m_sorts;
    private final TypePersonnage m_type;

    public Personnage(String nom, String symbol, TypePersonnage type, int pv, int force, int dexterite, int vitesse, int initiative, Arme arme, Armure armure, ArrayList<Equipement> inventaire, ArrayList<Sort> sorts) {
        m_nom = nom;
        m_symbol = symbol;
        m_type = type;
        m_pv = pv;
        m_pvMax = pv;
        m_force = force;
        m_dexterite = dexterite;
        m_vitesse = vitesse;
        m_initiative = initiative;
        m_arme = arme;
        m_armure = armure;
        m_inventaire = new ArrayList<>(inventaire);
        m_sorts = new ArrayList<>(sorts);
        m_pion = new Pion(0, 0, symbol);
    }

    public void seDeplacer(int x, int y){
        //Se déplacer vers les coordonnées x, y
        m_pion.setPosition(x, y);
    }

    public void seDeplacer(Pion p){
        //Se déplacer sur le pion p
        m_pion.setPosition(p);
    }

    public void attaquer(Personnage perso){
        //Attaquer un personnage.
        int attaque = 20 + m_arme.getBonus();
        Affichage.lancerDe(attaque);
        System.console().readLine();
        int resultatLance = De.lance(attaque);
        int degats = getAttribut();
        int total = resultatLance + degats;
        Affichage.attaquer(m_arme.estArmeDistance(), resultatLance, degats, total);
        infligerDegats(perso, total);
    }

    private int getDegats(){
        //Renvoyer les dégâts de l'arme.
        return m_arme.attaque();
    }

    private void infligerDegats(Personnage persoCible, int degats){
        //Infliger les dégats à la cible si les dégâts sont supérieurs à la classe d'armure de la cible
        int classeArmureCible = persoCible.getClasseArmure();
        String nomCible = persoCible.getNom();
        if (degats > classeArmureCible){
            Affichage.infligerDegatsSuccesPerso(classeArmureCible, nomCible, m_arme.getAmplitudeDegats());
            System.console().readLine();
            int resultatLance = getDegats();
            persoCible.subirAttaque(resultatLance, m_nom);
        }
        else {
            Affichage.infligerDegatsEchecPerso(classeArmureCible, nomCible);
        }
    }

    public void recuperer(Equipement equip){
        //Ajouter l'équipement à l'inventaire
        m_inventaire.add(equip);
    }

    public void equiper(){
        //Choisir l'équipement à équiper
        Equipement equipChoisi = choisirEquipementEquiper();
        m_inventaire.remove(equipChoisi); //Supprimer l'équipement choisi de l'inventaire
        SlotEquipable slot = switch (equipChoisi.getType()){
            case ARME -> new SlotArme(this);
            case ARMURE -> new SlotArmure(this);
        };
        Equipement ancienEquip = slot.get();
        if(!ancienEquip.pasDefinie()){
            recuperer(ancienEquip);
            ancienEquip.retirerEffets(this);
        }
        slot.set(equipChoisi);
        equipChoisi.appliquerEffets(this);
    }

    private Equipement choisirEquipement(ArrayList<Equipement> equipements){
        //Choisir l'équipement
        int index = demandeEntier(1, equipements.size(), Affichage.choisirEquipement(equipements));
        return equipements.get(index-1);
    }

    private Equipement choisirEquipementEquiper(){
        //Choisir un équipement dans l'inventaire
        return choisirEquipement(m_inventaire);
    }

    public Equipement choisirEquipementType(TypeEquipement type){
        //Choisir un équipement du type choisi
        return choisirEquipement(getEquipement(type));
    }

    private ArrayList<Equipement> getEquipement(TypeEquipement type){
        //Renvoyer la liste des equipements du type choisi
        ArrayList<Equipement> equipements = new ArrayList<>();
        SlotEquipable slot = switch (type){
            case ARME -> new SlotArme(this);
            case ARMURE -> new SlotArmure(this);
        };
        equipements.add(slot.get());
        for(Equipement equip : m_inventaire){
            if(equip.getType() == type){
                equipements.add(equip);
            }
        }
        return equipements;
    }

    public int getTailleInventaire(){
        //Renvoyer la taille de l'inventaire
        return m_inventaire.size();
    }

    public ArrayList<Equipement> getInventaire(){
        return m_inventaire;
    }

    public Arme getArme(){
        return m_arme;
    }

    public Armure getArmure(){
        return m_armure;
    }

    public void setArme(Arme nouvelleArme){
        m_arme = nouvelleArme;
    }

    public void setArmure(Armure nouvelleArmure){
        m_armure = nouvelleArmure;
    }

    public boolean lancerSort(ArrayList<Personnage> personnages){
        //Chosir le sort puis le lancer
        Sort s = choisirSort();
        return s.lancer(personnages);
    }

    private Sort choisirSort(){
        //Choisir le sort
        int index = demandeEntier(1, m_sorts.size(), Affichage.choisirSort(m_sorts));
        return m_sorts.get(index-1);
    }

    public void guerir(int pv){
        //Redonner des points de vie au personnage
        m_pv += pv;
        if (m_pv > m_pvMax){
            m_pv = m_pvMax;
        }
        Affichage.guerirPerso(m_nom, pv, m_pv, m_pvMax);
    }

    public int getAttribut(){
        //Renvoyer la dextérité si on utilise une arme à distance et la force sinon.
        return m_arme.estArmeDistance()? m_dexterite: m_force;
    }

    public void subirAttaque(int degats, String attaquant){
        //Déduire les dégâts reçus de la vie.
        m_pv -= degats;
        Affichage.subirAttaquePerso(getNom(), degats, getPv(), attaquant);
    }

    public int getClasseArmure(){
        //Renvoyer la classe d'armure de l'armure équipée.
        return m_armure.getClasseArmure();
    }

    public Pion getPion(){
        //Renvoyer le pion associé au personnage.
        return m_pion;
    }

    public int getInitiative(){
        //Renvoyer l'initiative.
        return m_initiative;
    }

    public void diminuerInitiative(){
        //Décrémente l'initiative
        m_initiative--;
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

    public void setVitesse(int vitesse){
        m_vitesse = vitesse;
    }

    public int getForce(){
        return m_force;
    }

    public void setForce(int force){
        m_force = force;
    }

    public int getDexterite(){
        return m_dexterite;
    }

    public boolean estMort(){
        return m_pv <= 0;
    }

    public boolean peutAttaquer(){
        return !m_arme.pasDefinie();
    }

    public boolean peutLancerSorts(){
        //Renvoie true si le personnage peut lancer un sort, false sinon
        return !m_sorts.isEmpty();
    }

    public String getSymbol(){
        //Renvoyer le symbol.
        return m_symbol;
    }

    public String getNom(){
        return m_nom;
    }

    public String sePresenter(){
        //Renvoyer la formule de présentation pour l'affichage.
        return m_nom;
    }

    public String getInfos(){
        //Renvoyer les informations utiles sur le personnage pour l'affichage, a pour but d'être surchargé par la classe fille Joueur.
        return Affichage.getInfosPerso(getNom(), getPv(), getPvMax());
    }

    public int getPv(){
        //Renvoyer les points de vie.
        return m_pv;
    }

    public int getPvMax() {
        return m_pvMax;
    }

    public TypePersonnage getType(){
        return m_type;
    }

    public boolean equals(Personnage perso) {
        return m_nom.equals(perso.m_nom);
    }

    public int getAction(){
        //Renvoyer l'entier correspondant à l'action choisie, a pour but d'être surchargé par la classe fille Joueur.
         return demandeEntier(1, 2, Affichage.getActionPerso(getNom(), getInitiative()));
    }

    @Override
    public String toString() {
        return m_nom;
    }
}
