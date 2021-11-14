package Food;

import java.text.DecimalFormat;
import java.util.ArrayList;
/**
 * Represents the setItems in the SetMenu class 
 * @author tengwei
 * @version 1.0
 * @since 2021-11-14 
 */
public class SetItem extends Products {
	/**
	 * The setItemId of this SetItem
	 */
    private int setItemId;
    /**
     * The initialisation of the setItemId
     */
    private static int nextSetItemId = 1;
    /**
     * The list of menuItems in this SetItem's setItems
     */
    private final ArrayList<MenuItem> setItems;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // constructors
    /**
     * Creates a new SetItem with this SetItem's name, price, setItems
     * @param name this SetItem's name
     * @param price this SetItem's price
     * @param setItems this SetItem's setItems
     */
    public SetItem(String name, double price, ArrayList<MenuItem> setItems) {
        super(name, price);
        setItemId = getNextSetItemId();
        this.setItems = setItems;
    }
    /**
     * Creates a new SetItem with this SetItem's name, price, setItems
     * @param name this SetItem's name
     * @param price this SetItem's price
     * @param setItemId this SetItem's setItemId
     * @param setItems this SetItem's setItems
     */
    public SetItem(String name, double price, int setItemId, ArrayList<MenuItem> setItems) {
        super(name, price);
        this.setItemId = setItemId;
        this.setItems = setItems;
    }

    // getters
    /**
     * Gets the setItemId of this SetItem
     * @return this SetItem's setItemId
     */
    public int getSetItemId() {return setItemId;}
    /**
     * Gets the setItems of this SetItem
     * @return this SetItem's setItems
     */
    public ArrayList<MenuItem> getSetItems() {return setItems;}

    // setters
    /**
     * Sets the setItemId of this SetItem
     * @param nextSetItemId this SetItem's setItemId
     */
    public static void setNextSetItemId(int nextSetItemId) {SetItem.nextSetItemId = nextSetItemId;}

    // methods
    /**
     * Gets the next setItemId
     * @return nextSetItemId incremented by 1
     */
    private static int getNextSetItemId() {
        return nextSetItemId++;
    }

    /**
     * Adds a menuItem into this SetItem's setItems
     * @param menuItem this new menuItem object
     */
    public void addMenuItem(MenuItem menuItem) {
        setItems.add(menuItem);
    }

    /**
     * Removes a menuItme from this SetItem's setItems
     * @param menuItem this new menuItem object
     */
    public void removeMenuItem(MenuItem menuItem) {
        setItems.remove(menuItem);
    }

    /**
     * Prints this SetItem's details
     */
    public void printSetItem() {
        System.out.println(ANSI_YELLOW+  "-------------------------------" + ANSI_RESET);
        System.out.println(ANSI_YELLOW+  "Set ID: " + setItemId + ANSI_RESET);
        System.out.println(ANSI_YELLOW+  "Name: " + name + ANSI_RESET);
        DecimalFormat df = new DecimalFormat("0.00" );
        System.out.println(ANSI_YELLOW+  "Price: $" + df.format(price) + ANSI_RESET);
        System.out.println(ANSI_YELLOW+  "Number of Items: " + setItems.size() + ANSI_RESET);
        for (MenuItem menuItem : setItems) {
            System.out.println(ANSI_YELLOW+  "-------------------" + ANSI_RESET);
            System.out.println(ANSI_YELLOW+  "||Item ID: " + menuItem.getMenuItemId() + ANSI_RESET);
            System.out.println(ANSI_YELLOW+  "||Name: " + menuItem.getName() + ANSI_RESET);
            if (menuItem.getFoodType() == FoodType.MAIN_COURSE) {
                System.out.println(ANSI_YELLOW+  "||Food Type: Main Course" + ANSI_RESET);
            }
            else if (menuItem.getFoodType() == FoodType.DRINKS) {
                System.out.println(ANSI_YELLOW+  "||Food Type: Drinks" + ANSI_RESET);
            }
            else {
                System.out.println(ANSI_YELLOW+  "||Food Type: Dessert" + ANSI_RESET);
            }
            System.out.println(ANSI_YELLOW+  "||Description: " + menuItem.getDescription() + ANSI_RESET);
        }
    }
}
