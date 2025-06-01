package affichage.langues;

import donjon.casePlateau.Symbols;
import personnages.Personnage;
import personnages.equipements.Equipement;
import personnages.equipements.armes.Arme;
import personnages.equipements.armures.Armure;
import utils.EtatDonjon;

import java.util.ArrayList;

public class Anglais implements Langue{

    @Override
    public String toString() {
        return "English";
    }

    public String demandeLangue(){
        return """
                
                Enter the code corresponding to the language to choose:
                French:     fr
                English:    en
                
                """;
    }

    public void afficherLangueChoisie(){
        System.out.println("\nYou chose: " + this + "\n");
    }



    /// Affichage classe Joueur

    public String sePresenterJoueur(String nom, String race, String classe){
        //Affiche le nom, la race et la classe du joueur
        return nom + " (" + race + " " + classe.toLowerCase() + ")";
    }

    public String getInfosJoueur(String nom, String race, String classe, int pv, int pvMax){
        //En plus du nom, race, classe, affiche le pv/pvMax
        return nom + " (" + race + " " + classe + ", " + pv + "/" + pvMax + ")";
    }

    public String getActionJoueur(String nom, int initiative) {
        return nom + " you have " + initiative + " actions left, what do you want to do ?\n" +
                """
                        Attack:         1
                        Move:           2
                        Equip:          3
                        Cast spells:    4
                        
                        """;
    }

    public String toStringJoueur(String nom, String race, String classe, int pv, int pvMax, int force, int dexterite, int vitesse, ArrayList<Equipement> inventaire, Armure armure, Arme arme){
        String chaine = nom + " (" + race + ", " + classe + ")\n"
                + "\tHeatlh: " + pv + "/" + pvMax + "\n"
                + "\tArmor: ";
        if (armure.pasDefinie()){
            chaine += "none\n";
        }
        else {
            chaine += armure + "\n";
        }
        chaine += "\tWeapon: ";
        if (arme.pasDefinie()){
            chaine += "none\n";
        }
        else {
            chaine += arme + "\n";
        }
        chaine += "\tInventory: [" + inventaire.size() + "]\n" + contenuInventaire(inventaire);
        chaine += "\tStrength: " + force + "\n";
        chaine += "\tDexterity: " + dexterite + "\n";
        chaine += "\tSpeed: " + vitesse + "\n";
        return chaine;
    }


    /// Affichage classe Personnage

    public String contenuInventaire(ArrayList<Equipement> inventaire){
        StringBuilder chaine = new StringBuilder();
        for (Equipement equipement : inventaire) {
            chaine.append("\t › ").append(equipement.toString()).append("\n");
        }
        return chaine.toString();
    }

    public String choisirEquipement() {
        return "Enter the number corresponding to the equipment you want to choose:\n";
    }

    public String choisirSort(){
        return "Enter the number corresponding to the spell you want to cast:\n";
    }

    public String getInfosPerso(String nom, int pv, int pvMax){
        return nom + " (" + pv + "/" + pvMax + ")";
    }


    public String getActionPerso(String nom, int initiative){
        return nom + " you have " + initiative + " actions left, what do you want to do ?\n" +
                """
                Attack:     1
                Move:       2
                
                """;
    }

    public void subirAttaquePerso(String nom, int degats, int pv, String attaquant){
        System.out.println(nom + " takes " + degats + " damage !");
        if (pv > 0){
            System.out.println(nom + " has " + pv + " health points left !");
        }
        else {
            System.out.println(nom + " was killed by " + attaquant + " !");
        }
    }

    public String getNomAttribut(Boolean armeDistance){
        //Renvoyer 'Dextérité' si on utilise une arme à distance et 'Force' sinon.
        return armeDistance? "Dexterity": "Strength";
    }

    public void lancerDe(int attaque){
        System.out.println("Roll a dice of " + attaque + " (press 'ENTER')\n");
    }

    public void attaquer(Boolean armeDistance, int resultatLance, int degats, int total){
        System.out.println("You rolled " + resultatLance);
        System.out.println("Your attack is " + resultatLance + "+" + degats + "(" + getNomAttribut(armeDistance) + ")" + " = " + total + ".");
    }

    public void infligerDegatsSuccesPerso(int classeArmureCible, String nomCible, int amplitudeDegatsArme){
        System.out.println("Your attack pierced " + nomCible + "'s armor (" + classeArmureCible + ").");
        System.out.println("Roll a dice of " + amplitudeDegatsArme + " to inflict damage (press 'ENTER')");
    }

    public void infligerDegatsEchecPerso(int classeArmureCible, String nomCible){
        System.out.println("Your attack didn't manage to pierce " + nomCible + "'s armor (" + classeArmureCible + ").");
    }

    public void guerirPerso(String nom, int pv, int nouveauxPv, int pvMax){
        System.out.println(nom + " regains " + pv + " health points.");
        System.out.println(nom + " now has " + nouveauxPv + "/" + pvMax + " health points.\n");
    }



    /// Affichage classe GestionDonjon

    public String creerEquipementDonjon(){
        return """
                Enter the number corresponding to the equipment to add:
                ------ Weapons ------
                Crossbow:           1
                Bow:                2
                Stick:              3
                Long Sword:         4
                Slingshot:          5
                Mace:               6
                Rapier:             7
                Two-handed sword:   8
                ----- Armors -----
                Scale armor:        9
                Chainmail:          10
                Half-plate:         11
                Harnois:            12
                """;
    }

    public String demandeNbCreationObjet(String objectACreer){
        return "How many " + objectACreer + " do you wish to create ?";
    }

    public void choisirCaseDonjon(String element){
        System.out.println("Enter the square on which you want to add " + element + ": ");
    }

    public String caseObstacleAjout(){
        return "the obstacle";
    }

    public String demandeNbCreationMonstre(){
        return "monsters";
    }

    public String demandeNbCreationObstacle(){
        return "obstacles";
    }

    public String demandeNbCreationEquipement(){
        return "equipments";
    }

    public String demandeMonstreEspece(){
        return "Enter the monster's species: ";
    }

    public String demandeMonstreSymbol(){
        return "Enter the monster's symbol: ";
    }

    public String demandeMonstrePv(){
        return "Enter the monster's health points:";
    }
    public String demandeMonstreForce(){
        return "Enter the monster's strength:";
    }
    public String demandeMonstreDexterite(){
        return "Enter the monster's dexterity:";
    }

    public String demandeMonstreVitesse(){
        return "Enter the monster's speed:";
    }

    public String demandeMonstreDeFace(){
        return "Enter the number of faces on the monster's attack dice:";
    }

    public String demandeMonstrePortee(){
        return "Enter the monster's reach:";
    }

    public String demandeMonstreClasseArmure(){
        return "Enter the monster's armor class:";
    }

    public String demanderEquiperDonjon(){
        return """
                    Would you like to equip yourself ?
                    0 --> No
                    1 --> Yes
                    
                    """;
    }



    ///Affichage classe Donjon

    public void afficheCreationDonjon(int numero){
        System.out.println("▬▬▬▬▬▬▬▬ Dungeon Creation ▬▬▬▬▬▬▬▬\n"+ "Dungeon n°" + numero);
    }

    public String demandeDonjonDefaut(){
        return """
                Do you want to generate the default dungeon ?
                0 --> No
                1 --> Yes
                """;
    }

    public String demandeColonnesDonjon(){
        return "Enter the number of columns in the dungeon: ";
    }

    public String demandeLignesDonjon(){
        return "Enter the number of rows in the dungeon:";
    }

    public void appuyerSurEntree(){
        System.out.println("Press 'ENTER' to continue.");
    }

    public String afficherLegendePlateau(){
        //Affichage de la légende
        return "     " + Symbols.CASE_VIDE + "Empty square   │  " + Symbols.CASE_EQUIPEMENT + "Equipment   │   " + Symbols.CASE_OBSTACLE + " Obstacle\n";
    }

    public String donjon(){
        return "Dungeon";
    }

    public void afficheMsgFinDonjon(EtatDonjon etat){
        //Affiche le message de fin du donjon
        System.out.println("The dungeon collapses.");
        String msgFin = "";
        switch (etat){
            case VICTOIRE:
                msgFin += "You won !";
                break;
            case DEFAITE:
                msgFin += "You lost !";
        }

        System.out.println(msgFin);
    }

    public void aucunEquipement(){
        System.out.println("You don't have any equipement to equip.");
    }

    public void peutPasLancerSort(Personnage personnage){
        System.out.println(personnage.getNom() + " can't cast spells.");
    }

    public void recupEquipement(String equipement){
        System.out.println("You picked up: " + equipement);
    }

    public void nbCasesDeplacement(int distanceMax){
        System.out.println("You can move " + distanceMax + " squares.");
    }

    public void caseHorsPortee(){
        System.out.println("The square is out of reach.");
    }

    public void aucuneArmeEquipee(){
        System.out.println("You have no weapons equipped");
    }

    public void porteeAttaque(int portee){
        System.out.println("Your attack range is " + portee + " squares.");
    }

    public void attaqueAllie(){
        System.out.println("You cannot attack your ally.");
    }

    public void cibleHorsPortee(String persoCible){
        System.out.println(persoCible+ " is out of reach.");
    }

    public String getActionMaitreJeu(){
        return "\nGame Master, what do you want to do ?\n" +
                """
                Nothing:                    1
                Move a character:           2
                Inflict damage:             3
                Add obstacles:              4
                Skip the turn:              5
                
                """;
    }

    public String demandeNbDegatInfliger(){
        return "Enter the amount of damage to inflict: \n";
    }

    public String caseDestination(){
        return "of destination";
    }

    public String caseDeplacement(){
        return "to move";
    }

    public String caseJoueurAjout(String perso){
        return "the player: "+perso;
    }

    public void mjFinTour(String persoActuel){
        System.out.println("The game master ends " + persoActuel + "'s turn.");
    }

    public void mjIntervientPas(){
        System.out.println("The game master does not intervene.");
    }

    public String maitreDuJeu(){
        return "the game master";
    }



    /// Affichage classe Jeu

    public void afficheCreationJoueur(){
        System.out.println("▬▬▬▬▬▬▬▬ Player Creation ▬▬▬▬▬▬▬▬");
    }

    public String demandeNbCreationJoueur(){
        return "How many players do you want to create ?";
    }

    public String demandeNbCreationDonjon(){
        return "How many dungeons do you want to create ?";
    }

    public String demandeRace(){
        return """
                Enter the number corresponding to the character's race:
                Elf:        1
                Halfling:   2
                Human:      3
                Dwarf:      4
                """;
    }

    public String demandeNomJoueur(){
        return "Enter the player's name: ";
    }

    public String demandeClasse(){
        return """
                Enter the number corresponding to the character's class:
                Cleric:     1
                Warrior:    2
                Magician:   3
                Rogue:      4
                """;
    }

    public void confimationCreationJoueur(int numero, String joueur){
        System.out.println("Player n°" + numero +" was successfully created !\n" + joueur);
    }



    /// Affichage classe Arme et Armure

    public String ouiOuNon(boolean condition){
        return condition ? "yes" : "no";
    }

    public String toStringArme(int nbLance, int amplitudeDegats, int portee, boolean estLourde){
        return " (damage: "+ nbLance +"d" + amplitudeDegats + ", reach: " + portee + ", heavy: " + ouiOuNon(estLourde) + ")";
    }

    public String toStringArmure(int classeArmure, boolean estLourde){
        return " (armor class: " + classeArmure + ", heavy: " + ouiOuNon(estLourde) + ")";
    }


    /// Affichage classes qui héritent d'Arme

    public String nomArbalete(){
        return "Light crossbow";
    }

    public String nomArc(){
        return "Short bow";
    }

    public String nomBaton(){
        return "Stick";
    }

    public String nomEpee2Mains(){
        return "Two-handed sword";
    }

    public String nomEpeeLongue(){
        return "Long sword";
    }

    public String nomFronde(){
        return "Slingshot";
    }

    public String nomMasse(){
        return "Mace";
    }

    public String nomRapiere(){
        return "Rapier";
    }



    /// Affichage classes qui héritent d'Armure

    public String nomArmureEcailles(){
        return "Scale armor";
    }

    public String nomCotteDeMailles(){
        return "Chainmail";
    }

    public String nomDemiPlate(){
        return "Half-plate";
    }

    public String nomHarnois(){
        return "Harnois";
    }



    ///Affichage classes qui héritent de sort

    public String nomGuerison(){
        return "Healing";
    }

    public String nomBoogieWoogie(){
        return "Boogie Woogie";
    }

    public String nomArmeMagique(){
        return "Magic weapon";
    }

    public String selectionJoueur(){
        return "player";
    }

    public String selectionPersonnage(){
        return "character";
    }

    public void aucuneSelection(String element){
        System.out.println("No " + element + " was selected.");
    }

    public void joueurInexistant(){
        System.out.println("The player doesn't exist.");
    }

    public void armeInexistante(){
        System.out.println("The weapon doesn't exist.");
    }

    public void personnageInexistant(){
        System.out.println("One of the selected characters doesn't exist.");
    }

    public void memePersonnage(){
        System.out.println("You can't choose the same character twice.");
    }



    /// Affichage classe CasePlateau

    public void mauvaisFormatCase(){
        System.out.println("Wrong square format.");
    }

    public void caseInexistante(){
        System.out.println("This square doesn't exist.");
    }

    public void caseInvalide(){
        System.out.println("This square is invalid.");
    }



    /// Affichage classes qui héritent de Classe

    public String nomClerc(){
        return "Cleric";
    }

    public String nomGuerrier(){
        return "Warrior";
    }

    public String nomMagicien(){
        return "Magician";
    }

    public String nomRoublard(){
        return "Rogue";
    }



    /// Affichage classes qui héritent de Race

    public String nomElf(){
        return "Elf";
    }

    public String nomHalfelin(){
        return "Halfling";
    }

    public String nomHumain(){
        return "Human";
    }

    public String nomNain(){
        return "Dwarf";
    }



    /// Affichage classe Demande

    public String intervalleEntier(int min, int max){
        return "/!\\ You must enter an integer between " + min + " and " + max + ". /!\\";
    }

    public String demandeElement(String element){
        return "Enter the number corresponding to the " + element + " to choose:\n";
    }

    public String demandePersonnageNumero(int numero){
        return "Enter the number corresponding to the character n°" + numero + " to choose:\n";
    }
}
