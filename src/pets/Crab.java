package pets;

import constants.PetList;
import interfaces.OnBattleStart;
import interfaces.OnPlaced;
import models.Pet;

public abstract class Crab extends Pet implements OnBattleStart, OnPlaced {
    public Crab() {
        super(PetList.CRAB, 2, 4, 1);
    }
}
