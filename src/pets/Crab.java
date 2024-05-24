package pets;

import constants.PetList;
import interfaces.OnBattleStart;
import models.Pet;

public abstract class Crab extends Pet implements OnBattleStart {
    public Crab() {
        super(PetList.CRAB, 2, 4, 1);
    }
}
