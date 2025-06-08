package personnages;

import personnages.classes.Classe;
import personnages.equipements.armes.Arme;
import personnages.equipements.armures.Armure;
import personnages.races.Race;
import affichage.Affichage;
import utils.De;

import static utils.Demande.demandeEntier;

public class Joueur extends Personnage{
    private final Race m_race;
    private final Classe m_classe;

    public Joueur(String nom, Race race, Classe classe){
        m_race = race;
        m_classe = classe;
        int pv = classe.getPv() + race.getPv();
        int force = race.getForce();
        int dexterite = race.getDexterite();
        int vitesse = race.getVitesse();
        int initiative = 0;
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
        String symbol = nom;
        if (nom.length() > 3){
            symbol = nom.substring(0, 3);
        }
        super(nom, symbol, TypePersonnage.JOUEUR, pv, force, dexterite, vitesse, initiative, new Arme("", 0, 0, false), new Armure("", 0, false), classe.getEquipements(), classe.getSorts());
    }

    public String getClasse(){
        //Renvoyer le nom de la classe.
        return m_classe.toString();
    }

    @Override
    public String sePresenter() {
        return Affichage.sePresenterJoueur(getNom(), m_race.toString(), m_classe.toString());
    }

    @Override
    public String getInfos() {
        return Affichage.getInfosJoueur(getNom(), m_race.toString(), m_classe.toString(), getPv(), getPvMax());
    }

    @Override
    public int getAction() {
        //Renvoyer l'entier correspondant à l'action à effectuer
        return demandeEntier(1, 4, Affichage.getActionJoueur(getNom(), getInitiative()));
    }

    @Override
    public String toString() {
        return Affichage.toStringJoueur(getNom(), m_race.toString(), m_classe.toString(), getPv(), getPvMax(), getForce(), getDexterite(), getVitesse(), getInventaire(), getArmure(), getArme());
        }
    }
