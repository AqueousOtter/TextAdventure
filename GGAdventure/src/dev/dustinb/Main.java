package dev.dustinb;


import dev.dustinb.battles.BattleMaker;
import dev.dustinb.monsters.MonsterGen;
import dev.dustinb.player.Player;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input =  new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = input.nextLine();

        Player player = new Player(name);
        MonsterGen monsterGenerator = new MonsterGen();
        BattleMaker battleMaker = new BattleMaker();
        System.out.println("testing database");


        for(int i = 0; i < 4; i++){
            battleMaker.BattleLoop(monsterGenerator.monsterMaker(1), player);
        }
        System.out.println(player.getInventory().size());
        System.out.println(player.getInventory());
        player.viewInventory();
        System.out.println("End");
        player.viewInventory();
        System.out.println("end of program");

    }
}
