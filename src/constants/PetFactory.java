package constants;

import interfaces.OnFaint;
import interfaces.OnLevelup;
import interfaces.OnPlaced;
import main.Arena;
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
            public void onPlaced() {
                pTeam.addOnFaint(this);
            }

            @Override
            public void onFaint() {
                pTeam.doRandom(pet -> pet.buff(getLv(), getLv()));
            }
        };
    }

    public Pet getPig() {
        return new Pig() {
            @Override
            public void onSell() {
                arena.incMoney(getLv());
            }

            @Override
            public void onPlaced() {
                pTeam.addOnSell(this);
            }
        };
    }

    public Pet getFish() {
        return new Fish() {
            @Override
            public void onLvUp() {
                for (int i = 0; i < 2; i++) {
                    pTeam.doRandom(pet -> pet.buff(getLv() - 1, getLv() - 1));
                }
            }

            @Override
            public void onPlaced() {
                pTeam.addOnLvUp(this);
            }
        };
    }

    public Pet getCricket() {
        return new Cricket() {
            @Override
            public void onFaint() {
                Pet temp = getZombieCricket();
                temp.setStats(getLv(), getLv());
                pTeam.summonPet(temp, getPos());
            }

            @Override
            public void onPlaced() {
                pTeam.addOnFaint(this);
            }
        };
    }

    public Pet getMosquito() {
        return new Mosquito() {
            @Override
            public void onBattleStart() {
                for(int i = 0; i < getLv(); i++) {
                    int idx = eTeam.getRandIdx();
                    eTeam.getPet(idx).damage(1, idx);
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
                for(int i = 0; i < 2; i++) {
                    pTeam.doRandom(pet -> pet.buff(getLv(), 0));
                }
            }

            @Override
            public void onPlaced() {
                pTeam.addOnSell(this);
            }
        };
    }

    public Pet getOtter() {
        return new Otter() {
            @Override
            public void onPurchase() {
                for(int i = 0; i < getLv(); i++) {
                    pTeam.doRandom(pet -> pet.buff(0, 1));
                }
            }

            @Override
            public void onPlaced() {
                pTeam.addOnPurchase(this);
            }
        };
    }

    public Pet getDuck() {
        return new Duck() {
            @Override
            public void onSell() {
                arena.getShop().buffShop(0, getLv());
            }

            @Override
            public void onPlaced() {
                pTeam.addOnSell(this);
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

    //endregion

    // region <Tier2>
    public Pet getRat() {
        return new Rat() {
            @Override
            public void onFaint() {
                for(int i = 0; i < getLv(); i++) {
                    eTeam.summonPet(getDirtyRat(), Team.FRONT_INDEX);
                }
            }

            @Override
            public void onPlaced() {
                pTeam.addOnFaint(this);
            }
        };
    }

    public Pet getHedgehog() {
        return new Hedgehog() {
            @Override
            public void onFaint() {
                int dmg = 2 * getLv();
                pTeam.doAll((pet) -> pet.damage(dmg, pet.getPos()));
            }

            @Override
            public void onPlaced() {
                pTeam.addOnFaint(this);
            }
        };
    }

    public Pet getFlamingo() {
        return new Flamingo() {
            @Override
            public void onFaint() {
                pTeam.doBehind(getPos(), 2, pet -> pet.buff(getLv(), getLv()));
            }

            @Override
            public void onPlaced() {
                pTeam.addOnFaint(this);
            }
        };
    }

    public Pet getSpider() {
        return new Spider() {
            @Override
            public void onFaint() {
                int stat = 2 * getLv();
                Pet temp = getPet(3);
                temp.setStats(1, stat, stat);
                pTeam.summonPet(temp, getPos());
            }

            @Override
            public void onPlaced() {
                pTeam.addOnFaint(this);
            }
        };
    }

    // endregion

    // region <Others>
    public Pet getZombieCricket() {
        return new Pet(PetList.ZOMBIE_CRICKET, 1, 1, 1) {
            @Override
            public void onPlaced() {

            }
        };
    }

    public static Pet getBee() {
        return new Pet(PetList.BEE, 1, 1, 1) {
            @Override
            public void onPlaced() {

            }
        };
    }

    public static Pet getDirtyRat() {
        return new Pet(PetList.DIRTY_RAT, 1, 1, 1) {
            @Override
            public void onPlaced() {

            }
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
//            case PIGEON -> getPigeon();

            case RAT -> getRat();
            case HEDGEHOG -> getHedgehog();
            case FLAMINGO -> getFlamingo();
            case SPIDER -> getSpider();
//            case WORM -> getWorm();
//            case SWAN -> getSwan();
//            case PEACOCK -> getPeacock();
//            case SNAIL -> getSnail();
//            case CRAB -> getCrab();
//            case KANGAROO -> getKangaroo();

//            case DOLPHIN -> getDolphin();
//            case RABBIT -> getRabbit();
//            case DOG -> getDog();
//            case DODO -> getDodo();
//            case ELEPHANT -> getElephant();
//            case SHEEP -> getSheep();
//            case BADGER -> getBadger();
//            case CAMEL -> getCamel();
//            case OX -> getOx();
//            case GIRAFFE -> getGiraffe();

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