package constants;

public enum PetList {
    ANT("Ant"),
    PIG("Pig"),
    FISH("Fish");

    private String name;

    PetList(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
