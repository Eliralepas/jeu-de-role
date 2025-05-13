package donjon;

import donjon.pions.Pion;

import java.util.ArrayList;

public class Donjon {
    private String[][] m_plateau;
    private ArrayList<Pion> m_pions;
    private int m_longueur;
    private int m_largeur;

    public Donjon(int longueur, int largeur){
        m_longueur = longueur;
        m_largeur = largeur;
        m_plateau = new String[longueur][largeur];
        m_pions = new ArrayList<Pion>();
    }

    public void remplir(){
        for(int i=0; i<m_longueur; i++){
            for (int j=0; j<m_largeur; j++){
                m_plateau[i][j] = " . ";
            }
        }
    }

    public void ajouterPion(Pion p){
        m_pions.add(p);
    }

    public void supprimerPion(Pion p){
        m_pions.remove(p);
    }

    private void afficherPions(){
        for(Pion p: m_pions){
            int x = p.getX();
            int y = p.getY();
            m_plateau[x][y] = p.toString();
        }
    }
}
