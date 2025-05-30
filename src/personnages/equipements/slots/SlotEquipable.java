package personnages.equipements.slots;

import personnages.equipements.Equipement;

public interface SlotEquipable {
    //Contrat: permet de récupérer/modifier le contenu de l'emplacement d’équipement associé d'un Personnage.
    Equipement get();
    void set(Equipement equip);
}