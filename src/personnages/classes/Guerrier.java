package personnages.classes;

import personnages.equipements.Equipement;
import personnages.equipements.armes.Arbalete;
import personnages.equipements.armes.EpeeLongue;
import personnages.equipements.armures.CotteDeMailles;
import affichage.Affichage;

import java.util.ArrayList;

public class Guerrier extends Classe{

    public Guerrier() {
        ArrayList<Equipement> equipements = new ArrayList<>();
        equipements.add(new CotteDeMailles());
        equipements.add(new EpeeLongue());
        equipements.add(new Arbalete());
        super(Affichage.nomGuerrier(), 20, equipements);
    }
}
