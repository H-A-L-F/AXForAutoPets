package constants;

public enum ShopStat {

    TIER1(1, 3, 1),
    TIER2(2, 3, 1),
    TIER3(3, 4, 2),
    TIER4(4, 4, 2),
    TIER5(5, 5, 2),
    TIER6(6, 5, 2);

    private final int TIER;
    private final int PET_SLOT;
    private final int FRUIT_SLOT;
    public static final int MAX_PET_SLOT = 5;
    public static final int MAX_FRUIT_SLOT = 2;
    public static final int BUY_PRICE = 3;
    public static final int ROLL_PRICE = 1;
    public static final int SELL_PRICE = 1;

    private ShopStat(int TIER, int PET_SLOT, int FRUIT_SLOT) {
        this.TIER = TIER;
        this.PET_SLOT = PET_SLOT;
        this.FRUIT_SLOT = FRUIT_SLOT;
    }

    public int getTIER() {return this.TIER;}
    public int getPET_SLOT() {return this.PET_SLOT;}
    public int getFRUIT_SLOT() {return this.FRUIT_SLOT;}
}
