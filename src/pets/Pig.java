package pets;

import constants.PetList;
import interfaces.OnSell;
import models.Pet;

public abstract class Pig extends Pet implements OnSell {
    public Pig() {
        super(PetList.PIG, 1, 4, 1);
    }
}
