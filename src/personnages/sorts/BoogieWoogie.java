package personnages.sorts;

import donjon.pion.Pion;
import personnages.Personnage;
import affichage.Affichage;

import java.util.ArrayList;

import static utils.Demande.demanderPersonnagesFilter;

public class BoogieWoogie extends Sort{

    public BoogieWoogie(){ super(Affichage.nomBoogieWoogie()); }

    @Override
    public boolean lancer(ArrayList<Personnage> personnages) {
        ArrayList<Personnage> persoChoisis = demanderPersonnagesFilter(personnages, 2);
        if (personnages.isEmpty()) {
            Affichage.aucuneSelection(Affichage.selectionPersonnage());
            return false;
        }
        Personnage p1 = persoChoisis.getFirst();
        Personnage p2 = persoChoisis.getLast();
        if (p1 == null || p2 == null) {
            Affichage.personnageInexistant();
            return false;
        }
        if (p1.equals(p2)) {
            Affichage.memePersonnage();
            return false;
        }
        Pion pionP1 = new Pion(p1.getPion());
        p1.seDeplacer(p2.getPion());
        p2.seDeplacer(pionP1);
        return true;
    }
}
