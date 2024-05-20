package constants;

import main.Arena;
import models.Pet;
import models.Team;

public class PetFactory {
    private Arena arena;
    private final Team pTeam;
    private final Team eTeam;

    public PetFactory(Arena arena, Team pTeam, Team eTeam) {
        this.arena = arena;
        this.pTeam = pTeam;
        this.eTeam = eTeam;
    }

    public Pet getAnt() {
        return new Pet(PetList.ANT, 1, 2, 2) {
            @Override
            public void onFaint(int pos) {
                super.onFaint(pos);
                pTeam.getRandPet().buff(getLv(), getLv());
            }
        };
    }

    public Pet getPig() {
        return new Pet(PetList.PIG, 1, 4, 1) {
            @Override
            public void onSell() {
                super.onSell();
                arena.incMoney(getLv());
            }
        };
    }

    public Pet getFish() {
        return new Pet(PetList.FISH, 1, 2, 3) {
            @Override
            public void onLvUp() {
                super.onLvUp();
                for (int i = 0; i < 2; i++) {
                    pTeam.getRandPet().buff(getLv() - 1, getLv() - 1);
                }
            }
        };
    }

    public Pet getCricket() {
        return new Pet(PetList.CRICKET, 1, 1, 2) {
            @Override
            public void onFaint(int pos) {
                super.onFaint(pos);
                Pet temp = getZombieCricket();
                temp.setStats(getLv(), getLv());
                pTeam.addPet(temp, pos);
            }
        };
    }

    public Pet getZombieCricket() {
        return new Pet(PetList.ZOMBIE_CRICKET, 1, 1, 1) {
        };
    }

    public static Pet getBee() {
        return new Pet(PetList.BEE, 1, 1, 1) {
        };
    }

    public Pet getMosquito() {
        return new Pet(PetList.MOSQUITO, 1, 2, 2) {
            @Override
            public void onBattleStart() {
                super.onBattleStart();
                for(int i = 0; i < getLv(); i++) {
                    eTeam.getRandPet().damage(1);
                }
            }
        };
    }

    public Pet getBeaver() {
        return new Pet(PetList.BEAVER, 1, 3, 2) {
            @Override
            public void onSell() {
                super.onSell();
                for(int i = 0; i < 2; i++) {
                    pTeam.getRandPet().buff(getLv(), 0);
                }
            }
        };
    }

    public Pet getPet(PetList name) {
        return switch (name) {
            case ANT -> getAnt();
//            case BEAVER -> getBeaver();
            case CRICKET -> getCricket();
//            case DUCK -> getDuck();
            case FISH -> getFish();
//            case HORSE -> getHorse();
            case MOSQUITO -> getMosquito();
//            case OTTER -> getOtter();
            case PIG -> getPig();
//            case CRAB -> getCrab();
//            case DODO -> getDodo();
//            case ELEPHANT -> getElephant();
//            case FLAMINGO -> getFlamingo();
//            case HEDGEHOG -> getHedgehog();
//            case PEACOCK -> getPeacock();
//            case RAT -> getRat();
//            case SHRIMP -> getShrimp();
//            case SPIDER -> getSpider();
//            case SWAN -> getSwan();
//            case BADGER -> getBadger();
//            case BLOWFISH -> getBlowfish();
//            case CAMEL -> getCamel();
//            case DOG -> getDog();
//            case GIRAFFE -> getGiraffe();
//            case KANGAROO -> getKangaroo();
//            case OX -> getOx();
//            case RABBIT -> getRabbit();
//            case SHEEP -> getSheep();
//            case SNAIL -> getSnail();
//            case TURTLE -> getTurtle();
//            case BISON -> getBison();
//            case DEER -> getDeer();
//            case DOLPHIN -> getDolphin();
//            case HIPPO -> getHippo();
//            case PARROT -> getParrot();
//            case PENGUIN -> getPenguin();
//            case ROOSTER -> getRooster();
//            case SKUNK -> getSkunk();
//            case SQUIRREL -> getSquirrel();
//            case WHALE -> getWhale();
//            case COW -> getCow();
//            case CROCODILE -> getCrocodile();
//            case MONKEY -> getMonkey();
//            case RHINO -> getRhino();
//            case SCORPION -> getScorpion();
//            case SEAL -> getSeal();
//            case SHARK -> getShark();
//            case TURKEY -> getTurkey();
//            case BOAR -> getBoar();
//            case CAT -> getCat();
//            case DRAGON -> getDragon();
//            case FLY -> getFly();
//            case GORILLA -> getGorilla();
//            case LEOPARD -> getLeopard();
//            case MAMMOTH -> getMammoth();
//            case SNAKE -> getSnake();
//            case TIGER -> getTiger();
//            case BUS -> getBus();
//            case CHICK -> getChick();
            case BEE -> getBee();
            case ZOMBIE_CRICKET -> getZombieCricket();
            default -> null;
        };
    }

    public Pet getPet(int tier) {
        return getPet(PetList.getRandPetByTier(tier));
    }
}
