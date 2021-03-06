package Order;

import java.util.HashMap;

import Food.MenuItem;
import Food.SetItem;
/**
 * Represent an order to be made by a customer at the restaurant
 * @author tengwei
 * @version 1.0
 * @since 2021-11-14
 */
public class Order {
	/**
	 * The orderId of this Order
	 */
    protected int orderId;
    /**
     * The initialisation of the orderId 
     */
    private static int nextOrderId = 1;
    /**
     * The staff name of this Order
     */
    protected String staffName;
    /**
     * The tableId of this Order
     */
    protected int tableId;
    /**
     * The ordered menu items of this Order
     */
    protected boolean isMember;
    protected final HashMap<MenuItem, Integer> orderedMenuItems;
    /**
     * The ordered set menu items of this Order
     */
    protected final HashMap<SetItem, Integer> orderedSetItems;

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
     * Creates a new order with this Order's staffName, tableId, orderedMenuItems and orderedSetItems
     * @param staffName this Order's staff name
     * @param tableId this Order's tableId
     * @param isMember this Order's customer's membership
     * @param orderedMenuItems this Order's ordered menu items
     * @param orderedSetItems this Order's ordered set items
     */
    public Order(String staffName, int tableId, boolean isMember, HashMap<MenuItem, Integer> orderedMenuItems, HashMap<SetItem, Integer> orderedSetItems) {
        orderId = getNextOrderId();
        this.staffName = staffName;
        this.tableId = tableId;
        this.isMember = isMember;
        this.orderedMenuItems = orderedMenuItems;
        this.orderedSetItems = orderedSetItems;
    }
    /**
     * Creates a new Order with this Order's staffName, tableId, orderedMenuItems and orderedSetItems
     * @param orderId this Order's orderId
     * @param staffName this Order's staff name
     * @param tableId this Order's tableId
     * @param isMember this Order's customer's membership
     * @param orderedMenuItems this Order's ordered menu items
     * @param orderedSetItems this Order's ordered set items
     */
    public Order(int orderId, String staffName, int tableId, boolean isMember, HashMap<MenuItem, Integer> orderedMenuItems, HashMap<SetItem, Integer> orderedSetItems) {
        this.orderId = orderId;
        this.staffName = staffName;
        this.tableId = tableId;
        this.isMember = isMember;
        this.orderedMenuItems = orderedMenuItems;
        this.orderedSetItems = orderedSetItems;

    }

    // getters
    /**
     * Gets the orderId of this Order
     * @return this Order' orderId
     */
    public int getOrderId() {return orderId;}
    /**
     * Gets the staff name of this Order
     * @return this Order's staffName
     */
    public String getStaffName() {return staffName;}
    /**
     * Gets the tableId of this Order
     * @return this Order's tableId
     */
    public int getTableId() {return tableId;}
 
    /**
     * Gets the membership of this Order's customer
     * @return whether this customer is a member
     */
    public boolean isMember() {return isMember;}
    /**
     * Gets the ordered menu items of this Order
     * @return this Order's orderedMenuItems
     */
    public HashMap<MenuItem, Integer> getOrderedMenuItems() {return orderedMenuItems;}
    /**
     * Gets the ordered set items of this Order
     * @return this Order's orderedSetItems
     */
    public HashMap<SetItem, Integer> getOrderedSetItems() {return orderedSetItems;}

    // setters
    /**
     * Sets the orderId of this Order
     * @param nextOrderId this Order's orderId
     */
    public static void setNextOrderId(int nextOrderId) {Order.nextOrderId = nextOrderId;}
    /**
     * Sets the name of the staff of this Order
     * @param staffName this Order's staffName
     */
    public void setStaffName(String staffName) {this.staffName = staffName;}
    /**
     * Sets the tableId of this Order
     * @param tableId this Order's tableId
     */
    public void setTableId(int tableId) {this.tableId = tableId;}

    // methods
    /**
     * Gets the next orderId
     * @returns nextOrderId incremented by 1
     */
    private static int getNextOrderId() {
        return nextOrderId++;
    }

    /**
     * Adds a menuItem to this Order's orderdMenuItems
     * @param menuItem this new menuItem object
     * @param quantity the number of menuItem objects to be added
     */
    public void addMenuItem(MenuItem menuItem, int quantity) {
        if (!orderedMenuItems.containsKey(menuItem)) {
            orderedMenuItems.put(menuItem, quantity);
        }
        else {
            orderedMenuItems.put(menuItem, orderedMenuItems.get(menuItem) + quantity);
        }
    }

    /**
     * Adds a setItem to this Order's orderedSetItems
     * @param setItem this new setItem object
     * @param quantity the number of setItem objects to be added
     */
    public void addSetItem(SetItem setItem, int quantity) {
        if (!orderedSetItems.containsKey(setItem)) {
            orderedSetItems.put(setItem, quantity);
        }
        else {
            orderedSetItems.put(setItem, orderedSetItems.get(setItem) + quantity);
        }
    }

    /**
     * Removes a menuItem from this Order's orderedMenuItems
     * if the orderedMenuItems contains the quantity of menuItem
     * @param menuItem this new menuItem object
     * @param quantity the number of menuItem objects to be removed
     * @return whether the menuItem has been removed from the orderedMenuItems
     */
    public int removeMenuItem(MenuItem menuItem, int quantity) {
        if (orderedMenuItems.containsKey(menuItem)) {
            if (orderedMenuItems.get(menuItem) == quantity) {
                orderedMenuItems.remove(menuItem);
            }
            else {
                orderedMenuItems.put(menuItem, orderedMenuItems.get(menuItem) - quantity);
            }
            return 1;
        }
        return 0;
    }

    /**
     * Removes a setItem from this Order's orderedSetItems
     * if the orderedSetItems contains the quantity of setItem
     * @param setItem this new setItem object
     * @param quantity the number of setItem objects to be removed
     * @return whether the setItem has been removed from the orderedSetItems
     */
    public int removeSetItem(SetItem setItem, int quantity) {
        if (orderedSetItems.containsKey(setItem)) {
            if (orderedSetItems.get(setItem) == quantity) {
                orderedSetItems.remove(setItem);
            }
            else {
                orderedSetItems.put(setItem, orderedSetItems.get(setItem) - quantity);
            }
            return 1;
        }
        return 0;
    }

    /**
     * Prints this Order's details
     */
    public void printOrder() {
        System.out.println(ANSI_BLUE+  "-------------------------------" + ANSI_RESET);
        System.out.println(ANSI_BLUE+  "Order ID: " + orderId + ANSI_RESET);
        System.out.println(ANSI_BLUE+  "Staff: " + staffName + ANSI_RESET);
        System.out.println(ANSI_BLUE+  "Table ID: " + tableId + ANSI_RESET);
        System.out.println(ANSI_BLUE+  "-------------------" + ANSI_RESET);
        System.out.println(ANSI_BLUE+  "MenuItems:" + ANSI_RESET);
        for (MenuItem menuItem : orderedMenuItems.keySet()) {
            System.out.println(ANSI_BLUE+  "||" + orderedMenuItems.get(menuItem) + " x " + menuItem.getName() + " ||" + ANSI_RESET);
        }
        System.out.println(ANSI_BLUE+  "-------------------" + ANSI_RESET);
        System.out.println(ANSI_BLUE+  "SetItems:" + ANSI_RESET);
        for (SetItem setItem : orderedSetItems.keySet()) {
            System.out.println(ANSI_BLUE+  "||" + orderedSetItems.get(setItem) + " x " + setItem.getName() + " ||" + ANSI_RESET);
        }
    }
}
