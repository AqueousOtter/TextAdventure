package dev.dustinb.player;

import dev.dustinb.items.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Player {
    private int hp;
    private int exp;
    private int gold;
    private int attackDmg;
    private Item weapon;
    private String name;
    //private Map<Item, Integer> inventory;
    private ArrayList<Item> inventory;

    public Player(String name) {
        this.hp = 20;
        this.exp = 0;
        this.gold = 10;
        this.attackDmg = 6;
        this.weapon = new Item("Dull Sword", 3, 2, true, false);
        this.name = name;
        this.inventory = new ArrayList<>();
        inventory.add(weapon);
    }

    public void addToInventory(Item item){
        inventory.add(item);
    }
    public void viewInventory(){
        String[] stringInventory = new String[inventory.size()];
        Iterator<Item> iterator = inventory.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next().toString());
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

    public void setInventory(ArrayList inventory) {
        this.inventory = inventory;
    }
}
