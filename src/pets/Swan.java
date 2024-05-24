package pets;

import constants.PetList;
import interfaces.OnPlaced;
import interfaces.OnTurnStart;
import models.Pet;

public abstract class Swan extends Pet implements OnTurnStart, OnPlaced {
    public Swan() {
        super(PetList.SWAN, 2, 1, 2);
    }
}
