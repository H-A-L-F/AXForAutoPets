package pets;

import constants.PetList;
import models.Pet;

public abstract class Flamingo extends Pet {
    public Flamingo() {
        super(PetList.FLAMINGO, 2, 3, 2);
    }
}
