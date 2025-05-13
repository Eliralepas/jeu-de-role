package donjon;

public class Donjon {
    public String[][] m_plateau;
    private int m_longueur;
    private int m_largeur;

    public Donjon(int longueur, int largeur){
        m_longueur = longueur;
        m_largeur = largeur;
        m_plateau = new String[longueur][largeur];
    }

    public void remplir(){
        for(int i=0; i<m_longueur; i++){
            for (int j=0; j<m_largeur; j++){
                m_plateau[i][j] = " . ";
            }
        }
    }

    public void ajouter(String symbol){
        
    }


}
