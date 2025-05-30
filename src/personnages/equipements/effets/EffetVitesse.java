package personnages.equipements.effets;

import personnages.Personnage;

public class EffetVitesse implements Effet {
    private final int m_valeur;

    public EffetVitesse(int valeur) {
        m_valeur = valeur;
    }

    @Override
    public void appliquer(Personnage perso) {
        perso.setVitesse(perso.getVitesse() + m_valeur);
    }

    @Override
    public void retirer(Personnage perso) {
        perso.setVitesse(perso.getVitesse() - m_valeur);
    }
}
