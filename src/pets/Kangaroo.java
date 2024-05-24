package pets;

import constants.PetList;
import interfaces.OnFriendAttack;
import models.Pet;

public abstract class Kangaroo extends Pet implements OnFriendAttack {
    public Kangaroo() {
        super(PetList.KANGAROO, 2, 1, 2);
    }
}
