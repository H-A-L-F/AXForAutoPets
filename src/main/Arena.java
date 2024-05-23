package main;

import constants.FruitFactory;
import constants.Lib;
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

    private static final int DEFAULT_MONEY = 10;

    private Arena() {
        pTeam = new Team();
        enmTeam = new Team();
        round = 0;
        win = 0;
        life = 5;
        money = DEFAULT_MONEY;

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
        money = DEFAULT_MONEY;

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
        resetMoney();
        shop.startShop();
    }

    private void nextRound() {
        round++;
        shop.nextRound(round);
    }

    private BattleResult battle() {
        int toFight = Team.END_SIZE - 1;
        pTeam.arrangeBattleTeam();
        enmTeam.arrangeBattleTeam();
        while (pTeam.getPet(toFight).getStatus() == PetStatus.NORMAL && enmTeam.getPet(toFight).getStatus() == PetStatus.NORMAL) {
            pTeam.arrangeBattleTeam();
            enmTeam.arrangeBattleTeam();
            Lib.printTeams(pTeam, enmTeam);
            int pAtk = pTeam.getAtk(toFight);
            int enmAtk = enmTeam.getAtk(toFight);
            enmTeam.takeDamage(pAtk, toFight);
            pTeam.takeDamage(enmAtk, toFight);
        }
        if (pTeam.getPet(toFight) != null && pTeam.getPet(toFight).getStatus() == PetStatus.NORMAL) return BattleResult.WIN;
        else if (enmTeam.getPet(toFight) != null && enmTeam.getPet(toFight).getStatus() == PetStatus.NORMAL) return BattleResult.LOSE;
        else return BattleResult.DRAW;
    }

    public void printStats() {
        System.out.printf("[Money: %d | Round: %d | Life %d | Win %d]\n", money, round, life, win);
    }

    public void incMoney(int amt) {
        this.money += amt;
    }

    private void resetMoney() {
        this.money = DEFAULT_MONEY;
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