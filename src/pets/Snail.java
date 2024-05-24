package pets;

import constants.PetList;
import interfaces.OnTurnEnd;
import models.Pet;

public abstract class Snail extends Pet implements OnTurnEnd {
    public Snail() {
        super(PetList.SNAIL, 2, 2, 2);
    }
}
