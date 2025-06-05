package personnages.equipements.slots;

import personnages.Personnage;
import personnages.equipements.Equipement;
import personnages.equipements.armures.Armure;

public class SlotArmure implements SlotEquipable{
    private final Personnage m_perso;

    public SlotArmure(Personnage perso) {
        m_perso = perso;
    }

    @Override
    public Equipement get() {
        return m_perso.getArmure();
    }

    @Override
    public void set(Equipement equip) {
        m_perso.setArmure((Armure) equip);
    }

    @Override
    public String toString() {
        return "Slot Armure";
    }
}
