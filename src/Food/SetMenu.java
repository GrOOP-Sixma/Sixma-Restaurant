package Food;

import java.io.Serializable;
import java.util.HashMap;

public class SetMenu implements Serializable {
    private HashMap<Integer, Products> setMenu;
    private String name;

    public SetMenu() {
        setMenu = new HashMap<>();
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    // create a new set
    public void addSet(MenuItem[] set, String name, double price) {
        int setId = setMenu.size() + 1;
        SetItem newSet = new SetItem();
        newSet.setName(name);
        newSet.setSetItems(set);
        newSet.setSetPrice(price);
        setMenu.put(setId, newSet);
    }

    // modify a set
    public void modifySet(int setId, MenuItem[] set, String name, double price) {
        SetItem newSet = new SetItem();
        newSet.setName(name);
        newSet.setSetItems(set);
        newSet.setSetPrice(price);
        setMenu.put(setId, newSet);
    }

    // delete a set
    public void deleteSet(int setId) {
        setMenu.remove(setId);
    }

    // getters
    public HashMap<Integer, Products> getSetMenu() {
        return setMenu;
    }

    public String getName() {
        return name;
    }

    public SetItem getSetItem(String name) {
        for (Products set : setMenu.values()) {
            if (set.getName().equals(name)) {
                return (SetItem) set;
            }
        }
        return null;
    }

    // print
    public void printSetMenu() {
        int i = 0;
        for (Products curr : setMenu.values()) {
            i++;
            SetItem currSet = (SetItem) curr;
            System.out.println("Set ID: " + i + " Name: " + currSet.getName() + " Price: " + currSet.getPrice());
            currSet.printSet();
        }
    }
}
