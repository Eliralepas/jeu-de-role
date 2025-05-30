package affichage.langues;

import donjon.casePlateau.Symbols;
import personnages.equipements.Equipement;
import personnages.equipements.armes.Arme;
import personnages.equipements.armures.Armure;
import utils.EtatDonjon;

import java.util.ArrayList;

public class Francais implements Langue {

    @Override
    public String toString(){
        return "Francais";
    }

    public String demandeLangue(){
        return """
                
                Entrez le code correspondant à la langue à choisir:
                Français:   fr
                Anglais:    en
                
                """;
    }

    public void afficherLangueChoisie(){
        System.out.println("\nVous avez choisi: " + this + "\n");
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
        return nom + " il vous reste " + initiative + " actions, que souhaitez-vous faire ?\n" +
                """
                        Attaquer:    1
                        Se déplacer: 2
                        S'équiper:   3
                        Lancer sort: 4
                        
                        """;
    }

    public String toStringJoueur(String nom, String race, String classe, int pv, int pvMax, int force, int dexterite, int vitesse, ArrayList<Equipement> inventaire, Armure armure, Arme arme){
        String chaine = nom + " (" + race + ", " + classe + ")\n"
                + "\tVie: " + pv + "/" + pvMax + "\n"
                + "\tArmure: ";
        if (armure.pasDefinie()){
            chaine += "aucune\n";
        }
        else {
            chaine += armure + "\n";
        }
        chaine += "\tArme: ";
        if (arme.pasDefinie()){
            chaine += "aucune\n";
        }
        else {
            chaine += arme + "\n";
        }
        chaine += "\tInventaire: [" + inventaire.size() + "]\n" + contenuInventaire(inventaire);
        chaine += "\tForce: " + force + "\n";
        chaine += "\tDextérité: " + dexterite + "\n";
        chaine += "\tVitesse: " + vitesse + "\n";
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
        return "Entrez le numéro correspondant à l'équipement à choisir:\n";
    }

    public String choisirSort(){
        return "Entrez le numéro correspondant au sort à lancer:\n";
    }

    public String getInfosPerso(String nom, int pv, int pvMax){
        return nom + " (" + pv + "/" + pvMax + ")";
    }


    public String getActionPerso(String nom, int initiative){
        return nom + " il vous reste " + initiative + " actions, que souhaitez-vous faire ?\n" +
                """
                Attaquer:    1
                Se déplacer: 2
                
                """;
    }

    public void subirAttaquePerso(String nom, int degats, int pv, String attaquant){
        System.out.println(nom + " subit " + degats + " dégâts !");
        if (pv > 0){
            System.out.println(nom + " n'a plus que " + pv + " points de vie restants !");
        }
        else {
            System.out.println(nom + " a été tué par " + attaquant + " !");
        }
    }

    public String getNomAttribut(Boolean armeDistance){
        //Renvoyer 'Dextérité' si on utilise une arme à distance et 'Force' sinon.
        return armeDistance? "Dextérité": "Force";
    }

    public void lancerDe(int attaque){
        System.out.println("Lancez un dé de " + attaque + " (appuyez sur 'ENTREE')\n");
    }

    public void attaquer(Boolean armeDistance, int resultatLance, int degats, int total){
        System.out.println("Vous avez fait " + resultatLance);
        System.out.println("Votre attaque est de " + resultatLance + "+" + degats + "(" + getNomAttribut(armeDistance) + ")" + " = " + total + ".");
    }

    public void infligerDegatsSuccesPerso(int classeArmureCible, String nomCible, int amplitudeDegatsArme){
        System.out.println("Votre attaque perce l'armure de " + nomCible + " (" + classeArmureCible + ").");
        System.out.println("Lancez un dé de " + amplitudeDegatsArme + " pour infliger des dégâts (appuyez sur 'ENTREE')");
    }

    public void infligerDegatsEchecPerso(int classeArmureCible, String nomCible){
        System.out.println("Votre attaque ne parvient pas à percer l'armure de " + nomCible + "(" + classeArmureCible + ").");
    }

    public void guerirPerso(String nom, int pv, int nouveauxPv, int pvMax){
        System.out.println(nom + " regagne " + pv + " points de vie.");
        System.out.println(nom + " a maintenant " + nouveauxPv + "/" + pvMax + " points de vie.");
    }



    /// Affichage classe GestionDonjon

    public String creerEquipementDonjon(){
        return """
                Entrez le numéro correspondant à l'équipement à ajouter:
                ------ Armes ------
                Arbalète:           1
                Arc:                2
                Bâton:              3
                Epée Longue:        4
                Fronde:             5
                Masse:              6
                Rapière:            7
                Epée à 2 mains:     8
                ----- Armures -----
                Armure d'écailles:  9
                Cotte de mailles:   10
                Demi plate:         11
                Harnois:            12
                """;
    }

    public String demandeNbCreationObjet(String objectACreer){
        return "Combien " + objectACreer + " souhaitez-vous créer ?";
    }

    public void choisirCaseDonjon(String element){
        System.out.println("Entrez la case sur laquelle ajouter " + element + ": ");
    }

    public String caseObstacleAjout(){
        return "l'obstacle";
    }

    public String demandeNbCreationMonstre(){
        return "de monstres";
    }

    public String demandeNbCreationObstacle(){
        return "d'obstacles";
    }

    public String demandeNbCreationEquipement(){
        return "d'equipements";
    }

    public String demandeMonstreEspece(){
        return "Entrez l'espèce du monstre: ";
    }

    public String demandeMonstreSymbol(){
        return "Entrez le symbol du monstre: ";
    }

    public String demandeMonstrePv(){
        return "Entrez le nombre de points de vie du monstre:";
    }
    public String demandeMonstreForce(){
        return "Entrez la force du montre:";
    }
    public String demandeMonstreDexterite(){
        return "Entrez la dexterite du montre:";
    }

    public String demandeMonstreVitesse(){
        return "Entrez la vitesse du montre:";
    }

    public String demandeMonstreDeFace(){
        return "Entrez le nombre de faces du dé d'attaque du montre:";
    }

    public String demandeMonstrePortee(){
        return "Entrez la portee du montre:";
    }

    public String demandeMonstreClasseArmure(){
        return "Entrez la classe d'armure du montre:";
    }

    public String demanderEquiperDonjon(){
        return """
                    Souhaitez-vous vous équiper ?
                    0 --> Non
                    1 --> Oui
                    
                    """;
    }



    ///Affichage classe Donjon

    public void afficheCreationDonjon(int numero){
        System.out.println("▬▬▬▬▬▬▬▬ Création de donjon ▬▬▬▬▬▬▬▬\n"+ "Donjon n°" + numero);
    }

    public String demandeDonjonDefaut(){
        return """
                Souhaitez-vous générer le donjon par défaut ?
                0 --> Non
                1 --> Oui
                """;
    }

    public String demandeColonnesDonjon(){
        return "Entrez le nombre de colonnes du donjon: ";
    }

    public String demandeLignesDonjon(){
        return "Entrez le nombre de lignes du donjon: ";
    }

    public String afficherLegendePlateau(){
        //Affichage de la légende
        return "     " + Symbols.CASE_VIDE + "Case vide   │  " + Symbols.CASE_EQUIPEMENT + "Equipement   │   " + Symbols.CASE_OBSTACLE + " Obstacle\n";
    }

    public String donjon(){
        return "Donjon";
    }

    public void afficheMsgFinDonjon(EtatDonjon etat){
        //Affiche le message de fin du donjon
        System.out.println("Fin du donjon.");
        String msgFin = "";
        switch (etat){
            case VICTOIRE:
                msgFin += "Vous avez gagné !";
                break;
            case DEFAITE:
                msgFin += "Vous avez perdu !";
        }

        System.out.println(msgFin);
    }

    public void aucunEquipement(){
        System.out.println("Vous n'avez aucun équipement à équiper.");
    }

    public void recupEquipement(String equipement){
        System.out.println("Vous avez récupéré: " + equipement);
    }

    public void nbCasesDeplacement(int distanceMax){
        System.out.println("Vous pouvez vous déplacer de " + distanceMax + " cases.");
    }

    public void caseHorsPortee(){
        System.out.println("La case est hors de portée.");
    }

    public void aucuneArmeEquipee(){
        System.out.println("Vous n'avez pas d'armes équipées.");
    }

    public void porteeAttaque(int portee){
        System.out.println("Votre portée d'attaque est de " + portee + " cases.");
    }

    public void attaqueAllie(){
        System.out.println("Vous ne pouvez pas attaquer votre allié.");
    }

    public void cibleHorsPortee(String persoCible){
        System.out.println(persoCible+ " est hors de portée.");
    }

    public String getActionMaitreJeu(){
        return "\nMaître du jeu, que souhaitez-vous faire ?\n" +
                """
                Ne rien faire:              1
                Déplacer un personnage:     2
                Infliger des dégâts:        3
                Ajouter des obstacles:      4
                Sauter le tour:             5
                
                """;
    }

    public String demandeNbDegatInfliger(){
        return "Entrez le nombre de dégâts à infliger: \n";
    }

    public String caseDestination(){
        return "de destination";
    }

    public String caseDeplacement(){
        return "de déplacement";
    }

    public String caseJoueurAjout(String perso){
        return "le joueur: "+perso;
    }

    public void mjFinTour(String persoActuel){
        System.out.println("Le maître du jeu termine le tour de " + persoActuel + ".");
    }

    public void mjIntervientPas(){
        System.out.println("Le maître du jeu n'intervient pas.");
    }

    public String maitreDuJeu(){
        return "le maître du jeu";
    }



    /// Affichage classe Jeu

    public void afficheCreationJoueur(){
        System.out.println("▬▬▬▬▬▬▬▬ Création de joueurs ▬▬▬▬▬▬▬▬");
    }

    public String demandeNbCreationJoueur(){
        return "Combien de joueurs souhaitez-vous créer ?";
    }

    public String demandeNbCreationDonjon(){
        return "Combien de donjons souhaitez-vous créer ?";
    }

    public String demandeRace(){
        return """
                Entrez le numéro correspondant à la race du personnage:
                Elf:      1
                Halfelin: 2
                Humain:   3
                Nain:     4
                """;
    }

    public String demandeNomJoueur(){
        return "Entrez le nom du joueur: ";
    }

    public String demandeClasse(){
        return """
                Entrez le numéro correspondant à la classe du personnage:
                Clerc:    1
                Guerrier: 2
                Magicien: 3
                Roublard: 4
                """;
    }

    public void confimationCreationJoueur(int numero, String joueur){
        System.out.println("Joueur n°" + numero +" a été créé !\n" + joueur);
    }



    /// Affichage classe Arme et Armure

    public String ouiOuNon(boolean condition){
        return condition ? "oui" : "non";
    }

    public String toStringArme(int nbLance, int amplitudeDegats, int portee, boolean estLourde){
        return " (dégâts: "+ nbLance +"d" + amplitudeDegats + ", portee: " + portee + ", lourde: " + ouiOuNon(estLourde) + ")";
    }

    public String toStringArmure(int classeArmure, boolean estLourde){
        return " (classe d'armure: " + classeArmure + ", lourde: " + ouiOuNon(estLourde) + ")";
    }


    /// Affichage classes qui héritent d'Arme

    public String nomArbalete(){
        return "Arbalète légère";
    }

    public String nomArc(){
        return "Arc court";
    }

    public String nomBaton(){
        return "Bâton";
    }

    public String nomEpee2Mains(){
        return "Épée à deux mains";
    }

    public String nomEpeeLongue(){
        return "Épée longue";
    }

    public String nomFronde(){
        return "Fronde";
    }

    public String nomMasse(){
        return "Masse d'armes";
    }

    public String nomRapiere(){
        return "Rapière";
    }



    /// Affichage classes qui héritent d'Armure

    public String nomArmureEcailles(){
        return "Armure d'écailles";
    }

    public String nomCotteDeMailles(){
        return "Cotte de mailles";
    }

    public String nomDemiPlate(){
        return "Demi-plate";
    }

    public String nomHarnois(){
        return "Harnois";
    }




    ///Affichage classes qui héritent de sort

    public String nomGuerison(){
        return "Guérison";
    }

    public String nomBoogieWoogie(){
        return "Boogie Woogie";
    }

    public String nomArmeMagique(){
        return "Arme magique";
    }

    public String selectionJoueur(){
        return "joueur";
    }

    public String selectionPersonnage(){
        return "personnage";
    }

    public void aucuneSelection(String element){
        System.out.println("Aucun " + element + " n'a été sélectionné.");
    }

    public void joueurInexistant(){
        System.out.println("Le joueur n'existe pas.");
    }

    public void armeInexistante(){
        System.out.println("L'arme n'existe pas.");
    }

    public void personnageInexistant(){
        System.out.println("Un des personnages sélectionnés n'existe pas.");
    }

    public void memePersonnage(){
        System.out.println("Vous ne pouvez pas choisir le même personnage.");
    }



    /// Affichage classe CasePlateau

    public void mauvaisFormatCase(){
        System.out.println("Mauvais format de case.");
    }

    public void caseInexistante(){
        System.out.println("Cette case n'existe pas.");
    }

    public void caseInvalide(){
        System.out.println("La case n'est pas valide");
    }



    /// Affichage classes qui héritent de Classe

    public String nomClerc(){
        return "Clerc";
    }

    public String nomGuerrier(){
        return "Guerrier";
    }

    public String nomMagicien(){
        return "Magicien";
    }

    public String nomRoublard(){
        return "Roublard";
    }



    /// Affichage classes qui héritent de Race

    public String nomElf(){
        return "Elf";
    }

    public String nomHalfelin(){
        return "Halfelin";
    }

    public String nomHumain(){
        return "Humain";
    }

    public String nomNain(){
        return "Nain";
    }



    /// Affichage classe Demande

    public String intervalleEntier(int min, int max){
        return "/!\\ Vous devez entrer un nombre entier entre " + min + " et " + max + ". /!\\";
    }

    public String demandeElement(String element){
        return "Entrez le numéro correspondant au " + element + " à choisir:\n";
    }

    public String demandePersonnageNumero(int numero){
        return "Entrez le numéro correspondant au personnage n°" + numero + " à choisir:\n";
    }
}
