package pets;

import constants.PetList;
import interfaces.OnFaint;
import models.Pet;

public abstract class Cricket extends Pet implements OnFaint {
    public Cricket() {
        super(PetList.CRICKET, 1, 1, 2);
    }
}
