package personnages.equipements.sorts;

import personnages.Joueur;
import personnages.Personnage;
import personnages.equipements.Equipement;

import java.util.ArrayList;

import static utils.Demande.demandeEntier;


public abstract class Sort {
    protected String m_nom;
    protected ArrayList<Personnage> m_personnages;

    public Sort(String nom){
        m_nom = nom;
    }

    public abstract boolean lancer(ArrayList<Personnage> personnages);

    public static ArrayList<Personnage> choisirPersonnages(ArrayList<Personnage> personnages, int nbPerso){
        ArrayList<Personnage> persoChoisis = new ArrayList<>();
        int n = personnages.size();
        StringBuilder msgDemande = new StringBuilder("Entrez le numéro correspondant au personnage à choisir:\n");
        int compteur = 1;
        for(Personnage p : personnages){
            msgDemande.append(compteur).repeat(" ", n/10).append("\t --> \t").append(p.sePresenter()).append("\n");
            compteur++;
        }
        for(int i=0; i<nbPerso; i++){
            int index = demandeEntier(1, n, msgDemande.toString());
            persoChoisis.add(personnages.get(index-1));
        }
        return persoChoisis;
    }

    public static ArrayList<Joueur> choisirJoueurs(ArrayList<Joueur> joueurs, int nbPerso){
        ArrayList<Joueur> joueursChoisis = new ArrayList<>();
        int n = joueurs.size();
        StringBuilder msgDemande = new StringBuilder("Entrez le numéro correspondant au joueur à choisir:\n");
        int compteur = 1;
        for(Personnage p : joueurs){
            msgDemande.append(compteur).repeat(" ", n/10).append("\t --> \t").append(p.sePresenter()).append("\n");
            compteur++;
        }
        for(int i=0; i<nbPerso; i++){
            int index = demandeEntier(1, n, msgDemande.toString());
            joueursChoisis.add(joueurs.get(index-1));
        }
        return joueursChoisis;
    }

    public static ArrayList<Joueur> getJoueurs(ArrayList<Personnage> personnages){
        ArrayList<Joueur> joueurs = new ArrayList<>();
        for(Personnage p : personnages){
            if(p.estJoueur()){
                joueurs.add((Joueur)p);
            }
        }
        return joueurs;
    }

    @Override
    public String toString() {
        return m_nom;
    }
}
