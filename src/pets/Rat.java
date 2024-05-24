package pets;

import constants.PetList;
import models.Pet;

public abstract class Rat extends Pet {
    public Rat() {
        super(PetList.RAT, 2, 3, 6);
    }
}
