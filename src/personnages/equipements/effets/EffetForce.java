package personnages.equipements.effets;

import personnages.Personnage;

public class EffetForce implements Effet {
    private final int m_valeur;

    public EffetForce(int valeur) {
        m_valeur = valeur;
    }

    @Override
    public void appliquer(Personnage perso) {
        perso.setForce(perso.getForce() + m_valeur);
    }

    @Override
    public void retirer(Personnage perso) {
        perso.setForce(perso.getForce() - m_valeur);
    }
}
