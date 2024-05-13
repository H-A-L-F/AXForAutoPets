package constants;

public enum PetList {
    ANT("Ant"),
    PIG("Pig"),
    FISH("Fish"),
    ZOMBIE_CRICKET("ZombieCricket");

    private String name;

    PetList(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
