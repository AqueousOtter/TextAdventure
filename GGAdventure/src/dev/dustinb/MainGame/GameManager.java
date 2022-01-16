package dev.dustinb.MainGame;

import dev.dustinb.InventoryManagement.InventoryManager;
import dev.dustinb.battles.BattleMaker;
import dev.dustinb.monsters.MonsterGen;
import dev.dustinb.player.Player;
import dev.dustinb.player.PlayerSave;

public class GameManager {
    Player player;
    BattleMaker battleMaker;

    public GameManager(Player player) {
        this.player = player;
        InventoryManager inventoryManager;
        inventoryManager= new InventoryManager(player);
        MonsterGen monsterGenerator = new MonsterGen();
        BattleMaker battleMaker = new BattleMaker();
        System.out.println("testing database");


        for(int i = 0; i < 4; i++){
            battleMaker.BattleLoop(monsterGenerator.monsterMaker(1), player);
        }
        System.out.println(player.getInventory());
        System.out.println(player.getInventory());
        inventoryManager.viewInventory();
        System.out.println("End");
        inventoryManager.viewInventory();

        new PlayerSave().saveGame(player);
    }

}
