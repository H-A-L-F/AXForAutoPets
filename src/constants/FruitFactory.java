package constants;

import main.Arena;
import models.Fruit;
import models.Pet;
import models.Team;

public class FruitFactory {
    private Arena arena;
    private final Team pTeam;
    private final Team eTeam;

    public FruitFactory(Arena arena, Team pTeam, Team eTeam) {
        this.arena = arena;
        this.pTeam = pTeam;
        this.eTeam = eTeam;
    }

    public Fruit getApple() {
        return new Fruit(FruitList.APPLE, 1) {
            @Override
            public void onEaten(Pet pet) {
                super.onEaten(pet);
                pet.buff(1, 1);
                System.out.printf("%s eats %s, getting %d atk & %d hp\n", pet.getName(), getName(), 1, 1);
            }
        };
    }

    public Fruit getHoney() {
        return new Fruit(FruitList.HONEY, 1) {
            @Override
            public void onEaten(Pet pet) {
                super.onEaten(pet);
                pet.setFruit(this);
                System.out.printf("%s eats %s, now it has the honey perk\n", pet.getName(), getName());
            }

            @Override
            public void onFaint(Pet pet) {
                super.onFaint(pet);
                Pet temp = PetFactory.getBee();
                pTeam.summonPet(temp, pet.getPos());
            }
        };
    }

    public static Fruit getBreadCrumbs() {
        return new Fruit(FruitList.BREAD_CRUMBS, 1) {
            @Override
            public void onEaten(Pet pet) {
                super.onEaten(pet);
                pet.buff(1, 0);
                System.out.printf("%s eats %s, getting %d atk\n", pet.getName(), getName(), 1);
            }
        };
    }

    public static Fruit getWormApple(Pet pet) {
        return new Fruit(FruitList.WORM_APPLE, 1) {
            @Override
            public void onEaten(Pet pet) {
                super.onEaten(pet);
                pet.buff(pet.getLv(), pet.getLv());
                System.out.printf("%s eats %s, getting %d atk & %d hp\n", pet.getName(), getName(), pet.getLv(), pet.getLv());
            }
        };
    }

    public Fruit getMeatBone() {
        return new Fruit(FruitList.MEAT_BONE, 2) {
            @Override
            public void onEaten(Pet pet) {
                super.onEaten(pet);
                pet.setFruit(this);
                pet.buff(3, 0);
                System.out.printf("%s eats %s, now it has the %s perk\n", pet.getName(), getName(), getName());
            }

            @Override
            public void onReplaced(Pet pet) {
                super.onReplaced(pet);
                pet.buff(-3, 0);
            }

            @Override
            public void onAttack(Pet pet) {
                super.onAttack(pet);
                System.out.println(pet.getName() + " attacks with +3 dmg from meatbone.");
            }
        };
    }

    public Fruit getCupcake() {
        return new Fruit(FruitList.CUPCAKE, 2) {
            @Override
            public void onEaten(Pet pet) {
                super.onEaten(pet);
                pet.setFruit(this);
                pet.buff(3, 3);
                System.out.printf("%s eats %s, getting %d atk & %d hp until end of battle\n", pet.getName(), getName(), 3, 3);
            }

            @Override
            public void onBattleEnd(Pet pet) {
                super.onBattleEnd(pet);
                pet.buff(-3, -3);
                pet.removeFruit(this);
            }
        };
    }

    public Fruit getSaladBowl() {
        return new Fruit(FruitList.SALAD_BOWL, 3) {
            @Override
            public void onEaten(Pet pet) {
                super.onEaten(pet);
                pTeam.doPets(
                        () -> pTeam.getRandPets(2),
                        (p) -> {
                            p.buff(1, 1);
                            System.out.printf("%s eats %s, getting %d atk & %d hp\n", p.getName(), getName(), 1, 1);
                        }
                );
            }
        };
    }

    public Fruit getGarlic() {
        return new Fruit(FruitList.GARLIC, 3) {
            @Override
            public void onEaten(Pet pet) {
                super.onEaten(pet);
                pet.setFruit(this);
                System.out.printf("%s eats %s, now it has the %s perk taking 3 less dmg\n", pet.getName(), getName(), getName());
            }

            @Override
            public int onDamaged(int dmg) {
                int res = dmg - 3;
                return Math.max(res, 0);
            }
        };
    }

    public Fruit getFruit(FruitList name) {
        return switch (name) {
            case APPLE -> getApple();
            case HONEY -> getHoney();

            case MEAT_BONE -> getMeatBone();
            case CUPCAKE -> getCupcake();

            case SALAD_BOWL -> getSaladBowl();
            case GARLIC -> getGarlic();

//            case CANNED_FOOD -> getCannedFood();
//            case PEAR -> getPear();
//            case CHILI -> getChili();
//            case CHOCOLATE -> getChocolate();
//            case SUSHI -> getSushi();
//            case MELON -> getMelon();
//            case MUSHROOM -> getMushroom();
//            case PIZZA -> getPizza();
//            case STEAK -> getSteak();

            case BREAD_CRUMBS -> getBreadCrumbs();
//            case WORM_APPLE -> getWormApple();
            default -> null;
        };
    }

    public Fruit getFruit(int tier) {
        return getFruit(FruitList.getRandFoodByTier(tier));
    }
}
