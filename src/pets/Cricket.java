package pets;

import constants.PetList;
import models.Pet;

public abstract class Cricket extends Pet {
    public Cricket() {
        super(PetList.CRICKET, 1, 1, 2);
    }
}
