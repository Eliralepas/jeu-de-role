package donjon;

import donjon.casePlateau.CasePlateau;
import personnages.Joueur;
import personnages.Monstre;
import personnages.Personnage;
import personnages.equipements.Equipement;
import personnages.equipements.armes.*;
import personnages.equipements.armures.ArmureEcailles;
import personnages.equipements.armures.CotteDeMailles;
import personnages.equipements.armures.DemiPlate;
import personnages.equipements.armures.Harnois;
import affichage.Affichage;
import utils.TypeCase;

import java.util.ArrayList;

import static utils.Demande.demandeEntier;
import static utils.Demande.demandeString;

public abstract class GestionDonjon {

    private static int demanderNombreCreation(int min, int max, String objectACreer){
        //Renvoyer un entier correspondant au nombre de création voulu
        return demandeEntier(min, max, Affichage.demandeNbCreationObjet(objectACreer));
    }

    public static CasePlateau demanderCase(String[][] plateau, String element, TypeCase action){
        CasePlateau caseChoisie = null;
        while (caseChoisie == null){
            Affichage.choisirCaseDonjon(element);
            caseChoisie = new CasePlateau(System.console().readLine());
            if (!caseChoisie.estValide(plateau, action)){
                caseChoisie = null;
            }
        }
        return caseChoisie;
    }

    public static ArrayList<CasePlateau> demanderCreationObstacles(String[][] plateau, int max){
        ArrayList<CasePlateau> casesChoisies = new ArrayList<>();
        int nbObstacles = demanderNombreCreation(0, max, Affichage.demandeNbCreationObstacle());
        for (int i=0; i<nbObstacles; i++){
            casesChoisies.add(demanderCase(plateau, Affichage.caseObstacleAjout(), TypeCase.AJOUT));
        }
        return casesChoisies;
    }

    public static ArrayList<Personnage> demanderCreationMonstres(int max){
        ArrayList<Personnage> monstres = new ArrayList<>();
        int nbMonstres = demanderNombreCreation(1, max, Affichage.demandeNbCreationMonstre());
        for (int i=0; i<nbMonstres; i++){
            String espece = demandeString(Affichage.demandeMonstreEspece(), 15);
            String symbol = demandeString(Affichage.demandeMonstreSymbol(), 3);
            int pv = demandeEntier(1, 100, Affichage.demandeMonstrePv());
            int force = demandeEntier(1, 100, Affichage.demandeMonstreForce());
            int dexterite = demandeEntier(1, 100, Affichage.demandeMonstreDexterite());
            int vitesse = demandeEntier(1, 100, Affichage.demandeMonstreVitesse());
            int amplitudeDegats = demandeEntier(1, 100, Affichage.demandeMonstreDeFace());
            int portee = demandeEntier(1, 100, Affichage.demandeMonstrePortee());
            int classeArmure = demandeEntier(0, 100, Affichage.demandeMonstreClasseArmure());
            Monstre m = new Monstre(espece, symbol, i+1, pv, force, dexterite, vitesse, amplitudeDegats, portee, classeArmure);
            monstres.add(m);
        }
        return monstres;
    }

    public static ArrayList<Equipement> demanderCreationEquipements(int max){
        ArrayList<Equipement> equipements = new ArrayList<>();
        int nbEquipements = demanderNombreCreation(0, max, Affichage.demandeNbCreationEquipement());
        for (int i=0; i<nbEquipements; i++){
            Equipement equipementChoisi = switch (demandeEntier(1, 12, Affichage.creerEquipementDonjon())) {
                case 1 -> new Arbalete();
                case 2 -> new Arc();
                case 3 -> new Baton();
                case 4 -> new EpeeLongue();
                case 5 -> new Fronde();
                case 6 -> new Masse();
                case 7 -> new Rapiere();
                case 8 -> new Epee2Mains();
                case 9 -> new ArmureEcailles();
                case 10 -> new CotteDeMailles();
                case 11 -> new DemiPlate();
                case 12 -> new Harnois();
                default -> null;
            };
            if (equipementChoisi != null){
                equipements.add(equipementChoisi);
            }
        }
        return equipements;
    }

    public static void demanderEquiper(Joueur joueur){
        //Demander au joueur choisi s'il souhaite s'équiper, si oui gère l'action de s'équiper
        System.out.println(joueur);
        int i=1;
        String msgDemande = Affichage.demanderEquiperDonjon();
        while (i == 1){
            i = demandeEntier(0, 1, msgDemande);
            if (i == 1){
                joueur.equiper();
            }
        }
    }
}
