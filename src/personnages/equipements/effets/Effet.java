package personnages.equipements.effets;

import personnages.Personnage;

public interface Effet {
    //Contrat : permet d'augmenter/diminuer d'un certain montant un attribut d’un Personnage (comme vie, force, vitesse, etc...)
    void appliquer(Personnage perso);
    void retirer(Personnage perso);
    String toString();
}
