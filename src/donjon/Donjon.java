package donjon;

import donjon.pions.Pion;
import personnages.Personnage;

import java.util.ArrayList;

public class Donjon {
    private final int m_numero;
    private String[][] m_plateau;
    private int m_longueur;
    private int m_largeur;
    private ArrayList<Pion> m_obstacles;
    private ArrayList<Pion> m_equipements;
    private ArrayList<Pion> m_personnages;

    public Donjon(int numero, int longueur, int largeur){
        m_numero = numero;
        m_longueur = longueur;
        m_largeur = largeur;
        m_plateau = new String[longueur][largeur];
        m_obstacles = new ArrayList<Pion>();
        m_equipements = new ArrayList<Pion>();
        m_personnages = new ArrayList<Pion>();
    }

    public void remplir(){
        for(int i=0; i<m_longueur; i++){
            for (int j=0; j<m_largeur; j++){
                m_plateau[i][j] = " . ";
            }
        }
    }

    public void ajouterElement(int x, int y, String element){
        m_plateau[x][y] = element;
    }

    public void ajouterPersonnage(Personnage perso, int x, int y){
        m_personnages.add(perso.getPion());
    }

    public void supprimerPersonnage(Personnage perso){
        m_personnages.remove(perso.getPion());
    }

    private void afficherPersonnages(){
        for(Pion p: m_personnages){
            int x = p.getX();
            int y = p.getY();
            m_plateau[x][y] = p.toString();
        }
    }
}
