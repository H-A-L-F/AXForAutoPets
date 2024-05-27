package pets;

import constants.PetList;
import models.Pet;

public abstract class Sheep extends Pet {
    public Sheep() {
        super(PetList.SHEEP, 3, 2, 2);
    }
}
