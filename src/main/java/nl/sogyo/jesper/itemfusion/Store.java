package nl.sogyo.jesper.itemfusion;

import java.util.ArrayList;

/**
 * Created by jvdberg on 24/04/2014.
 */
public class Store {
    private String name;
    private ArrayList<Item> inventory = new ArrayList<Item>();

    public Store(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String nameTemp) {
        name = nameTemp.trim();
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void addToInventory(Item itemToAdd) {
        this.inventory.add(itemToAdd);
    }
}
