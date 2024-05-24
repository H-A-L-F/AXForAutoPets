package pets;

import constants.PetList;
import models.Pet;

public abstract class Ant extends Pet {
    public Ant() {
        super(PetList.ANT, 1, 2, 2);
    }
}
