package main;

import console_input.ConsoleInput;
import constants.*;
import models.Pet;
import models.Team;
import repository.MatchRepository;
import repository.UserRepository;

public class Arena {
    private Team pTeam;
    private Team enmTeam;
    private int round, win, life;
    private int money;
    private BattleResult lastBattleResult;
    private static Arena instance;

    private Shop shop;
    private PetFactory playerPetFactory;
    private FruitFactory fruitFactory;
    private ConsoleInput in;

    private static final int DEFAULT_MONEY = 10;

    private Arena(Team pTeam) {
        this.pTeam = pTeam;
        enmTeam = new Team();
        round = 0;
        win = 0;
        life = 5;
        money = DEFAULT_MONEY;

        playerPetFactory = new PetFactory(this, pTeam, enmTeam);
        fruitFactory = new FruitFactory(this, pTeam, enmTeam);
        shop = new Shop(this, this.playerPetFactory, this.fruitFactory, this.pTeam);
        in = ConsoleInput.getInstance();
    }

    public static Arena getInstance() {
        return instance;
    }

    public static Arena getInstance(Team pTeam) {
        if (instance == null) instance = new Arena(pTeam);
        return instance;
    }

    public void newGame() {
        MatchRepository.newInstance(UserRepository.getInstance().getId(), pTeam.getName());
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
            lastBattleResult = battle();
            switch (lastBattleResult) {
                case WIN:
                    win();
                    break;
                case LOSE:
                    lose();
                    break;
                case DRAW:
                    draw();
                    break;
            }
            in.enter();
        }
    }

    private void win() {
        win++;
        System.out.println("Your team won!");
    }

    private void lose() {
        life--;
        System.out.println("Your team lost!");
    }

    private void draw() {
        System.out.println("It's a draw!");
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
        pTeam.arrangeBattleTeam();
        enmTeam.arrangeBattleTeam();

        if(pTeam.getPet(Team.FRONT_INDEX) == null && enmTeam.getPet(Team.FRONT_INDEX) == null) return BattleResult.DRAW;
        else if(pTeam.getPet(Team.FRONT_INDEX) == null) return BattleResult.LOSE;
        else if(enmTeam.getPet(Team.FRONT_INDEX) == null) return BattleResult.WIN;

        while (pTeam.getPet(Team.FRONT_INDEX).getStatus() == PetStatus.NORMAL && enmTeam.getPet(Team.FRONT_INDEX).getStatus() == PetStatus.NORMAL) {
            pTeam.arrangeBattleTeam();
            enmTeam.arrangeBattleTeam();
            Lib.printTeams(pTeam, enmTeam);
            Pet pPet = pTeam.getPet(Team.FRONT_INDEX);
            Pet ePet = enmTeam.getPet(Team.FRONT_INDEX);
            int pAtk = pTeam.getAtk(Team.FRONT_INDEX);
            int enmAtk = enmTeam.getAtk(Team.FRONT_INDEX);
            enmTeam.takeDamage(pAtk, Team.FRONT_INDEX);
            pTeam.takeDamage(enmAtk, Team.FRONT_INDEX);
            pTeam.doPet(
                    () -> pPet,
                    Pet::onAfterAttack
            );
            enmTeam.doPet(
                    () -> ePet,
                    Pet::onAfterAttack
            );
        }
        if (pTeam.getPet(Team.FRONT_INDEX).getStatus() == PetStatus.NORMAL) return BattleResult.WIN;
        else if (enmTeam.getPet(Team.FRONT_INDEX).getStatus() == PetStatus.NORMAL) return BattleResult.LOSE;
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

    public BattleResult getLastBattleResult() {
        return lastBattleResult;
    }

    public int getMoney() {
        return this.money;
    }

    public Shop getShop() {
        return this.shop;
    }
}