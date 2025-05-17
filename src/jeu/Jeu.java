package jeu;

import donjon.Donjon;
import personnages.Joueur;
import personnages.classes.*;
import personnages.races.*;

import java.util.ArrayList;

import static utils.Demande.demandeEntier;
import static utils.Demande.demandeString;

public class Jeu {
    private final ArrayList<Joueur> m_joueurs;
    private final ArrayList<Donjon> m_donjons;

    public Jeu(){
        m_joueurs = new ArrayList<Joueur>();
        m_donjons = new ArrayList<Donjon>();
        creerJoueur();
        creerDonjon();
        jouer();
    }

    public void jouer(){
        int i=0;
        int n = m_donjons.size();
        boolean finJeu = false;
        while(i<n && !finJeu){
            Donjon d = m_donjons.get(i);
            finJeu = d.jouerDonjon();
            if(!finJeu){ //Récupérer tous les joueurs
                m_joueurs.clear();
                m_joueurs.addAll(d.getJoueurs());
            }
        }
    }

    public void creerJoueur(){
        System.out.println("▬▬▬▬▬▬▬▬ Création de joueurs ▬▬▬▬▬▬▬▬");
        int nbJoueurs = demandeEntier(1, 20, "Combien de joueurs souhaitez-vous créer ?"); //Max 20 joueurs par défaut
        for (int i=0; i<nbJoueurs; i++){
            String nom = demandeString("Entrez le nom du personnage: ", 15);
            String msgRace =
                    """
                    Entrez le numéro correspondant à la race du personnage:
                    Elf:      1
                    Halfelin: 2
                    Humain:   3
                    Nain:     4
                    """;
            Race raceJoueur = switch (demandeEntier(1, 4, msgRace)) {
                case 1 -> new Elf();
                case 2 -> new Halfelin();
                case 3 -> new Humain();
                case 4 -> new Nain();
                default -> null;
            };
            String msgClasse =
                    """
                    Entrez le numéro correspondant à la classe du personnage:
                    Clerc:    1
                    Guerrier: 2
                    Magicien: 3
                    Roublard: 4
                    """;
            Classe classeJoueur = switch (demandeEntier(1, 4, msgClasse)){
                case 1 -> new Clerc();
                case 2 -> new Guerrier();
                case 3 -> new Magicien();
                case 4 -> new Roublard();
                default -> null;
            };
            Joueur j = new Joueur(nom, raceJoueur, classeJoueur);
            m_joueurs.add(j);
            System.out.println("Joueur n°" + m_joueurs.size() +" a été créé !\n" + j.toString());
        }
    }

    public void creerDonjon(){
        System.out.println("▬▬▬▬▬▬▬▬ Création du donjon ▬▬▬▬▬▬▬▬");
        int nbDonjons = demandeEntier(1, 5, "Combien de donjons souhaitez-vous créer ?"); //Max 5 donjons par défaut
        for (int i=1; i<nbDonjons+1; i++) {
            System.out.println("\nDonjon n°" + i);
            String msgDonjon = """
                    Souhaitez-vous générer le donjon par défaut ?
                    0 --> Non
                    1 --> Oui
                    """;
            int defaut = demandeEntier(0, 1, msgDonjon);
            Donjon d = null;
            if (defaut == 0) {
                int colonnes = demandeEntier(15, 25, "Entrez le nombre de colonnes du donjon: ");
                int lignes = demandeEntier(15, 25, "Entrez le nombre de lignes du donjon: ");
                d = new Donjon(i, colonnes, lignes, m_joueurs);
            } else {
                //Donjon par défaut
                d = new Donjon(i, m_joueurs);
            }
            m_donjons.add(d);
        }
    }
}
