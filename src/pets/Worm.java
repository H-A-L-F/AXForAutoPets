package pets;

import constants.PetList;
import interfaces.OnPlaced;
import interfaces.OnTurnStart;
import models.Pet;

public abstract class Worm extends Pet implements OnTurnStart, OnPlaced {
    public Worm() {
        super(PetList.WORM, 2, 1, 2);
    }
}
