package constants;

import models.Pet;

public class Lib {

    public static String center(String s, int size) {
        return center(s, size, ' ');
    }

    public static String center(String s, int size, char pad) {
        if (s == null || size <= s.length())
            return s;

        StringBuilder sb = new StringBuilder(size);
        sb.append(String.valueOf(pad).repeat((size - s.length()) / 2));
        sb.append(s);
        while (sb.length() < size) {
            sb.append(pad);
        }
        return sb.toString();
    }

    public static void printSlot(Pet pet) {
        StringBuilder firstLn = new StringBuilder();
        StringBuilder secondLn = new StringBuilder();
        if(pet == null) {
            firstLn.append(String.format("[ %5s ]", ""));
            secondLn.append(String.format("[ %d | %d ]", 0, 0));
        } else {
            String first = "[ " + pet.getNameStr() + " ]";
            String second = pet.getAtk() + " | " + pet.getHp();
            int len = firstLn.length();
            String secondFormat = "[ $%-" + len + "s ]";
            firstLn.append(first);
            secondLn.append(String.format(secondFormat, center(second, len)));
        }
    }
}
