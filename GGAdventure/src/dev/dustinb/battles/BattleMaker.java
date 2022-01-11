package dev.dustinb.battles;

import dev.dustinb.items.Item;
import dev.dustinb.monsters.Monster;
import dev.dustinb.player.Player;
import java.util.Random;
import java.util.Scanner;

public class BattleMaker {

    public void BattleLoop(Monster monster, Player player){
        //constants
        final int PLAYERATTACK = player.getAttackDmg();
        final int SPECIALATTACK = (int) Math.round(player.getAttackDmg() * 1.33);
        final int MONSTERDAMAGE = monster.getAttackDmg();
        final String PLAYERWEAPON = player.getWeapon().getName();

        Scanner inputScanner = new Scanner(System.in);
        int playerChoice;
        int playerCharge = 0;
        boolean escape = false;
        boolean battleOver = false;
        boolean playerAlive = true;
        Random random = new Random();

        //begin console/input loop for battle //ends when monsterDead set to true(0hp) or escape is true
        System.out.println("*********************************************");
        System.out.println(player.getName() + " has encountered a " + monster.getName() + ". A fight begins...");
        do {

            System.out.println("-------------------------------------------");
            System.out.println("\t\t" + monster.getName() + " HP: "+ monster.getHp() + " | " + player.getName() + " HP: " + player.getHp());
            System.out.println("-----------\tSelect an action\t-----------");
            System.out.println("[1] Attack\t[2] Special Attack\n[3] Bag\t[4] Escape");
            playerChoice = inputScanner.nextInt();
            switch (playerChoice){
                case(1):
                    System.out.println(player.getName() + " strikes " + monster.getName() + " with " + PLAYERWEAPON + " causing " +
                            PLAYERATTACK + " damage...");
                    monster.setHp(monster.getHp() - PLAYERATTACK);
                    break;
                case(2):
                    if(playerCharge == 1){
                        playerCharge = 0;
                        System.out.println(player.getName() + " goes berserk causing " + SPECIALATTACK +
                                " damage to "+ monster.getName());
                        monster.setHp(monster.getHp() - SPECIALATTACK);
                    }else{
                        System.out.println("A rage starts to form inside of " + player.getName());
                        playerCharge++;
                    }
                    break;
                case(4):
                    int escapeChance = random.nextInt(7);
                    if((escapeChance%2) > 0){
                        System.out.println("" +
                                "\t\tSuccessfully escaped!");
                        escape = true;
                        battleOver = true;
                    }else{
                        System.out.println("\t\tFailed to escape...");
                    }
                    break;
                default:
                    System.out.println("Both parties are confused...");
                    break;
            }

            //monster turn/end of battle logic
            if (monster.getHp() > 0 && !escape) {
                //Monster attack -- check for missed attack
                if(monster.attack() != 0){

                    System.out.println("\n" + monster.getName() + " attacks " + player.getName() + " with a " +
                            monster.getWeapon() + " causing " + MONSTERDAMAGE + " damage to " + player.getName()+ "\n");
                    player.setHp((player.getHp() - MONSTERDAMAGE));
                    if(player.getHp() <= 0){
                        battleOver = true;
                        playerAlive = false;
                    }
                }
                else {
                    System.out.println(monster.getName() + "'s attack missed!\n");
                }
            }else if(monster.getHp() <= 0){
                System.out.println(player.getName() + " has defeated " + monster.getName() + "...\n");
                battleOver = true;
            }

        }while(!battleOver);
        if(playerAlive && !escape){
            battleRewards(monster, player);
            System.out.println(player.getInventory().size());
        }
    }

    //delivers exp, gold and items to player
    public void battleRewards(Monster monster, Player player){
        Item monsterDrop = monster.itemDrop();
        int currentExp = player.getExp();
        int currentGold = player.getGold();
        player.setExp(currentExp + monster.getExperience());
        player.setGold(currentGold + monster.getGold());
        //drop item
        if(monsterDrop != null){
            System.out.println(monster.getName() + " dropped " + monsterDrop.getName() + "...");
            player.addToInventory(monsterDrop);
        }
        System.out.println(player.getName() + " gained " + monster.getExperience() + "xp and " + monster.getGold() + "g");
    }
}
