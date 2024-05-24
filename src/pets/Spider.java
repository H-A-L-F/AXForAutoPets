package pets;

import constants.PetList;
import models.Pet;

public abstract class Spider extends Pet {
    public Spider() {
        super(PetList.SPIDER, 2, 2, 2);
    }
}
