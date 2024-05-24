package pets;

import constants.PetList;
import interfaces.OnFaint;
import models.Pet;

public abstract class Flamingo extends Pet implements OnFaint {
    public Flamingo() {
        super(PetList.FLAMINGO, 2, 3, 2);
    }
}
