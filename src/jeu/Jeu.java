package jeu;

import donjon.Donjon;
import personnages.Joueur;
import personnages.classes.*;
import personnages.races.*;

import java.util.ArrayList;

public class Jeu {
    private final ArrayList<Joueur> m_joueurs;
    private final ArrayList<Donjon> m_donjons;
    private int m_donjonActuel;
    private int m_numeroTour;
    private Joueur m_joueurActuel;
    private int m_indexJoueur;

    public Jeu(){
        m_joueurs = new ArrayList<Joueur>();
        m_donjons = new ArrayList<Donjon>();
        m_donjonActuel = 0;
        m_numeroTour = 0;
        m_indexJoueur=0;
        m_joueurActuel = m_joueurs.getFirst();
    }

    public void creerJoueur(){
        String nom = "";
        while(nom == ""){
            System.out.println("Entrez le nom du personnage: ");
            nom = System.console().readLine();
        }
        Race raceJoueur = null;
        String msgRace =
                "Entrez le numéro correspondant à la race du personnage:\n" +
                "Elf:      0\n" +
                "Halfelin: 1\n" +
                "Humain:   2\n" +
                "Nain:     3\n";
        while(raceJoueur == null){
            raceJoueur = switch (demandeEntier(msgRace, "Vous devez entrer un nombre entier entre 0 et 3 inclu.")) {
                case 0 -> new Elf();
                case 1 -> new Halfelin();
                case 2 -> new Humain();
                case 3 -> new Nain();
                default -> null;
            };
        }
        Classe classeJoueur = null;
        String msgClasse =
                "Entrez le numéro correspondant à la classe du personnage:\n" +
                "Clerc:    0\n" +
                "Guerrier: 1\n" +
                "Magicien: 2\n" +
                "Roublard: 3\n";
        while(classeJoueur == null){
            classeJoueur = switch (demandeEntier(msgClasse, "Vous devez entrer un nombre entier entre 0 et 3 inclu.")) {
                case 0 -> new Clerc();
                case 1 -> new Guerrier();
                case 2 -> new Magicien();
                case 3 -> new Roublard();
                default -> null;
            };
        }
        Joueur j = new Joueur(nom, raceJoueur, classeJoueur);
        m_joueurs.add(j);
        System.out.println("Joueur n°" + m_joueurs.size() +" a été créé !\n" + j.toString());
    }

    public void creerDonjon(){
        int longueur = 0;
        while(longueur == -1 || longueur == 0){
            longueur = demandeEntier("Entrez la longueur du donjon: ", "Vous devez entrer un nombre entier positif non nul.");
        }
        int largeur = 0;
        while(largeur == -1 || largeur == 0){
            largeur = demandeEntier("Entrez la largeur du donjon: ", "Vous devez entrer un nombre entier positif non nul.");
        }
        m_donjons.add(new Donjon(m_donjons.size(), longueur, largeur));
    }

    public int demandeEntier(String msgDemande, String msgErreur) throws NumberFormatException{
        int entier = -1;
        System.out.println(msgDemande);
        String reponse = System.console().readLine();
        try {
            entier = Integer.parseInt(reponse);
        } catch (NumberFormatException e) {
            System.out.println( "/!\\ " + msgErreur + " /!\\");
        }
        return entier;
    }

    public ArrayList<Joueur> getJoueurs(){
        return m_joueurs;
    }

    public int getNumeroTour(){
        return m_numeroTour;
    }

    public Joueur getJoueurActuel(){
        return m_joueurActuel;
    }

    public void JoueurSuivant(){
        int nb_joueur = m_joueurs.size();
        if (m_indexJoueur==nb_joueur){
            m_indexJoueur = 0;
            m_joueurActuel = m_joueurs.getFirst();
            m_numeroTour++;
        }
        else {
            m_indexJoueur++;
            m_joueurActuel = m_joueurs.get(m_indexJoueur);
        }
    }
}
