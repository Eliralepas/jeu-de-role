package personnages.sorts;

import personnages.Joueur;
import personnages.Personnage;
import personnages.equipements.Equipement;
import personnages.equipements.armes.Arme;
import affichage.Affichage;
import personnages.equipements.TypeEquipement;

import java.util.ArrayList;

import static utils.Demande.demanderJoueurs;
import static utils.Demande.getJoueurs;

public class ArmeMagique extends Sort{

    public ArmeMagique(){ super(Affichage.nomArmeMagique()); }

    @Override
    public boolean lancer(ArrayList<Personnage> personnages) {
        //Choisir le joueur
        ArrayList<Joueur> joueurChoisi = demanderJoueurs(getJoueurs(personnages), 1);
        if (joueurChoisi.isEmpty()) {
            Affichage.aucuneSelection(Affichage.selectionJoueur());
            return false;
        }
        Joueur joueur = joueurChoisi.getFirst();
        if (joueur == null) {
            Affichage.joueurInexistant();
            return false;
        }
        //Choisir l'arme
        Equipement equip = joueur.choisirEquipementType(TypeEquipement.ARME);
        if (equip == null) {
            Affichage.armeInexistante();
            return false;
        }
        Arme arme = (Arme) equip;
        arme.addBonus(1);
        return true;
    }
}
