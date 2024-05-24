package pets;

import constants.PetList;
import interfaces.OnTurnStart;
import models.Pet;

public abstract class Swan extends Pet implements OnTurnStart {
    public Swan() {
        super(PetList.SWAN, 2, 1, 2);
    }
}
