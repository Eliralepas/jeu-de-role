package donjon;

import donjon.pions.Pion;
import personnages.Joueur;
import personnages.Monstre;
import personnages.Personnage;
import personnages.equipements.Equipement;
import personnages.equipements.armes.*;
import personnages.equipements.armures.ArmureEcailles;
import personnages.equipements.armures.CotteDeMailles;
import personnages.equipements.armures.DemiPlate;
import personnages.equipements.armures.Harnois;
import personnages.races.*;

import java.util.ArrayList;

public class Donjon {
    private final int m_numero;
    private String[][] m_plateau;
    private int m_longueur;
    private int m_largeur;
    private ArrayList<Pion> m_pionsObstacle;
    private ArrayList<Equipement> m_equipements;
    private ArrayList<Personnage> m_personnages;

    public Donjon(int numero, int longueur, int largeur, ArrayList<Joueur> listeJoueurs){
        m_numero = numero;
        m_longueur = longueur;
        m_largeur = largeur;
        m_plateau = new String[longueur][largeur];
        m_pionsObstacle = new ArrayList<Pion>();
        m_equipements = new ArrayList<Equipement>();
        m_personnages = new ArrayList<Personnage>();
        m_personnages.addAll(listeJoueurs);
        remplir();
        creerObstacles();
        creerMonstres();
        positionnerJoueurs();
        creerEquipements();
        //presenteContexte();
    }

    public void remplir(){
        for(int i=0; i<m_longueur; i++){
            for (int j=0; j<m_largeur; j++){
                m_plateau[i][j] = " . ";
            }
        }
    }

    private int demanderNombreCreation(String objectACreer){
        int max = (m_longueur * m_largeur)/4; //Arbitrairement, on définit le nombre max de création au quart des cases du plateau
        return demandeEntier(0, max, "Combien " + objectACreer + " souhaitez-vous créer ?");
    }

    private void creerObstacles(){
        int nbObstacles = demanderNombreCreation("d'obstacles");
        for (int i=0; i<nbObstacles; i++){
            String caseChoisie = "";
            while (caseChoisie.isEmpty()){
                caseChoisie = choisirCase("l'obstacle");
            }
            ajouterObstacle(caseChoisie.charAt(0)-65, caseChoisie.charAt(1)-49);
        }
    }

    private void creerMonstres(){
        int nbMonstres = demanderNombreCreation("de monstres");
        for (int i=0; i<nbMonstres; i++){
            String espece = demandeString("Entrez l'espèce du monstre: ", 15);
            String symbol = demandeString("Entrez le nom abrégé du monstre: ", 3);
            int pv = demandeEntier(1, 100, "Entrez le nombre de points de vie du monstre");
            int force = demandeEntier(1, 100, "Entrez la force du montre");
            int dexterite = demandeEntier(1, 100, "Entrez la dexterite du montre");
            int vitesse = demandeEntier(1, 100, "Entrez la vitesse du montre");
            int initiative = demandeEntier(1, 10, "Entrez l'initiative du montre");
            int amplitudeDegats = demandeEntier(1, 100, "Entrez le nombre de faces du dé d'attaque du montre");
            int portee = demandeEntier(1, 100, "Entrez la portee du montre");
            int classeArmure = demandeEntier(0, 100, "Entrez la classe d'armure du montre");
            Monstre m = new Monstre(espece, symbol, i, pv, force, dexterite, vitesse, initiative, amplitudeDegats, portee, classeArmure);
            String caseChoisie = "";
            while (caseChoisie.isEmpty()){
                caseChoisie = choisirCase("le monstre");
            }
            ajouterPersonnage(m, caseChoisie.charAt(0)-65, caseChoisie.charAt(1)-49);
        }
    }

    private void creerEquipements(){
        int nbEquipements = demanderNombreCreation("d'equipements");
        for (int i=0; i<nbEquipements; i++){
            String msgEquipement =
                    "Entrez le numéro correspondant à l'équipement à ajouter:\n" +
                    "------ Armes ------\n" +
                    "Arbalète:           0\n" +
                    "Arc:                1\n" +
                    "Bâton:              2\n" +
                    "Epée Longue:        3\n" +
                    "Fronde:             4\n" +
                    "Masse:              5\n" +
                    "Rapière:            6\n" +
                    "----- Armures -----\n" +
                    "Armure d'écailles:  7\n" +
                    "Cotte de mailles:   8\n" +
                    "Demi plate:         9\n" +
                    "Harnois:            10\n";
            Equipement equipementChoisi = switch (demandeEntier(0, 10, msgEquipement)) {
                case 0 -> new Arbalete();
                case 1 -> new Arc();
                case 2 -> new Baton();
                case 3 -> new EpeeLongue();
                case 4 -> new Fronde();
                case 5 -> new Masse();
                case 6 -> new Rapiere();
                case 7 -> new ArmureEcailles();
                case 8 -> new CotteDeMailles();
                case 9 -> new DemiPlate();
                case 10 -> new Harnois();
                default -> null;
            };
            String caseChoisie = "";
            while (caseChoisie.isEmpty()){
                caseChoisie = choisirCase("l'équipement");
            }
            ajouterEquipement(equipementChoisi, caseChoisie.charAt(0)-65, caseChoisie.charAt(1)-49);
        }
    }

    private void positionnerJoueurs(){
        for(Personnage perso: m_personnages){
            if(perso.estJoueur()){
                String caseChoisie = "";
                while (caseChoisie.isEmpty()){
                    caseChoisie = choisirCase("le joueur");
                }
                perso.seDeplacer(caseChoisie.charAt(0)-65, caseChoisie.charAt(1)-49);
            }
        }
    }

    private String choisirCase(String element){
        String caseChoisie = "";
        while(caseChoisie.isEmpty()) {
            System.out.println("Entrez la case sur laquelle ajouter " + element + ": ");
            caseChoisie = System.console().readLine();
            if (!estCaseValide(caseChoisie)){
                caseChoisie = "";
            }
        }
        return caseChoisie;
    }

    private boolean estCaseValide(String caseChoisie){
        int n = caseChoisie.length();
        if (!(2 <= n && n <= 3)){
            System.out.println("Mauvais format de case.");
            return false;
        }
        int x = caseChoisie.charAt(0) - 65; // On veut que la lettre A représente l'index 0 et 'A' = 65 donc index = lettre - 65
        if (x < 0 || x > m_longueur-1){
            System.out.println("Cette case n'existe pas.");
            return false;
        }
        int y = -1;
        if (n == 2){
            y = caseChoisie.charAt(1) - 49; // On veut que le chiffre 1 représente l'index 0 et '1' = 49 donc index = lettre - 49
        }
        else{ //Si n == 3
            y = (caseChoisie.charAt(1)-48)* 10 + (caseChoisie.charAt(2)-49);
        }
        if (y < 0 || y > m_largeur-1){
            System.out.println("Cette case n'existe pas.");
            return false;
        }
        if (!(m_plateau[x][y].equals(" . "))){
            System.out.println("La case n'est pas vide");
            return false;
        }
        return true;
    }

    public void ajouterObstacle(int x, int y){
        m_plateau[x][y] = "[ ]";
        m_pionsObstacle.add(new Pion(x, y, "[ ]"));
    }

    public void ajouterEquipement(Equipement equip, int x, int y){
        m_plateau[x][y] = " * ";
        m_equipements.add(equip);
    }

    public void ajouterPersonnage(Personnage perso, int x, int y){
        perso.seDeplacer(x, y);
        m_personnages.add(perso);
    }

    public void supprimerPersonnage(Personnage perso){
        m_personnages.remove(perso);
    }

    private void afficherPersonnages(){
        for(Personnage perso: m_personnages){
            Pion p = perso.getPion();
            int x = p.getX();
            int y = p.getY();
            m_plateau[x][y] = p.toString();
        }
    }

    public void triParInitiative(){
        ArrayList<Personnage> listeTriee = new ArrayList<Personnage>();
        listeTriee.add(m_personnages.getFirst());
        int n = m_personnages.size();
        for(int i=1; i<n; i++){
            Personnage perso = m_personnages.get(i);
            int initiativePerso = perso.getInitiative();
            int m = listeTriee.size();
            int j = 0;
            boolean indexTrouve = false;
            while(j<m && !indexTrouve){
                Personnage p = listeTriee.get(j);
                if(p.getInitiative() < initiativePerso){
                    indexTrouve = true;
                }
                j++;
            }
            listeTriee.add(j-1, perso);
        }
        m_personnages.clear();
        m_personnages.addAll(listeTriee);
    }

    public int demandeEntier(int min, int max, String msgDemande) throws NumberFormatException{
        int entier = min - 1;
        String msgErreur = "/!\\ Vous devez entrer un nombre entier entre " + min + " et " + max + ". /!\\";
        while (!(min <= entier && entier <= max)) {
            System.out.println(msgDemande);
            String reponse = System.console().readLine();
            try {
                entier = Integer.parseInt(reponse);
                if(!(min <= entier && entier <= max)){
                    System.out.println(msgErreur);
                }
            } catch (NumberFormatException e) {
                System.out.println(msgErreur);
            }
        }
        return entier;
    }

    public String demandeString(String msgDemande, int tailleMax){
        String chaine = "";
        while(chaine.isEmpty()){
            System.out.println(msgDemande);
            chaine = System.console().readLine();
            if (chaine.length() > tailleMax){
                chaine = "";
            }
        }
        return chaine;
    }
}
