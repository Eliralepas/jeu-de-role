package personnages.classes;

import personnages.equipements.Equipement;
import personnages.equipements.armes.Baton;
import personnages.equipements.armes.Fronde;
import personnages.sorts.ArmeMagique;
import personnages.sorts.BoogieWoogie;
import personnages.sorts.Guerison;
import personnages.sorts.Sort;
import affichage.Affichage;

import java.util.ArrayList;

public class Magicien extends Classe{

    public Magicien() {
        ArrayList<Equipement> equipements = new ArrayList<>();
        equipements.add(new Baton());
        equipements.add(new Fronde());

        ArrayList<Sort> sorts = new ArrayList<>();
        sorts.add(new Guerison());
        sorts.add(new BoogieWoogie());
        sorts.add(new ArmeMagique());

        super(Affichage.nomMagicien(), 16, equipements, sorts);
    }
}
