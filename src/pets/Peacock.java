package pets;

import constants.PetList;
import interfaces.OnHurt;
import models.Pet;

public abstract class Peacock extends Pet implements OnHurt {
    public Peacock() {
        super(PetList.PEACOCK, 2, 2, 5);
    }
}
