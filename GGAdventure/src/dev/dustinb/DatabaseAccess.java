package dev.dustinb;

import dev.dustinb.items.Item;
import dev.dustinb.monsters.Monster;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseAccess {

    private ArrayList<Item> itemList;
    private ArrayList<Monster> monsterList;
    private Connection connection;

    public DatabaseAccess() {
        this.itemList = new ArrayList<Item>();
        this.monsterList = new ArrayList<Monster>();
        this.connection = null;
    }

    //grab info from database and populate Arraylist

    public ArrayList<Monster> getMonsterList(int category) {
        connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:UntitledAdventure.db");
            String sql = "SELECT * FROM monsters";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

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


}
