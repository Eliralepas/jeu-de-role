package personnages;

import personnages.classes.Classe;
import personnages.equipements.armes.Arme;
import personnages.equipements.Equipement;
import personnages.equipements.armures.Armure;
import personnages.races.Race;
import utils.De;

import java.util.ArrayList;

public class Joueur extends Personnage{
    private final Race m_race;
    private final Classe m_classe;
    private final ArrayList<Equipement> m_inventaire;

    public Joueur(String nom, Race race, Classe classe){
        m_race = race;
        m_classe = classe;
        m_inventaire = classe.getEquipements();
        int pv = classe.getPv();
        int force = 0;
        int dexterite = 0;
        int vitesse = 0;
        int initiative = 0;
        race.setAttributs(pv, force, dexterite, vitesse, initiative);
        for(int i=0; i<4; i++){
            force += De.lance(4);
            dexterite += De.lance(4);
            vitesse += De.lance(4);
            initiative += De.lance(4);
        }
        force += 3;
        dexterite += 3;
        vitesse += 3;
        initiative += 3;
        super(nom, pv, force, dexterite, vitesse, initiative, new Arme("", 0, 0, false), new Armure("", 0, false));
    }
}
