package dev.dustinb;

import dev.dustinb.Menus.MainMenu;
import dev.dustinb.player.PlayerSave;

public class Main {

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.showMainMenu();
        System.out.println("end of program");
    }
}
