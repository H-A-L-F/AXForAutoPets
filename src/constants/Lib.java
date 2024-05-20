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

    public static void printSlots(List<Pet> pets) {
        StringBuilder firstLn = new StringBuilder();
        StringBuilder secondLn = new StringBuilder();
        for (Pet pet : pets) {
            if(pet == null) {
                firstLn.append(String.format("[ %5s ] ", ""));
                secondLn.append(String.format("[ %d | %d ] ", 0, 0));
            } else {
                String first = "[ " + pet.getName() + " ]";
                String second = pet.getAtk() + " | " + pet.getHp();
                int len = firstLn.length();
                String secondFormat = "[ $%-" + len + "s ]";
                firstLn.append(first).append(" ");
                secondLn.append(String.format(secondFormat, center(second, len))).append(" ");
            }
        }
        System.out.println(firstLn.toString());
        System.out.println(secondLn.toString());
    }

    public static void printFruits(List<Fruit> fruits) {
        for (Fruit fruit : fruits) {
            System.out.printf(fruit.getName() + " ");
        }
        System.out.println();
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
