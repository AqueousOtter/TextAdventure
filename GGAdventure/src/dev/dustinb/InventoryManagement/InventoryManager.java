package dev.dustinb.InventoryManagement;

import dev.dustinb.items.Item;
import dev.dustinb.player.Player;

import java.util.*;
import java.util.stream.Collectors;

/*
    Class to handle all inventory options, including battle bag, and all inventory access/removal
 */
public class InventoryManager {

    private final ArrayList<Item> inventory;
    Player player;
    Scanner input = new Scanner(System.in);

    public InventoryManager(Player player) {
        this.player = player;
        this.inventory = player.getInventory();

    }

    public void viewInventory(){
        int inventorySpace = 0;
        int userInput;
        //check size
        if(inventory.size() > 1){
            //cycle through inventory
            Iterator<Item> iterator = inventory.iterator();
            System.out.println("\t\t\t********   INVENTORY   ********");
            System.out.println("   Current Weapon: " + player.getWeapon().getName() + " | Power: " + player.getWeapon().getStatBoost() + " || Health: "+ player.getHp());
            System.out.println("--------------------------------------------------------------");
            List<Item> displayInventory = inventory.stream().toList();
            for(int i = 1; i < displayInventory.size(); i++){ //i = 1 - skips equipped item
                inventorySpace++;
                System.out.println("["+ inventorySpace + "]\t" + displayInventory.get(i).toString());
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
        int userInput;

        //check size
        if(inventory.size() > 1){
            //cycle through inventory
            System.out.println("\t\t\t********   BAG   ********");
            System.out.println("\t\t\t\t   Health: "+ player.getHp());
            System.out.println("--------------------------------------------------------------");
            List<Item> bag = inventory.stream().filter(Item::isHealing).toList();
            for(int i =0; i < bag.size(); i++){
                System.out.println("["+ (i+1) + "] "+ bag.get(i));
            }
            //Allow user to equip or use an item, close w/0
            System.out.println("{  press number of item you wish to use, 0 to close  }");
            userInput = input.nextInt();
            if(userInput != 0){
                player.setHp(player.getHp() + bag.get(userInput-1).getStatBoost());
                System.out.println("Used " + bag.get(userInput-1).getName() +" HP: " + player.getHp());
                usedItem = true;
                player.removeItem(bag.get(userInput-1).getName());
            }
        }
        else{
            System.out.println("You have no items to use...");
        }
        return usedItem;
    }
}

