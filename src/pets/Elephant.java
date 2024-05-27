package pets;

import constants.PetList;
import models.Pet;

public abstract class Elephant extends Pet {
    public Elephant() {
        super(PetList.ELEPHANT, 3, 3, 7);
    }
}
