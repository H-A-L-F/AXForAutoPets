package main;

import console_input.ConsoleInput;
import constants.*;
import models.Pet;
import models.Team;
import repository.MatchRepository;
import repository.RoundRepository;
import repository.UserRepository;

public class Arena {
    private Team pTeam;
    private Team enmTeam;
    private int round, win, life;
    private int money;
    private boolean isQuit;
    private BattleResult lastBattleResult;
    private static Arena instance;

    private Shop shop;
    private PetFactory playerPetFactory, enmPetFactory;
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
        isQuit = false;

        playerPetFactory = new PetFactory(this, pTeam, enmTeam);
        enmPetFactory = new PetFactory(this, enmTeam, pTeam);
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

    public static Arena newInstance(Team pTeam) {
        instance = new Arena(pTeam);
        return instance;
    }

    public void viewRound(RoundRepository playerR, RoundRepository enmR) {
        pTeam.setTeamFromRound(playerPetFactory, fruitFactory, playerR);
        enmTeam.setTeamFromRound(enmPetFactory, fruitFactory, enmR);
        pTeam.initBattleTeam();
        pTeam.arrangeBattleTeam();
        enmTeam.initBattleTeam();
        enmTeam.arrangeBattleTeam();
        Lib.printTeams(pTeam, enmTeam);
    }

    public void replayRound(RoundRepository playerR, RoundRepository enmR) {
        pTeam.setTeamFromRound(playerPetFactory, fruitFactory, playerR);
        enmTeam.setTeamFromRound(enmPetFactory, fruitFactory, enmR);
        battleSystem();
    }

    private void battleSystem() {
        pTeam.initBattleTeam();
        enmTeam.initBattleTeam();
        lastBattleResult = battle();
        pTeam.resetPets();
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

    public void newGame() {
        if(DevelopmentState.state.contains(DevelopmentState.INSERT_MATCH)) MatchRepository.newInstance(UserRepository.getInstance().getId(), pTeam.getName());
        reset();
        play();
        updateWins();
    }

    private void reset() {
        pTeam = new Team(pTeam.getName());
        enmTeam = new Team(enmTeam.getName());
        round = 0;
        win = 0;
        life = 5;
        money = DEFAULT_MONEY;

        playerPetFactory = new PetFactory(this, pTeam, enmTeam);
        enmPetFactory = new PetFactory(this, enmTeam, pTeam);
        fruitFactory = new FruitFactory(this, pTeam, enmTeam);
        shop = new Shop(this, this.playerPetFactory, this.fruitFactory, this.pTeam);
    }

    private void play() {
        while (life > 0 && win < 10) {
            nextRound();
            shop();
            if(isQuit) break;
            battleSystem();
            battleEnd();
        }
    }

    public void quit() {
        isQuit = true;
    }

    private void updateWins() {
        MatchRepository.getInstance().updateWin(win);
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
        RoundRepository enmRoundRepo = null;
        int enmRoundId = -1;
        if(DevelopmentState.state.contains(DevelopmentState.GET_ENM_ROUND)) {
            enmRoundRepo = RoundRepository.getRandRoundRepository(round);
            enmRoundId = enmRoundRepo.getId();
        }
        if(DevelopmentState.state.contains(DevelopmentState.INSERT_ROUND)) RoundRepository.newInstance(MatchRepository.getInstance().getId(), enmRoundId, round);
        if(DevelopmentState.state.contains(DevelopmentState.GET_ENM_ROUND)) enmTeam.setTeamFromRound(enmPetFactory, fruitFactory, enmRoundRepo);
        shop.nextRound(round);
    }

    private BattleResult battle() {
        pTeam.arrangeBattleTeam();
        enmTeam.arrangeBattleTeam();
        System.out.printf("Team %s vs Team %s\n", pTeam.getName(), enmTeam.getName());
        Lib.printTeams(pTeam, enmTeam);
        pTeam.onBattleStart();
        enmTeam.onBattleStart();
        pTeam.arrangeBattleTeam();
        enmTeam.arrangeBattleTeam();

        Pet currP = pTeam.getBattlePet();
        Pet currEnm = enmTeam.getBattlePet();

        while ((currP != null && currP.getStatus() == PetStatus.NORMAL) && (currEnm != null && currEnm.getStatus() == PetStatus.NORMAL)) {
            pTeam.arrangeBattleTeam();
            enmTeam.arrangeBattleTeam();
            Lib.printTeams(pTeam, enmTeam);
            currP.onAttack(currEnm);
            currEnm.onAttack(currP);
            currEnm.damage(currP.getAtk());
            pTeam.onAttack(currP);
            currP.damage(currEnm.getAtk());
            enmTeam.onAttack(currEnm);
            if(currP.getStatus() == PetStatus.FAINT) currP = pTeam.getBattlePet();
            if(currEnm.getStatus() == PetStatus.FAINT) currEnm = enmTeam.getBattlePet();
        }
        if (currP != null && currP.getStatus() == PetStatus.NORMAL) return BattleResult.WIN;
        else if (currEnm != null && currEnm.getStatus() == PetStatus.NORMAL) return BattleResult.LOSE;
        else return BattleResult.DRAW;
    }

    private void battleEnd() {
        pTeam.onBattleEnd();
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