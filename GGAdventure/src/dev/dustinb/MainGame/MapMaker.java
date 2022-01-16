package dev.dustinb.MainGame;

import dev.dustinb.battles.BattleMaker;
import dev.dustinb.monsters.MonsterGen;
import dev.dustinb.player.Player;

import java.util.Random;

public class MapMaker {
    //First area player is in. Contains low level monsters and is short.
    private Random random = new Random();
    private int[] mapKey;
    private int mapLength;
    private int monsterLevel;
    private int playerProgress = 0;
    MonsterGen monsterGen = new MonsterGen();
    BattleMaker battleMaker = new BattleMaker();

    public void setMap(int playerMapLevel){
        switch (playerMapLevel){
            case 1:
                playerProgress = 0;
                monsterLevel = 1;
                mapKey = new int[]{1,1,3,2,4}; // north, north, east, south, west;
                mapLength = mapKey.length;
                break;
            case 2:
                playerProgress = 0;
                monsterLevel = 2;
                mapKey = new int[]{4,4,4,1,3,3}; // west, west, west, north, east, north
                mapLength = mapKey.length;
                break;
        }
    }

    public boolean mapProgress(int playerDirection, Player player){
        boolean isMapFinished = false;
        if(playerDirection == mapKey[playerProgress]){
            System.out.println("You continue on the trail...");
            playerProgress++;
            if(random.nextInt(5) < 3 ){
                battleMaker.BattleLoop(monsterGen.monsterMaker(monsterLevel), player);
            }
        }
        else {
            System.out.println("The trail seems to lead in the wrong direction...");
            battleMaker.BattleLoop(monsterGen.monsterMaker(monsterLevel), player);
        }
        if(playerProgress == mapLength){
            isMapFinished = true;
            player.setMapLevel(player.getMapLevel() + 1);
        }
        return isMapFinished;
    }

}
