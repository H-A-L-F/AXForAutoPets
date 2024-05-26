package pets;

import constants.PetList;
import interfaces.OnBattleStart;
import interfaces.OnPlaced;
import models.Pet;

public abstract class Dodo extends Pet implements OnBattleStart, OnPlaced {
    public Dodo() {
        super(PetList.DODO, 3, 4, 2);
    }
}
