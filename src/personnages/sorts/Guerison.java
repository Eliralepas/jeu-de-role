package personnages.sorts;

import personnages.Joueur;
import personnages.Personnage;
import affichage.Affichage;
import utils.De;
import static utils.Demande.demanderJoueurs;
import static utils.Demande.getJoueurs;

import java.util.ArrayList;

public class Guerison extends Sort{

    public Guerison() { super(Affichage.nomGuerison()); }

    @Override
    public boolean lancer(ArrayList<Personnage> personnages) {
        ArrayList<Joueur> joueurChoisi = demanderJoueurs(getJoueurs(personnages), 1);
        if (joueurChoisi.isEmpty()) {
            Affichage.aucuneSelection(Affichage.selectionJoueur());
            return false;
        }
        Personnage perso = joueurChoisi.getFirst();
        if (perso == null) {
            Affichage.joueurInexistant();
            return false;
        }
        int soin = De.lance(10);
        perso.guerir(soin);
        return true;
    }
}
