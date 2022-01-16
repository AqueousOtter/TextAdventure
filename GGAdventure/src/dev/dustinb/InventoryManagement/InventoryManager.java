package dev.dustinb.InventoryManagement;

import dev.dustinb.items.Item;
import dev.dustinb.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class InventoryManager {


    private final ArrayList<Item> inventory;
    Player player;
    Scanner input = new Scanner(System.in);

/*    public InventoryManager(){
        this.inventory = new ArrayList<>();
    }*/

    public InventoryManager(Player player) {
        this.player = player;
        this.inventory = player.getInventory();

    }

    public void viewInventory(){
        int inventorySpace = 0;
        int userInput = 0;
        //check size
        if(inventory.size() > 1){
            //cycle through inventory
            Iterator<Item> iterator = inventory.iterator();
            System.out.println("\t\t\t********   INVENTORY   ********");
            System.out.println("   Current Weapon: " + player.getWeapon().getName() + " | Power: " + player.getWeapon().getStatBoost() + " || Health: "+ player.getHp());
            System.out.println("--------------------------------------------------------------");
            boolean isWeaponEquipped = false;
            while(iterator.hasNext()){
                Item newItem = iterator.next();
                if(newItem.getName().equalsIgnoreCase(player.getWeapon().getName()) && !isWeaponEquipped){ //prevents equipped from showing
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
                    player.setWeapon(inventory.get(userInput));
                    System.out.println("Set weapon to " + player.getWeapon().getName());
                    Collections.swap(inventory, 0, (userInput)); // moves weapon to first slot to prevent sout numbers from not matching up
                }
                else if(inventory.get(userInput).isHealing()){
                    player.setHp(player.getHp() + inventory.get(userInput).getStatBoost());
                    System.out.println("Used " + inventory.get(userInput).getName() +" HP: " + player.getHp());
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

    public boolean useBag(){
        boolean usedItem = false;
        int inventorySpace = 0;
        int userInput = 0;

        //check size
        if(inventory.size() > 1){
            //cycle through inventory
            ArrayList<Item> bag = new ArrayList<>();
            bag.add(new Item()); // empty placeholder item to keep arraylist
            Iterator<Item> iterator = inventory.iterator();
            System.out.println("\t\t\t********   BAG   ********");
            System.out.println("\t\t\t   Health: "+ player.getHp());
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
            System.out.println("{  press number of item you wish to use, 0 to close  }");
            userInput = input.nextInt();
            if(userInput != 0){
                player.setHp(player.getHp() + bag.get(userInput-1).getStatBoost());
                System.out.println("Used " + bag.get(userInput-1).getName() +" HP: " + player.getHp());
                usedItem = true;
                player.removeItem(bag.get(userInput).getName()); // ERROR?
            }
        }
        else{
            System.out.println("You have no items to use...");
        }
        return usedItem;
    }
}

