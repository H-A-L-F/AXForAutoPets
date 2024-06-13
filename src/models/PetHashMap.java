package models;

import interfaces.RunnablePet;

import java.util.HashMap;

public class PetHashMap extends HashMap<Integer, Pet> {
    RunnablePet onRemove;

    public PetHashMap(RunnablePet onRemove) {
        this.onRemove = onRemove;
    }

    @Override
    public Pet remove(Object key) {
        Pet pet = super.remove(key);
        onRemove.runPet(pet);
        return pet;
    }
}
