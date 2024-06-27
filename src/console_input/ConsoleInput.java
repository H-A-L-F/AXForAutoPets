package console_input;

import java.util.Scanner;

public class ConsoleInput {
    private Scanner scanner;
    private static ConsoleInput instance;

    private static final int FAIL_NUMBER = -999;

    private ConsoleInput() {
        scanner = new Scanner(System.in);
    }

    public static ConsoleInput getInstance() {
        if (instance == null) instance = new ConsoleInput();
        return instance;
    }

    private int handledGetInt() {
        try {
            int res = scanner.nextInt();
            scanner.nextLine();
            return res;
        } catch (Exception ex) {
            scanner.nextLine();
            return FAIL_NUMBER;
        }
    }

    public int getInt(ValidatorInt validator, String str) {
        int res = FAIL_NUMBER;
        do {
            System.out.printf(str);
            res = handledGetInt();
        } while (!validator.validateInt(res));
        return res;
    }

    public int getIntInRange(int min, int max, String str) {
        int res = min - 1;
        do {
            System.out.printf(str);
            res = handledGetInt();
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
