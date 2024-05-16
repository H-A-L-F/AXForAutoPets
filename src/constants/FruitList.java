package constants;

public enum FruitList {

    APPLE("Apple"),
    HONEY("Honey");

    private final String name;

    FruitList(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
