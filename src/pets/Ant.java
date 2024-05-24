package pets;

import constants.PetList;
import interfaces.OnFaint;
import interfaces.OnPlaced;
import models.Pet;

public abstract class Ant extends Pet implements OnFaint {
    public Ant() {
        super(PetList.ANT, 1, 2, 2);
    }
}
