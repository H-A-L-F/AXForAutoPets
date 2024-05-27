package pets;

import constants.PetList;
import interfaces.OnPlaced;
import interfaces.OnSummon;
import models.Pet;

public abstract class Dog extends Pet implements OnSummon, OnPlaced {
    public Dog() {
        super(PetList.DOG, 3, 3, 2);
    }
}
