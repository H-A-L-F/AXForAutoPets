package constants;

import models.Fruit;
import models.Pet;
import models.Team;

import java.util.*;

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

    public static void printSlots(HashMap<Integer, Pet> pets) {
        StringBuilder firstLn = new StringBuilder();
        StringBuilder secondLn = new StringBuilder();
        StringBuilder thirdLn = new StringBuilder();
        for(int i = Team.BACK_INDEX; i < Team.END_SIZE; i++) {
            Pet pet = pets.get(i);
            petPrintWrapper(firstLn, secondLn, thirdLn, pet);
        }
        System.out.println(firstLn);
        System.out.println(secondLn);
        System.out.println(thirdLn);
    }

    private static void printEmpty(StringBuilder firstLn, StringBuilder secondLn, StringBuilder thirdLn) {
        firstLn.append(String.format("[ %5s ] ", ""));
        secondLn.append(String.format("[ %d | %d ] ", 0, 0));
        thirdLn.append(String.format("[ %d:%d/%d ] ", 0, 0, 0));
    }

    static PetPrinter petPrinter = new PetPrinter() {
        @Override
        public void print(StringBuilder firstLn, StringBuilder secondLn, StringBuilder thirdLn, Pet pet) {
            String first = String.format("[ %-5s ]", center(pet.getName(), 5));
            String second = String.format("%d | %d", pet.getAtk(), pet.getHp());
            String third = String.format("%d:%d/%d", pet.getLv(), pet.getExp(), pet.getExpLimit());
            int len = first.length() - 4;
            String secondFormat = "[ %-" + len + "s ]";
            firstLn.append(first).append(" ");
            secondLn.append(String.format(secondFormat, center(second, len))).append(" ");
            thirdLn.append(String.format(secondFormat, center(third, len))).append(" ");
        }
    };

    private static void petPrintWrapper(StringBuilder firstLn, StringBuilder secondLn, StringBuilder thirdLn, Pet pet) {
        if (pet == null) printEmpty(firstLn, secondLn, thirdLn);
        else petPrinter.print(firstLn, secondLn, thirdLn, pet);
    }

    private static void petPrintWrapperBattle(StringBuilder firstLn, StringBuilder secondLn, StringBuilder thirdLn, Pet pet) {
        if (pet == null || pet.getStatus() == PetStatus.FAINT) printEmpty(firstLn, secondLn, thirdLn);
        else petPrinter.print(firstLn, secondLn, thirdLn, pet);
    }

    public static void printTeams(Team pteam, Team eTeam) {
        StringBuilder firstLn = new StringBuilder();
        StringBuilder secondLn = new StringBuilder();
        StringBuilder thirdLn = new StringBuilder();
        for (int i = 0; i < Team.END_SIZE; i++) {
            Pet pet = pteam.getBattlePet(i);
            petPrintWrapper(firstLn, secondLn, thirdLn, pet);
        }
        String vs = "-> <- ";
        firstLn.append(vs);
        secondLn.append(String.format("%" + vs.length() + "s", ""));
        for (int i = Team.FRONT_INDEX; i >= 0; i--) {
            Pet pet = eTeam.getBattlePet(i);
            petPrintWrapper(firstLn, secondLn, thirdLn, pet);
        }
        System.out.println(firstLn);
        System.out.println(secondLn);
//        System.out.println(thirdLn);
    }

    public static void printTeams(HashMap<Integer, Pet> pTeam, HashMap<Integer, Pet> eTeam) {
        StringBuilder firstLn = new StringBuilder();
        StringBuilder secondLn = new StringBuilder();
        StringBuilder thirdLn = new StringBuilder();
        for (int i = 0; i < Team.END_SIZE; i++) {
            Pet pet = pTeam.get(i);
            petPrintWrapper(firstLn, secondLn, thirdLn, pet);
        }
        String vs = "-> <- ";
        firstLn.append(vs);
        secondLn.append(String.format("%" + vs.length() + "s", ""));
        for (int i = Team.FRONT_INDEX; i >= 0; i--) {
            Pet pet = eTeam.get(i);
            petPrintWrapper(firstLn, secondLn, thirdLn, pet);
        }
        System.out.println(firstLn);
        System.out.println(secondLn);
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

interface PetPrinter {
    public abstract void print(StringBuilder firstLn, StringBuilder secondLn, StringBuilder thirdLn, Pet pet);
}
