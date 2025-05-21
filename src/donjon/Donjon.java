package donjon;

import donjon.casePlateau.CasePlateau;
import donjon.pion.Pion;
import personnages.Joueur;
import personnages.Monstre;
import personnages.Personnage;
import personnages.equipements.Equipement;
import personnages.equipements.armes.*;
import personnages.equipements.armures.ArmureEcailles;
import personnages.equipements.armures.CotteDeMailles;
import personnages.equipements.armures.DemiPlate;
import personnages.equipements.armures.Harnois;
import personnages.equipements.sorts.Sort;
import utils.De;

import java.util.ArrayList;

import static utils.Demande.*;

public class Donjon {
    private final int m_numero;
    private final String[][] m_plateau;
    private final int m_colonnes;
    private final int m_lignes;
    private final ArrayList<Pion> m_pionsObstacle;
    private final ArrayList<Equipement> m_equipements;
    private final ArrayList<Personnage> m_personnages;
    private int m_tour;
    private Etat m_termine;

    public int getColonnes() {
        return m_colonnes;
    }

    public int getLignes(){
        return m_lignes;
    }

    enum Etat {
        ENCOURS, PERDU, GAGNE
    }

    public Donjon(int numero, int colonnes, int lignes, ArrayList<Joueur> listeJoueurs){
        m_numero = numero;
        m_colonnes = colonnes;
        m_lignes = lignes;
        m_plateau = new String[lignes][colonnes];
        m_pionsObstacle = new ArrayList<>();
        m_equipements = new ArrayList<>();
        m_personnages = new ArrayList<>();
        m_personnages.addAll(listeJoueurs);
        m_tour = 0;
        m_termine = Etat.ENCOURS;
        remplirEtJouer();
    }

    public Donjon(int numero, ArrayList<Joueur> listeJoueurs){
        m_numero = numero;
        m_colonnes = 23;
        m_lignes = 18;
        m_plateau = new String[m_lignes][m_colonnes];
        m_pionsObstacle = new ArrayList<>();
        m_equipements = new ArrayList<>();
        m_personnages = new ArrayList<>();
        m_personnages.addAll(listeJoueurs);
        m_tour = 0;
        m_termine = Etat.ENCOURS;
        genererDefaut();
    }

    private void genererDefaut(){
        remplirPlateau();
        //Obstacles
        ajouterObstacle(new CasePlateau("J8"));
        ajouterObstacle(new CasePlateau("J9"));
        ajouterObstacle(new CasePlateau("K10"));
        ajouterObstacle(new CasePlateau("K11"));
        ajouterObstacle(new CasePlateau("K12"));
        //Monstres
        Monstre m1 = new Monstre("Demogorgon", " X(", 1, 45, 15, 0, 6, 10, 1, 6);
        ajouterPersonnage(m1, new CasePlateau("P14"));
        Monstre m2 = new Monstre("Dragon bleu", " X^", 2, 50, 0, 18, 3, 14, 6, 12);
        ajouterPersonnage(m2, new CasePlateau("E4"));
        //Equipements
        ajouterEquipement(new Arbalete(), new CasePlateau("Q7"));
        ajouterEquipement(new EpeeLongue(), new CasePlateau("E15"));
        ajouterEquipement(new Masse(), new CasePlateau("T14"));
        ajouterEquipement(new DemiPlate(), new CasePlateau("K4"));
        ajouterEquipement(new CotteDeMailles(), new CasePlateau("J15"));
        //Clear la console
        positionnerJoueurs();
        jouerDonjon();
    }

    private void remplirEtJouer(){
        remplirPlateau();
        creerObstacles();
        creerMonstres();
        positionnerJoueurs();
        creerEquipements();
        jouerDonjon();
    }

    private void remplirPlateau(){
        for(int i = 0; i< m_lignes; i++){
            for (int j = 0; j< m_colonnes; j++){
                m_plateau[i][j] = " . ";
            }
        }
    }

    private int demanderNombreCreation(int min, String objectACreer){
        int max = (m_lignes * m_colonnes)/4; //Arbitrairement, on définit le nombre max de création au quart des cases du plateau
        return demandeEntier(min, max, "Combien " + objectACreer + " souhaitez-vous créer ?");
    }

    private void creerObstacles(){
        System.out.println(afficherPlateau());
        int nbObstacles = demanderNombreCreation(0, "d'obstacles");
        for (int i=0; i<nbObstacles; i++){
            CasePlateau caseChoisie = null;
            while (caseChoisie == null){
                caseChoisie = choisirCase("l'obstacle", 0);
            }
            ajouterObstacle(caseChoisie);
        }
    }

    private void creerMonstres(){
        System.out.println(afficherPlateau());
        int nbMonstres = demanderNombreCreation(1, "de monstres");
        for (int i=0; i<nbMonstres; i++){
            String espece = demandeString("Entrez l'espèce du monstre: ", 15);
            String symbol = demandeString("Entrez le nom abrégé du monstre: ", 3);
            int pv = demandeEntier(1, 100, "Entrez le nombre de points de vie du monstre");
            int force = demandeEntier(1, 100, "Entrez la force du montre");
            int dexterite = demandeEntier(1, 100, "Entrez la dexterite du montre");
            int vitesse = demandeEntier(1, 100, "Entrez la vitesse du montre");
            int amplitudeDegats = demandeEntier(1, 100, "Entrez le nombre de faces du dé d'attaque du montre");
            int portee = demandeEntier(1, 100, "Entrez la portee du montre");
            int classeArmure = demandeEntier(0, 100, "Entrez la classe d'armure du montre");
            Monstre m = new Monstre(espece, symbol, i+1, pv, force, dexterite, vitesse, amplitudeDegats, portee, classeArmure);
            CasePlateau caseChoisie = null;
            while (caseChoisie == null){
                caseChoisie = choisirCase("sur laquelle ajouter le monstre", 0);
            }
            ajouterPersonnage(m, caseChoisie);
        }
    }

    private void creerEquipements(){
        System.out.println(afficherPlateau());
        int nbEquipements = demanderNombreCreation(0, "d'equipements");
        for (int i=0; i<nbEquipements; i++){
            String msgEquipement =
                """
                Entrez le numéro correspondant à l'équipement à ajouter:
                ------ Armes ------
                Arbalète:           1
                Arc:                2
                Bâton:              3
                Epée Longue:        4
                Fronde:             5
                Masse:              6
                Rapière:            7
                ----- Armures -----
                Armure d'écailles:  8
                Cotte de mailles:   9
                Demi plate:         10
                Harnois:            11
                """;
            Equipement equipementChoisi = switch (demandeEntier(1, 11, msgEquipement)) {
                case 1 -> new Arbalete();
                case 2 -> new Arc();
                case 3 -> new Baton();
                case 4 -> new EpeeLongue();
                case 5 -> new Fronde();
                case 6 -> new Masse();
                case 7 -> new Rapiere();
                case 8 -> new ArmureEcailles();
                case 9 -> new CotteDeMailles();
                case 10 -> new DemiPlate();
                case 11 -> new Harnois();
                default -> null;
            };
            CasePlateau caseChoisie = null;
            while (caseChoisie == null){
                caseChoisie = choisirCase("sur laquelle ajouter l'équipement", 0);
            }
            if (equipementChoisi != null){
                ajouterEquipement(equipementChoisi, caseChoisie);
            }
        }
    }

    private void positionnerJoueurs(){
        for(Personnage perso: m_personnages){
            if(perso.estJoueur()){
                CasePlateau caseChoisie = null;
                while (caseChoisie == null){
                    caseChoisie = choisirCase("sur laquelle ajouter le joueur: " + perso.sePresenter(), 0);
                }
                ajouterPersonnage(perso, caseChoisie);
            }
        }
    }

    private CasePlateau choisirCase(String element, int mode){
        /*mode:
            0 --> ajout
            1 --> attaque
            2 --> déplacement
        */
        boolean caseNonChoisie = true;
        CasePlateau caseChoisie = null;
        while(caseNonChoisie) {
            System.out.println("Entrez la case " + element + ": ");
            caseChoisie = new CasePlateau(System.console().readLine());
            if (estCaseValide(caseChoisie, mode)){
                caseNonChoisie = false;
            }
        }
        return caseChoisie;
    }

    private boolean estCaseValide(CasePlateau caseChoisie, int mode){
        /*mode:
            0 --> ajout
            1 --> attaque
            2 --> déplacement
        */
        if (!caseChoisie.estValide()){
            System.out.println("Mauvais format de case.");
            return false;
        }
        int x = caseChoisie.getColonne();
        int y = caseChoisie.getLigne();
        if (0 > x || x > m_colonnes -1 || 0 > y || y > m_lignes -1){
            System.out.println("Cette case n'existe pas.");
            return false;
        }
        boolean estValide = switch (mode) {
            case 0 -> m_plateau[y][x].equals(" . ");
            case 1 -> !(m_plateau[y][x].equals(" . ") || m_plateau[y][x].equals(" * "));
            case 2 -> m_plateau[y][x].equals(" . ") || m_plateau[y][x].equals(" * ");
            default -> false;
        };
        if(!estValide){
            System.out.println("La case n'est pas valide");
            return false;
        }
        return true;
    }

    private void ajouterObstacle(CasePlateau caseChoisie){
        int x = caseChoisie.getColonne();
        int y = caseChoisie.getLigne();
        m_plateau[y][x] = "[ ]";
        m_pionsObstacle.add(new Pion(x, y, "[ ]"));
        System.out.println(afficherPlateau());
    }

    private void ajouterEquipement(Equipement equip, CasePlateau caseChoisie){
        int x = caseChoisie.getColonne();
        int y = caseChoisie.getLigne();
        equip.setPion(x, y);
        m_plateau[y][x] = " * ";
        m_equipements.add(equip);
        System.out.println(afficherPlateau());
    }

    private void ajouterPersonnage(Personnage perso, CasePlateau caseChoisie){
        int x = caseChoisie.getColonne();
        int y = caseChoisie.getLigne();
        String symbol = perso.getSymbol();
        if (symbol.length() < 3){ //Ajouter un espace au début si la taille du symbol est < 3
            symbol = " " + symbol;
        }
        if (symbol.length() < 3){ //Ajouter un espace à la fin si la taille du symbol est encore < 3
            symbol += " ";
        }
        if(perso.estJoueur()){
            demanderEquiper(((Joueur) perso)); //Le joueur peut équiper l'armure et l'arme de son choix.
        }
        else {
            m_personnages.add(perso); //Ajouter le monstre à la liste des personnages
        }
        m_plateau[y][x] = symbol;
        perso.seDeplacer(x, y);
        System.out.println(afficherPlateau());
    }

    private Equipement getEquipement(Pion pionRecherche){
        Equipement equip = null;
        int i = 0;
        int n = m_equipements.size();
        while (i<n && equip == null){
            Equipement e = m_equipements.get(i);
            Pion p = e.getPion();
            //System.out.println(e + "(" + p.getX() + "," + p.getY() + ") == (" + pionRecherche.getX() + "," + pionRecherche.getY() + ")");
            if (pionRecherche.equals(p)) {
                equip = e;
            }
            i++;
        }
        return equip;
    }

    public boolean jouerDonjon(){
        while(m_termine==Etat.ENCOURS){
            m_tour++;
            lancerInitiative();
            triParInitiative();
            for(int i=0; i<m_personnages.size(); i++){
                Personnage persoActuel = m_personnages.get(i);
                System.out.println(affichageTour(persoActuel));
                //Faire l'action choisie par l'utilisateur tant qu'il reste des actions à faire.
                int initiative = persoActuel.getInitiative();
                while(persoActuel.getInitiative() > 0 && m_termine == Etat.ENCOURS){
                    boolean resultat = switch (persoActuel.getAction()){
                        case 1 -> tryAttaque(persoActuel);
                        case 2 -> tryDeplacement(persoActuel);
                        case 3 -> tryEquiper((Joueur)persoActuel);
                        case 4 -> tryLancerSort((Joueur)persoActuel);
                        default -> false;
                    };
                    if (resultat){
                        if (m_termine == Etat.PERDU){
                            return false;
                        }
                        else if (m_termine == Etat.GAGNE) {
                            return true;
                        }
                        persoActuel.diminuerInitiative();
                        //Le maître du jeu peut intervenir à la fin de l'action d'un joueur ou d'un monstre.
                        interventionMaitrejeu(persoActuel);
                    }
                    System.out.println(afficherPlateau());
                }
            }
        }
        return true;
    }

    private void infligerDegats(){
        Personnage perso = demanderPersonnages(m_personnages, 1).getFirst();
        int degats = demandeEntier(1, 100, "Entrez le nombre de dégâts à infliger: \n");
        perso.subirAttaque(degats, "le maître du jeu");
        testMortPerso(perso);
    }

    private void interventionMaitrejeu(Personnage persoActuel){
        System.out.println(afficherPlateau());
        switch (getActionMaitreJeu()){
            case 2 -> {
                Personnage perso = demanderPersonnages(m_personnages, 1).getFirst();
                deplacerPerso(perso, choisirCase("de déplacement", 2));
            }
            case 3 -> infligerDegats();
            case 4 -> creerObstacles();
            case 5 -> {
                persoActuel.setInitiative(0);
                System.out.println("Le maître du jeu termine le tour de " + persoActuel.sePresenter() + ".");
            }
            default -> System.out.println("Le maître du jeu n'intervient pas.");
        }
    }

    private int getActionMaitreJeu(){
        String msgAction =
                "\nMaître du jeu, que souhaitez-vous faire ?\n" +
                        """
                        Ne rien faire:              1
                        Déplacer un personnage:     2
                        Infliger des dégâts:        3
                        Ajouter des obstacles:      4
                        Sauter le tour:             5
                        
                        """;
        return demandeEntier(1, 5, msgAction);
    }

    private boolean tryAttaque(Personnage perso){
        if (!perso.peutAttaquer()){
            System.out.println("Vous n'avez pas d'armes équipées.");
            return false;
        }
        System.out.println(afficherPlateau());
        System.out.println("Votre portée d'attaque est de " + perso.getPortee() + " cases.");
        Personnage persoCible = demanderPersonnagesWithoutSelf(m_personnages, 1, perso).getFirst();
        if (perso.estJoueur() == persoCible.estJoueur()){ //Si un monstre attaque un monstre ou un joueur attaque un joueur
            System.out.println("Vous ne pouvez pas attaquer votre allié.");
            return false;
        }
        Pion pionCible = persoCible.getPion();
        Pion pionPerso = perso.getPion();
        if (perso.getPortee() < pionPerso.getDistance(pionCible)){
            System.out.println(persoCible.sePresenter() + " est hors de portée.");
            return false;
        }
        perso.attaquer(persoCible);
        testMortPerso(persoCible);
        return true;
    }

    private boolean tryDeplacement(Personnage perso){
        System.out.println(afficherPlateau());
        int distanceMax = perso.getVitesse()/3;
        System.out.println("Vous pouvez vous déplacer de " + distanceMax + " cases.");
        //Choisir une case
        CasePlateau caseChoisie = null;
        while (caseChoisie == null){
            caseChoisie = choisirCase("de destination", 2);
        }
        int x = caseChoisie.getColonne();
        int y = caseChoisie.getLigne();
        Pion pionPerso = perso.getPion();
        //Si la distance max a été dépassée
        if (distanceMax < pionPerso.getDistance(x, y)) {
            System.out.println("La case est hors de portée.");
            return false;
        }
        deplacerPerso(perso, caseChoisie);
        return true;
    }

    private void deplacerPerso(Personnage perso, CasePlateau caseChoisie){
        //Récupérer les nouvelles coordonnées
        int x = caseChoisie.getColonne();
        int y = caseChoisie.getLigne();
        //Récupérer les coordonnées d'origine
        Pion pionPerso = new Pion(perso.getPion());
        int xOrigine = pionPerso.getX();
        int yOrigine = pionPerso.getY();
        //Déplacer le personnage
        perso.seDeplacer(x, y);
        m_plateau[y][x] = perso.getSymbol();
        Pion newPionPerso = perso.getPion();
        //Ramasser l'équipement si la nouvelle case contient un équipement et que le personnage est un joueur
        if (perso.estJoueur()){
            Equipement equip = getEquipement(newPionPerso);
            if (equip != null){
                Joueur j = (Joueur) perso;
                j.recuperer(equip);
                System.out.println("Vous avez récupéré: " + equip);
            }
        }
        //Afficher à nouveau l'équipement si la case d'origine contient un équipement qui était caché par un monstre
        m_plateau[yOrigine][xOrigine] = " . ";
        if (!perso.estJoueur() && getEquipement(pionPerso) != null){
            m_plateau[yOrigine][xOrigine] = " * ";
        }
    }

    private boolean tryEquiper(Joueur joueur){
        System.out.println(joueur);
        if (joueur.getTailleInventaire() < 0){
            System.out.println("Vous n'avez aucun équipement à équiper.");
            return false;
        }
        joueur.equiper();
        return true;
    }

    private void demanderEquiper(Joueur joueur){
        System.out.println(joueur);
        int i = 0;
        int n = 2;
        boolean continuer = true;
        while (i < n && continuer){
            String msgDemande = """
                    Souhaitez-vous vous équiper ?
                    0 --> Non
                    1 --> Oui
                    
                    """;
            if (demandeEntier(0, 1, msgDemande) == 1){
                joueur.equiper();
            }
            else {
                continuer = false;
            }
            i++;
        }
    }

    public boolean tryLancerSort(Joueur joueur){
        if (!joueur.peutLancerSorts()){
            return false;
        }
        boolean resultat = joueur.lancerSort(m_personnages);
        updatePosPersos();
        return resultat;
    }

    private void updatePosPersos(){
        for(Personnage perso : m_personnages){
            Pion p = perso.getPion();
            int x = p.getX();
            int y = p.getY();
            m_plateau[y][x] = perso.getSymbol();
        }
    }

    private void testMortPerso(Personnage perso){
        if(perso.estMort()){
            Pion p = perso.getPion();
            m_plateau[p.getY()][p.getX()] = " . ";
            m_personnages.remove(perso);
            System.out.println(testFinDonjon());
            if (perso.estJoueur()){
                terminerDonjon(Etat.PERDU);
            }
        }
    }

    private void lancerInitiative(){
        for (Personnage perso: m_personnages){
            perso.setInitiative(De.lance(20));
        }
    }

    private void triParInitiative(){
        ArrayList<Personnage> listeTriee = new ArrayList<>();
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
                else{
                    j++;
                }
            }
            listeTriee.add(j, perso);
        }
        m_personnages.clear();
        m_personnages.addAll(listeTriee);
    }

    private String afficherPlateau(){
        StringBuilder affichage = new StringBuilder("      ");
        //Le haut de l'affichage avec les lettres de l'alphabet
        for (int i = 0; i< m_colonnes; i++) {
            affichage.append((char) (i + 65)).append("  ");
        }
        affichage.append("\n   ┌─");
        String ligne= "───";
        affichage.append(ligne.repeat(m_colonnes));
        affichage.append("─┐\n");
        //Chaque ligne du plateau
        for (int i = 1; i< m_lignes +1; i++){
            affichage.append(i);
            if (i < 10){ //Un espace de plus pour combler le caractère manquant des nombres à 1 chiffre
                affichage.append(" ");
            }
            affichage.append(" │ ");
            for (int j = 0; j< m_colonnes; j++){
                affichage.append(m_plateau[i - 1][j]);
            }
            affichage.append(" │\n");
        }
        affichage.append("   └─");
        affichage.append(ligne.repeat(m_colonnes));
        affichage.append("─┘\n");
        //Affichage de la légende
        affichage.append("    * Equipement   │   [ ] Obstacle  │\n");
        return affichage.toString();
    }

    private String affichageTour(Personnage perso){
        //Affichage du numéro du Donjon et le joueur
        StringBuilder affiche = new StringBuilder(
                """
                *********************************************************************************
                Donjon \s""" + m_numero + "\n" +
                "\t\t\t\t\t\t" + perso.sePresenter() + "\n" + """
                
                *********************************************************************************
                
                """);
        //Affichage du tour et de l'ordre des personnages
        affiche.append("Tour ").append(m_tour).append(":\n");
        for(Personnage p: m_personnages){
            if (p.equals(perso)) {
                affiche.append("->");
            }
            affiche.append("\t" + p.getSymbol() + "\t" + p.getInfos() + "\n");
        }
        //Affichage du plateau
        affiche.append("\n" + afficherPlateau());
        //Affichage de la totalité du personnage
        affiche.append("\n" + perso);
        return affiche+"\n";
    }

    private void afficheMsgFin(String resultat){
        System.out.println("Fin du donjon.");
        System.out.println("Vous avez " + resultat + " !");
    }

    private String testFinDonjon(){
        int nbMonstres = 0;
        for (Personnage perso: m_personnages){
            if (!perso.estJoueur()){
                nbMonstres++;
            }
        }
        if (nbMonstres == 0){
            terminerDonjon(Etat.GAGNE);
        }
        return "Il reste " + nbMonstres + " monstres.";
    }

    private void terminerDonjon(Etat e){
        m_termine = e;
        String resultat = "gagné";
        if (m_termine == Etat.PERDU){
            resultat = "perdu";
        }
        afficheMsgFin(resultat);
    }

    public ArrayList<Joueur> getJoueurs(){
        //Renvoie la liste des joueurs après avoir rendu les points de vie aux joueurs
        ArrayList<Joueur> joueurs = new ArrayList<>();
        for (Personnage p: m_personnages){
            if(p.estJoueur()){
                Joueur j = (Joueur) p;
                j.guerir(j.getPvMax());
                joueurs.add(j);
            }
        }
        return joueurs;
    }
}
