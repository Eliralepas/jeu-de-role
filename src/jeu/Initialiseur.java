package jeu;

import affichage.Affichage;

public class Initialiseur {

    public static void lancerJeu() {
        Affichage.choisirLangue();
        new Jeu();
    }
}
