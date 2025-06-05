package personnages.equipements.effets;

import java.util.ArrayList;


public abstract class EffetsStandards {

    public static final ArrayList<Effet> ARMURE_LOURDE;
    static {
        ARMURE_LOURDE = new ArrayList<>();
        ARMURE_LOURDE.add(new EffetVitesse(-4));
    }

    public static final ArrayList<Effet> ARME_LOURDE;
    static {
        ARME_LOURDE = new ArrayList<>();
        ARME_LOURDE.add(new EffetForce(4));
        ARME_LOURDE.add(new EffetVitesse(-2));
    }

    public static final ArrayList<Effet> SANS_EFFET = new ArrayList<>();

    @Override
    public String toString() {
        return "Effets Standards";
    }
}
