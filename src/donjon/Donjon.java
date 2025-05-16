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

import java.util.ArrayList;

import static affichage.Demande.demandeEntier;
import static affichage.Demande.demandeString;

public class Donjon {
    private final int m_numero;
    private final String[][] m_plateau;
    private final int m_colonnes;
    private final int m_lignes;
    private final ArrayList<Pion> m_pionsObstacle;
    private final ArrayList<Equipement> m_equipements;
    private final ArrayList<Personnage> m_personnages;
    private int m_tour;

    public Donjon(int numero, int colonnes, int lignes, ArrayList<Joueur> listeJoueurs){
        m_numero = numero;
        m_colonnes = colonnes;
        m_lignes = lignes;
        m_plateau = new String[lignes][colonnes];
        m_pionsObstacle = new ArrayList<Pion>();
        m_equipements = new ArrayList<Equipement>();
        m_personnages = new ArrayList<Personnage>();
        m_personnages.addAll(listeJoueurs);
        m_tour = 0;
        remplir();
        creerObstacles();
        creerMonstres();
        positionnerJoueurs();
        creerEquipements();
        //presenteContexte();
    }

    public void remplir(){
        for(int i = 0; i< m_lignes; i++){
            for (int j = 0; j< m_colonnes; j++){
                m_plateau[i][j] = " . ";
            }
        }
    }

    private int demanderNombreCreation(String objectACreer){
        int max = (m_lignes * m_colonnes)/4; //Arbitrairement, on définit le nombre max de création au quart des cases du plateau
        return demandeEntier(0, max, "Combien " + objectACreer + " souhaitez-vous créer ?");
    }

    private void creerObstacles(){
        System.out.println(afficherPlateau());
        int nbObstacles = demanderNombreCreation("d'obstacles");
        for (int i=0; i<nbObstacles; i++){
            CasePlateau caseChoisie = null;
            while (caseChoisie == null){
                caseChoisie = choisirCase("l'obstacle");
            }
            ajouterObstacle(caseChoisie);
        }
    }

    private void creerMonstres(){
        System.out.println(afficherPlateau());
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
            CasePlateau caseChoisie = null;
            while (caseChoisie == null){
                caseChoisie = choisirCase("sur laquelle ajouter le monstre");
            }
            ajouterPersonnage(m, caseChoisie);
            m_personnages.add(m);
        }
    }

    private void creerEquipements(){
        System.out.println(afficherPlateau());
        int nbEquipements = demanderNombreCreation("d'equipements");
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
                caseChoisie = choisirCase("sur laquelle ajouter l'équipement");
            }
            ajouterEquipement(equipementChoisi, caseChoisie);
        }
    }

    private void positionnerJoueurs(){
        for(Personnage perso: m_personnages){
            if(perso.estJoueur()){
                CasePlateau caseChoisie = null;
                while (caseChoisie == null){
                    caseChoisie = choisirCase("sur laquelle ajouter le joueur");
                }
                ajouterPersonnage(perso, caseChoisie);
            }
        }
    }

    private CasePlateau choisirCase(String element){
        boolean caseNonChoisie = true;
        CasePlateau caseChoisie = null;
        while(caseNonChoisie) {
            System.out.println("Entrez la case " + element + ": ");
            caseChoisie = new CasePlateau(System.console().readLine());
            if (estCaseValide(caseChoisie)){
                caseNonChoisie = false;
            }
        }
        return caseChoisie;
    }

    private boolean estCaseValide(CasePlateau caseChoisie){
        if (!caseChoisie.estValide()){
            return false;
        }
        caseChoisie.convertirString();
        int x = caseChoisie.getColonne();
        int y = caseChoisie.getLigne();
        if (x > m_colonnes -1 || y > m_lignes -1 || y == -1){
            System.out.println("Cette case n'existe pas.");
            return false;
        }
        if (!(m_plateau[y][x].equals(" . "))){
            System.out.println("La case n'est pas vide");
            return false;
        }
        return true;
    }

    public void ajouterObstacle(CasePlateau caseChoisie){
        int x = caseChoisie.getColonne();
        int y = caseChoisie.getLigne();
        m_plateau[y][x] = "[ ]";
        m_pionsObstacle.add(new Pion(x, y, "[ ]"));
        System.out.println(afficherPlateau());
    }

    public void ajouterEquipement(Equipement equip, CasePlateau caseChoisie){
        int x = caseChoisie.getColonne();
        int y = caseChoisie.getLigne();
        m_plateau[y][x] = " * ";
        m_equipements.add(equip);
        System.out.println(afficherPlateau());
    }

    public void ajouterPersonnage(Personnage perso, CasePlateau caseChoisie){
        int x = caseChoisie.getColonne();
        int y = caseChoisie.getLigne();
        String symbol = perso.getSymbol();
        if (symbol.length() < 3){ //Ajouter un espace au début si la taille du symbol est < 3
            symbol = " " + symbol;
        }
        if (symbol.length() < 3){ //Ajouter un espace à la fin si la taille du symbol est encore < 3
            symbol += " ";
        }
        m_plateau[y][x] = symbol;
        perso.seDeplacer(x, y);
        System.out.println(afficherPlateau());
    }

    public void supprimerPersonnage(Personnage perso){
        m_personnages.remove(perso);
    }

    private void mettreAJourPositions(){
        for(Personnage perso: m_personnages){
            Pion p = perso.getPion();
            int x = p.getX();
            int y = p.getY();
            m_plateau[y][x] = p.toString();
        }
    }

    public void gestionTour(){
        m_tour++;
        triParInitiative();
        Personnage persoActuel = m_personnages.getFirst();
        System.out.println(affichageTour(persoActuel));
        //Faire l'action choisie par l'utilisateur tant qu'il reste des actions à faire.
        int initiative = persoActuel.getInitiative();
        while(initiative > 0){
            boolean resultat = switch (persoActuel.getAction()){
                case 1 -> tryAttaque(persoActuel);
                case 2 -> tryDeplacement(persoActuel);
                case 3 -> tryEquiper(persoActuel);
                default -> false;
            };
            if (resultat){
                initiative--;
            }
        }
    }

    public boolean tryAttaque(Personnage perso){
        CasePlateau caseChoisie = null;
        while (caseChoisie == null){
            caseChoisie = choisirCase("à attaquer");
        }
        Pion p = perso.getPion();
        if (perso.getPortee() < p.getDistance(caseChoisie.getColonne(), caseChoisie.getLigne())){
            System.out.println("La case est hors de portée.");
            return false;
        }
        Personnage persoCible = null;
        int i = 1;
        int n = m_personnages.size();
        while(i<n && persoCible == null) {
            Personnage p2 = m_personnages.get(i);
            Pion pionP2 = p2.getPion();
            if (caseChoisie.getColonne() == pionP2.getX() && caseChoisie.getLigne() == pionP2.getY()){
                persoCible = p2;
            }
            i++;
        }
        if (i<n || persoCible == null){
            System.out.println("Aucun personnage n'est sur cette case.");
            return false;
        }
        perso.attaquer(persoCible);
        if(persoCible.estMort()){
            m_personnages.remove(persoCible);
        }
        return true;
    }

    public boolean tryDeplacement(Personnage perso){
        //chocsir une case
        CasePlateau caseChoisie = null;
        while (caseChoisie == null){
            caseChoisie = choisirCase("de destination");
        }
        int x = caseChoisie.getColonne();
        int y = caseChoisie.getLigne();
        Pion pion = perso.getPion();
        //si ce n'est pas une case avec équipement ou case vide
        if ((!m_plateau[y][x].equals(" . ")) || !(m_plateau[y][x].equals(" * "))){
            System.out.println("La case n'est pas vide.");
            return false;
        }

        //si la distance max a été dépassé
        int distance = pion.getDistance(x, y);
        if (distance > (perso.getVitesse()/3)) {
            System.out.println("Déplacement trop éloigné.");
            return false;
        }

        //déplacer le personnage dans le tableau 2D
        //si le perso est sur une case avec équipement et que celui-ci n'est pas pris alors remettre équipement
        m_plateau[pion.getY()][pion.getX()] = " . ";
        for (Equipement equip : m_equipements) {
            if ((equip.getPion().getX() == x) && (equip.getPion().getY() == y)) {
                m_plateau[pion.getY()][pion.getX()] = " * ";
            }
        }
        perso.seDeplacer(x, y);
        m_plateau[y][x] = perso.getSymbol();
        System.out.println(afficherPlateau());
        return true;
    }

    public boolean tryEquiper(Personnage perso){
        return false;
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

    public String afficherPlateau(){
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

    public String affichageTour(Personnage perso){
        //Affichage du numéro du Donjon et le joueur
        StringBuilder affiche = new StringBuilder(
                """
                *********************************************************************************
                Donjon \s""" + m_numero + """
                
                """ + perso.sePresenter() + """
                
                *********************************************************************************
                
                """);
        //Affichage du tour et de l'ordre des personnages
        affiche.append("Tour ").append(m_tour).append(":\n");
        for(Personnage p: m_personnages){
            if (p.equals(perso)) {
                affiche.append("->");
            }
            affiche.append("\t" + p.getSymbol() + "\t" + p.getInfos() + "\n\n");
        }
        //Affichage du plateau
        affiche.append(afficherPlateau());
        //Affichage de la totalité du personnage
        affiche.append(perso);
        return affiche+"\n";
    }

    public String[][] getPlateau(){
        return m_plateau;
    }

    public int getColonnes(){
        return m_colonnes;
    }

    public int getLignes(){
        return m_lignes;
    }
}
