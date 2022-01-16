package dev.dustinb.Menus;

import dev.dustinb.InventoryManagement.InventoryManager;
import dev.dustinb.player.Player;
import dev.dustinb.player.PlayerSave;


import java.util.Scanner;

public class GameMenu {
    Scanner input = new Scanner(System.in);
    PlayerSave playerSave = new PlayerSave();
    int userChoice;
    boolean isDirection = false;
/* ToDo:
    * Add shop option
 */
    public int viewGameMenu(Player player, InventoryManager inventoryManager, boolean isVillage){
        int direction = 0;
        do{
            System.out.println(player.toString());
            System.out.println("########## Choose an option below ##########");
            if(isVillage){
                System.out.println("[1] Continue Adventure\t [2] View Inventory\t [3] Save Game"); //add shop option
            }
            else{
                System.out.println("[1] Continue Adventure\t [2] View Inventory\t ");
            }
            userChoice = input.nextInt();
            switch (userChoice){
                case 1:
                    System.out.println("Which way would you like to head?");
                    System.out.println("[1] North\t [2] South\t [3] East\t [4] West");
                    userChoice =  input.nextInt();
                    if(userChoice > 0 && userChoice <= 4){
                        direction = userChoice;
                        isDirection = true;
                    }
                    break;
                case 2:
                    inventoryManager.viewInventory();
                    isDirection = false;
                    break;
                case 3:
                    if(isVillage){
                        System.out.println("Saving...");
                        playerSave.saveGame(player);
                        System.out.println("Finished.");
                        isDirection = false;
                    }
                    break;
                default:
                    System.out.println("Please enter a choice from above...");
                    break;
            }

        }while(!isDirection);

        return direction;
    }
}
