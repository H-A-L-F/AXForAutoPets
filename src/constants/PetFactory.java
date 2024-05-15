package constants;

import main.Arena;
import models.Pet;
import models.Team;

public class PetFactory {
    private Arena arena;
    private Team pTeam;
    private Team eTeam;

    public PetFactory(Arena arena) {
        this.arena = arena;
        this.pTeam = arena.getPlayerTeam();
        this.eTeam = arena.getEnmTeam();
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
                Arena.getInstance().incMoney(getLv());
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

    public Pet getPet(PetList name) {
        return switch (name) {
            case ANT -> getAnt();
            case PIG -> getPig();
            case FISH -> getFish();
            case CRICKET -> getCricket();
            default -> null;
        };
    }
}
