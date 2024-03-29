package dev.dustinb.items;

import java.io.Serializable;

public class Item implements Serializable {
    private final String name;
    private final int sell;
    private final int statBoost; //handles either healing or attack damage increases
    private final boolean isWeapon;
    private final boolean isHealing;


    public Item(){
        this.name = "Stick";
        this.sell = 1;
        this.statBoost = 0;
        this.isWeapon = true;
        this.isHealing = false;
    }
    public Item(String name, int sell, int statBoost, boolean isWeapon, boolean isHealing){
        this.name = name;
        this.sell = sell;
        this.statBoost = statBoost;
        this.isWeapon = isWeapon;
        this.isHealing = isHealing;
    }

    public String getName() {
        return name;
    }

    public int getSell() {
        return sell;
    }

    public int getStatBoost() {
        return statBoost;
    }

    //for equipment purposes
    public boolean isWeapon() {
        return isWeapon;
    }

    public boolean isHealing() {
        return isHealing;
    }

    @Override
    public String toString() {
        String property;
        if(isHealing){
            property = "Restores Health";
        }
        else if (isWeapon){
            property = "Weapon";
        }
        else{
            property = "Trash";
        }

        return (name + " | Power: " + statBoost + " | Value: " + sell + " | " + property);
    }
}
