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

public abstract class Jeu {

    public static void jouer(){
        //Lance le jeu
        ArrayList<Joueur> joueurs = creerJoueur();
        int nbDonjons = demandeEntier(1, 5, Affichage.demandeNbCreationDonjon()); //Max 5 donjons par défaut
        int i=0;
        boolean pasDefaite = true;
        while(i<nbDonjons && pasDefaite){
            Donjon d = Donjon.creerDonjon(i+1, joueurs);
            pasDefaite = d.jouerDonjon();
            if(pasDefaite){ //Récupérer tous les joueurs
                joueurs.clear();
                joueurs.addAll(d.recupererJoueurs());
            }
            i++;
        }
    }

    private static ArrayList<Joueur> creerJoueur(){
        //Demande à créer des joueurs
        ArrayList<Joueur> joueurs = new ArrayList<>();
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
                joueurs.add(j);
                Affichage.confimationCreationJoueur(joueurs.size(), j.toString());
            }
        }
        return joueurs;
    }

    @Override
    public String toString() {
        return "Jeu";
    }
}
