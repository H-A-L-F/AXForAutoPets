package main;

import constants.FruitFactory;
import constants.PetFactory;
import constants.PetStatus;
import models.Team;

public class Arena {
    private Team pTeam;
    private Team enmTeam;
    private int round, win, life;
    private int money;
    private static Arena instance;

    private Shop shop;
    private PetFactory playerPetFactory;
    private FruitFactory fruitFactory;

    private Arena() {
        pTeam = new Team();
        enmTeam = new Team();
        round = 0;
        win = 0;
        life = 5;
        money = 10;

        playerPetFactory = new PetFactory(this, pTeam, enmTeam);
        fruitFactory = new FruitFactory(this, pTeam, enmTeam);
        shop = new Shop(this, this.playerPetFactory, this.fruitFactory, this.pTeam);
    }

    public static Arena getInstance() {
        if (instance == null) instance = new Arena();
        return instance;
    }

    public void newGame() {
        reset();
        play();
    }

    private void reset() {
        pTeam = new Team();
        enmTeam = new Team();
        round = 0;
        win = 0;
        life = 5;
        money = 10;

        playerPetFactory = new PetFactory(this, pTeam, enmTeam);
        fruitFactory = new FruitFactory(this, pTeam, enmTeam);
        shop = new Shop(this, this.playerPetFactory, this.fruitFactory, this.pTeam);
    }

    private void play() {
        while (life > 0) {
            nextRound();
            shop();
            BattleResult res = battle();
            switch (res) {
                case WIN:
                    break;
                case LOSE:
                    break;
                case DRAW:
                    break;
            }
        }
    }

    private void shop() {
        shop.startShop();
    }

    private void nextRound() {
        round++;
        shop.nextRound(round);
    }

    private BattleResult battle() {
        while (pTeam.getPet(Team.END_SIZE).getStatus() == PetStatus.NORMAL && enmTeam.getPet(Team.END_SIZE).getStatus() == PetStatus.NORMAL) {
            int pAtk = pTeam.getAtk(Team.END_SIZE);
            int enmAtk = enmTeam.getAtk(Team.END_SIZE);
            enmTeam.takeDamage(pAtk, Team.END_SIZE);
            pTeam.takeDamage(enmAtk, Team.END_SIZE);
        }
        if (pTeam.getPet(Team.END_SIZE).getStatus() == PetStatus.NORMAL) return BattleResult.WIN;
        else if (enmTeam.getPet(Team.END_SIZE).getStatus() == PetStatus.NORMAL) return BattleResult.LOSE;
        else return BattleResult.DRAW;
    }

    public void printStats() {
        System.out.printf("[Money: %d | Round: %d | Life %d | Win %d]\n", money, round, life, win);
    }

    public void incMoney(int amt) {
        this.money += amt;
    }

    public int getMoney() {
        return this.money;
    }

    public Shop getShop() {
        return this.shop;
    }
}

enum BattleResult {
    WIN,
    LOSE,
    DRAW;
}