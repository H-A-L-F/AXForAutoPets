package pets;

import constants.PetList;
import interfaces.OnFriendFaint;
import interfaces.OnPlaced;
import models.Pet;

public abstract class Ox extends Pet implements OnFriendFaint, OnPlaced {
    public Ox() {
        super(PetList.OX, 3, 1, 3);
    }
}
