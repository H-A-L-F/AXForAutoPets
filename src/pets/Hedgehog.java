package pets;

import constants.PetList;
import models.Pet;

public abstract class Hedgehog extends Pet {
    public Hedgehog() {
        super(PetList.HEDGEHOG, 2, 3, 2);
    }
}
