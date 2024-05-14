package constants;

import main.Arena;
import models.Pet;
import models.Team;

public class PetFactory {
    private Team team;

    public PetFactory(Team team) {
        this.team = team;
    }

    public Pet getAnt() {
        return new Pet(PetList.ANT, 1, 2, 2) {
            @Override
            public void onFaint(int pos) {
                super.onFaint(pos);
                team.getRandPet().buff(getLv(), getLv());
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
                    team.getRandPet().buff(getLv() - 1, getLv() - 1);
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
                team.addPet(temp, pos);
            }
        };
    }

    public Pet getZombieCricket() {
        return new Pet(PetList.ZOMBIE_CRICKET, 1, 1, 1) {
        };
    }
}
