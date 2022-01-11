package dev.dustinb.monsters;

import dev.dustinb.items.Item;
import dev.dustinb.items.ItemGen;

import java.util.Random;

//class for automatic gen
public class Monster implements MonsterInterface {

    private String name;
    private int hp;
    private int attackDmg;
    private int experience;
    private String weapon;
    private int gold;
    private String dropItem;
    private Random random = new Random();
    private ItemGen itemGen = new ItemGen();

    public Monster(String name, int hp, int attack, int experience, String weapon, int gold, String dropItem) {
        this.name = name;
        this.hp = hp;
        this.attackDmg = attack;
        this.experience = experience;
        this.weapon = weapon;
        this.gold = gold;
        this.dropItem = dropItem;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getExperience() {
        return experience;
    }

    public int getGold() {
        return gold;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttackDmg() {
        return attackDmg;
    }

    public String getWeapon() {
        return weapon;
    }

    @Override
    public int attack() {
        if (random.nextInt(10) > 3) {
            return attackDmg;
        } else {
            return 0;
        }
    }

    @Override
    public Item itemDrop() {
        int dropChance = random.nextInt(10);
        if (dropChance <= 5) {
            return itemGen.itemDropper(dropItem); //returns generated item object
        } else {
            System.out.println("Nothing was found...");
            return null;
        }
    }
}
