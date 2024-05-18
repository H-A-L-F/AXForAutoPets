package models;

public abstract class Entity {
    protected int tier;

    public Entity(int tier) {
        this.tier = tier;
    }

    public abstract String getName();

    public int getTier() {
        return tier;
    }
}
