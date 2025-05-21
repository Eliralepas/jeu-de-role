package personnages.equipements.sorts;

import personnages.Joueur;
import personnages.Personnage;
import personnages.equipements.armes.Arme;

import java.util.ArrayList;

import static utils.Demande.demanderJoueurs;
import static utils.Demande.getJoueurs;

public class ArmeMagique extends Sort{

    public ArmeMagique(){ super("Arme magique"); }

    @Override
    public boolean lancer(ArrayList<Personnage> personnages) {
        //Choisir le joueur
        ArrayList<Joueur> joueurChoisi = demanderJoueurs(getJoueurs(personnages), 1);
        if (joueurChoisi.isEmpty()) {
            System.out.println("Aucun personnage n'a été sélectionné.");
            return false;
        }
        Joueur joueur = joueurChoisi.getFirst();
        if (joueur == null) {
            System.out.println("Le joueur n'existe pas.");
            return false;
        }
        //Choisir l'arme
        Arme arme = joueur.choisirArme();
        if (arme == null) {
            System.out.println("L'arme n'existe pas.");
            return false;
        }
        arme.addBonus(1);
        return true;
    }
}
