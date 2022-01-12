package dev.dustinb.items;

import dev.dustinb.DatabaseAccess;
import java.util.ArrayList;
/*
    Creates an item that matches database, returns and item object
 */
public class ItemGen {

    private DatabaseAccess databaseAccess = new DatabaseAccess();
    final ArrayList<Item> itemList = databaseAccess.getItemList();


    //checks to make sure item is in item catalog or creates a default
    public Item itemDropper(String item){

        Item dropItem = null;
        for (Item value : itemList) {
            if (value.getName().equalsIgnoreCase(item)) {
                dropItem = value;
            }
        }
        return dropItem;
    }

}
