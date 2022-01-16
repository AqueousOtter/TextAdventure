package dev.dustinb.Menus;

import dev.dustinb.MainGame.GameManager;
import dev.dustinb.player.Player;
import dev.dustinb.player.PlayerSave;

import java.util.Scanner;

public class MainMenu {


    Scanner input =  new Scanner(System.in);
    int userChoice;

    public void showMainMenu(){
        System.out.println("\n\t\t********* Untitled Text Adventure *********");
        System.out.println("SELECT AN OPTION:");
        System.out.println("[1] new game\t[2] load save\t[3] Exit");
        userChoice = input.nextInt();
        input.nextLine();

        switch (userChoice){
            case 1:
                System.out.println("Enter your name: ");
                String name = input.nextLine();
                new GameManager(new Player(name));
                break;
            case 2:
                System.out.println("Loading Character...");
                Player player = new PlayerSave().loadSave();
                new GameManager(player);
                break;

            default:
                break;

        }
        input.close();
    }
}
