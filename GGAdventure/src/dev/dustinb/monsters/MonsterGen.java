package dev.dustinb.monsters;

import dev.dustinb.DatabaseAccess;

import java.util.ArrayList;
import java.util.Random;


public class MonsterGen {

    private final Random random = new Random();
    private final DatabaseAccess databaseAccess = new DatabaseAccess();

    public Monster monsterMaker(int level){
        Monster badGuy;
        ArrayList<Monster> monsters = databaseAccess.getMonsterList(level);
        badGuy = monsters.get(random.nextInt(monsters.size()));
        return badGuy;
    }
}
