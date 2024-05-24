package pets;

import constants.PetList;
import interfaces.OnFaint;
import models.Pet;

public abstract class Rat extends Pet implements OnFaint {
    public Rat() {
        super(PetList.RAT, 2, 3, 6);
    }
}
