package jeu;

import affichage.Affichage;

public abstract class Initialiseur {

    public static void lancerJeu() {
        Affichage.choisirLangue();
        new Jeu();
    }

    @Override
    public String toString() {
        return "Initialiseur";
    }
}
