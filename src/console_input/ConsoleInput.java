package console_input;

import java.util.Scanner;

public class ConsoleInput {
    private Scanner scanner;
    private static ConsoleInput instance;

    private ConsoleInput() {
        scanner = new Scanner(System.in);
    }

    public static ConsoleInput getInstance() {
        if (instance == null) instance = new ConsoleInput();
        return instance;
    }

    public int getInt(ValidatorInt validator, String str) {
        int res = -1;
        do {
            System.out.printf(str);
            res = scanner.nextInt();
            scanner.nextLine();
        } while (!validator.validateInt(res));
        return res;
    }

    public int getIntInRange(int min, int max, String str) {
        int res = min - 1;
        do {
            System.out.printf(str);
            res = scanner.nextInt();
            scanner.nextLine();
        } while (res < min || res > max);
        return res;
    }

    public String getString(ValidatorString validator, String str) {
        String res = "";
        do {
            System.out.printf(str);
            res = scanner.nextLine();
        } while(!validator.validateStr(res));
        return res;
    }

    public String getStringInRange(int min, int max, String str) {
        ValidatorString validator = (s) -> s.length() >= min && s.length() <= max;
        return getString(validator, str);
    }

    public void enter() {
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }
}
