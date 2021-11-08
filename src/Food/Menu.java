package Food;
import java.util.*;

public class Menu {
    private HashMap<Integer, MenuItem> menu; // menu item id, menu item

    public Menu() {
        menu = new HashMap<Integer, MenuItem>();
    }

    public HashMap<Integer, MenuItem> getMenu() {
        return menu;
    }

    public void addMenuItem(MenuItem item) {
        int id = menu.size() + 1;
        menu.put(id, item);
    }

    public void addFood(MenuItem food) {
        addMenuItem(food);
    }

    public  MenuItem getMenuItem(int id) {
        return menu.get(id);
    }

    public void printMenu() {
        for (MenuItem item : menu.values()) {
            System.out.println(item);
        }
    }

    public void removeMenuItem(int id) {
        menu.remove(id);
    }


}
