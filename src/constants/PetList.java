package constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
    CRAB("Crab"),
    DODO("Dodo"),
    ELEPHANT("Elephant"),
    FLAMINGO("Flamingo"),
    HEDGEHOG("Hedgehog"),
    PEACOCK("Peacock"),
    RAT("Rat"),
    SHRIMP("Shrimp"),
    SPIDER("Spider"),
    SWAN("Swan"),

    // tier 3
    BADGER("Badger"),
    BLOWFISH("Blowfish"),
    CAMEL("Camel"),
    DOG("Dog"),
    GIRAFFE("Giraffe"),
    KANGAROO("Kangaroo"),
    OX("Ox"),
    RABBIT("Rabbit"),
    SHEEP("Sheep"),
    SNAIL("Snail"),
    TURTLE("Turtle"),

    // tier 4
    BISON("Bison"),
    DEER("Deer"),
    DOLPHIN("Dolphin"),
    HIPPO("Hippo"),
    PARROT("Parrot"),
    PENGUIN("Penguin"),
    ROOSTER("Rooster"),
    SKUNK("Skunk"),
    SQUIRREL("Squirrel"),
    WHALE("Whale"),

    // tier 5
    COW("Cow"),
    CROCODILE("Crocodile"),
    MONKEY("Monkey"),
    RHINO("Rhino"),
    SCORPION("Scorpion"),
    SEAL("Seal"),
    SHARK("Shark"),
    TURKEY("Turkey"),

    // tier 6
    BOAR("Boar"),
    CAT("Cat"),
    DRAGON("Dragon"),
    FLY("Fly"),
    GORILLA("Gorilla"),
    LEOPARD("Leopard"),
    MAMMOTH("Mammoth"),
    SNAKE("Snake"),
    TIGER("Tiger"),

    //other
    BUS("Bus"),
    CHICK("Chick"),
    ZOMBIE_CRICKET("ZombieCricket");

    private final String name;

    // Tier 1
    private static final List<PetList> VALUES1 = Collections.unmodifiableList(Arrays.asList(
            ANT, BEAVER, CRICKET, DUCK, FISH, HORSE, MOSQUITO, OTTER, PIG
    ));
    private static final int SIZE1 = VALUES1.size();

    // Tier 2
    private static final List<PetList> VALUES2 = Collections.unmodifiableList(Arrays.asList(
            CRAB, DODO, ELEPHANT, FLAMINGO, HEDGEHOG, PEACOCK, RAT, SHRIMP, SPIDER, SWAN
    ));
    private static final int SIZE2 = VALUES2.size();

    // Tier 3
    private static final List<PetList> VALUES3 = Collections.unmodifiableList(Arrays.asList(
            BADGER, BLOWFISH, CAMEL, DOG, GIRAFFE, KANGAROO, OX, RABBIT, SHEEP, SNAIL, TURTLE
    ));
    private static final int SIZE3 = VALUES3.size();

    // Tier 4
    private static final List<PetList> VALUES4 = Collections.unmodifiableList(Arrays.asList(
            BISON, DEER, DOLPHIN, HIPPO, PARROT, PENGUIN, ROOSTER, SKUNK, SQUIRREL, WHALE
    ));
    private static final int SIZE4 = VALUES4.size();

    // Tier 5
    private static final List<PetList> VALUES5 = Collections.unmodifiableList(Arrays.asList(
            COW, CROCODILE, MONKEY, RHINO, SCORPION, SEAL, SHARK, TURKEY
    ));
    private static final int SIZE5 = VALUES5.size();

    // Tier 6
    private static final List<PetList> VALUES6 = Collections.unmodifiableList(Arrays.asList(
            BOAR, CAT, DRAGON, FLY, GORILLA, LEOPARD, MAMMOTH, SNAKE, TIGER
    ));
    private static final int SIZE6 = VALUES6.size();

    // Other
    private static final List<PetList> VALUES_OTHER = Collections.unmodifiableList(Arrays.asList(
            BUS, CHICK, ZOMBIE_CRICKET
    ));
    private static final int SIZE_OTHER = VALUES_OTHER.size();

    private static final Random RANDOM = new Random();

    PetList(String name) {
        this.name = name;
    }

    public PetList getRandTier1Pet() {
        return VALUES1.get(RANDOM.nextInt(SIZE1));
    }

    public PetList getRandTier2Pet() {
        return VALUES2.get(RANDOM.nextInt(SIZE2));
    }

    public PetList getRandTier3Pet() {
        return VALUES3.get(RANDOM.nextInt(SIZE3));
    }

    public PetList getRandTier4Pet() {
        return VALUES4.get(RANDOM.nextInt(SIZE4));
    }

    public PetList getRandTier5Pet() {
        return VALUES5.get(RANDOM.nextInt(SIZE5));
    }

    public PetList getRandTier6Pet() {
        return VALUES6.get(RANDOM.nextInt(SIZE6));
    }

    public PetList getRandOtherPet() {
        return VALUES_OTHER.get(RANDOM.nextInt(SIZE_OTHER));
    }

    public String getName() {
        return name;
    }
}
