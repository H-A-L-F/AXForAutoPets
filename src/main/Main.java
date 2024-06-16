package main;

import console_input.ConsoleInput;
import constants.Connect;
import constants.Lib;
import models.Team;
import repository.UserRepository;

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
        System.out.println("AXForAutoPets");
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
        con.execQuery(String.format(query, name, password));
        try {
            if(!con.rs.next()) {
                System.out.println("Invalid username or password");
                System.out.println("Login Failed");
                ci.enter();
                return;
            }
            int id = con.rs.getInt(1);
            String username = con.rs.getString(2);
            String userpass = con.rs.getString(3);
            UserRepository.getInstance(id, username, userpass);
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
            optHome();
            opt = ci.getIntInRange(1, 4, ">> ");
            Lib.clear();

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
        ArrayList<UserRepository> users = UserRepository.getTopWin(10);
        for(int i = 0; i < users.size(); i++) {
            UserRepository u = users.get(i);
            System.out.printf("%d. [%s | %d]\n", i + 1, u.getName(), u.getWins());
        }
        System.out.println(1);
        ci.enter();
    }

    private void menuHistory() {

    }

    private void menuLogout() {
        UserRepository.logout();
    }

    public static void main(String[] args) {
        new Main();
    }
}