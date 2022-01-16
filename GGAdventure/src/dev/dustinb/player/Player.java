package dev.dustinb.player;


import dev.dustinb.items.Item;

import java.io.Serializable;
import java.util.*;

/*
    * TODO
    *

 */

public class Player implements Serializable {

    final int BASEXP = 25;
    ArrayList<Item> inventory;
    private int hp;
    private int level;
    private int exp;
    private int nextLevel;
    private int gold;
    private final int attackDmg;
    private Item weapon;
    private String name;


    public Player(String name) {
        this.hp = 20;
        this.level = 1;
        this.exp = 0;
        this.nextLevel = (int) Math.round( BASEXP * level * .3);;;
        this.gold = 10;
        this.attackDmg = 6;
        this.weapon = new Item("Dull Sword", 3, 2, true, false);
        this.name = name;
        this.inventory = new ArrayList<Item>();
        inventory.add(weapon);
    }

    //remove used items during gameplay/ inventory screens
    public void removeItem(String item) {
        for (Item value : inventory) {
            if (value.getName().equalsIgnoreCase(item)) {
                inventory.remove(value);
                System.out.println("removed 1");
                break;
            }
        }
    }
    //level up system
    public void playerLevelProgress(int expGained){
        exp += expGained;
        if(exp == nextLevel){
            level++;
        }
    }


    public int getLevel() {
        return level;
    }

    public int getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(int nextLevel) {
        this.nextLevel = nextLevel;
    }

    public int getAttackDmg() {
        return attackDmg+weapon.getStatBoost();
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public Item getWeapon() {
        return weapon;
    }

    public void setWeapon(Item weapon) {
        this.weapon = weapon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void addToInventory(Item item){
        inventory.add(item);
    }
}
