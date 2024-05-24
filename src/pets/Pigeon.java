package pets;

import constants.PetList;
import interfaces.OnSell;
import models.Pet;

public abstract class Pigeon extends Pet implements OnSell {
    public Pigeon() {
        super(PetList.PIGEON, 1, 3, 1);
    }
}
