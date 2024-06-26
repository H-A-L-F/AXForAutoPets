package constants;

import pets.Worm;

import java.util.*;

public enum PetList {

    // tier 1
    ANT("Ant"),
    PIG("Pig"),
    FISH("Fish"),
    CRICKET("Cricket"),
    MOSQUITO("Mosquito"),
    BEAVER("Beaver"),
    OTTER("Otter"),
    DUCK("Duck"),
    HORSE("Horse"),
    PIGEON("Pigeon"),

    // tier 2
    RAT("Rat"),
    HEDGEHOG("Hedgehog"),
    FLAMINGO("Flamingo"),
    SPIDER("Spider"),
    WORM("Worm"),
    SWAN("Swan"),
    PEACOCK("Peacock"),
    SNAIL("Snail"),
    CRAB("Crab"),
    KANGAROO("Kangaroo"),

    // tier 3
    DOLPHIN("Dolphin"),
    RABBIT("Rabbit"),
    DOG("Dog"),
    DODO("Dodo"),
    ELEPHANT("Elephant"),
    SHEEP("Sheep"),
    BADGER("Badger"),
    CAMEL("Camel"),
    OX("Ox"),
    GIRAFFE("Giraffe"),

    // tier 4,
    BLOWFISH("Blowfish"),
    SKUNK("Skunk"),
    TURTLE("Turtle"),
    SQUIRREL("Squirrel"),
    DEER("Deer"),
    BISON("Bison"),
    WHALE("Whale"),
    PENGUIN("Penguin"),
    HIPPO("Hippo"),
    PARROT("Parrot"),

    // tier 5,
    SCORPION("Scorpion"),
    RHINO("Rhino"),
    SEAL("Seal"),
    ARMADILLO("Armadillo"),
    COW("Cow"),
    MONKEY("Monkey"),
    SHARK("Shark"),
    TURKEY("Turkey"),
    CROCODILE("Crocodile"),
    ROOSTER("Rooster"),

    // tier 6
    DRAGON("Dragon"),
    LEOPARD("Leopard"),
    MAMMOTH("Mammoth"),
    GORILLA("Gorilla"),
    BOAR("Boar"),
    TIGER("Tiger"),
    SNAKE("Snake"),
    FLY("Fly"),
    CAT("Cat"),
    WOLVERINE("Wolverine"),

    //other
    RAM("Ram"),
    BUS("Bus"),
    CHICK("Chick"),
    ZOMBIE_CRICKET("ZombieCricket"),
    DIRTY_RAT("DirtyRat"),
    BEE("Bee");

    private final String name;

    // Tier 1
    private static final List<PetList> VALUES1 = Collections.unmodifiableList(Arrays.asList(
            ANT, BEAVER, CRICKET, DUCK, FISH, HORSE, MOSQUITO, OTTER, PIG, PIGEON
    ));
    private static final int SIZE1 = VALUES1.size();

    // Tier 2
    private static final List<PetList> VALUES2 = Collections.unmodifiableList(Arrays.asList(
            CRAB, FLAMINGO, HEDGEHOG, PEACOCK, RAT, SPIDER, SWAN, WORM, KANGAROO, SNAIL
    ));
    private static final int SIZE2 = VALUES2.size();

    // Tier 3
    private static final List<PetList> VALUES3 = Collections.unmodifiableList(Arrays.asList(
            DOLPHIN, RABBIT, DOG, DODO, ELEPHANT, SHEEP, BADGER, CAMEL, OX, GIRAFFE
    ));
    private static final int SIZE3 = VALUES3.size();

    // Tier 4
    private static final List<PetList> VALUES4 = Collections.unmodifiableList(Arrays.asList(
            BLOWFISH, SKUNK, TURTLE, SQUIRREL, DEER, BISON, WHALE, PENGUIN, HIPPO, PARROT
    ));
    private static final int SIZE4 = VALUES4.size();

    // Tier 5
    private static final List<PetList> VALUES5 = Collections.unmodifiableList(Arrays.asList(
            SCORPION, RHINO, SEAL, ARMADILLO, COW, MONKEY, SHARK, TURKEY, CROCODILE, ROOSTER
    ));
    private static final int SIZE5 = VALUES5.size();

    // Tier 6
    private static final List<PetList> VALUES6 = Collections.unmodifiableList(Arrays.asList(
            DRAGON, LEOPARD, MAMMOTH, GORILLA, BOAR, TIGER, SNAKE, FLY, CAT, WOLVERINE
    ));
    private static final int SIZE6 = VALUES6.size();

    // Other
    private static final List<PetList> VALUES_OTHER = Collections.unmodifiableList(Arrays.asList(
            RAM, BUS, CHICK, ZOMBIE_CRICKET, BEE, DIRTY_RAT
    ));
    private static final int SIZE_OTHER = VALUES_OTHER.size();

    private static final Random RANDOM = new Random();

    PetList(String name) {
        this.name = name;
    }

    public static PetList getRandPetByTier(int tier) {
        switch (tier) {
            case 1:
                return VALUES1.get(RANDOM.nextInt(SIZE1));
            case 2:
                List<PetList> combinedList2 = Lib.combineLists(VALUES1, VALUES2);
                return combinedList2.get(RANDOM.nextInt(combinedList2.size()));
            case 3:
                List<PetList> combinedList3 = Lib.combineLists(VALUES1, VALUES2, VALUES3);
                return combinedList3.get(RANDOM.nextInt(combinedList3.size()));
            case 4:
                List<PetList> combinedList4 = Lib.combineLists(VALUES1, VALUES2, VALUES3, VALUES4);
                return combinedList4.get(RANDOM.nextInt(combinedList4.size()));
            case 5:
                List<PetList> combinedList5 = Lib.combineLists(VALUES1, VALUES2, VALUES3, VALUES4, VALUES5);
                return combinedList5.get(RANDOM.nextInt(combinedList5.size()));
            case 6:
                List<PetList> combinedList6 = Lib.combineLists(VALUES1, VALUES2, VALUES3, VALUES4, VALUES5, VALUES6);
                return combinedList6.get(RANDOM.nextInt(combinedList6.size()));
            default:
                throw new IllegalArgumentException("Invalid tier: " + tier);
        }
    }

    public static PetList getRandTier1Pet() {
        return VALUES1.get(RANDOM.nextInt(SIZE1));
    }

    public static PetList getRandTier2Pet() {
        return VALUES2.get(RANDOM.nextInt(SIZE2));
    }

    public static PetList getRandTier3Pet() {
        return VALUES3.get(RANDOM.nextInt(SIZE3));
    }

    public static PetList getRandTier4Pet() {
        return VALUES4.get(RANDOM.nextInt(SIZE4));
    }

    public static PetList getRandTier5Pet() {
        return VALUES5.get(RANDOM.nextInt(SIZE5));
    }

    public static PetList getRandTier6Pet() {
        return VALUES6.get(RANDOM.nextInt(SIZE6));
    }

    public static PetList getRandOtherPet() {
        return VALUES_OTHER.get(RANDOM.nextInt(SIZE_OTHER));
    }

    public String getName() {
        return name;
    }
}
