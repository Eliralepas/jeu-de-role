package affichage.langues;

import personnages.Personnage;
import personnages.equipements.Equipement;
import personnages.equipements.armes.Arme;
import personnages.equipements.armures.Armure;
import utils.EtatDonjon;

import java.util.ArrayList;

public interface Langue {
    //Contrat: Permet de renvoyer la traduction de chaque mots/phrases du jeu


    String toString();

    String demandeLangue();

    void afficherLangueChoisie();



    /// Affichage classe Joueur

    String sePresenterJoueur(String nom, String race, String classe); //Affiche le nom, la race et la classe du joueur

    String getInfosJoueur(String nom, String race, String classe, int pv, int pvMax); //En plus du nom, race, classe, affiche le pv/pvMax

    String getActionJoueur(String nom, int initiative);

    String toStringJoueur(String nom, String race, String classe, int pv, int pvMax, int force, int dexterite, int vitesse, ArrayList<Equipement> inventaire, Armure armure, Arme arme);



    /// Affichage classe Personnage

    String contenuInventaire(ArrayList<Equipement> inventaire);

    String choisirEquipement();

    String choisirSort();

    String getInfosPerso(String nom, int pv, int pvMax);

    String getActionPerso(String nom, int initiative);

    void subirAttaquePerso(String nom, int degats, int pv, String attaquant);

    String getNomAttribut(Boolean armeDistance); //Renvoyer 'Dextérité' si on utilise une arme à distance et 'Force' sinon.

    void lancerDe(int attaque);

    void attaquer(Boolean armeDistance, int resultatLance, int degats, int total);

    void infligerDegatsSuccesPerso(int classeArmureCible, String nomCible, int amplitudeDegatsArme);

    void infligerDegatsEchecPerso(int classeArmureCible, String nomCible);

    void guerirPerso(String nom, int pv, int nouveauxPv, int pvMax);



    /// Affichage classe GestionDonjon

    String creerEquipementDonjon();

    String demandeNbCreationObjet(String objectACreer);

    void choisirCaseDonjon(String element);

    String caseObstacleAjout();

    String demandeNbCreationMonstre();

    String demandeNbCreationObstacle();

    String demandeNbCreationEquipement();

    String demandeMonstreEspece();

    String demandeMonstreSymbol();

    String demandeMonstrePv();

    String demandeMonstreForce();

    String demandeMonstreDexterite();

    String demandeMonstreVitesse();

    String demandeMonstreDeFace();

    String demandeMonstrePortee();

    String demandeMonstreClasseArmure();

    String demanderEquiperDonjon();



    ///Affichage classe Donjon

    void afficheCreationDonjon(int numero);

    String demandeDonjonDefaut();

    String demandeColonnesDonjon();

    String demandeLignesDonjon();

    String afficherLegendePlateau();

    void appuyerSurEntree();

    String donjon();

    void afficheMsgFinDonjon(EtatDonjon etat);

    void aucunEquipement();

    void peutPasLancerSort(Personnage personnage);

    void recupEquipement(String equipement);

    void nbCasesDeplacement(int distanceMax);

    void caseHorsPortee();

    void aucuneArmeEquipee();

    void porteeAttaque(int portee);

    void attaqueAllie();

    void cibleHorsPortee(String persoCible);

    String getActionMaitreJeu();

    String demandeNbDegatInfliger();

    String caseDestination();

    String caseDeplacement();

    String caseJoueurAjout(String perso);

    void mjFinTour(String persoActuel);

    void mjIntervientPas();

    String maitreDuJeu();



    /// Affichage classe Jeu

    void afficheCreationJoueur();

    String demandeNbCreationJoueur();

    String demandeNbCreationDonjon();

    String demandeRace();

    String demandeNomJoueur();

    String demandeClasse();

    void confimationCreationJoueur(int numero, String joueur);



    /// Affichage classe Arme et Armure

    String ouiOuNon(boolean condition);

    String toStringArme(int nbLance, int amplitudeDegats, int portee, boolean estLourde);

    String toStringArmure(int classeArmure, boolean estLourde);



    /// Affichage classes qui héritent d'Arme

    String nomArbalete();

    String nomArc();

    String nomBaton();

    String nomEpee2Mains();

    String nomEpeeLongue();

    String nomFronde();

    String nomMasse();

    String nomRapiere();



    /// Affichage classes qui héritent d'Armure

    String nomArmureEcailles();

    String nomCotteDeMailles();

    String nomDemiPlate();

    String nomHarnois();




    ///Affichage classes qui héritent de sort

    String nomGuerison();

    String nomBoogieWoogie();

    String nomArmeMagique();

    String selectionJoueur();

    String selectionPersonnage();

    void aucuneSelection(String element);

    void joueurInexistant();

    void armeInexistante();

    void personnageInexistant();

    void memePersonnage();



    /// Affichage classe CasePlateau

    void mauvaisFormatCase();

    void caseInexistante();

    void caseInvalide();



    /// Affichage classes qui héritent de Classe

    String nomClerc();

    String nomGuerrier();

    String nomMagicien();

    String nomRoublard();



    /// Affichage classes qui héritent de Race

    String nomElf();

    String nomHalfelin();

    String nomHumain();

    String nomNain();



    /// Affichage classe Demande

    String intervalleEntier(int min, int max);

    String demandeElement(String element);

    String demandePersonnageNumero(int numero);
}
