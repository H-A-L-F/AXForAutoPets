package constants;

public enum PetList {
    ANT("Ant");

    private String name;

    PetList(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
