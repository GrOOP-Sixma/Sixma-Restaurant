package Food;
import java.util.ArrayList;

public class Menu {
    private int numberOfItems = 0; // number of items in the menu
    protected ArrayList<MenuItem> menuItems; // the menu items

    // constructor
    public Menu() {
        menuItems = new ArrayList<MenuItem>();
    }

    // add a menu item
    public void addItem(MenuItem item) {
        menuItems.add(item);
        numberOfItems++;
    }

    // remove a menu item
    public void removeItem(MenuItem item) {
        menuItems.remove(item);
        numberOfItems--;
    }

    // get a menu item
    public MenuItem getItem(int index) {
        return menuItems.get(index);
    }

    // get all menu items
    public ArrayList<MenuItem> getAllItems() {
        return menuItems;
    }

    // get the number of items in the menu
    public int getNumberOfItems() {
        return numberOfItems;
    }

    // print the menu
    public void print() {
        System.out.println("Menu");
        System.out.println("-----------------------------");
        for (int i = 0; i < numberOfItems; i++) {
            MenuItem item = menuItems.get(i);
            System.out.println(item.getName());
            System.out.println("\t" + item.getDescription());
            System.out.println("\t" + item.getPrice());
        }
        System.out.println("-----------------------------");
    }
}
