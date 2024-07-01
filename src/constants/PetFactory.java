package constants;

import main.Arena;
import models.Fruit;
import models.Pet;
import models.Team;
import pets.*;

public class PetFactory {
    private Arena arena;
    private final Team pTeam;
    private final Team eTeam;

    public PetFactory(Arena arena, Team pTeam, Team eTeam) {
        this.arena = arena;
        this.pTeam = pTeam;
        this.eTeam = eTeam;
    }

    // region <Tier1>
    public Pet getAnt() {
        return new Ant() {
            @Override
            public void onFaint() {
                super.onFaint();
                System.out.println("Ant fainted and buffed a pet!");
                pTeam.doPet(
                        this,
                        pTeam::getRandPet,
                        pet -> pet.buff(getLv(), getLv())
                );
            }
        };
    }

    public Pet getPig() {
        return new Pig() {
            @Override
            public void onSell() {
                super.onSell();
                arena.incMoney(getLv());
                System.out.printf("Selling %s gave %d extra gold\n", getName(), getLv());
            }
        };
    }

    public Pet getFish() {
        return new Fish() {
            @Override
            public void onLevelUp() {
                super.onLevelUp();
                for (int i = 0; i < 2; i++) {
                    pTeam.doPet(
                            this,
                            pTeam::getRandPet,
                            pet -> {
                                int buff = getLv() - 1;
                                pet.buff(buff, buff);
                                System.out.printf("%s buffed %s for %dn\n",getName(), pet.getName(), buff);
                            }
                    );
                }
            }
        };
    }

    public Pet getCricket() {
        return new Cricket() {
            @Override
            public void onFaint() {
                super.onFaint();
                Pet temp = getZombieCricket();
                temp.setStats(getLv(), getLv());
                if(pTeam.summonPet(temp, getPos())) System.out.printf("Cricket summoned %s\n", temp.getName());
            }
        };
    }

    public Pet getMosquito() {
        return new Mosquito() {
            @Override
            public void onBattleStart() {
                for (int i = 0; i < getLv(); i++) {
                    int dmg = 1;
                    eTeam.doPet(
                            this,
                            eTeam::getRandPet,
                            pet -> {
                                pet.damage(1);
                                System.out.printf("Mosquito damaged %s for %d!\n", pet.getName(), dmg);
                            }
                    );
                }
            }

            @Override
            public void onPlaced() {
                pTeam.addOnBattleStart(this);
            }

            @Override
            protected void onFaint() {
                super.onFaint();
                pTeam.removeOnBattleStart(this);
            }
        };
    }

    public Pet getBeaver() {
        return new Beaver() {
            @Override
            public void onSell() {
                super.onSell();
                for (int i = 0; i < 2; i++) {
                    pTeam.doPet(
                            this,
                            pTeam::getRandPet,
                            pet -> {
                                pet.buff(getLv(), 0);
                                System.out.printf("%s buffed %s atk for %d\n", getName(), pet.getName(), getLv());
                            }
                    );
                }
            }
        };
    }

    public Pet getOtter() {
        return new Otter() {
            @Override
            public void onPurchased() {
                super.onPurchased();
                for (int i = 0; i < getLv(); i++) {
                    pTeam.doPet(
                            this,
                            pTeam::getRandPet,
                            pet -> {
                                pet.buff(0, 1);
                                System.out.printf("%s buffed %s hp for 1\n", getName(), pet.getName());
                            }
                    );
                }
            }
        };
    }

    public Pet getDuck() {
        return new Duck() {
            @Override
            public void onSell() {
                super.onSell();
                arena.getShop().buffShop(0, getLv());
                System.out.printf("%s buffed shop pets hp for %d\n", getName(), getLv());
            }
        };
    }

    public Pet getHorse() {
        return new Horse() {
            @Override
            public void onSummon(Pet pet) {
                pet.buff(getLv(), 0);
                System.out.printf("%s buffed %s atk for %d\n", getName(), pet.getName(), getLv());
            }

            @Override
            public void onPlaced() {
                pTeam.addOnSummon(this);
            }

            @Override
            protected void onFaint() {
                super.onFaint();
                pTeam.removeOnSummon(this);
            }
        };
    }

    public Pet getPigeon() {
        return new Pigeon() {
            @Override
            public void onSell() {
                super.onSell();
                for(int i = 0; i < getLv(); i++) {
                    arena.getShop().addFruit(FruitFactory.getBreadCrumbs());
                }
                System.out.printf("Sold pigeon created %d breadcrumbs in shop\n", getLv());
            }
        };
    }
    //endregion

    // region <Tier2>
    public Pet getRat() {
        return new Rat() {
            @Override
            public void onFaint() {
                super.onFaint();
                for (int i = 0; i < getLv(); i++) {
                    eTeam.summonPet(getDirtyRat(), Team.FRONT_INDEX);
                }
                System.out.println("%s died, Dirty Rat is summoned on the enemy team");
            }
        };
    }

    public Pet getHedgehog() {
        return new Hedgehog() {
            @Override
            public void onFaint() {
                super.onFaint();
                int dmg = 2 * getLv();
                pTeam.doAll((pet) -> {
                    pet.damage(dmg);
                    System.out.printf("%s damaged %s for %d\n", getName(), pet.getName(), dmg);
                });
            }
        };
    }

    public Pet getFlamingo() {
        return new Flamingo() {
            @Override
            public void onFaint() {
                super.onFaint();
                for(int i = 0; i < 2; i++) {
                    int pos = getPos() - i - 1;
                    pTeam.doPet(
                            this,
                            () -> pTeam.getBattlePet(pos),
                            pet -> {
                                pet.buff(getLv(), getLv());
                                System.out.printf("%s buffed %s for %d atk and %d hp\n", getName(), pet.getName(), getLv(), getLv());
                            }
                    );
                }
            }
        };
    }

    public Pet getSpider() {
        return new Spider() {
            @Override
            public void onFaint() {
                super.onFaint();
                int stat = 2 * getLv();
                Pet temp = getPet(3);
                temp.setStats(1, stat, stat);
                pTeam.summonPet(temp, getPos());
                System.out.printf("%s fainted and summoned %s\n", getName(), temp.getName());
            }
        };
    }

    public Pet getWorm() {
        return new Worm() {
            @Override
            public void onTurnStart() {
                arena.getShop().addFruit(FruitFactory.getWormApple(this));
                System.out.printf("%s added worm apple to shop\n", getName());
            }

            @Override
            public void onPlaced() {
                pTeam.addOnTurnStart(this);
            }

            @Override
            protected void onSell() {
                super.onSell();
                pTeam.removeOnTurnStart(this);
            }
        };
    }

    public Pet getSwan() {
        return new Swan() {
            @Override
            public void onTurnStart() {
                arena.incMoney(getLv());
                System.out.printf("%s added %d coin\n", getName(), getLv());
            }

            @Override
            public void onPlaced() {
                pTeam.addOnTurnStart(this);
            }

            @Override
            protected void onSell() {
                super.onSell();
                pTeam.removeOnTurnStart(this);
            }
        };
    }

    public Pet getPeacock() {
        return new Peacock() {
            @Override
            public void onHurt() {
                super.onHurt();
                buff(4, 0);
                System.out.printf("%s buffed for %d atk", getName(), 4);
            }
        };
    }

    public Pet getSnail() {
        return new Snail() {
            @Override
            public void onTurnEnd() {
                if (arena.getLastBattleResult() == BattleResult.LOSE) pTeam.doAll(pet -> {
                    pet.buff(0, getLv());
                    System.out.printf("%s buffed %s for %d hp\n", getName(), pet.getName(), getLv());
                });
            }

            @Override
            public void onPlaced() {
                pTeam.addOnTurnEnd(this);
            }

            @Override
            protected void onSell() {
                super.onSell();
                pTeam.removeOnTurnEnd(this);
            }
        };
    }

    public Pet getCrab() {
        return new Crab() {
            @Override
            public void onBattleStart() {
                Pet temp = pTeam.getMostAttribute((pet1, pet2) -> {
                    if (pet1.getHp() >= pet2.getHp()) return pet1;
                    return pet2;
                });
                int hp = (int) (((double) temp.getHp() * 0.5) * getLv());
                setHp(hp);
                System.out.printf("%s got %d hp", getName(), hp);
            }

            @Override
            public void onPlaced() {
                pTeam.addOnBattleStart(this);
            }

            @Override
            protected void onFaint() {
                super.onFaint();
                pTeam.removeOnBattleStart(this);
            }
        };
    }

    public Pet getKangaroo() {
        return new Kangaroo() {
            @Override
            public void onFriendAttack(Pet pet) {
                if(pet.getPos() == getPos() + 1) {
                    buff(getLv(), getLv());
                    System.out.printf("%s buffed for %d atk and hp", getName(), getLv());
                }
            }

            @Override
            public void onPlaced() {
                pTeam.addOnFriendAttack(this);
            }

            @Override
            protected void onFaint() {
                super.onFaint();
                pTeam.removeOnFriendAttack(this);
            }
        };
    }

    // endregion

    // region <Tier3>

    public Pet getDodo() {
        return new Dodo() {
            @Override
            public void onBattleStart() {
                if (getPos() == Team.FRONT_INDEX) return;
                int atk = (int) ((double) getAtk() * 0.5) * getLv();
                pTeam.getBattlePet(getPos() + 1).buff(atk, 0);
            }

            @Override
            public void onPlaced() {
                pTeam.addOnBattleStart(this);
            }

            @Override
            protected void onFaint() {
                super.onFaint();
                pTeam.removeOnBattleStart(this);
            }
        };
    }

    public Pet getBadger() {
        return new Badger() {
            @Override
            protected void onFaint() {
                super.onFaint();
                int dmg = (int) ((double) getAtk() * 0.5) * getLv();
                if (getPos() == Team.FRONT_INDEX) {
                    pTeam.doPet(
                            this,
                            () -> pTeam.getBattlePet(getPos() - 1),
                            pet -> pet.damage(dmg)
                    );
                    eTeam.doPet(
                            this,
                            () -> eTeam.getBattlePet(Team.FRONT_INDEX),
                            pet -> pet.damage(dmg)
                    );
                } else {
                    pTeam.doPet(
                            this,
                            () -> pTeam.getBattlePet(getPos() + 1),
                            pet -> pet.damage(dmg)
                    );
                    pTeam.doPet(
                            this,
                            () -> pTeam.getBattlePet(getPos() - 1),
                            pet -> pet.damage(dmg)
                    );
                }
            }
        };
    }

    public Pet getDolphin() {
        return new Dolphin() {
            @Override
            public void onBattleStart() {
                for (int i = 0; i < getLv(); i++) {
                    int dmg = 4;
                    eTeam.doPet(
                            this,
                            () -> eTeam.getMostAttribute((x, y) -> x.getHp() <= y.getHp() ? x : y),
                            pet -> pet.damage(dmg)
                    );
                }
            }

            @Override
            public void onPlaced() {
                pTeam.addOnBattleStart(this);
            }

            @Override
            protected void onFaint() {
                super.onFaint();
                pTeam.removeOnBattleStart(this);
            }
        };
    }

    public Pet getGiraffe() {
        return new Giraffe() {
            @Override
            public void onTurnEnd() {
                for(int i = 0; i < getLv(); i++) {
                    int pos = getPos() + i + 1;
                    pTeam.doPet(
                            this,
                            () -> pTeam.getBattlePet(pos),
                            pet -> {
                                if(pet == null) return;
                                pet.buff(1, 1);
                            }
                    );
                }
            }

            @Override
            public void onPlaced() {
                pTeam.addOnTurnEnd(this);
            }

            @Override
            protected void onSell() {
                super.onSell();
                pTeam.removeOnTurnEnd(this);
            }
        };
    }

    public Pet getElephant() {
        return new Elephant() {
            @Override
            public void onAfterAttack() {
                super.onAfterAttack();
                int dmg = 1;
                for(int i = 0; i < getLv(); i++) {
                    pTeam.doPet(
                            this,
                            () -> pTeam.getBattlePet(getPos() - 1),
                            pet -> pet.damage(dmg)
                    );
                }
            }
        };
    }

    public Pet getCamel() {
        return new Camel() {
            @Override
            protected void onHurt() {
                super.onHurt();
                int atk = getLv();
                int hp = 2 * getLv();
                pTeam.doPet(
                        this,
                        () -> pTeam.getBattlePet(getPos() - 1),
                        pet -> pet.buff(atk, hp)
                );
            }
        };
    }

    public Pet getRabbit() {
        return new Rabbit() {
            @Override
            public void onEatFruit(Pet pet, Fruit fruit) {
                pet.buff(0, getLv());
            }

            @Override
            public void onPlaced() {
                pTeam.addOnFriendEatFruit(this);
            }

            @Override
            protected void onSell() {
                super.onSell();
                pTeam.removeOnFriendEatFruit(this);
            }
        };
    }

    public Pet getOx() {
        return new Ox() {
            @Override
            public void onFriendFaint(Pet pet) {
                // TODO
                //if(getPos() + 1 == pet.getPos())
            }

            @Override
            public void onPlaced() {
                pTeam.addOnFriendFaint(this);
            }

            @Override
            protected void onFaint() {
                super.onFaint();
                pTeam.removeOnFriendFaint(this);
            }
        };
    }

    public Pet getDog() {
        return new Dog() {
            @Override
            public void onSummon(Pet pet) {
                int atk = 2 * getLv();
                int hp = getLv();
                buff(atk, hp);
            }

            @Override
            public void onPlaced() {
                pTeam.addOnSummon(this);
            }

            @Override
            protected void onFaint() {
                super.onFaint();
                pTeam.removeOnSummon(this);
            }
        };
    }

    public Pet getSheep() {
        return new Sheep() {
            @Override
            protected void onFaint() {
                super.onFaint();
                Pet ram = getRam();
                ram.setStats(getLv(), 2 * getLv(), 2 * getLv());
            }
        };
    }

    // endregion

    // region <Others>
    public Pet getZombieCricket() {
        return new Pet(PetList.ZOMBIE_CRICKET, 1, 1, 1) {
        };
    }

    public static Pet getBee() {
        return new Pet(PetList.BEE, 1, 1, 1) {
        };
    }

    public static Pet getDirtyRat() {
        return new Pet(PetList.DIRTY_RAT, 1, 1, 1) {
        };
    }

    public static Pet getRam() {
        return new Pet(PetList.RAM, 1, 2, 2) {
        };
    }
    // endregion

    public Pet getPet(PetList name) {
        switch (name) {
            case ANT:
                return getAnt();
            case BEAVER:
                return getBeaver();
            case CRICKET:
                return getCricket();
            case DUCK:
                return getDuck();
            case FISH:
                return getFish();
            case HORSE:
                return getHorse();
            case MOSQUITO:
                return getMosquito();
            case OTTER:
                return getOtter();
            case PIG:
                return getPig();
            case PIGEON:
                return getPigeon();

            case RAT:
                return getRat();
            case HEDGEHOG:
                return getHedgehog();
            case FLAMINGO:
                return getFlamingo();
            case SPIDER:
                return getSpider();
            case WORM:
                return getWorm();
            case SWAN:
                return getSwan();
            case PEACOCK:
                return getPeacock();
            case SNAIL:
                return getSnail();
            case CRAB:
                return getCrab();
            case KANGAROO:
                return getKangaroo();

            case DOLPHIN:
                return getDolphin();
            case RABBIT:
                return getRabbit();
            case DOG:
                return getDog();
            case DODO:
                return getDodo();
            case ELEPHANT:
                return getElephant();
            case SHEEP:
                return getSheep();
            case BADGER:
                return getBadger();
            case CAMEL:
                return getCamel();
            case OX:
                return getOx();
            case GIRAFFE:
                return getGiraffe();

//        case BLOWFISH:
//            return getBlowfish();
//        case SKUNK:
//            return getSkunk();
//        case TURTLE:
//            return getTurtle();
//        case SQUIRREL:
//            return getSquirrel();
//        case DEER:
//            return getDeer();
//        case BISON:
//            return getBison();
//        case WHALE:
//            return getWhale();
//        case PENGUIN:
//            return getPenguin();
//        case HIPPO:
//            return getHippo();
//        case PARROT:
//            return getParrot();

//        case SCORPION:
//            return getScorpion();
//        case RHINO:
//            return getRhino();
//        case SEAL:
//            return getSeal();
//        case ARMADILLO:
//            return getArmadillo();
//        case COW:
//            return getCow();
//        case MONKEY:
//            return getMonkey();
//        case SHARK:
//            return getShark();
//        case TURKEY:
//            return getTurkey();
//        case CROCODILE:
//            return getCrocodile();
//        case ROOSTER:
//            return getRooster();

//        case DRAGON:
//            return getDragon();
//        case LEOPARD:
//            return getLeopard();
//        case MAMMOTH:
//            return getMammoth();
//        case GORILLA:
//            return getGorilla();
//        case BOAR:
//            return getBoar();
//        case TIGER:
//            return getTiger();
//        case SNAKE:
//            return getSnake();
//        case FLY:
//            return getFly();
//        case CAT:
//            return getCat();
//        case WOLVERINE:
//            return getWolverine();

//        case BUS:
//            return getBus();
//        case CHICK:
//            return getChick();
            case RAM:
                return getRam();
            case BEE:
                return getBee();
            case ZOMBIE_CRICKET:
                return getZombieCricket();
            case DIRTY_RAT:
                return getDirtyRat();
            default:
                return null;
        }
    }

    public Pet getPet(int tier) {
        return getPet(PetList.getRandPetByTier(tier));
    }
}