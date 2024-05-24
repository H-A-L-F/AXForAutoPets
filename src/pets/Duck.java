package pets;

import constants.PetList;
import models.Pet;

public abstract class Duck extends Pet {
    public Duck() {
        super(PetList.DUCK, 1, 2, 3);
    }
}
