package pets;

import constants.PetList;
import models.Pet;

public abstract class Badger extends Pet {
    public Badger() {
        super(PetList.BADGER, 3, 6, 3);
    }
}
