package pets;

import constants.PetList;
import interfaces.OnTurnStart;
import models.Pet;

public abstract class Worm extends Pet implements OnTurnStart {
    public Worm() {
        super(PetList.WORM, 2, 1, 2);
    }
}
