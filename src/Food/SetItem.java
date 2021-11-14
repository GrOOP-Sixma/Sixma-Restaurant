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
