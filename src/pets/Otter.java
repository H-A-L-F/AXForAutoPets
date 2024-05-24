package pets;

import constants.PetList;
import interfaces.OnPurchase;
import models.Pet;

public abstract class Otter extends Pet implements OnPurchase {
    public Otter() {
        super(PetList.OTTER, 1, 1, 3);
    }
}
