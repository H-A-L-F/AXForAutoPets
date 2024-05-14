package pets;

import constants.PetList;
import models.Pet;

public class Ant extends Pet {
    public Ant() {
        super(PetList.ANT, 1, 2, 2);
    }

    @Override
    public void onFaint(int pos) {
        super.onFaint(pos);
        team.getRandPet().buff(getLv(), getLv());
    }
}
