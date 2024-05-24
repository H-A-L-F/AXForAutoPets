package pets;

import constants.PetList;
import interfaces.OnFaint;
import models.Pet;

public abstract class Spider extends Pet implements OnFaint {
    public Spider() {
        super(PetList.SPIDER, 2, 2, 2);
    }
}
