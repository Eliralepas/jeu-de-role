package personnages.classes;

import personnages.equipements.Equipement;
import personnages.equipements.armes.Arbalete;
import personnages.equipements.armes.Masse;
import personnages.equipements.armures.ArmureEcailles;
import personnages.sorts.Guerison;
import personnages.sorts.Sort;
import affichage.Affichage;

import java.util.ArrayList;

public class Clerc extends Classe{

    public Clerc() {
        ArrayList<Equipement> equipements = new ArrayList<>();
        equipements.add(new Masse());
        equipements.add(new ArmureEcailles());
        equipements.add(new Arbalete());

        ArrayList<Sort> sorts = new ArrayList<>();
        sorts.add(new Guerison());

        super(Affichage.nomClerc(), 16, equipements, sorts);
    }
}
