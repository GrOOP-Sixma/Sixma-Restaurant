package Order;

import java.util.HashMap;

import Food.MenuItem;
import Food.SetItem;

public class Order {
    protected int orderId;
    private static int nextOrderId = 1;
    protected String staffName;
    protected int tableId;
    protected boolean isMember;
    protected final HashMap<MenuItem, Integer> orderedMenuItems;
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
    public Order(String staffName, int tableId, boolean isMember, HashMap<MenuItem, Integer> orderedMenuItems, HashMap<SetItem, Integer> orderedSetItems) {
        orderId = getNextOrderId();
        this.staffName = staffName;
        this.tableId = tableId;
        this.isMember = isMember;
        this.orderedMenuItems = orderedMenuItems;
        this.orderedSetItems = orderedSetItems;
    }

    public Order(int orderId, String staffName, int tableId, boolean isMember, HashMap<MenuItem, Integer> orderedMenuItems, HashMap<SetItem, Integer> orderedSetItems) {
        this.orderId = orderId;
        this.staffName = staffName;
        this.tableId = tableId;
        this.isMember = isMember;
        this.orderedMenuItems = orderedMenuItems;
        this.orderedSetItems = orderedSetItems;

    }

    // getters
    public int getOrderId() {return orderId;}
    public String getStaffName() {return staffName;}
    public int getTableId() {return tableId;}
    public boolean isMember() {return isMember;}
    public HashMap<MenuItem, Integer> getOrderedMenuItems() {return orderedMenuItems;}
    public HashMap<SetItem, Integer> getOrderedSetItems() {return orderedSetItems;}

    // setters
    public static void setNextOrderId(int nextOrderId) {Order.nextOrderId = nextOrderId;}
    public void setStaffName(String staffName) {this.staffName = staffName;}
    public void setTableId(int tableId) {this.tableId = tableId;}

    // methods
    private static int getNextOrderId() {
        return nextOrderId++;
    }

    public void addMenuItem(MenuItem menuItem, int quantity) {
        if (!orderedMenuItems.containsKey(menuItem)) {
            orderedMenuItems.put(menuItem, quantity);
        }
        else {
            orderedMenuItems.put(menuItem, orderedMenuItems.get(menuItem) + quantity);
        }
    }

    public void addSetItem(SetItem setItem, int quantity) {
        if (!orderedSetItems.containsKey(setItem)) {
            orderedSetItems.put(setItem, quantity);
        }
        else {
            orderedSetItems.put(setItem, orderedSetItems.get(setItem) + quantity);
        }
    }

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
