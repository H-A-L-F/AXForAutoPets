package pets;

import constants.PetList;
import interfaces.OnFriendEatFruit;
import interfaces.OnPlaced;
import models.Pet;

public abstract class Rabbit extends Pet implements OnFriendEatFruit, OnPlaced {
    public Rabbit() {
        super(PetList.RABBIT, 3, 1, 2);
    }
}
