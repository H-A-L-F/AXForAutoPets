package pets;

import constants.PetList;
import interfaces.OnFriendAttack;
import interfaces.OnPlaced;
import models.Pet;

public abstract class Kangaroo extends Pet implements OnFriendAttack, OnPlaced {
    public Kangaroo() {
        super(PetList.KANGAROO, 2, 1, 2);
    }
}
