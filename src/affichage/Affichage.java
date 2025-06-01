package affichage;

import affichage.langues.Anglais;
import affichage.langues.Francais;
import affichage.langues.Langue;
import personnages.Personnage;
import personnages.equipements.Equipement;
import personnages.equipements.armes.Arme;
import personnages.equipements.armures.Armure;
import personnages.sorts.Sort;
import utils.EtatDonjon;

import java.util.ArrayList;

import static utils.Demande.demandeString;

public abstract class Affichage {
    private static Langue m_langue = new Francais(); //Langue par défaut

    public static void choisirLangue() {
        String message = demandeLangue();
        m_langue = null;
        while (m_langue == null) {
            m_langue = switch (demandeString(message, 2)) {
                case "fr" -> new Francais();
                case "en" -> new Anglais();
                default -> null;
            };
        }
        afficherLangueChoisie();
    }

    public static String demandeLangue(){
        return m_langue.demandeLangue();
    }

    public static void afficherLangueChoisie(){
        m_langue.afficherLangueChoisie();
    }



    /// Affichage classe Joueur

    public static String sePresenterJoueur(String nom, String race, String classe){
        //Affiche le nom, la race et la classe du joueur
        return m_langue.sePresenterJoueur(nom, race, classe);
    }

    public static String getInfosJoueur(String nom, String race, String classe, int pv, int pvMax){
        //En plus du nom, race, classe, affiche le pv/pvMax
        return m_langue.getInfosJoueur(nom, race, classe, pv, pvMax);
    }

    public static String getActionJoueur(String nom, int initiative) {
        return m_langue.getActionJoueur(nom, initiative);
    }

    public static String toStringJoueur(String nom, String race, String classe, int pv, int pvMax, int force, int dexterite, int vitesse, ArrayList<Equipement> inventaire, Armure armure, Arme arme){
        return m_langue.toStringJoueur(nom, race, classe, pv, pvMax, force, dexterite, vitesse, inventaire, armure, arme);
    }


    /// Affichage classe Personnage

    public static String contenuInventaire(ArrayList<Equipement> inventaire){
        return m_langue.contenuInventaire(inventaire);
    }

    public static String choisirEquipement(ArrayList<Equipement> equipements) {
        StringBuilder message = new StringBuilder(m_langue.choisirEquipement());
        int compteur = 1;
        int n = equipements.size();
        for (Equipement equip : equipements) {
            message.append(compteur).repeat(" ", n / 10).append("\t --> \t").append(equip).append("\n");
            compteur++;
        }
        return message.toString();
    }

    public static String choisirSort(ArrayList<Sort> sorts){
        StringBuilder msgSort = new StringBuilder(m_langue.choisirSort());
        int compteur = 1;
        int n = sorts.size();
        for(Sort s: sorts){
            msgSort.append(compteur).repeat(" ", n/10).append("\t --> \t").append(s).append("\n");
            compteur++;
        }
        return msgSort.toString();
    }

    public static String getInfosPerso(String nom, int pv, int pvMax){
        return m_langue.getInfosPerso(nom, pv, pvMax);
    }


    public static String getActionPerso(String nom, int initiative){
        return m_langue.getActionPerso(nom, initiative);
    }

    public static void subirAttaquePerso(String nom, int degats, int pv, String attaquant){
        m_langue.subirAttaquePerso(nom, degats, pv, attaquant);
    }

    public static void lancerDe(int attaque){
        m_langue.lancerDe(attaque);
    }

    public static void attaquer(Boolean armeDistance, int resultatLance, int degats, int total){
        m_langue.attaquer(armeDistance, resultatLance, degats, total);
    }

    public static void infligerDegatsSuccesPerso(int classeArmureCible, String nomCible, int amplitudeDegatsArme){
        m_langue.infligerDegatsSuccesPerso(classeArmureCible, nomCible, amplitudeDegatsArme);
    }

    public static void infligerDegatsEchecPerso(int classeArmureCible, String nomCible){
        m_langue.infligerDegatsEchecPerso(classeArmureCible, nomCible);
    }

    public static void guerirPerso(String nom, int pv, int nouveauxPv, int pvMax){
        m_langue.guerirPerso(nom, pv, nouveauxPv, pvMax);
    }



    /// Affichage classe GestionDonjon

    public static String creerEquipementDonjon(){
        return m_langue.creerEquipementDonjon();
    }

    public static String demandeNbCreationObjet(String objectACreer){
        return m_langue.demandeNbCreationObjet(objectACreer);
    }

    public static void choisirCaseDonjon(String element){
        m_langue.choisirCaseDonjon(element);
    }

    public static String caseObstacleAjout(){
        return m_langue.caseObstacleAjout();
    }

    public static String demandeNbCreationMonstre(){
        return m_langue.demandeNbCreationMonstre();
    }

    public static String demandeNbCreationObstacle(){
        return m_langue.demandeNbCreationObstacle();
    }

    public static String demandeNbCreationEquipement(){
        return m_langue.demandeNbCreationEquipement();
    }

    public static String demandeMonstreEspece(){
        return m_langue.demandeMonstreEspece();
    }

    public static String demandeMonstreSymbol(){
        return m_langue.demandeMonstreSymbol();
    }

    public static String demandeMonstrePv(){
        return m_langue.demandeMonstrePv();
    }
    public static String demandeMonstreForce(){
        return m_langue.demandeMonstreForce();
    }
    public static String demandeMonstreDexterite(){
        return m_langue.demandeMonstreDexterite();
    }

    public static String demandeMonstreVitesse(){
        return m_langue.demandeMonstreVitesse();
    }

    public static String demandeMonstreDeFace(){
        return m_langue.demandeMonstreDeFace();
    }

    public static String demandeMonstrePortee(){
        return m_langue.demandeMonstrePortee();
    }

    public static String demandeMonstreClasseArmure(){
        return m_langue.demandeMonstreClasseArmure();
    }

    public static String demanderEquiperDonjon(){
        return m_langue.demanderEquiperDonjon();
    }



    ///Affichage classe Donjon

    public static void afficheCreationDonjon(int numero){
        m_langue.afficheCreationDonjon(numero);
    }

    public static String demandeDonjonDefaut(){
        return m_langue.demandeDonjonDefaut();
    }

    public static String demandeColonnesDonjon(){
        return m_langue.demandeColonnesDonjon();
    }

    public static String demandeLignesDonjon(){
        return m_langue.demandeLignesDonjon();
    }

    public static void appuyerSurEntree(){
        m_langue.appuyerSurEntree();
    }

    public static String afficherPlateauDonjon(int colonnes, int lignes, String[][] plateau){
        StringBuilder affichage = new StringBuilder("      ");
        //Le haut de l'affichage avec les lettres de l'alphabet
        for (int i = 0; i< colonnes; i++) {
            affichage.append((char) (i + 65)).append("  ");
        }
        affichage.append("\n   ┌─");
        String ligne= "───";
        affichage.append(ligne.repeat(colonnes));
        affichage.append("─┐\n");
        //Chaque ligne du plateau
        for (int i = 1; i< lignes +1; i++){
            affichage.append(i);
            if (i < 10){ //Un espace de plus pour combler le caractère manquant des nombres à 1 chiffre
                affichage.append(" ");
            }
            affichage.append(" │ ");
            for (int j = 0; j< colonnes; j++){
                affichage.append(plateau[i - 1][j]);
            }
            affichage.append(" │\n");
        }
        affichage.append("   └─");
        affichage.append(ligne.repeat(colonnes));
        affichage.append("─┘\n");
        //Affichage de la légende
        affichage.append(m_langue.afficherLegendePlateau());
        return affichage.toString();
    }

    public static String afficherTour(int colonnes, int lignes, String[][] plateau , int numero, ArrayList<Personnage> personnages, int tour, Personnage perso){
        StringBuilder affiche = new StringBuilder(
                """
                *********************************************************************************
                """ + m_langue.donjon() + " " + numero + "\n" +
                        "\t\t\t\t\t\t" + perso.sePresenter() + "\n" + """
                
                *********************************************************************************
                
                """);
        //Affichage du tour et de l'ordre des personnages
        affiche.append("Tour ").append(tour).append(":\n");
        for(Personnage p: personnages){
            if (p.equals(perso)) {
                affiche.append("->");
            }
            affiche.append("\t").append(p.getSymbol()).append("\t").append(p.getInfos()).append("\n");
        }
        //Affichage du plateau
        affiche.append("\n").append(afficherPlateauDonjon(colonnes, lignes, plateau));
        //Affichage de la totalité du personnage
        affiche.append("\n").append(perso).append("\n");
        return affiche.toString();
    }

    public static void afficheMsgFinDonjon(EtatDonjon etat){
        m_langue.afficheMsgFinDonjon(etat);
    }

    public static void aucunEquipement(){
        m_langue.aucunEquipement();
    }

    public static void peutPasLancerSort(Personnage personnage){
        m_langue.peutPasLancerSort(personnage);
    }

    public static void recupEquipement(String equipement){
        m_langue.recupEquipement(equipement);
    }

    public static void nbCasesDeplacement(int distanceMax){
        m_langue.nbCasesDeplacement(distanceMax);
    }

    public static void caseHorsPortee(){
        m_langue.caseHorsPortee();
    }

    public static void aucuneArmeEquipee(){
        m_langue.aucuneArmeEquipee();
    }

    public static void porteeAttaque(int portee){
        m_langue.porteeAttaque(portee);
    }

    public static void attaqueAllie(){
        m_langue.attaqueAllie();
    }

    public static void cibleHorsPortee(String persoCible){
        m_langue.cibleHorsPortee(persoCible);
    }

    public static String getActionMaitreJeu(){
        return m_langue.getActionMaitreJeu();
    }

    public static String demandeNbDegatInfliger(){
        return m_langue.demandeNbDegatInfliger();
    }

    public static String caseDestination(){
        return m_langue.caseDestination();
    }

    public static String caseDeplacement(){
        return m_langue.caseDeplacement();
    }

    public static String caseJoueurAjout(String perso){
        return m_langue.caseJoueurAjout(perso);
    }

    public static void mjFinTour(String persoActuel){
        m_langue.mjFinTour(persoActuel);
    }

    public static void mjIntervientPas(){
        m_langue.mjIntervientPas();
    }

    public static String maitreDuJeu(){
        return m_langue.maitreDuJeu();
    }



    /// Affichage classe Jeu

    public static void afficheCreationJoueur(){
        m_langue.afficheCreationJoueur();
    }

    public static String demandeNbCreationJoueur(){
        return m_langue.demandeNbCreationJoueur();
    }

    public static String demandeNbCreationDonjon(){
        return m_langue.demandeNbCreationDonjon();
    }

    public static String demandeRace(){
        return m_langue.demandeRace();
    }

    public static String demandeNomJoueur(){
        return m_langue.demandeNomJoueur();
    }

    public static String demandeClasse(){
        return m_langue.demandeClasse();
    }

    public static void confimationCreationJoueur(int numero, String joueur){
        m_langue.confimationCreationJoueur(numero, joueur);
    }



    /// Affichage classe Arme et Armure

    public static String toStringArme(int nbLance, int amplitudeDegats, int portee, boolean estLourde){
        return m_langue.toStringArme(nbLance, amplitudeDegats, portee, estLourde);
    }

    public static String toStringArmure(int classeArmure, boolean estLourde){
        return m_langue.toStringArmure(classeArmure, estLourde);
    }


    /// Affichage classes qui héritent d'Arme

    public static String nomArbalete(){
        return m_langue.nomArbalete();
    }

    public static String nomArc(){
        return m_langue.nomArc();
    }

    public static String nomBaton(){
        return m_langue.nomBaton();
    }

    public static String nomEpee2Mains(){
        return m_langue.nomEpee2Mains();
    }

    public static String nomEpeeLongue(){
        return m_langue.nomEpeeLongue();
    }

    public static String nomFronde(){
        return m_langue.nomFronde();
    }

    public static String nomMasse(){
        return m_langue.nomMasse();
    }

    public static String nomRapiere(){
        return m_langue.nomRapiere();
    }



    /// Affichage classes qui héritent d'Armure

    public static String nomArmureEcailles(){
        return m_langue.nomArmureEcailles();
    }

    public static String nomCotteDeMailles(){
        return m_langue.nomCotteDeMailles();
    }

    public static String nomDemiPlate(){
        return m_langue.nomDemiPlate();
    }

    public static String nomHarnois(){
        return m_langue.nomHarnois();
    }




    ///Affichage classes qui héritent de sort

    public static String nomGuerison(){
        return m_langue.nomGuerison();
    }

    public static String nomBoogieWoogie(){
        return m_langue.nomBoogieWoogie();
    }

    public static String nomArmeMagique(){
        return m_langue.nomArmeMagique();
    }

    public static String selectionJoueur(){
        return m_langue.selectionJoueur();
    }

    public static String selectionPersonnage(){
        return m_langue.selectionPersonnage();
    }

    public static void aucuneSelection(String element){
        m_langue.aucuneSelection(element);
    }

    public static void joueurInexistant(){
        m_langue.joueurInexistant();
    }

    public static void armeInexistante(){
        m_langue.armeInexistante();
    }

    public static void personnageInexistant(){
        m_langue.personnageInexistant();
    }

    public static void memePersonnage(){
        m_langue.memePersonnage();
    }



    /// Affichage classe CasePlateau

    public static void mauvaisFormatCase(){
        m_langue.mauvaisFormatCase();
    }

    public static void caseInexistante(){
        m_langue.caseInexistante();
    }

    public static void caseInvalide(){
        m_langue.caseInvalide();
    }



    /// Affichage classes qui héritent de Classe

    public static String nomClerc(){
        return m_langue.nomClerc();
    }

    public static String nomGuerrier(){
        return m_langue.nomGuerrier();
    }

    public static String nomMagicien(){
        return m_langue.nomMagicien();
    }

    public static String nomRoublard(){
        return m_langue.nomRoublard();
    }



    /// Affichage classes qui héritent de Race

    public static String nomElf(){
        return m_langue.nomElf();
    }

    public static String nomHalfelin(){
        return m_langue.nomHalfelin();
    }

    public static String nomHumain(){
        return m_langue.nomHumain();
    }

    public static String nomNain(){
        return m_langue.nomNain();
    }



    /// Affichage classe Demande

    public static String intervalleEntier(int min, int max){
        return m_langue.intervalleEntier(min, max);
    }

    public static String demandeElement(String element){
        return m_langue.demandeElement(element);
    }

    public static String demandePersonnageNumero(int numero){
        return m_langue.demandePersonnageNumero(numero);
    }
}

