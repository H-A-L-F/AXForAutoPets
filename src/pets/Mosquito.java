package pets;

import constants.PetList;
import interfaces.OnBattleStart;
import models.Pet;

public abstract class Mosquito extends Pet implements OnBattleStart {
    public Mosquito() {
        super(PetList.MOSQUITO, 1, 2, 2);
    }
}
