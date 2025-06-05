package jeu;

import donjon.Donjon;
import personnages.Joueur;
import personnages.classes.*;
import personnages.races.*;
import affichage.Affichage;

import java.util.ArrayList;

import static personnages.classes.Classe.getClasses;
import static personnages.races.Race.getRaces;
import static utils.Demande.demandeEntier;
import static utils.Demande.demandeString;

public class Jeu {
    private final ArrayList<Joueur> m_joueurs;
    private final int m_nbDonjons;

    public Jeu(){
        m_joueurs = new ArrayList<>();
        creerJoueur();
        m_nbDonjons = demandeEntier(1, 5, Affichage.demandeNbCreationDonjon()); //Max 5 donjons par défaut
        jouer();
    }

    public void jouer(){
        //Lance le jeu
        int i=0;
        boolean pasDefaite = true;
        while(i<m_nbDonjons && pasDefaite){
            Donjon d = Donjon.creerDonjon(i+1, m_joueurs);
            pasDefaite = d.jouerDonjon();
            if(pasDefaite){ //Récupérer tous les joueurs
                m_joueurs.clear();
                m_joueurs.addAll(d.recupererJoueurs());
            }
            i++;
        }
    }

    public void creerJoueur(){
        //Demande à créer des joueurs
        Affichage.afficheCreationJoueur();
        int nbJoueurs = demandeEntier(1, 20, Affichage.demandeNbCreationJoueur()); //Max 20 joueurs par défaut
        for (int i=0; i<nbJoueurs; i++){
            String nom = demandeString(Affichage.demandeNomJoueur(), 15);

            ArrayList<Race> races = getRaces();
            int numero = demandeEntier(1, races.size(), Affichage.demandeRace());
            Race raceJoueur = races.get(numero-1);

            ArrayList<Classe> classes = getClasses();
            numero = demandeEntier(1, classes.size(), Affichage.demandeClasse());
            Classe classeJoueur = classes.get(numero-1);

            if (raceJoueur != null && classeJoueur != null){
                Joueur j = new Joueur(nom, raceJoueur, classeJoueur);
                m_joueurs.add(j);
                Affichage.confimationCreationJoueur(m_joueurs.size(), j.toString());
            }
        }
    }

    @Override
    public String toString() {
        return "Jeu";
    }
}
