package pets;

import constants.PetList;
import interfaces.OnSummon;
import models.Pet;

public abstract class Horse extends Pet implements OnSummon {
    public Horse() {
        super(PetList.HORSE, 1, 2, 1);
    }
}
