package main;

import console_input.ConsoleInput;
import constants.Connect;
import constants.Lib;
import models.User;

import java.sql.SQLException;

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
            con.rs.next();
            int id = con.rs.getInt(1);
            String username = con.rs.getString(2);
            String userpass = con.rs.getString(3);
            User.getInstance(id, name, userpass);
            System.out.println("Logged in as " + username);
            ci.enter();
            menuHome();
        } catch (SQLException e) {
            System.out.println("Login failed");
            throw new RuntimeException(e);
        }
    }

    private void menuRegister() {
        String query = "INSERT INTO User (name, password) values('%s', '%s')";
        String name = ci.getStringInRange(5, 20, "Name [5 - 20]: ");
        String password = ci.getStringInRange(5, 20, "Password [5 - 20]: ");
        con.execUpdate(String.format(query, name, password));
    }

    private void optHome() {
        System.out.println("1. Arena");
        System.out.println("2. Leaderboard");
        System.out.println("3. Logout");
    }

    private void menuHome() {
        boolean run = true;
        int opt = -1;

        while (run) {
            optHome();
            opt = ci.getIntInRange(1, 3, ">> ");
            Lib.clear();

            switch (opt) {
                case 1:
                    menuArena();
                    break;
                case 2:
                    menuLeaderboard();
                    break;
                case 3:
                    menuLogout();
                    run = false;
                    break;
                default:
                    break;
            }
        }
    }

    private void menuArena() {
        Arena.getInstance().newGame();
    }

    private void menuLeaderboard() {

    }

    private void menuLogout() {

    }

    public static void main(String[] args) {
        new Main();
    }
}