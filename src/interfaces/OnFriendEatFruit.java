package interfaces;

import models.Fruit;
import models.Pet;

public interface OnFriendEatFruit {
    void onEatFruit(Pet pet, Fruit fruit);
}
