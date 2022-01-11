package dev.dustinb.items;
/*
 **** Possible change to reading information from a monster.text or //database// ****
 */
import java.util.Random;
/*
    Creates an item that matches array, returns and item object
 */
public class ItemGen {
    private String[] weaponList = {"Wooden Spear", "Stone Club", "Torn Gloves", "Sharp Sword"};
    private String[] healingList = {"Potion", "Herbs", "Remedy"};
    private Random random = new Random();


    //checks to make sure item is in item catalog or creates a default
    public Item itemDropper(String item){
        boolean itemExists = false;
        Item dropItem = null;
        for(int i = 0; i < weaponList.length; i++){
            if(weaponList[i].equalsIgnoreCase(item)){
                itemExists = true;
                dropItem = itemMaker(item);
            }
        }
        if (!itemExists){
            dropItem = itemMaker("wooden spear");
        }
        return dropItem;
    }

    //make item matching given string
    public Item itemMaker(String item) {
        Item makeItem;
        String lowerItem = item.toLowerCase();
        switch (lowerItem){
            case "wooden spear":
                makeItem = new Item("Wooden Spear", 4, 2, true, false);
                break;
            case "stone club":
                makeItem = new Item("Stone Club", 5, 4, true, false);
                break;
            case "torn gloves":
                makeItem = new Item("Torn Gloves", 3,0, false, false);
                break;
            case "sharp sword":
                makeItem = new Item("Sharp Sword", 7, 5, true, false);
                break;
            default:
                makeItem = new Item();
                break;
        }
        return makeItem;
    }
}
