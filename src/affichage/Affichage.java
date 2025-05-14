package affichage;
import donjon.Donjon;

public class Affichage {
    private Donjon m_donjon;
    private static String[] indexPlato = new String[]{
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    public Affichage(Donjon donjon){
        m_donjon = donjon;
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

}
