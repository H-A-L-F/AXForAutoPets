package constants;

import main.Arena;
import models.Pet;
import models.Team;

public class PetFactory {
    private Team team;
    public Pet Ant;
    public Pet Pig;
    public Pet Fish;

    public PetFactory(Team team) {
        this.team = team;

        this.Ant = new Pet(PetList.ANT, 1, 2, 2) {
            @Override
            public void onFaint() {
                super.onFaint();
                team.getRandPet().buff(getLv(), getLv());
            }
        };

        this.Pig = new Pet(PetList.PIG, 1, 4, 1) {
            @Override
            public void onSell() {
                super.onSell();
                Arena.getInstance().incMoney(getLv());
            }
        };

        this.Fish = new Pet(PetList.FISH, 1, 2, 3) {
            @Override
            public void onLvUp() {
                super.onLvUp();
                for(int i = 0; i < 2; i++) {
                    team.getRandPet().buff(getLv() - 1, getLv() - 1);
                }
            }
        };
    }
}
