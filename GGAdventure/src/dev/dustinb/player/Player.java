package dev.dustinb.player;

import dev.dustinb.items.Item;
import java.io.Serializable;
import java.util.*;

public class Player implements Serializable {

    final int BASEXP = 25;
    ArrayList<Item> inventory;
    private int hp;
    private int level;
    private int exp;
    private int nextLevel;
    private int gold;
    private int attackDmg;
    private Item weapon;
    private String name;
    private int mapLevel;
    private int baseHP;


    public Player(String name) {
        this.baseHP = 20;
        this.hp = 20;
        this.level = 1;
        this.exp = 0;
        this.nextLevel = BASEXP;
        this.gold = 10;
        this.attackDmg = (6);
        this.weapon = new Item("Dull Sword", 3, 2, true, false);
        this.name = name;
        this.inventory = new ArrayList<Item>();
        inventory.add(weapon);
        this.mapLevel = 1;
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
        if(exp >= nextLevel){
            System.out.println(name + " has Leveled Up!");
            level++;
            if(level%2 == 0){
                baseHP += 5;
            }
            else {
                baseHP += 2;
            }
            setHp(baseHP);
            attackDmg++;
            exp = 0;
            nextLevel = (int) Math.round( BASEXP * level * .6);
        }
    }

    @Override
    public String toString() {
        return ("\n\t\t" + name + " | HP:" + hp + " | LVL: " + level +
                "\nWEAPON: " + weapon.getName() + " | GOLD: " + gold + " | NEXT LEVEL: " + exp + "/" + nextLevel);
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

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void addToInventory(Item item){
        inventory.add(item);
    }

    public int getMapLevel() {
        return mapLevel;
    }

    public void setMapLevel(int mapLevel) {
        this.mapLevel = mapLevel;
    }
}
