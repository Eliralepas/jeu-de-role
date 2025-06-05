package personnages;

import personnages.equipements.armes.Arme;
import personnages.equipements.armures.Armure;
import utils.TypePersonnage;

import java.util.ArrayList;

public class Monstre extends Personnage{
    private final int m_numero;

    public Monstre(String espece, String symbol, int numero, int pv, int force, int dexterite, int vitesse, int amplitudeDegats, int portee, int classeArmure){
        super(espece, symbol, TypePersonnage.MONSTRE, pv, force, dexterite, vitesse, 0, new Arme("attaque monstre", amplitudeDegats, portee, false), new Armure("armure monstre", classeArmure, false), new ArrayList<>(), new ArrayList<>());
        m_numero = numero;
    }

    @Override
    public String toString() {
        String chaine = getNom();
        // Si m_numero != 1, alors un autre monstre avec le même nom d'espèce existe déjà, il faut les différencier.
        if (m_numero != 1){
            // Ajouter le numéro du monstre après son nom d'espèce
            chaine += " " + m_numero;
        }
        return chaine;
    }
}
