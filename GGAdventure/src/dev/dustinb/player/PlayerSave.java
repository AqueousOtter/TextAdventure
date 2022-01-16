package dev.dustinb.player;

import java.io.*;

public class PlayerSave {

    //Saves player stats and inventory -- Item class and Player Class are Serializable
    public void saveGame(Player player) {
        try{
            FileOutputStream fileOut = new FileOutputStream("player.data");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(player);
            fileOut.flush();
            objectOut.flush();
            fileOut.close();
            objectOut.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public Player loadSave() {
        Player player = null;
        try {
            //read from disk
            FileInputStream fileIn = new FileInputStream("player.data");
            //read object
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            //cast
            player = (Player) objectIn.readObject();
            System.out.println("Read player. Name: " + player.getName());

        }catch(Exception e){
            e.printStackTrace();
        }
        return player;
    }
}
