package dev.dustinb;

import dev.dustinb.items.Item;
import dev.dustinb.monsters.Monster;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseAccess {

    private final ArrayList<Item> itemList;
    private final ArrayList<Monster> monsterList;
    private Connection connection;

    public DatabaseAccess() {
        this.itemList = new ArrayList<Item>();
        this.monsterList = new ArrayList<Monster>();
        this.connection = null;
    }

    //grab info from database and populate Arraylist
//monsters
    public ArrayList<Monster> getMonsterList(int category) {
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:TextAdventure/GGAdventure/src/dev/dustinb/UntitledAdventure.db");
            String sql = "SELECT * FROM monsters WHERE category = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(category));

            ResultSet rs = preparedStatement.executeQuery();;

            //loop through entries
            while (rs.next()){
                Monster newMonster = new Monster(rs.getString("name"), rs.getInt("hp"), rs.getInt("attack"),
                        rs.getInt("experience"), rs.getString("weapon"), rs.getInt("gold"), rs.getString("drop"));
                monsterList.add(newMonster);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return monsterList;
    }

    //items
    public ArrayList<Item> getItemList(){
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:TextAdventure/GGAdventure/src/dev/dustinb/UntitledAdventure.db");
            String sql = "SELECT * FROM items";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            //loop through entries
            while (rs.next()){
                Item newItem = new Item(rs.getString("name"), rs.getInt("sell"), rs.getInt("statBoost"),
                        rs.getBoolean("isWeapon"), rs.getBoolean("isHealing"));
                itemList.add(newItem);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return itemList;
    }


}
