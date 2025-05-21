package personnages;

import personnages.classes.Classe;
import personnages.equipements.armes.Arme;
import personnages.equipements.Equipement;
import personnages.equipements.armures.Armure;
import personnages.equipements.sorts.Sort;
import personnages.races.Race;
import utils.De;

import java.util.ArrayList;

import static utils.Demande.demandeEntier;

public class Joueur extends Personnage{
    private final Race m_race;
    private final Classe m_classe;
    private final ArrayList<Equipement> m_inventaire;
    private final ArrayList<Sort> m_sorts;

    public Joueur(String nom, Race race, Classe classe){
        m_race = race;
        m_classe = classe;
        m_inventaire = classe.getEquipements();
        m_sorts = classe.getSorts();
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
        super(nom, symbol, pv, force, dexterite, vitesse, initiative, new Arme("", 0, 0, false), new Armure("", 0, false));
    }

    public void recuperer(Equipement equip){
        m_inventaire.add(equip);
    }

    public void equiper(){
        Equipement equip = choisirEquipement(); //Choisir l'équipement à équiper
        m_inventaire.remove(equip); //Supprimer l'équipement choisi de l'inventaire
        //Si l'équipement choisi est une armure
        if(equip.estArmure()){
            //Stocker l'armure actuellement équipée dans l'inventaire
            if (!m_armure.pasDefinie()){
                m_inventaire.add(m_armure);
                if (m_armure.estLourd()){
                    m_vitesse += 4;
                }
            }
            //Equiper l'armure
            m_armure = (Armure) equip;
            if (equip.estLourd()){
                m_vitesse -= 4;
            }
        }
        else {
            //Stocker l'arme actuellement équipée dans l'inventaire
            if (!m_arme.pasDefinie()){
                m_inventaire.add(m_arme);
                if (m_arme.estLourd()){
                    m_vitesse += 2;
                    m_force -= 4;
                }
            }
            //Equiper l'arme
            m_arme = (Arme) equip;
            if (equip.estLourd()){
                m_vitesse -= 2;
                m_force += 4;
            }
        }
    }

    private Equipement choisirEquipement(){
        StringBuilder msgEquipement = new StringBuilder("Entrez le numéro correspondant à l'equipement à equiper:\n");
        int compteur = 1;
        int n = m_inventaire.size();
        for(Equipement equip : m_inventaire){
            msgEquipement.append(compteur).repeat(" ", n/10).append("\t --> \t").append(equip).append("\n");
            compteur++;
        }
        int index = demandeEntier(1, n, msgEquipement.toString());
        return m_inventaire.get(index-1);
    }

    public Arme choisirArme(){
        StringBuilder msgArme = new StringBuilder("Entrez le numéro correspondant à l'arme à choisir:\n");
        ArrayList<Arme> armes = getArmes();
        int compteur = 1;
        int n = armes.size();
        for(Arme a: armes){
            msgArme.append(compteur).repeat(" ", n/10).append("\t --> \t").append(a).append("\n");
            compteur++;
        }
        int index = demandeEntier(1, n, msgArme.toString());
        return armes.get(index-1);
    }

    public boolean lancerSort(ArrayList<Personnage> personnages){
        Sort s = choisirSort();
        return s.lancer(personnages);
    }

    private Sort choisirSort(){
        StringBuilder msgSort = new StringBuilder("Entrez le numéro correspondant au sort à lancer:\n");
        int compteur = 1;
        int n = m_inventaire.size();
        for(Sort s: m_sorts){
            msgSort.append(compteur).repeat(" ", n/10).append("\t --> \t").append(s).append("\n");
            compteur++;
        }
        int index = demandeEntier(1, m_sorts.size(), msgSort.toString());
        return m_sorts.get(index-1);
    }

    public String getClasse(){
        //Renvoyer le nom de la classe.
        return m_classe.toString();
    }

    private ArrayList<Arme> getArmes(){
        ArrayList<Arme> armes = new ArrayList<>();
        armes.add(m_arme);
        for(Equipement equip : m_inventaire){
            if(!equip.estArmure()){
                armes.add((Arme)equip);
            }
        }
        return armes;
    }

    public String contenuInventaire(){
        String chaine = "";
        for (Equipement equipement : m_inventaire) {
            chaine += "\t › " + equipement.toString() + "\n";
        }
        return chaine;
    }

    public int getTailleInventaire(){
        return m_inventaire.size();
    }

    @Override
    public String sePresenter() {
        return m_nom + " (" + m_race + " " + m_classe.toString().toLowerCase() + ")";
    }

    @Override
    public String getInfos() {
        return  m_nom + " (" + m_race + " " + m_classe + ", " + m_pv + "/" + m_pvMax + ")";
    }

    @Override
    public int getAction() {
        String msgAction =
                m_nom + " il vous reste " + m_initiative + " actions, que souhaitez-vous faire ?\n" +
                """
                Attaquer:    1
                Se déplacer: 2
                S'équiper:   3
                Lancer sort: 4
                
                """;
        return demandeEntier(1, 4, msgAction);
    }

    @Override
    public boolean peutLancerSorts() {
        return !m_sorts.isEmpty();
    }

    @Override
    public String toString() {
        String chaine = m_nom + " (" + m_race.toString() + ", " + m_classe.toString() + ")\n"
                + "\tVie: " + m_pv + "/" + m_pvMax + "\n"
                + "\tArmure: ";
        if (m_armure.pasDefinie()){
            chaine += "aucune\n";
        }
        else {
            chaine += m_armure + "\n";
        }
        chaine += "\tArme: ";
        if (m_arme.pasDefinie()){
            chaine += "aucune\n";
        }
        else {
            chaine += m_arme + "\n";
        }
        chaine += "\tInventaire: [" + m_inventaire.size() + "]\n" + contenuInventaire();
        chaine += "\tForce: " + m_force + "\n";
        chaine += "\tDextérité: " + m_dexterite + "\n";
        chaine += "\tVitesse: " + m_vitesse + "\n";
        return chaine;
    }

    @Override
    public boolean estJoueur(){
        return true;
    }
}
