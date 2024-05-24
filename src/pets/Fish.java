package pets;

import constants.PetList;
import models.Pet;

public abstract class Fish extends Pet {
    public Fish() {
        super(PetList.FISH, 1, 2, 3);
    }
}
