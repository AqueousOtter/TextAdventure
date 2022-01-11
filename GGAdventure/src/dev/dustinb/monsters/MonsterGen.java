package dev.dustinb.monsters;

import java.util.Random;
/*
    **** Possible change to reading information from a monster.text or //database// ****
 */
public class MonsterGen {

    private String[] monster = {"Ghoul", "Imp", "Goblin", "Ogre"};
    private Random random = new Random();

    public Monster monsterMaker(int level){
        Monster badGuy;
        //implement switch statement to handle level
        switch(random.nextInt(monster.length)){
            case 0:
                badGuy = new Monster("Ghoul", 12, 6, 6, "Ghost Hand", 6, "Torn Gloves");
                break;
            case 1:
                badGuy = new Monster("Imp", 7, 4, 4, "Wooden Spear", 3, "Wooden Spear");
                break;
            case 2:
                badGuy = new Monster("Goblin", 10, 7, 8, "Stone Club", 5, "Skull CLub");
                break;
            case 4:
                badGuy = new Monster("Ogre", 15, 6, 11, "Stone Mace", 8, "Dagger");
                break;
            default:
                badGuy = new Monster("Imp", 7, 4, 4, "Wooden Spear", 3, "Wooden Spear");
                break;
        }
        return badGuy;
    }
}
