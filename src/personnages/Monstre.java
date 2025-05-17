package personnages;

import personnages.equipements.armes.Arme;
import personnages.equipements.armures.Armure;

public class Monstre extends Personnage{
    private final int m_numero;

    public Monstre(String espece, String symbol, int numero, int pv, int force, int dexterite, int vitesse, int amplitudeDegats, int portee, int classeArmure){
        super(espece, symbol, pv, force, dexterite, vitesse, 0, new Arme("attaque", amplitudeDegats, portee, false), new Armure("armure", classeArmure, false));
        m_numero = numero;
    }

    @Override
    public String toString() {
        String chaine = m_nom;
        // Si m_numero != 1, alors un autre monstre avec le même nom d'espèce existe déjà, il faut les différencier.
        if (m_numero != 1){
            // Ajouter le numéro du monstre après son nom d'espèce
            chaine += " " + m_numero;
        }
        return chaine;
    }
}
