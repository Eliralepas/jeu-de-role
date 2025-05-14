package affichage;
import donjon.Donjon;
import jeu.Jeu;
import personnages.Joueur;
import personnages.Personnage;

public class Affichage {
    private Donjon m_donjon;
    private Jeu m_jeu;
    private static String[] indexPlato = new String[]{
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    public Affichage(Donjon donjon, Jeu jeu){
        m_donjon = donjon;
        m_jeu = jeu;
    }

    public String afficherPlato(){
        String affichage = "      ";
        //le haut du affichage
        int largeur = m_donjon.getLargeur();
        for (int i=0; i<largeur; i++) {
            affichage += indexPlato[i] + "  ";
        }
        affichage += "\n   *-";
        String ligne= "----";
        for (int i=0; i<largeur; i++){
            affichage += ligne;
        }
        affichage+= "-*\n";

        //chaque ligne du plateau
        String[][] plateau = m_donjon.getPlateau();

        int longueur = m_donjon.getLongueur();
        for (int i=0; i<longueur; i++){
            affichage+= i+"  | ";
            for (int j=0; j<largeur; j++){
                affichage+= plateau[j][i];
            }
            affichage+="\n";
        }

        affichage += "\n   *-";
        for (int i=0; i<largeur; i++){
            affichage += ligne;
        }
        affichage+= "-*\n";

        //Affichage de la légende
        affichage+="    * Equipement   |   [ ] Obstacle  |";
        return affichage;
    }

    //A refaire
    public String affichageTour(){
        //Affichage du numéro du Donjon et le joueur
        String affiche = "*********************************************************************************\n" +
             "\t\t\tDonjon "+m_donjon.getNumero()+":\n";
        affiche+="\t\t";
        Joueur joueurActuelle = m_jeu.getJoueurActuel();
        affiche+=joueurActuelle.getNom()+" ("+joueurActuelle.getRace()+" "+joueurActuelle.getClasse().toLowerCase()+")\n";
        affiche+= "*********************************************************************************\n\n";

        //Affichage de la n-ième tour et l'ordre des personnages
        affiche+="\t\tTour "+m_jeu.getNumeroTour()+":\n";
        for(Joueur joueur: m_jeu.getJoueurs()){
            if (joueur.equals(joueurActuelle)) {
                affiche += "\t->\t" + joueur.getPion().toString() + "\t" + joueur.getNom() + " (" + joueur.getRace() + " " + joueur.getClasse().toLowerCase() + ", "+joueur.getPvAffichage()+")\n";
            }
            else{
                affiche += "\t\t" + joueur.getPion().toString() + "\t" + joueur.getNom() + " (" + joueur.getRace() + " " + joueur.getClasse().toLowerCase() + ", "+joueur.getPvAffichage()+")\n";
            }
        }
         return affiche+"\n";
    }

    public String afficheJoueurDetails(){
        return m_jeu.getJoueurActuel().toString();
    }

}
