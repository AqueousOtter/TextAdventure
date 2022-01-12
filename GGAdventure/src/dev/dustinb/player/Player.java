package dev.dustinb.player;

import dev.dustinb.items.Item;

import java.util.*;

/*
    * TODO
    * add equip item option
    * add healing option

 */

public class Player {

    Scanner input = new Scanner(System.in);
    private int hp;
    private int exp;
    private int gold;
    private int attackDmg;
    private Item weapon;
    private String name;
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
        int inventorySpace = 0;
        int userInput = 0;
        //check size
        if(inventory.size() > 1){
            //cycle through inventory
            Iterator<Item> iterator = inventory.iterator();
            System.out.println("\t\t\t********   INVENTORY   ********");
            System.out.println("   Current Weapon: " + weapon.getName() + " | Power: " + weapon.getStatBoost() + " || Health: "+ getHp());
            System.out.println("--------------------------------------------------------------");
            boolean isWeaponEquipped = false;
            while(iterator.hasNext()){
                Item newItem = iterator.next();
                if(newItem.getName().equalsIgnoreCase(weapon.getName()) && !isWeaponEquipped){ //prevents equipped from showing
                    isWeaponEquipped = true;
                    newItem = iterator.next(); //skips equipped

                }
                inventorySpace++;
                System.out.println("["+ inventorySpace + "]\t" + newItem.toString());
            }
            //Allow user to equip or use an item, close w/0
            System.out.println("{  press number of item you wish to use, 0 to close  }");
            userInput = input.nextInt();
            if(userInput != 0){
                if(inventory.get(userInput).isWeapon()){
                    setWeapon(inventory.get(userInput));
                    System.out.println("Set weapon to " + weapon.getName());
                    Collections.swap(inventory, 0, (userInput)); // moves weapon to first slot to prevent sout numbers from not matching up
                }
                else if(inventory.get(userInput).isHealing()){
                    setHp(hp + inventory.get(userInput).getStatBoost());
                    System.out.println("Used " + inventory.get(userInput).getName() +" HP: " + getHp());
                    inventory.remove(userInput); //deletes item
                }
                else {
                    System.out.println("Item cannot be equipped.");
                }
            }
        }
        else{
            System.out.println("You have no items to use...");
        }
    }

    //view bag - view inventory but only show healing
    public boolean useBag(){
        boolean usedItem = false;
        int inventorySpace = 0;
        int userInput = 0;

        //check size
        if(inventory.size() > 1){
            //cycle through inventory
            ArrayList<Item> bag = new ArrayList<>();
            Iterator<Item> iterator = inventory.iterator();
            System.out.println("\t\t\t********   BAG   ********");
            System.out.println("\t\t\t   Health: "+ getHp());
            System.out.println("--------------------------------------------------------------");
            while(iterator.hasNext()){
                Item newItem = iterator.next();
                if( newItem != null && newItem.isHealing()){ //prevents equipped from showing
                    bag.add(newItem);
                    inventorySpace++;
                    System.out.println("["+ inventorySpace + "]\t" + newItem);
                }
            }
            //Allow user to equip or use an item, close w/0
            System.out.println("{press number of item you wish to use, 0 to close}");
            userInput = input.nextInt();
            if(userInput != 0){
                setHp(hp + inventory.get(userInput).getStatBoost());
                System.out.println("Used " + bag.get(userInput).getName() +" HP: " + getHp());
                bag.remove(userInput); //deletes item
                usedItem = true;
                removeItem(bag.get(userInput).getName());
            }
        }
        else{
            System.out.println("You have no items to use...");
        }
        return usedItem;
    }

    //remove used items
    private void removeItem(String item) {
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

    public void setInventory(ArrayList inventory) {
        this.inventory = inventory;
    }
}
