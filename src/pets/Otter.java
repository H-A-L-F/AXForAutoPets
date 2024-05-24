package pets;

import constants.PetList;
import models.Pet;

public abstract class Otter extends Pet {
    public Otter() {
        super(PetList.OTTER, 1, 1, 3);
    }
}
