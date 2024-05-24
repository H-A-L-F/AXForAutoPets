package pets;

import constants.PetList;
import interfaces.OnSell;
import models.Pet;

public abstract class Beaver extends Pet implements OnSell {
    public Beaver() {
        super(PetList.BEAVER, 1, 3, 2);
    }
}
