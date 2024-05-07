package models;

import interfaces.Ability;

public class Pet {
    private String name;
    private int atk;
    private int hp;
    private Ability ability;

    public Pet(String name, int atk, int hp, Ability ability) {
        this.name = name;
        this.atk = atk;
        this.hp = hp;
        this.ability = ability;
    }
}
