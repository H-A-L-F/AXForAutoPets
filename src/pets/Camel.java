package pets;

import constants.PetList;
import models.Pet;

public abstract class Camel extends Pet {
    public Camel() {
        super(PetList.CAMEL, 3, 3, 4);
    }
}
