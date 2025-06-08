package personnages.equipements.slots;

import personnages.Personnage;
import personnages.equipements.Equipement;
import personnages.equipements.armes.Arme;

public class SlotArme implements SlotEquipable{
    private final Personnage m_perso;

    public SlotArme(Personnage perso) {
        m_perso = perso;
    }

    @Override
    public Equipement get() {
        return m_perso.getArme();
    }

    @Override
    public void set(Equipement equip) {
        m_perso.setArme((Arme) equip);
    }

    @Override
    public String toString() {
        return "Slot Arme";
    }
}
