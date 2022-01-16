package dev.dustinb;


import dev.dustinb.InventoryManagement.InventoryManager;
import dev.dustinb.Menus.MainMenu;
import dev.dustinb.battles.BattleMaker;
import dev.dustinb.monsters.MonsterGen;
import dev.dustinb.player.Player;
import dev.dustinb.player.PlayerSave;


import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        MainMenu mainMenu = new MainMenu();
        mainMenu.showMainMenu();
        new PlayerSave().loadSave();

        System.out.println("end of program");

    }
}
