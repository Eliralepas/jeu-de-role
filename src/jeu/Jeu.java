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

    public Jeu(){
        m_joueurs = new ArrayList<Joueur>();
        m_donjons = new ArrayList<Donjon>();
        m_donjonActuel = 0;
    }

    public void creerJoueur(){
        String nom = "";
        while(nom.isEmpty()){
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
            raceJoueur = switch (demandeEntier(0, 3, msgRace)) {
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
            classeJoueur = switch (demandeEntier(0, 3, msgClasse)){
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
        int longueur = demandeEntier(15, 25, "Entrez la longueur du donjon: ");
        int largeur = demandeEntier(15, 25, "Entrez la largeur du donjon: ");
        m_donjons.add(new Donjon(m_donjons.size(), longueur, largeur));
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
}
