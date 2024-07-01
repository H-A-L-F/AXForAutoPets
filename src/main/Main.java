package main;

import console_input.ConsoleInput;
import constants.Connect;
import constants.Lib;
import constants.PetFactory;
import models.Team;
import models.UserWinModel;
import repository.MatchRepository;
import repository.RoundRepository;
import repository.UserRepository;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    private final ConsoleInput ci;
    private final Connect con;

    public Main() {
        ci = ConsoleInput.getInstance();
        con = Connect.getInstance();

        menuAuth();
    }

    private void title() {
        System.out.println(
                " █████  ██   ██ ███████  ██████  ██████   █████  ██    ██ ████████  ██████  ██████  ███████ ████████ ███████ \n" +
                "██   ██  ██ ██  ██      ██    ██ ██   ██ ██   ██ ██    ██    ██    ██    ██ ██   ██ ██         ██    ██      \n" +
                "███████   ███   █████   ██    ██ ██████  ███████ ██    ██    ██    ██    ██ ██████  █████      ██    ███████ \n" +
                "██   ██  ██ ██  ██      ██    ██ ██   ██ ██   ██ ██    ██    ██    ██    ██ ██      ██         ██         ██ \n" +
                "██   ██ ██   ██ ██       ██████  ██   ██ ██   ██  ██████     ██     ██████  ██      ███████    ██    ███████ \n");
    }

    private void optAuth() {
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
    }

    private void menuAuth() {
        boolean run = true;
        int opt = -1;

        while (run) {
            title();
            optAuth();
            opt = ci.getIntInRange(1, 3, ">> ");
            Lib.clear();

            switch (opt) {
                case 1:
                    menuLogin();
                    break;
                case 2:
                    menuRegister();
                    break;
                case 3:
                    run = false;
                    break;
            }
        }
    }

    private void menuLogin() {
        String query = "SELECT * FROM User WHERE name = '%s' and password = '%s'";
        String name = ci.getStringInRange(5, 20, "Name [5 - 20]: ");
        String password = ci.getStringInRange(5, 20, "Password [5 - 20]: ");
        ResultSet rs = con.execQueryWithRes(String.format(query, name, password));
        try {
            if(!con.rs.next()) {
                System.out.println("Invalid username or password");
                System.out.println("Login Failed");
                ci.enter();
                return;
            }
            int user_id = con.rs.getInt(1);
            String username = con.rs.getString(2);
            String userpass = con.rs.getString(3);
            UserRepository.getInstance(user_id, username, userpass);
            System.out.println("Logged in as " + username);
            ci.enter();
            menuHome();
        } catch (SQLException e) {
            System.out.println("Login failed");
            throw new RuntimeException(e);
        }
    }

    private void menuRegister() {
        String name = ci.getStringInRange(5, 20, "Name [5 - 20]: ");
        String password = ci.getStringInRange(5, 20, "Password [5 - 20]: ");
        if(!UserRepository.checkName(name)) {
            System.out.println("Name already exists");
        } else {
            UserRepository.getInstance().insert(name, password);
            System.out.println("Successfully registered");
        }
        ci.enter();
    }

    private void optHome() {
        System.out.println("1. Arena");
        System.out.println("2. Leaderboard");
        System.out.println("3. History");
        System.out.println("4. Logout");
    }

    private void menuHome() {
        boolean run = true;
        int opt = -1;

        while (run) {
            Lib.clear();
            title();
            optHome();
            opt = ci.getIntInRange(1, 4, ">> ");

            switch (opt) {
                case 1:
                    menuArena();
                    break;
                case 2:
                    menuLeaderboard();
                    break;
                case 3:
                    menuHistory();
                    break;
                case 4:
                    menuLogout();
                    run = false;
                    break;
                default:
                    break;
            }
        }
    }

    private void menuArena() {
        String name = ci.getStringInRange(5, 20, "Team Name [5 - 20]: ");
        Arena.getInstance(new Team(name)).newGame();
    }

    private void menuLeaderboard() {
        System.out.println("===Leaderboard===");
        ArrayList<UserWinModel> users = UserRepository.getTopWin(10);
        for(int i = 0; i < users.size(); i++) {
            UserWinModel u = users.get(i);
            System.out.printf("%d. [%s | %d]\n", i + 1, u.name, u.win);
        }
        System.out.println(1);
        ci.enter();
    }

    private void menuHistory() {
        ArrayList<MatchRepository> matches = MatchRepository.getMatchesForUser(UserRepository.getInstance().getId());
        while (true) {
            menuViewHistory(matches);
            System.out.println("-1. Exit");
            System.out.println("no. View Match Detail");
            int opt = ci.getIntInRange(-1, matches.size(), ">> ");
            if(opt == -1) return;
            opt--;
            MatchRepository match = matches.get(opt);
            menuMatchDetail(match);
        }
    }

    private void menuLogout() {
        UserRepository.logout();
    }

    private void menuViewHistory(ArrayList<MatchRepository> matches) {
        System.out.println("=== History ===");
        for (int i = 0; i < matches.size(); i++) {
            MatchRepository m = matches.get(i);
            System.out.printf("%d. %s | wins: %d\n", i + 1, m.getTeamName(), m.getWin());
        }
        System.out.println("===============");
        System.out.println();
    }

    private void menuMatchDetail(MatchRepository match) {
        ArrayList<RoundRepository> rounds = RoundRepository.getRoundsForMatch(match.getId());
        while (true) {
            menuViewRounds(rounds, match);
            System.out.println("-1. Exit");
            System.out.println("no. View Round Replay");
            int opt = ci.getIntInRange(-1, rounds.size(), ">> ");
            if(opt == -1) return;
            opt--;
            RoundRepository pr = rounds.get(opt);
            RoundRepository er = RoundRepository.getRoundRepoById(pr.getEnmRoundId());
            Arena arena = Arena.newInstance(new Team(match.getTeamName()));
            arena.replayRound(pr, er);
        }
    }

    private void menuViewRounds(ArrayList<RoundRepository> rounds, MatchRepository match) {
        System.out.println("=== Match Detail ===");
        for (int i = 0; i < rounds.size(); i++) {
            System.out.println("Round " + (i + 1));
            RoundRepository pr = rounds.get(i);
            RoundRepository er = RoundRepository.getRoundRepoById(pr.getEnmRoundId());
            Arena arena = Arena.newInstance(new Team(match.getTeamName()));
            arena.viewRound(pr, er);
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}