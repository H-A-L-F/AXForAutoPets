package pets;

import constants.PetList;
import interfaces.OnPlaced;
import interfaces.OnTurnEnd;
import models.Pet;

public abstract class Giraffe extends Pet implements OnTurnEnd, OnPlaced {
    public Giraffe() {
        super(PetList.GIRAFFE, 3, 1, 2);
    }
}
