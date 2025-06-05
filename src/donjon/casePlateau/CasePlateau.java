package donjon.casePlateau;

import affichage.Affichage;
import utils.TypeCase;

import static donjon.casePlateau.Symbols.CASE_EQUIPEMENT;
import static donjon.casePlateau.Symbols.CASE_VIDE;

public class CasePlateau {
    private final String m_case;
    private int m_colonne;
    private int m_ligne;

    public CasePlateau(String c){
        m_case = c;
        if(estBonFormat()){
            convertirString();
        }
    }

    private void convertirString(){
        m_colonne = m_case.charAt(0) - 65; // On veut que la lettre A représente l'index 0 et 'A' = 65 donc index = lettre - 65.
        if (m_case.length() == 2){
            m_ligne = m_case.charAt(1) - 49; // On veut que le chiffre 1 représente l'index 0 et '1' = 49 donc index = lettre - 49.
        }
        else{ //Si n == 3
            m_ligne = (m_case.charAt(1)-48)* 10 + (m_case.charAt(2)-49);
        }
    }

    public boolean estValide(String[][] plateau, TypeCase action){
        //Vérifie si la case choisie est valide
        if (!estBonFormat()){
            Affichage.mauvaisFormatCase();
            return false;
        }
        int x = m_colonne;
        int y = m_ligne;
        int nbColonnes = plateau.length;
        int nbLignes = plateau[0].length;
        if (0 > x || x > nbColonnes -1 || 0 > y || y > nbLignes -1){
            Affichage.caseInexistante();
            return false;
        }
        boolean estValide = switch (action) {
            case AJOUT -> plateau[y][x].equals(CASE_VIDE);
            case DEPLACEMENT -> plateau[y][x].equals(CASE_VIDE) || plateau[y][x].equals(CASE_EQUIPEMENT);
        };
        if(!estValide){
            Affichage.caseInvalide();
            return false;
        }
        return true;
    }

    private boolean estBonFormat(){
        //Renvoyer true si la case est au bon format.
        int n = m_case.length();
        return (2 <= n && n <= 3) && (estLettre(m_case.charAt(0)) && estNombre(m_case.substring(1, n)));
    }

    private boolean estLettre(char c){
        return (65 <= c && c <= 90);
    }

    private boolean estChiffre(char c){
        return (48 <= c && c <= 57);
    }

    private boolean estNombre(String s){
        if (s.length() == 1){
            return estChiffre(s.charAt(0));
        }
        return (estChiffre(s.charAt(0)) && estChiffre(s.charAt(1)));
    }

    public int getColonne(){
        //Renvoyer la colonne correspondante.
        return m_colonne;
    }

    public int getLigne(){
        //Renvoyer la ligne correspondante.
        return  m_ligne;
    }

    @Override
    public String toString() {
        return m_case;
    }
}
