package main;

import console_input.ConsoleInput;
import constants.Connect;
import constants.Lib;

public class Main {
    private final ConsoleInput ci;
    private final Connect con;

    public Main() {
        ci = ConsoleInput.getInstance();
        con = Connect.getInstance();

        menuHome();
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
                    break;
                case 2:
                    break;
                case 3:
                    run = false;
                    break;
            }
        }
    }

    private void menuLogin() {
        String query = "SELECT * FROM user";
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