package models;

import constants.FruitFactory;
import constants.Lib;
import constants.PetFactory;
import constants.PetStatus;
import interfaces.*;
import main.EventManager;
import repository.FruitRepository;
import repository.PetRepository;
import repository.RoundRepository;

import java.util.ArrayList;
import java.util.Comparator;

public class Team {
    String name;
    private EventManager eventManager;
    private Pet[] pets;
    private int slot;

    private ArrayList<OnBattleStart> onBattleStarts;
    private ArrayList<OnSummon> onSummons;
    private ArrayList<OnTurnStart> onTurnStarts;
    private ArrayList<OnTurnEnd> onTurnEnds;
    private ArrayList<OnFriendAttack> onFriendAttacks;
    private ArrayList<OnFriendEatFruit> onFriendEatFruits;
    private ArrayList<OnFriendFaint> onFriendFaints;

    public static final int START_SIZE = 1;
    public static final int END_SIZE = 5;
    public static final int FRONT_INDEX = END_SIZE - 1;
    public static final int BACK_INDEX = START_SIZE - 1;

    public Team() {
        this.eventManager = new EventManager(this);
        pets = new Pet[END_SIZE];
        slot = END_SIZE;

        onBattleStarts = new ArrayList<>();
        onSummons = new ArrayList<>();
        onTurnStarts = new ArrayList<>();
        onTurnEnds = new ArrayList<>();
        onFriendAttacks = new ArrayList<>();
        onFriendEatFruits = new ArrayList<>();
        onFriendFaints = new ArrayList<>();
    }

    public Team(String name) {
        this.name = name;
        this.eventManager = new EventManager(this);
        pets = new Pet[END_SIZE];
        slot = END_SIZE;

        onBattleStarts = new ArrayList<>();
        onSummons = new ArrayList<>();
        onTurnStarts = new ArrayList<>();
        onTurnEnds = new ArrayList<>();
        onFriendAttacks = new ArrayList<>();
        onFriendEatFruits = new ArrayList<>();
        onFriendFaints = new ArrayList<>();
    }

    public void setRandTeamFromDB(PetFactory petF, FruitFactory fruF, int round) {
        RoundRepository roundRepo = RoundRepository.getRandRoundRepository(round);
        Pet[] temp = PetRepository.getPetsForRound(petF, fruF, roundRepo.getId());
        for(int i = 0; i < END_SIZE; i++) {
            Pet curr = temp[i];
            if(curr == null) continue;
            placePet(curr, curr.getPos());
        }
    }

    public void placePet(Pet pet, int pos) {
        pets[pos] = pet;
        if(pets[pos] instanceof OnPlaced) ((OnPlaced) pets[pos]).onPlaced();
    }

    public void printTeam() {
        Lib.printSlots(pets);
    }

    public void saveTeam() {
        for (Pet p : pets) {
            if(p == null) continue;
            PetRepository curr = PetRepository.newInstance(RoundRepository.getInstance().getId(), p.getName(), p.getAtk(), p.getHp(), p.getLv(), p.getExp(), p.getPos());
            if(p.getFruit() != null) FruitRepository.newInstance(curr.getId(), p.getFruit().getName());
        }
    }

    public EventManager getEventManager() {
        return this.eventManager;
    }

    public void feedPetAt(Fruit fruit, int idx) {
        pets[idx].eatFruit(fruit);
    }

    public int getAtk(int idx) {
        return pets[idx].attack();
    }

    public int takeDamage(int damage, int idx) {
        return pets[idx].damage(damage);
    }

    public void doAll(DoPet doPet) {
        for(int i = BACK_INDEX; i < END_SIZE; i++) {
            if(pets[i] == null) continue;
            doPet.doPet(pets[i]);
        }
    }

    public void doPet(Pet origin, GetPet getPet, DoPet doPet) {
        Pet pet;
        do {
            pet = getPet.getPet();
        } while(pet != null && pet != origin && pet.getStatus() != PetStatus.FAINT);
        doPet.doPet(pet);
    }

    public void arrangeBattleTeam() {
        for (int i = FRONT_INDEX; i >= 0; i--) {
            if(pets[i] != null) continue;
            for(int j = i - 1; j >= 0; j--) {
                if(pets[j] == null) continue;
                pets[i] = pets[j];
                pets[j] = null;
                break;
            }
        }
    }

    public Pet getRandPet() {
        Pet res = null;
        while (res == null) {
            int idx = (int) (Math.random() * END_SIZE);
            res = pets[idx];
        }
        return res;
    }

    public int getRandIdx() {
        int idx = -1;
        do {
            idx = (int) (Math.random() * END_SIZE);
        } while(pets[idx] == null);
        return idx;
    }

    public void summonPet(Pet pet, int pos) {
        if(slot == 0) {
            failSpawn(pet);
            return;
        }
        if(pets[pos] == null) {
            pets[pos] = pet;
            return;
        }
        for(int i = FRONT_INDEX; i > pos; i--) {
            pets[i] = pets[i-1];
        }
        pets[pos] = pet;
    }

    public void boughtPet(Pet pet, int pos) {
        pet.setPos(pos);
        pets[pos] = pet;
        pets[pos].onPurchased();
        if(pets[pos] instanceof OnPlaced) ((OnPlaced) pets[pos]).onPlaced();
    }

    public void mergePet(Pet pet, int pos) {
        int atk = Math.max(pets[pos].getAtk(), pet.getAtk());
        int hp = Math.max(pets[pos].getHp(), pet.getHp());
        pets[pos].setStats(atk, hp);
        pets[pos].onMerge();
    }

    public void removePet(int pos) {
        pets[pos] = null;
    }

    public void mergeBoughtPet(Pet pet, int pos) {
        mergePet(pet, pos);
        pets[pos].onPurchased();
    }

    public void swapPet(int idx1, int idx2) {
        Pet temp = pets[idx1];
        pets[idx1] = pets[idx2];
        pets[idx2] = temp;
    }

    public void sellPet(int idx) {
        pets[idx].onSell();
        pets[idx] = null;
    }

    public Pet getMostAttribute(ComparePet comparePet) {
        Pet res = null;
        for (int i = BACK_INDEX; i < FRONT_INDEX; i++) {
            if(pets[i] == null) continue;
            if(res == null) res = pets[i];
            res = comparePet.comparePet(res, pets[i]);
        }
        return res;
    }

    public void addOnBattleStart(OnBattleStart onBattleStart) {
        onBattleStarts.add(onBattleStart);
    }

    public void addOnSummon(OnSummon onSummon) {
        onSummons.add(onSummon);
    }

    public void addOnTurnStart(OnTurnStart onTurnStart) {
        onTurnStarts.add(onTurnStart);
    }

    public void addOnTurnEnd(OnTurnEnd onTurnEnd) {
        onTurnEnds.add(onTurnEnd);
    }

    public void addOnFriendAttack(OnFriendAttack onFriendAttack) {
        onFriendAttacks.add(onFriendAttack);
    }

    public void addOnFriendEatFruit(OnFriendEatFruit onFriendEatFruit) {
        onFriendEatFruits.add(onFriendEatFruit);
    }

    public void addOnFriendFaint(OnFriendFaint onFriendFaint) {
        onFriendFaints.add(onFriendFaint);
    }

    public String getName() {
        return this.name;
    }

    public int getTier(int idx) {
        return pets[idx].getTier();
    }

    public Pet getPet(int idx) {
        return pets[idx];
    }

    private void failSpawn(Pet pet) {
        System.out.println("Failed to spawn pet");
    }

    public int getSlot() {
        return slot;
    }
}
