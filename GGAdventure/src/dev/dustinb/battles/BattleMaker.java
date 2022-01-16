package dev.dustinb.battles;

import dev.dustinb.InventoryManagement.InventoryManager;
import dev.dustinb.items.Item;
import dev.dustinb.monsters.Monster;
import dev.dustinb.player.Player;
import java.util.Random;
import java.util.Scanner;

public class BattleMaker {
    Random random = new Random();
    InventoryManager inventory;

    public void BattleLoop(Monster monster, Player player){
        //constants
        final int PLAYERATTACK = player.getAttackDmg();
        final int SPECIALATTACK = (int) Math.round(player.getAttackDmg() * 1.33);
        final int MONSTERDAMAGE = monster.getAttackDmg();
        final String PLAYERWEAPON = player.getWeapon().getName();
        final InventoryManager INVENTORY = new InventoryManager(player);

        Scanner inputScanner = new Scanner(System.in);
        int monsterHP = monster.getHp();
        int playerChoice;
        int playerCharge = 0;
        boolean escape = false;
        boolean battleOver = false;
        boolean playerAlive = true;


        //begin console/input loop for battle //ends when monsterDead set to true(0hp) or escape is true
        System.out.println("*********************************************");
        System.out.println(player.getName() + " has encountered a " + monster.getName() + ". A fight begins...");
        do {

            System.out.println("-------------------------------------------");
            System.out.println("\t\t" + monster.getName() + " HP: "+ monsterHP + " | " + player.getName() + " HP: " + player.getHp());
            System.out.println("-----------\tSelect an action\t-----------");
            System.out.println("[1] Attack\t[2] Special Attack\n[3] Bag\t[4] Escape");
            playerChoice = inputScanner.nextInt();
            switch (playerChoice){
                case(1):
                    System.out.println(player.getName() + " strikes " + monster.getName() + " with " + PLAYERWEAPON + " causing " +
                            PLAYERATTACK + " damage...");
                    monsterHP -= PLAYERATTACK;
                    break;
                case(2):
                    if(playerCharge == 1){
                        playerCharge = 0;
                        System.out.println(player.getName() + " goes berserk causing " + SPECIALATTACK +
                                " damage to "+ monster.getName());
                        monsterHP -= SPECIALATTACK;
                    }else{
                        System.out.println("A rage starts to form inside of " + player.getName());
                        playerCharge++;
                    }
                    break;
                case(3):
                    if(INVENTORY.useBag()){
                        break;
                    }
                    else{
                        continue;
                    }
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
            if (monsterHP > 0 && !escape) {
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
            }else if(monsterHP <= 0){
                System.out.println(player.getName() + " has defeated " + monster.getName() + "...\n");
                battleOver = true;
            }

        }while(!battleOver);
        if(playerAlive && !escape){
            battleRewards(monster, player);
        }

    }

    //delivers exp, gold and items to player
    public void battleRewards(Monster monster, Player player){

        int currentExp = player.getExp();
        int currentGold = player.getGold();
        player.setExp(currentExp + monster.getExperience());
        player.setGold(currentGold + monster.getGold());
        //drop item
        int dropChance = random.nextInt(3);
        if (dropChance <= 1) {
            player.addToInventory(monster.itemDrop());
        }
        System.out.println(player.getName() + " gained " + monster.getExperience() + "xp and " + monster.getGold() + "g");
    }
}
