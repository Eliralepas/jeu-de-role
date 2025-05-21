package personnages.equipements.sorts;

import personnages.Joueur;
import personnages.Personnage;
import utils.De;
import static utils.Demande.demanderJoueurs;
import static utils.Demande.getJoueurs;

import java.util.ArrayList;

public class Guerison extends Sort{

    public Guerison() { super("Guérison"); }

    @Override
    public boolean lancer(ArrayList<Personnage> personnages) {
        ArrayList<Joueur> joueurChoisi = demanderJoueurs(getJoueurs(personnages), 1);
        if (joueurChoisi.isEmpty()) {
            System.out.println("Aucun joueur n'a été sélectionné.");
            return false;
        }
        Personnage perso = joueurChoisi.getFirst();
        if (perso == null) {
            System.out.println("Le joueur n'existe pas.");
            return false;
        }
        int soin = De.lance(10);
        perso.guerir(soin);
        return true;
    }
}
