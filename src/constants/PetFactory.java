package constants;

import models.Pet;
import models.Team;

public class PetFactory {
    private Team team;
    public Pet Ant;

    public PetFactory(Team team) {
        this.team = team;

        this.Ant = new Pet(PetList.ANT, 2, 2) {
            @Override
            public void onFaint() {
                switch (this.getLv()) {
                    case 1:
                        team.getRandPet().buff(1, 1);
                        break;
                    case 2:
                        team.getRandPet().buff(2, 2);
                        break;
                    case 3:
                        team.getRandPet().buff(3, 3);
                        break;
                }
            }
        };
    }
}
