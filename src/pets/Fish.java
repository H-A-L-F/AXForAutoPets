package pets;

import constants.PetList;
import models.Pet;

public abstract class Fish extends Pet implements OnLevelup {
    public Fish() {
        super(PetList.FISH, 1, 2, 3);
    }
}
