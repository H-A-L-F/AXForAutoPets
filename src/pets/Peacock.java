package pets;

import constants.PetList;
import models.Pet;

public abstract class Peacock extends Pet {
    public Peacock() {
        super(PetList.PEACOCK, 2, 2, 5);
    }
}
