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
import java.util.HashMap;

public class Team {
    String name;
    private EventManager eventManager;
    private HashMap<Pet, PetStats> petStats;
    private PetHashMap battlePets;
    private HashMap<Integer, Pet> pets;
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
        this.petStats = new HashMap<>();
        this.battlePets = new PetHashMap(onFriendFaint);
        this.pets = new HashMap<>();
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
        this.petStats = new HashMap<>();
        this.battlePets = new PetHashMap(onFriendFaint);
        this.pets = new HashMap<>();
        slot = END_SIZE;

        onBattleStarts = new ArrayList<>();
        onSummons = new ArrayList<>();
        onTurnStarts = new ArrayList<>();
        onTurnEnds = new ArrayList<>();
        onFriendAttacks = new ArrayList<>();
        onFriendEatFruits = new ArrayList<>();
        onFriendFaints = new ArrayList<>();
    }

    public Team(Team t) {
        this.name = t.name;
        this.eventManager = t.eventManager;
        this.petStats = t.petStats;
        this.battlePets = t.battlePets;
        this.pets = t.pets;
        this.slot = t.slot;

        this.onBattleStarts = t.onBattleStarts;
        this.onSummons = t.onSummons;
        this.onTurnStarts = t.onTurnStarts;
        this.onTurnEnds = t.onTurnEnds;
        this.onFriendAttacks = t.onFriendAttacks;
        this.onFriendEatFruits = t.onFriendEatFruits;
        this.onFriendFaints = t.onFriendFaints;
    }

    //region<On...>

    public void onBattleStart() {
        for (OnBattleStart o : onBattleStarts) {
            o.onBattleStart();
        }
    }

    public void onTurnStart() {
        for (OnTurnStart o : onTurnStarts) {
            o.onTurnStart();
        }
    }

    public void onTurnEnd() {
        for (OnTurnEnd o : onTurnEnds) {
            o.onTurnEnd();
        }
    }

    public void onAttack(Pet pet) {
        for (OnFriendAttack o : onFriendAttacks) {
            o.onFriendAttack(pet);
        }
        if(pet.getStatus() == PetStatus.NORMAL) pet.onAfterAttack();
    }

    private void onFriendEatFruit(Pet pet, Fruit fruit) {
        for (OnFriendEatFruit o : onFriendEatFruits) {
            o.onEatFruit(pet, fruit);
        }
    }

    private RunnablePet onFriendFaint = (Pet pet) -> {
        onFriendFaints.forEach(o -> o.onFriendFaint(pet));
    };

    public void onBattleEnd() {
        for (int i = BACK_INDEX; i < END_SIZE; i++) {
            Pet p = pets.get(i);
            if(p != null) p.onBattleEnd();
        }
    }

    //endregion

    private void put(Pet pet, int pos) {
        pet.setPos(pos);
        pets.put(pet.getPos(), pet);
    }

    private void putBattlePet(Pet pet, int pos) {
        pet.setPos(pos);
        battlePets.put(pet.getPos(), pet);
    }

    private void putStats(Pet pet) {
        petStats.put(pet, new PetStats(pet.getAtk(), pet.getHp(), pet.getPos()));
    }

    public Pet getBattlePet() {
        arrangeBattleTeam();
        for (int i = FRONT_INDEX; i >= 0; i++) {
            Pet p = battlePets.get(i);
            if(p != null) return p;
        }
        return null;
    }

    private void resetStats(Pet pet) {
        PetStats s = petStats.get(pet);
        pet.setStatsWPos(s.atk, s.hp, s.pos);
        pet.setStatus(PetStatus.NORMAL);
    }

    public void setRandTeamFromDB(PetFactory petF, FruitFactory fruF, int round) {
        RoundRepository roundRepo = RoundRepository.getRandRoundRepository(round);
        setTeamFromRound(petF, fruF, roundRepo);
    }

    public void setTeamFromRound(PetFactory petF, FruitFactory fruF, RoundRepository roundRepo) {
        Pet[] temp = PetRepository.getPetsForRound(petF, fruF, roundRepo.getId());
        for(int i = 0; i < END_SIZE; i++) {
            Pet curr = temp[i];
            if(curr == null) continue;
            placePet(curr, curr.getPos());
        }
    }

    public void placePet(Pet pet, int pos) {
        put(pet, pos);
        if(pet instanceof OnPlaced) ((OnPlaced) pet).onPlaced();
    }

    public void printTeam() {
        Lib.printSlots(pets);
    }

    public void initBattleTeam() {
        petStats.clear();
        battlePets.clear();
        for(int i = BACK_INDEX; i < END_SIZE; i++) {
            Pet p = pets.get(i);
            if(p == null) continue;
            putStats(p);
            putBattlePet(p, i);
        }
    }

    public void resetPets() {
        for(int i = BACK_INDEX; i < END_SIZE; i++) {
            Pet p = pets.get(i);
            if(p == null) continue;
            resetStats(p);
        }
    }

    public void saveTeam() {
        for(int i = Team.BACK_INDEX; i < Team.END_SIZE; i++) {
            Pet p = pets.get(i);
            if(p == null) continue;
            PetRepository curr = PetRepository.newInstance(RoundRepository.getInstance().getId(), p.getName(), p.getAtk(), p.getHp(), p.getLv(), p.getExp(), p.getPos());
            if(p.getFruit() != null) FruitRepository.newInstance(curr.getId(), p.getFruit().getName());
        }
    }

    public EventManager getEventManager() {
        return this.eventManager;
    }

    public void feedPetAt(Fruit fruit, int idx) {
        Pet p = pets.get(idx);
        p.eatFruit(fruit);
        onFriendEatFruit(p, fruit);
    }

    public int getAtk(int idx) {
        return pets.get(idx).attack();
    }

    public int takeDamage(int damage, int idx) {
        return pets.get(idx).damage(damage);
    }

    public void doAll(DoPet doPet) {
        for(int i = BACK_INDEX; i < END_SIZE; i++) {
            Pet p = pets.get(i);
            if(p == null) continue;
            doPet.doPet(p);
        }
    }

    public void doPet(Pet origin, GetPet getPet, DoPet doPet) {
        Pet pet;
        do {
            pet = getPet.getPet();
        } while(pet == null|| pet == origin);
        doPet.doPet(pet);
    }

    public void doPets(GetPets getPets, DoPet doPet) {
        ArrayList<Pet> pets;
        pets = getPets.getPets();
        pets.forEach(doPet::doPet);
    }

    public void arrangeBattleTeam() {
        for (int i = FRONT_INDEX; i >= 0; i--) {
            Pet p = battlePets.get(i);
            if(p != null && p.getStatus() == PetStatus.FAINT) battlePets.remove(p.getPos());
            if(p != null && p.getStatus() == PetStatus.NORMAL) continue;
            for(int j = i - 1; j >= 0; j--) {
                Pet q = battlePets.get(j);
                if(q == null || q.getStatus() == PetStatus.FAINT) continue;
                putBattlePet(q, i);
                battlePets.remove(j);
                break;
            }
        }
    }

    public Pet getRandPet() {
        Pet res = null;
        while (res == null) {
            int idx = (int) (Math.random() * END_SIZE);
            res = pets.get(idx);
        }
        return res;
    }

    public ArrayList<Pet> getRandPets(int n) {
        ArrayList<Pet> res = new ArrayList<>();
        while(res.size() < n) {
            Pet p = getRandPet();
            if(!res.contains(p)) res.add(p);
        }
        return res;
    }

    public boolean summonPet(Pet pet, int pos) {
        arrangeBattleTeam();
        for(int i = BACK_INDEX; i < pos; i++) {
            if(battlePets.get(i) != null) {
                System.out.printf("Team full failed to summon %s.\n", pet.getName());
                return false;
            }
            for(int j = i + 1; j <= pos; j++) {
                if(battlePets.get(j) == null) continue;
                putBattlePet(battlePets.get(j), i);
                battlePets.remove(j);
                break;
            }
        }
        if(battlePets.get(pos) == null) {
            putBattlePet(pet, pos);
            onSummons.forEach(o -> o.onSummon(pet));
            arrangeBattleTeam();
            return true;
        } else {
            System.out.printf("Team full failed to summon %s.\n", pet.getName());
            return false;
        }
    }

    public void boughtPet(Pet pet, int pos) {
        put(pet, pos);
        pet.onPurchased();
        if(pet instanceof OnPlaced) ((OnPlaced) pet).onPlaced();
    }

    public void mergePet(Pet pet, int pos) {
        Pet target = pets.get(pos);
        int atk = Math.max(pet.getAtk(), target.getAtk());
        int hp = Math.max(pet.getHp(), target.getHp());
        int exp = Math.max(pet.getExp(), target.getExp());
        int lv = Math.max(pet.getLv(), target.getLv());
        target.setStats(atk, hp, lv, exp, pos);
        target.onMerge();
    }

    public void removePet(int pos) {
        pets.remove(pos);
    }

    public void mergeBoughtPet(Pet pet, int pos) {
        mergePet(pet, pos);
        pet.onPurchased();
    }

    public void swapPet(int idx1, int idx2) {
        Pet temp = pets.get(idx1);
        put(pets.get(idx2), idx1);
        put(temp, idx2);
    }

    public void sellPet(int idx) {
        pets.get(idx).onSell();
        pets.remove(idx);
    }

    public Pet getMostAttribute(ComparePet comparePet) {
        Pet res = null;
        for (int i = BACK_INDEX; i < FRONT_INDEX; i++) {
            if(pets.get(i) == null) continue;
            if(res == null) res = pets.get(i);
            res = comparePet.comparePet(res, pets.get(i));
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
        return pets.get(idx).getTier();
    }

    public Pet getPet(int idx) {
        return pets.get(idx);
    }

    public Pet getBattlePet(int idx) {
        return idx >= END_SIZE ? null : pets.get(idx);
    }

    private void failSpawn(Pet pet) {
        System.out.println("Failed to spawn pet");
    }

    public int getSlot() {
        return slot;
    }
}

class PetStats {
    public int atk, hp, pos;

    public PetStats(int atk, int hp, int pos) {
        this.atk = atk;
        this.hp = hp;
        this.pos = pos;
    }
}
