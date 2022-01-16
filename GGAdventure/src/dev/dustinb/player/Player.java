package dev.dustinb.player;

import dev.dustinb.InventoryManagement.InventoryManager;
import dev.dustinb.items.Item;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/*
    * TODO
    *  serialize player

 */

public class Player implements Serializable {

    ArrayList<Item> inventory;
    private int hp;
    private int exp;
    private int gold;
    private int attackDmg;
    private Item weapon;
    private String name;


    public Player(String name) {
        this.hp = 20;
        this.exp = 0;
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
