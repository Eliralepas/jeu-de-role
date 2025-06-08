package jeu;

import affichage.Affichage;

import static jeu.Jeu.jouer;

public abstract class Initialiseur {

    public static void lancerJeu() {
        Affichage.choisirLangue();
        jouer();
    }

    @Override
    public String toString() {
        return "Initialiseur";
    }
}
