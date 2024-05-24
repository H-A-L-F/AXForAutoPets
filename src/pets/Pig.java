package pets;

import constants.PetList;
import models.Pet;

public abstract class Pig extends Pet {
    public Pig() {
        super(PetList.PIG, 1, 4, 1);
    }
}
