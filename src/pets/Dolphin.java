package pets;

import constants.PetList;
import interfaces.OnBattleStart;
import interfaces.OnPlaced;
import models.Pet;

public abstract class Dolphin extends Pet implements OnBattleStart, OnPlaced {
    public Dolphin() {
        super(PetList.DOLPHIN, 3, 4, 3);
    }
}
