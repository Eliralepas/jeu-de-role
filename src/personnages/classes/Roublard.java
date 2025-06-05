package personnages.classes;

import personnages.equipements.Equipement;
import personnages.equipements.armes.Arc;
import personnages.equipements.armes.Rapiere;
import affichage.Affichage;

import java.util.ArrayList;

public class Roublard extends Classe{

    public Roublard() {
        ArrayList<Equipement> equipements = new ArrayList<>();
        equipements.add(new Rapiere());
        equipements.add(new Arc());
        super(Affichage.nomRoublard(), 16, equipements);
    }
}
