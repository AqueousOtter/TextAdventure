package dev.dustinb.MainGame;

import dev.dustinb.InventoryManagement.InventoryManager;
import dev.dustinb.Menus.GameMenu;
import dev.dustinb.player.Player;

public class GameManager {
    Player player;
    InventoryManager inventoryManager;
    GameMenu gameMenu = new GameMenu();
    MapMaker mapMaker = new MapMaker();
    int direction = 0;
    int playerMapLevel;
    boolean isMapFinished = false;

    public GameManager(Player player) {
        this.player = player;
        inventoryManager = new InventoryManager(player);
        playerMapLevel = player.getMapLevel();
        while (direction != -1 ){
            gamePlay();
        }

    }
    private void gamePlay(){
        isMapFinished = false;
        mapMaker.setMap(playerMapLevel);
        while (!isMapFinished){
            //begin gameplay
            direction = gameMenu.viewGameMenu(player, inventoryManager, false);
            isMapFinished = mapMaker.mapProgress(direction, player);
        }
        System.out.println("You have found a village...");
        direction = gameMenu.viewGameMenu(player, inventoryManager, isMapFinished);

    }

}
