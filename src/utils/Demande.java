package utils;

import affichage.Affichage;
import personnages.Joueur;
import personnages.Personnage;

import java.util.ArrayList;

public abstract class Demande {

    public static int demandeEntier(int min, int max, String msgDemande) throws NumberFormatException{
        int entier = min - 1;
        String msgErreur = Affichage.intervalleEntier(min, max);
        while (!(min <= entier && entier <= max)) {
            System.out.println(msgDemande);
            String reponse = System.console().readLine();
            try {
                entier = Integer.parseInt(reponse);
                if(!(min <= entier && entier <= max)){
                    System.out.println(msgErreur);
                }
            } catch (NumberFormatException e) {
                System.out.println(msgErreur);
            }
        }
        return entier;
    }

    public static String demandeString(String msgDemande, int tailleMax){
        String chaine = "";
        while(chaine.isEmpty()){
            System.out.println(msgDemande);
            chaine = System.console().readLine();
            if (chaine.length() > tailleMax){
                chaine = "";
            }
        }
        return chaine;
    }

    public static ArrayList<Personnage> demanderPersonnages(ArrayList<Personnage> personnages, int nbPerso){
        ArrayList<Personnage> persoChoisis = new ArrayList<>();
        int n = personnages.size();
        StringBuilder msgDemande = new StringBuilder(Affichage.demandeElement(Affichage.selectionPersonnage()));
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

    public static ArrayList<Personnage> demanderPersonnagesWithoutSelf(ArrayList<Personnage> personnages, int nbPerso, Personnage perso){
        ArrayList<Personnage> listePersos = new ArrayList<>(personnages);
        listePersos.remove(perso);
        return demanderPersonnages(listePersos, nbPerso);
    }

    public static ArrayList<Personnage> demanderPersonnagesFilter(ArrayList<Personnage> personnages, int nbPerso){
        ArrayList<Personnage> listePersos = new ArrayList<>(personnages);
        ArrayList<Personnage> persoChoisis = new ArrayList<>();
        int n = personnages.size();
        for(int i=0; i<nbPerso; i++){
            StringBuilder msgDemande = new StringBuilder(Affichage.demandePersonnageNumero(i+1));
            int compteur = 1;
            for(Personnage p : listePersos){
                msgDemande.append(compteur).repeat(" ", n/10).append("\t --> \t").append(p.sePresenter()).append("\n");
                compteur++;
            }
            int index = demandeEntier(1, n, msgDemande.toString());
            Personnage persoChoisi = listePersos.get(index-1);
            persoChoisis.add(persoChoisi);
            listePersos.remove(index-1);
        }
        return persoChoisis;
    }

    public static ArrayList<Joueur> demanderJoueurs(ArrayList<Joueur> joueurs, int nbPerso){
        ArrayList<Joueur> joueursChoisis = new ArrayList<>();
        int n = joueurs.size();
        StringBuilder msgDemande = new StringBuilder(Affichage.demandeElement(Affichage.selectionJoueur()));
        int compteur = 1;
        for(Joueur j : joueurs){
            msgDemande.append(compteur).repeat(" ", n/10).append("\t --> \t").append(j.sePresenter()).append("\n");
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
        for(Personnage perso : personnages){
            if(perso.getType() == TypePersonnage.JOUEUR){
                joueurs.add((Joueur)perso);
            }
        }
        return joueurs;
    }

    @Override
    public String toString() {
        return "Demande";
    }
}
