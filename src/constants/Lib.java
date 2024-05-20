package constants;

import models.Fruit;
import models.Pet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

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

    public static void printSlots(Pet[] pets) {
        StringBuilder firstLn = new StringBuilder();
        StringBuilder secondLn = new StringBuilder();
        for (Pet pet : pets) {
            if(pet == null) {
                firstLn.append(String.format("[ %5s ] ", ""));
                secondLn.append(String.format("[ %d | %d ] ", 0, 0));
            } else {
                String first = String.format("[ %-5s ]", pet.getName());
                String second = String.format("%d | %d", pet.getAtk(), pet.getHp());
                int len = first.length() - 4;
                String secondFormat = "[ %-" + len + "s ]";
                firstLn.append(first).append(" ");
                secondLn.append(String.format(secondFormat, center(second, len))).append(" ");
            }
        }
        System.out.println(firstLn.toString());
        System.out.println(secondLn.toString());
    }

    public static void printFruits(Fruit[] fruits) {
        for (Fruit fruit : fruits) {
            if(fruit == null) continue;
            System.out.printf(fruit.getName() + " ");
        }
        System.out.println();
    }

    public static void printDivider() {
        System.out.println("------------------------------");
    }

    public static void clear() {
        for (int i = 0; i < 25; i++) {
            System.out.println();
        }
    }

    @SafeVarargs
    public static <T> List<T> combineLists(List<T>... lists) {
        List<T> combinedList = new ArrayList<>();
        for (List<T> list : lists) {
            combinedList.addAll(list);
        }
        return Collections.unmodifiableList(combinedList);
    }
}
