package donjon;

import donjon.pions.Pion;
import personnages.Monstre;
import personnages.Personnage;

import java.util.ArrayList;

public class Donjon {
    private final int m_numero;
    private String[][] m_plateau;
    private int m_longueur;
    private int m_largeur;
    private ArrayList<Pion> m_pionsObstacle;
    private ArrayList<Pion> m_pionsEquipement;
    private ArrayList<Pion> m_pionsPersonnage;
    private ArrayList<Personnage> m_personnages;

    public Donjon(int numero, int longueur, int largeur){
        m_numero = numero;
        m_longueur = longueur;
        m_largeur = largeur;
        m_plateau = new String[longueur][largeur];
        m_pionsObstacle = new ArrayList<Pion>();
        m_pionsEquipement = new ArrayList<Pion>();
        m_pionsPersonnage = new ArrayList<Pion>();
        remplir();
        creerObstacles();
        creerMonstres();
        //creerEquipements();
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
                caseChoisie = choisirCase();
            }
            ajouterElement(caseChoisie.charAt(0)-17, caseChoisie.charAt(1)-1, "[ ]", m_pionsObstacle);
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
                caseChoisie = choisirCase();
            }
            ajouterElement(caseChoisie.charAt(0)-17, caseChoisie.charAt(1)-1, symbol, m_pionsPersonnage);
        }
    }

    private String choisirCase(){
        String caseChoisie = "";
        while(caseChoisie.isEmpty()) {
            System.out.println("Entrez la case sur laquelle ajouter l'obstacle: ");
            caseChoisie = System.console().readLine();
            if (!estCaseValide(caseChoisie)){
                caseChoisie = "";
            }
        }
        return caseChoisie;
    }

    private boolean estCaseValide(String caseChoisie){
        if (caseChoisie.length() != 2){
            return false;
        }
        int x = caseChoisie.charAt(0) - 17; // On veut que la lettre A représente l'index 0 et 'A' = 65; '0' = 48 donc index = lettre - 17
        if (x < 0 || x > m_longueur-1){ // Index en dehors du plateau
            return false;
        }
        int y = caseChoisie.charAt(1) - 1;
        if (y < 0 || y > m_largeur-1){ // Index en dehors du plateau
            return false;
        }
        return true;
    }

    public void ajouterElement(int x, int y, String element, ArrayList<Pion> liste){
        m_plateau[x][y] = element;
        liste.add(new Pion(x, y, element));
    }

    public void ajouterPersonnage(Personnage perso, int x, int y){
        m_pionsPersonnage.add(perso.getPion());
    }

    public void supprimerPersonnage(Personnage perso){
        m_pionsPersonnage.remove(perso.getPion());
    }

    private void afficherPersonnages(){
        for(Pion p: m_pionsPersonnage){
            int x = p.getX();
            int y = p.getY();
            m_plateau[x][y] = p.toString();
        }
    }

    public int demandeEntier(int min, int max, String msgDemande) throws NumberFormatException{
        int entier = min - 1;
        String msgErreur = "/!\\ Vous devez entrer un nombre entier entre " + min + " et " + max + ". /!\\";
        while (!(min < entier && entier < max)) {
            System.out.println(msgDemande);
            String reponse = System.console().readLine();
            try {
                entier = Integer.parseInt(reponse);
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
