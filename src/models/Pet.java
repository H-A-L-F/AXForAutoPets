package models;

import constants.PetStatus;
import interfaces.Ability;

public abstract class Pet {
    private String name;
    private int atk;
    private int hp;
    private Ability ability;
    private PetStatus status;

    public Pet(String name, int atk, int hp, Ability ability) {
        this.name = name;
        this.atk = atk;
        this.hp = hp;
        this.ability = ability;
    }

    public abstract void move();
}
