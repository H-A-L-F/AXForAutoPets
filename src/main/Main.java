package main;

import console_input.ConsoleInput;

public class Main {
    private final ConsoleInput ci;

    public Main() {
        ci = ConsoleInput.getInstance();

        menuHome();
    }

    private void title() {
        System.out.println("AXForAutoPets");
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

            switch (opt) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    run = false;
                    break;
                default:
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}