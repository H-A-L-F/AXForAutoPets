package pets;

import constants.PetList;
import models.Pet;

public abstract class Duck extends Pet implements OnSell {
    public Duck() {
        super(PetList.DUCK, 1, 2, 3);
    }
}
