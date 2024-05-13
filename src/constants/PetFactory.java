package constants;

import main.Arena;
import models.Pet;
import models.Team;

public class PetFactory {
    private Team team;

    // TIER 1
    public Pet ant;
    public Pet pig;
    public Pet fish;
    public Pet zombieCricket;

    // TIER 2

    public PetFactory(Team team) {
        this.team = team;

        this.ant = new Pet(PetList.ANT, 1, 2, 2) {
            @Override
            public void onFaint() {
                super.onFaint();
                team.getRandPet().buff(getLv(), getLv());
            }
        };

        this.pig = new Pet(PetList.PIG, 1, 4, 1) {
            @Override
            public void onSell() {
                super.onSell();
                Arena.getInstance().incMoney(getLv());
            }
        };

        this.fish = new Pet(PetList.FISH, 1, 2, 3) {
            @Override
            public void onLvUp() {
                super.onLvUp();
                for(int i = 0; i < 2; i++) {
                    team.getRandPet().buff(getLv() - 1, getLv() - 1);
                }
            }
        };

        this.zombieCricket = new Pet(PetList.ZOMBIE_CRICKET, 1, 1, 1) {
        };
    }
}
