package personnages.equipements.sorts;

import personnages.Personnage;
import utils.De;

import java.util.ArrayList;

public class Guerison extends Sort{

    public Guerison() { super("Guérison"); }

    @Override
    public boolean lancer(ArrayList<Personnage> personnages) {
        ArrayList<Personnage> persoChoisis = choisirPersonnages(personnages, 1);
        if (persoChoisis.isEmpty()) {
            System.out.println("Aucun personnage n'a été sélectionné.");
            return false;
        }
        Personnage perso = persoChoisis.getFirst();
        if (perso == null) {
            System.out.println("Le personnage n'existe pas.");
            return false;
        }
        int soin = De.lance(10);
        perso.guerir(soin);
        return true;
    }
}
