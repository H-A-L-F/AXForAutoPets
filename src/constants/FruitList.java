package constants;

import java.util.*;

public enum FruitList {

    // Tier 1
    APPLE("Apple"),
    HONEY("Honey"),

    // Tier 2
    CUPCAKE("Cupcake"),
    MEAT_BONE("MeatBone"),

    // Tier 3
    SALAD_BOWL("SaladBowl"),
    GARLIC("Garlic"),

    // Tier 4
    CANNED_FOOD("CannedFood"),
    PEAR("Pear"),

    // Tier 5
    CHILI("Chili"),
    CHOCOLATE("Chocolate"),
    SUSHI("Sushi"),

    // Tier 6
    MELON("Melon"),
    MUSHROOM("Mushroom"),
    PIZZA("Pizza"),
    STEAK("Steak"),

    // Others
    BREAD_CRUMBS("BreadCrumbs"),
    WORM_APPLE("WormApple");

    private final String name;

    @SafeVarargs
    private static List<FruitList> combineLists(List<FruitList>... lists) {
        List<FruitList> combinedList = new ArrayList<>();
        for (List<FruitList> list : lists) {
            combinedList.addAll(list);
        }
        return Collections.unmodifiableList(combinedList);
    }

    // Tier 1
    private static final List<FruitList> VALUES1 = Collections.unmodifiableList(Arrays.asList(
            FruitList.APPLE, FruitList.HONEY
    ));
    private static final int SIZE1 = VALUES1.size();

    // Tier 2
    private static final List<FruitList> VALUES2 = Collections.unmodifiableList(Arrays.asList(
            FruitList.CUPCAKE, FruitList.MEAT_BONE
    ));
    private static final int SIZE2 = VALUES2.size();

    // Tier 3
    private static final List<FruitList> VALUES3 = Collections.unmodifiableList(Arrays.asList(
            FruitList.SALAD_BOWL, FruitList.GARLIC
    ));
    private static final int SIZE3 = VALUES3.size();

    // Tier 4
    private static final List<FruitList> VALUES4 = Collections.unmodifiableList(Arrays.asList(
            FruitList.CANNED_FOOD, FruitList.PEAR
    ));
    private static final int SIZE4 = VALUES4.size();

    // Tier 5
    private static final List<FruitList> VALUES5 = Collections.unmodifiableList(Arrays.asList(
            FruitList.CHILI, FruitList.CHOCOLATE, FruitList.SUSHI
    ));
    private static final int SIZE5 = VALUES5.size();

    // Tier 6
    private static final List<FruitList> VALUES6 = Collections.unmodifiableList(Arrays.asList(
            FruitList.MELON, FruitList.MUSHROOM, FruitList.PIZZA, FruitList.STEAK
    ));
    private static final int SIZE6 = VALUES6.size();

    private static final List<FruitList> VALUES_OTHER = Collections.unmodifiableList(Arrays.asList(
            FruitList.BREAD_CRUMBS, FruitList.WORM_APPLE
    ));
    private static final int SIZE_OTHER = VALUES_OTHER.size();

    private static final Random RANDOM = new Random();

    FruitList(String name) {
        this.name = name;
    }

    public static FruitList getRandFoodByTier(int tier) {
        switch (tier) {
            case 1:
                return VALUES1.get(RANDOM.nextInt(SIZE1));
            case 2:
                List<FruitList> combinedList2 = Lib.combineLists(VALUES1, VALUES2);
                return combinedList2.get(RANDOM.nextInt(combinedList2.size()));
            case 3:
                List<FruitList> combinedList3 = Lib.combineLists(VALUES1, VALUES2, VALUES3);
                return combinedList3.get(RANDOM.nextInt(combinedList3.size()));
            case 4:
                List<FruitList> combinedList4 = Lib.combineLists(VALUES1, VALUES2, VALUES3, VALUES4);
                return combinedList4.get(RANDOM.nextInt(combinedList4.size()));
            case 5:
                List<FruitList> combinedList5 = Lib.combineLists(VALUES1, VALUES2, VALUES3, VALUES4, VALUES5);
                return combinedList5.get(RANDOM.nextInt(combinedList5.size()));
            case 6:
                List<FruitList> combinedList6 = Lib.combineLists(VALUES1, VALUES2, VALUES3, VALUES4, VALUES5, VALUES6);
                return combinedList6.get(RANDOM.nextInt(combinedList6.size()));
            default:
                throw new IllegalArgumentException("Invalid tier: " + tier);
        }
    }

    public FruitList getRandTier1Food() {
        return VALUES1.get(RANDOM.nextInt(SIZE1));
    }

    public FruitList getRandTier2Food() {
        return VALUES2.get(RANDOM.nextInt(SIZE2));
    }

    public FruitList getRandTier3Food() {
        return VALUES3.get(RANDOM.nextInt(SIZE3));
    }

    public FruitList getRandTier4Food() {
        return VALUES4.get(RANDOM.nextInt(SIZE4));
    }

    public FruitList getRandTier5Food() {
        return VALUES5.get(RANDOM.nextInt(SIZE5));
    }

    public FruitList getRandTier6Food() {
        return VALUES6.get(RANDOM.nextInt(SIZE6));
    }

    public String getName() {
        return name;
    }
}
