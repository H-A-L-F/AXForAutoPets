package pets;

import constants.PetList;
import interfaces.OnBattleStart;
import interfaces.OnPlaced;
import models.Pet;

public abstract class Mosquito extends Pet implements OnBattleStart, OnPlaced {
    public Mosquito() {
        super(PetList.MOSQUITO, 1, 2, 2);
    }
}
