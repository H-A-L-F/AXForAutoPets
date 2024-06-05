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
                            pet -> pet.buff(getLv() - 1, getLv() - 1)
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
                pTeam.summonPet(temp, getPos());
            }
        };
    }

    public Pet getMosquito() {
        return new Mosquito() {
            @Override
            public void onBattleStart() {
                for (int i = 0; i < getLv(); i++) {
                    int idx = eTeam.getRandIdx();
                    eTeam.getBattlePet(idx).damage(1);
                }
            }

            @Override
            public void onPlaced() {
                pTeam.addOnBattleStart(this);
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
                            pet -> pet.buff(getLv(), 0)
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
                            pet -> pet.buff(0, 1)
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
            }
        };
    }

    public Pet getHorse() {
        return new Horse() {
            @Override
            public void onSummon(Pet pet) {
                pet.buff(getLv(), 0);
            }

            @Override
            public void onPlaced() {
                pTeam.addOnSummon(this);
            }
        };
    }

    public Pet getPigeon() {
        return new Pigeon() {
            @Override
            public void onSell() {
                super.onSell();
                arena.getShop().addFruit(FruitFactory.getBreadCrumbs());
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
            }
        };
    }

    public Pet getHedgehog() {
        return new Hedgehog() {
            @Override
            public void onFaint() {
                super.onFaint();
                int dmg = 2 * getLv();
                pTeam.doAll((pet) -> pet.damage(dmg));
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
                            pet -> pet.buff(getLv(), getLv())
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
            }
        };
    }

    public Pet getWorm() {
        return new Worm() {
            @Override
            public void onTurnStart() {
                arena.getShop().addFruit(FruitFactory.getWormApple(this));
            }

            @Override
            public void onPlaced() {
                pTeam.addOnTurnStart(this);
            }
        };
    }

    public Pet getSwan() {
        return new Swan() {
            @Override
            public void onTurnStart() {
                arena.incMoney(getLv());
            }

            @Override
            public void onPlaced() {
                pTeam.addOnTurnStart(this);
            }
        };
    }

    public Pet getPeacock() {
        return new Peacock() {
            @Override
            public void onHurt() {
                super.onHurt();
                buff(4, 0);
            }
        };
    }

    public Pet getSnail() {
        return new Snail() {
            @Override
            public void onTurnEnd() {
                if (arena.getLastBattleResult() == BattleResult.LOSE) pTeam.doAll(pet -> pet.buff(0, getLv()));
            }

            @Override
            public void onPlaced() {
                pTeam.addOnTurnEnd(this);
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
            }

            @Override
            public void onPlaced() {
                pTeam.addOnBattleStart(this);
            }
        };
    }

    public Pet getKangaroo() {
        return new Kangaroo() {
            @Override
            public void onFriendAttack(Pet pet) {
                if(pet.getPos() == getPos() + 1) buff(getLv(), getLv());
            }

            @Override
            public void onPlaced() {
                pTeam.addOnFriendAttack(this);
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
                            pet -> pet.buff(1, 1)
                    );
                }
            }

            @Override
            public void onPlaced() {
                pTeam.addOnTurnEnd(this);
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
        return switch (name) {
            case ANT -> getAnt();
            case BEAVER -> getBeaver();
            case CRICKET -> getCricket();
            case DUCK -> getDuck();
            case FISH -> getFish();
            case HORSE -> getHorse();
            case MOSQUITO -> getMosquito();
            case OTTER -> getOtter();
            case PIG -> getPig();
            case PIGEON -> getPigeon();

            case RAT -> getRat();
            case HEDGEHOG -> getHedgehog();
            case FLAMINGO -> getFlamingo();
            case SPIDER -> getSpider();
            case WORM -> getWorm();
            case SWAN -> getSwan();
            case PEACOCK -> getPeacock();
            case SNAIL -> getSnail();
            case CRAB -> getCrab();
            case KANGAROO -> getKangaroo();

            case DOLPHIN -> getDolphin();
            case RABBIT -> getRabbit();
            case DOG -> getDog();
            case DODO -> getDodo();
            case ELEPHANT -> getElephant();
            case SHEEP -> getSheep();
            case BADGER -> getBadger();
            case CAMEL -> getCamel();
            case OX -> getOx();
            case GIRAFFE -> getGiraffe();

//            case BLOWFISH -> getBlowfish();
//            case SKUNK -> getSkunk();
//            case TURTLE -> getTurtle();
//            case SQUIRREL -> getSquirrel();
//            case DEER -> getDeer();
//            case BISON -> getBison();
//            case WHALE -> getWhale();
//            case PENGUIN -> getPenguin();
//            case HIPPO -> getHippo();
//            case PARROT -> getParrot();

//            case SCORPION -> getScorpion();
//            case RHINO -> getRhino();
//            case SEAL -> getSeal();
//            case ARMADILLO -> getArmadillo();
//            case COW -> getCow();
//            case MONKEY -> getMonkey();
//            case SHARK -> getShark();
//            case TURKEY -> getTurkey();
//            case CROCODILE -> getCrocodile();
//            case ROOSTER -> getRooster();

//            case DRAGON -> getDragon();
//            case LEOPARD -> getLeopard();
//            case MAMMOTH -> getMammoth();
//            case GORILLA -> getGorilla();
//            case BOAR -> getBoar();
//            case TIGER -> getTiger();
//            case SNAKE -> getSnake();
//            case FLY -> getFly();
//            case CAT -> getCat();
//            case WOLVERINE -> getWolverine();

//            case BUS -> getBus();
//            case CHICK -> getChick();
            case RAM -> getRam();
            case BEE -> getBee();
            case ZOMBIE_CRICKET -> getZombieCricket();
            case DIRTY_RAT -> getDirtyRat();
            default -> null;
        };
    }

    public Pet getPet(int tier) {
        return getPet(PetList.getRandPetByTier(tier));
    }
}