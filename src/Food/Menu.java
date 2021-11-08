package Food;
import java.io.Serializable;
import java.util.*;

public class Menu implements Serializable {
    private HashMap<Integer, Products> menu; // menu item id, menu item

    public Menu() {
        menu = new HashMap<Integer, Products>();
    }

    public HashMap<Integer, Products> getMenu() {
        return menu;
    }

    public void addMenuItem(Products item) {
        int id = menu.size() + 1;
        menu.put(id, item);
    }

    public void addFood(Products food) {
        addMenuItem(food);
    }

    public Products getMenuItem(int id) {
        return menu.get(id);
    }

    public MenuItem getMenuItem(String name) {
        for (Products item : menu.values()) {
            if (item.getName().equals(name)) {
                return (MenuItem) item;
            }
        }
        return null;
    }

    public void printMenu() {
        for (Products item : menu.values()) {
            System.out.println(item);
        }
    }

    public void removeMenuItem(int id) {
        menu.remove(id);
    }

}
