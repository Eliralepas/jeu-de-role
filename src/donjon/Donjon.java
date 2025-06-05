package donjon;

import affichage.Affichage;
import donjon.casePlateau.CasePlateau;
import donjon.pion.Pion;
import personnages.Joueur;
import personnages.Monstre;
import personnages.Personnage;
import personnages.equipements.Equipement;
import personnages.equipements.armes.*;
import personnages.equipements.armures.*;
import utils.*;

import java.util.ArrayList;

import static donjon.GestionDonjon.*;
import static donjon.casePlateau.Symbols.*;
import static utils.Demande.*;

public class Donjon {
    private final int m_numero;
    private final String[][] m_plateau;
    private final int m_colonnes;
    private final int m_lignes;
    private final ArrayList<Equipement> m_equipements;
    private final ArrayList<Personnage> m_personnages;
    private final int m_nbJoueurs;
    private int m_tour;
    private int m_casesLibres;

    public Donjon(int numero, int colonnes, int lignes, ArrayList<Joueur> listeJoueurs, boolean genererDefaut) {
        m_numero = numero;
        m_colonnes = colonnes;
        m_lignes = lignes;
        m_plateau = new String[m_lignes][m_colonnes];
        m_equipements = new ArrayList<>();
        m_personnages = new ArrayList<>(listeJoueurs);
        m_nbJoueurs = listeJoueurs.size();
        m_tour = 0;
        m_casesLibres = m_lignes * m_colonnes;
        remplirPlateau();
        if (genererDefaut) {
            genererDefaut();
        } else {
            remplir();
        }
    }

    public Donjon(int numero, int colonnes, int lignes, ArrayList<Joueur> listeJoueurs){
        //Constructeur du donjon personnalisé
        this(numero, colonnes, lignes, listeJoueurs, false);
    }

    public Donjon(int numero, ArrayList<Joueur> listeJoueurs){
        //Constructeur du donjon par défaut
        this(numero, 23, 18, listeJoueurs, true);
    }

    public static Donjon creerDonjon(int numero, ArrayList<Joueur> joueurs){
        Affichage.afficheCreationDonjon(numero);
        int defaut = demandeEntier(0, 1, Affichage.demandeDonjonDefaut());
        Donjon d;
        if (defaut == 0) {
            int colonnes = demandeEntier(15, 25, Affichage.demandeColonnesDonjon());
            int lignes = demandeEntier(15, 25, Affichage.demandeLignesDonjon());
            d = new Donjon(numero, colonnes, lignes, joueurs);
        } else {
            //Donjon par défaut
            d = new Donjon(numero, joueurs);
        }
        return d;
    }

    private void genererDefaut(){
        //Génère le donjon par défaut
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
        //Positionner les joueurs
        positionnerJoueurs();
    }

    private void remplir(){
        //Remplit tout le donjon
        creerObstacles();
        creerMonstres();
        positionnerJoueurs();
        creerEquipements();
    }

    private void remplirPlateau(){
        //Remplit le plateau de cases vides
        for(int i = 0; i< m_lignes; i++){
            for (int j = 0; j< m_colonnes; j++){
                m_plateau[i][j] = CASE_VIDE;
            }
        }
    }

    private CasePlateau choisirCase(String objet, TypeCase action){
        return demanderCase(m_plateau, objet, action);
    }

    private void creerObstacles(){
        //Crée et ajoute autant d'obstacles que souhaité
        System.out.println(this);
        int nbObstacles = demanderNombreCreation(0, getNbMaxCreation(), Affichage.demandeNbCreationObstacle());
        for(int i = 0; i<nbObstacles; i++){
            CasePlateau caseChoisie = demanderCase(m_plateau, Affichage.caseObstacleAjout(), TypeCase.AJOUT);
            ajouterObstacle(caseChoisie);
            System.out.println(this);
        }
    }

    private void creerMonstres(){
        //Crée et ajoute autant de monstres que souhaité
        System.out.println(this);
        ArrayList<Personnage> monstres = demanderCreationMonstres(getNbMaxCreation());
        ajouterPersonnages(monstres);
    }

    private void creerEquipements(){
        //Crée et ajoute autant d'équipements que souhaité
        System.out.println(this);
        ajouterEquipements(demanderCreationEquipements(getNbMaxCreation()));
    }

    private void positionnerJoueurs(){
        //Demande une case où placer chaque joueur
        for(Personnage perso: m_personnages){
            if(perso.getType() == TypePersonnage.JOUEUR){
                ajouterPersonnage(perso, choisirCase(Affichage.caseJoueurAjout(perso.sePresenter()), TypeCase.AJOUT));
            }
        }
    }

    private void ajouterObstacle(CasePlateau caseChoisie){
        //Ajouter un obstacle sur la case choisie
        int x = caseChoisie.getColonne();
        int y = caseChoisie.getLigne();
        m_plateau[y][x] = CASE_OBSTACLE;
        m_casesLibres--;
        System.out.println(this);
    }

    private void ajouterEquipement(Equipement equip, CasePlateau caseChoisie){
        //Ajouter un équipement sur la case choisie
        int x = caseChoisie.getColonne();
        int y = caseChoisie.getLigne();
        equip.setPion(x, y);
        m_plateau[y][x] = CASE_EQUIPEMENT;
        m_casesLibres--;
        m_equipements.add(equip);
        System.out.println(this);
    }

    private void ajouterEquipements(ArrayList<Equipement> equipements){
        for (Equipement equip: equipements){
            ajouterEquipement(equip, choisirCase(equip.toString(), TypeCase.AJOUT));
        }
    }

    private void ajouterPersonnage(Personnage perso, CasePlateau caseChoisie){
        //Ajouter un personnage sur la case choisie
        int x = caseChoisie.getColonne();
        int y = caseChoisie.getLigne();
        String symbol = formatSymbol(perso.getSymbol());
        if(perso.getType() == TypePersonnage.JOUEUR){
            demanderEquiper(((Joueur) perso)); //Le joueur peut équiper l'armure et l'arme de son choix.
        }
        else {
            m_personnages.add(perso); //Ajouter le monstre à la liste des personnages
        }
        m_plateau[y][x] = symbol;
        m_casesLibres--;
        perso.seDeplacer(x, y);
        System.out.println(this);
    }

    private void ajouterPersonnages(ArrayList<Personnage> personnages){
        for (Personnage perso : personnages) {
            ajouterPersonnage(perso, choisirCase(perso.sePresenter(), TypeCase.AJOUT));
        }
    }

    private Equipement getEquipement(Pion pionRecherche){
        //Renvoyer l'équipement à la position du pion de recherche
        Equipement equip = null;
        int i = 0;
        int n = m_equipements.size();
        while (i<n && equip == null){
            Equipement e = m_equipements.get(i);
            Pion p = e.getPion();
            if (pionRecherche.equals(p)) {
                equip = e;
            }
            i++;
        }
        return equip;
    }

    public boolean jouerDonjon(){
        //Gère chaque tour de chaque personnage tant que le donjon n'est pas terminé
        EtatDonjon etat = EtatDonjon.EN_COURS;
        while(etat == EtatDonjon.EN_COURS){
            Affichage.appuyerSurEntree();
            System.console().readLine();
            m_tour++;
            lancerInitiative(m_personnages); //Attribuer l'initiative à chaque personnage
            triParInitiative(m_personnages); //Trier les personnage par initiative décroissante
            etat = tourDonjon();
        }
        return etat == EtatDonjon.VICTOIRE;
    }

    private EtatDonjon tourDonjon(){
        EtatDonjon etat = EtatDonjon.EN_COURS;
        int i = 0;
        int n = m_personnages.size();
        while(i<n && etat == EtatDonjon.EN_COURS){
            Personnage perso = m_personnages.get(i);
            //Joueur le tour du personnage s'il est vivant
            if (!perso.estMort()){
                etat = tourPerso(m_personnages.get(i));
            }
            i++;
        }
        return etat;
    }

    private EtatDonjon tourPerso(Personnage perso){
        EtatDonjon etat = EtatDonjon.EN_COURS;
        System.out.println(affichageTour(perso));
        while(perso.getInitiative() > 0 && etat == EtatDonjon.EN_COURS){
            boolean resultat = switch (perso.getAction()){
                case 1 -> tryAttaque(perso);
                case 2 -> tryDeplacement(perso);
                case 3 -> tryEquiper(perso);
                case 4 -> tryLancerSort(perso);
                default -> false;
            };
            if (resultat){
                perso.diminuerInitiative();
                //Tester si le donjon est terminé
                etat = testFinDonjon();
                if (etat != EtatDonjon.EN_COURS){
                    return etat;
                }
                //Le maître du jeu peut intervenir à la fin de l'action du joueur.
                interventionMaitrejeu(perso);
                //Tester si le donjon est terminé
                etat = testFinDonjon();
                //Tester si le personnage est mort après l'intervention du maître du jeu.
                if (perso.estMort()){
                    return etat;
                }
                System.out.println(this);
                Affichage.appuyerSurEntree();
                System.console().readLine();
            }
        }
        return etat;
    }

    private void infligerDegats(){
        //Choisir le personnage et les dégâts à lui infliger
        Personnage perso = demanderPersonnages(getPersoVivants(m_personnages), 1).getFirst();
        int degats = demandeEntier(1, 100, Affichage.demandeNbDegatInfliger());
        perso.subirAttaque(degats, Affichage.maitreDuJeu());
        testMortPerso(perso);
    }

    private void interventionMaitrejeu(Personnage persoActuel){
        //Demander au maître du jeu quel action il souhaite effectuer
        System.out.println(this);
        switch (getActionMaitreJeu()){
            case 2 -> {
                Personnage perso = demanderPersonnages(getPersoVivants(m_personnages), 1).getFirst();
                deplacerPerso(perso, choisirCase(Affichage.caseDeplacement(), TypeCase.DEPLACEMENT));
            }
            case 3 -> infligerDegats();
            case 4 -> creerObstacles();
            case 5 -> {
                persoActuel.setInitiative(0);
                Affichage.mjFinTour(persoActuel.sePresenter());
            }
            default -> Affichage.mjIntervientPas();
        }
    }

    private int getActionMaitreJeu(){
        //Renvoyer l'entier correspondant à l'action du maître du jeu
        return demandeEntier(1, 5, Affichage.getActionMaitreJeu());
    }

    private boolean tryAttaque(Personnage perso){
        //Vérifie qu'une attaque est possible puis tente l'attaque
        if (!perso.peutAttaquer()){
            Affichage.aucuneArmeEquipee();
            return false;
        }
        System.out.println(this); //Afficher le plateau
        Affichage.porteeAttaque(perso.getPortee());
        Personnage persoCible = demanderPersonnagesWithoutSelf(getPersoVivants(m_personnages), 1, perso).getFirst();
        if (perso.getType() == persoCible.getType()){ //Si un monstre attaque un monstre ou un joueur attaque un joueur
            Affichage.attaqueAllie();
            return false;
        }
        Pion pionCible = persoCible.getPion();
        Pion pionPerso = perso.getPion();
        if (perso.getPortee() < pionPerso.getDistance(pionCible)){
            Affichage.cibleHorsPortee(persoCible.sePresenter());
            return false;
        }
        perso.attaquer(persoCible);
        testMortPerso(persoCible);
        return true;
    }

    private boolean tryDeplacement(Personnage perso){
        //Vérifie qu'un déplacement est possible puis effectue ce déplacement
        System.out.println(this);
        int distanceMax = perso.getVitesse()/3;
        Affichage.nbCasesDeplacement(distanceMax);
        //Choisir une case
        CasePlateau caseChoisie = choisirCase(Affichage.caseDestination(), TypeCase.DEPLACEMENT);
        int x = caseChoisie.getColonne();
        int y = caseChoisie.getLigne();
        Pion pionPerso = perso.getPion();
        //Si la distance max a été dépassée
        if (distanceMax < pionPerso.getDistance(x, y)) {
            Affichage.caseHorsPortee();
            return false;
        }
        deplacerPerso(perso, caseChoisie);
        return true;
    }

    private void deplacerPerso(Personnage perso, CasePlateau caseChoisie){
        //Déplacer le personnage choisi sur la case choisie
        //Récupérer les nouvelles coordonnées
        int x = caseChoisie.getColonne();
        int y = caseChoisie.getLigne();
        //Récupérer les coordonnées d'origine
        Pion pionOrigine = new Pion(perso.getPion());
        //Déplacer le personnage
        perso.seDeplacer(x, y);
        m_plateau[y][x] = perso.getSymbol();
        //Ramasser l'équipement si la nouvelle case contient un équipement et que le personnage est un joueur
        tryRamasserEquipement(perso);
        updateCase(pionOrigine);
    }

    private void tryRamasserEquipement(Personnage perso){
        //Ramasser l'équipement si la nouvelle case contient un équipement et que le personnage est un joueur
        if (perso.getType() == TypePersonnage.JOUEUR){
            Equipement equip = getEquipement(perso.getPion());
            if (equip != null){
                Joueur j = (Joueur) perso;
                j.recuperer(equip);
                Affichage.recupEquipement(equip.toString());
                m_equipements.remove(equip);
                m_casesLibres++;
            }
        }
    }

    private boolean tryEquiper(Personnage perso){
        //Vérifie que le personnage choisi peut équiper un équipement et lui demande quoi équiper
        System.out.println(Affichage.contenuInventaire(perso.getInventaire()));
        if (perso.getTailleInventaire() < 0){
            Affichage.aucunEquipement();
            return false;
        }
        perso.equiper();
        return true;
    }

    public boolean tryLancerSort(Personnage perso){
        //Vérifie que le joueur choisi peut lancer un sort, si oui lance le sort choisi
        if (!perso.peutLancerSorts()){
            Affichage.peutPasLancerSort(perso);
            return false;
        }
        boolean resultat = perso.lancerSort(getPersoVivants(m_personnages));
        updatePosPersos(); //Mise à jour des positions des personnages sur le plateau
        return resultat;
    }

    private void updatePosPersos(){
        //Mise à jour des positions des personnages sur le plateau
        for(Personnage perso : getPersoVivants(m_personnages)){
            Pion p = perso.getPion();
            int x = p.getX();
            int y = p.getY();
            m_plateau[y][x] = perso.getSymbol();
        }
    }

    private void updateCase(Pion p){
        int x = p.getX();
        int y = p.getY();
        //Mettre à jour la case où se trouvait le personnage
        //Afficher à nouveau l'équipement si la case d'origine contient un équipement qui était caché par un monstre
        if (getEquipement(p) != null){
            m_plateau[y][x] = CASE_EQUIPEMENT;
        }
        else {
            m_plateau[y][x] = CASE_VIDE;
        }
    }

    private void testMortPerso(Personnage perso){
        //Vérifie si le personnage choisi est mort, si oui le supprime du plateau
        if(perso.estMort()){
            updateCase(perso.getPion());
            m_casesLibres++;
        }
    }

    private void lancerInitiative(ArrayList<Personnage> personnages){
        //Met à jour l'initiative de chaque personnage au début du nouveau tour
        for (Personnage perso: personnages){
            perso.setInitiative(De.lance(20));
        }
    }

    private void triParInitiative(ArrayList<Personnage> personnages){
        //Trie la liste des personnages par initiative
        ArrayList<Personnage> listeTriee = new ArrayList<>();
        listeTriee.add(personnages.getFirst());
        int n = personnages.size();
        for(int i=1; i<n; i++){
            Personnage perso = personnages.get(i);
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
        personnages.clear();
        personnages.addAll(listeTriee);
    }

    private String affichageTour(Personnage perso){
        //Affiche les infos utiles au début de chaque tour
        //Affichage du numéro du Donjon et le joueur
        return Affichage.afficherTour(m_colonnes, m_lignes, m_plateau, m_numero, getPersoVivants(m_personnages), m_tour, perso);
    }

    private EtatDonjon testFinDonjon(){
        EtatDonjon etat = EtatDonjon.EN_COURS;
        //Teste si un joueur est mort
        if (getNbPerso(TypePersonnage.JOUEUR) != m_nbJoueurs){
            etat = EtatDonjon.DEFAITE;
        }
        //Teste si tous les monstres sont morts
        if (getNbPerso(TypePersonnage.MONSTRE) == 0){
            etat = EtatDonjon.VICTOIRE;
        }
        if (etat != EtatDonjon.EN_COURS){
            Affichage.afficheMsgFinDonjon(etat);
        }
        return etat;
    }

    public int getNbPerso(TypePersonnage type){
        int nb = 0;
        for (Personnage p: getPersoVivants(m_personnages)){
            if(p.getType() == type && !p.estMort()){
                nb++;
            }
        }
        return nb;
    }

    public ArrayList<Joueur> recupererJoueurs(){
        //Renvoie la liste des joueurs après avoir rendu les points de vie aux joueurs
        ArrayList<Joueur> joueurs = new ArrayList<>();
        for (Personnage p: m_personnages){
            if(p.getType() == TypePersonnage.JOUEUR){
                Joueur j = (Joueur) p;
                j.guerir(j.getPvMax());
                joueurs.add(j);
            }
        }
        return joueurs;
    }

    private String formatSymbol(String symbol){
        //Formate le symbol du joueur à afficher sur le plateau
        if (symbol.length() < 3){ //Ajouter un espace au début si la taille du symbol est < 3
            symbol = " " + symbol;
        }
        if (symbol.length() < 3){ //Ajouter un espace à la fin si la taille du symbol est encore < 3
            symbol += " ";
        }
        return symbol;
    }

    private int getNbMaxCreation(){
        //Renvoie le nombre max de création
        return m_casesLibres/4; //Arbitrairement, on définit le nombre max de création au quart des cases libres du plateau
    }

    private ArrayList<Personnage> getPersoVivants(ArrayList<Personnage> personnages){
        ArrayList<Personnage> persoVivants = new ArrayList<>();
        for (Personnage p: personnages){
            if (!p.estMort()){
                persoVivants.add(p);
            }
        }
        return persoVivants;
    }

    @Override
    public String toString() {
        return Affichage.afficherPlateauDonjon(m_colonnes, m_lignes, m_plateau);
    }
}

