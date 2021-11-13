package Food;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SetItem extends Products {
    private int setItemId;
    private static int nextSetItemId = 1;
    private final ArrayList<MenuItem> setItems;

    // constructors
    public SetItem(String name, double price, ArrayList<MenuItem> setItems) {
        super(name, price);
        setItemId = getNextSetItemId();
        this.setItems = setItems;
    }

    public SetItem(String name, double price, int setItemId, ArrayList<MenuItem> setItems) {
        super(name, price);
        this.setItemId = setItemId;
        this.setItems = setItems;
    }

    // getters
    public int getSetItemId() {return setItemId;}
    public ArrayList<MenuItem> getSetItems() {return setItems;}

    // setters
    public static void setNextSetItemId(int nextSetItemId) {SetItem.nextSetItemId = nextSetItemId;}

    // methods
    private static int getNextSetItemId() {
        return nextSetItemId++;
    }

    public void addMenuItem(MenuItem menuItem) {
        setItems.add(menuItem);
    }

    public void removeMenuItem(MenuItem menuItem) {
        setItems.remove(menuItem);
    }

    public void printSetItem() {
        System.out.println("-------------------------------");
        System.out.println("Set ID: " + setItemId);
        System.out.println("Name: " + name);
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("Price: $" + df.format(price));
        System.out.println("Number of Items: " + setItems.size());
        for (MenuItem menuItem : setItems) {
            System.out.println("-------------------");
            System.out.println("||Item ID: " + menuItem.getMenuItemId());
            System.out.println("||Name: " + menuItem.getName());
            if (menuItem.getFoodType() == FoodType.MAIN_COURSE) {
                System.out.println("||Food Type: Main Course");
            }
            else if (menuItem.getFoodType() == FoodType.DRINKS) {
                System.out.println("||Food Type: Drinks");
            }
            else {
                System.out.println("||Food Type: Dessert");
            }
            System.out.println("||Description: " + menuItem.getDescription());
        }
    }
}
