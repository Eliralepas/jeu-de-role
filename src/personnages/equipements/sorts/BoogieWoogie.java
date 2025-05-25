package personnages.equipements.sorts;

import donjon.pion.Pion;
import personnages.Personnage;

import java.util.ArrayList;

import static utils.Demande.demanderPersonnagesFilter;

public class BoogieWoogie extends Sort{

    public BoogieWoogie(){ super("Boogie Woogie"); }

    @Override
    public boolean lancer(ArrayList<Personnage> personnages) {
        ArrayList<Personnage> persoChoisis = demanderPersonnagesFilter(personnages, 2);
        if (personnages.isEmpty()) {
            System.out.println("Aucun personnage n'a été sélectionné.");
            return false;
        }
        Personnage p1 = persoChoisis.getFirst();
        Personnage p2 = persoChoisis.getLast();
        if (p1 == null || p2 == null) {
            System.out.println("Un des personnages sélectionnés n'existe pas.");
            return false;
        }
        if (p1.equals(p2)) {
            System.out.println("Vous ne pouvez pas choisir le même personnage.");
            return false;
        }
        Pion pionP1 = new Pion(p1.getPion());
        p1.seDeplacer(p2.getPion());
        p2.seDeplacer(pionP1);
        return true;
    }
}
