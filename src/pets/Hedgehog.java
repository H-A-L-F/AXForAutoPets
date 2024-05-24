package pets;

import constants.PetList;
import interfaces.OnFaint;
import models.Pet;

public abstract class Hedgehog extends Pet implements OnFaint {
    public Hedgehog() {
        super(PetList.HEDGEHOG, 2, 3, 2);
    }
}
