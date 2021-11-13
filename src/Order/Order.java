package Order;

import java.util.HashMap;

import Food.MenuItem;
import Food.SetItem;

public class Order {
    protected int orderId;
    private static int nextOrderId = 1;
    protected String staffName;
    protected int tableId;
    protected final HashMap<MenuItem, Integer> orderedMenuItems;
    protected final HashMap<SetItem, Integer> orderedSetItems;

    // constructors
    public Order(String staffName, int tableId, HashMap<MenuItem, Integer> orderedMenuItems, HashMap<SetItem, Integer> orderedSetItems) {
        orderId = getNextOrderId();
        this.staffName = staffName;
        this.tableId = tableId;
        this.orderedMenuItems = orderedMenuItems;
        this.orderedSetItems = orderedSetItems;
    }

    public Order(int orderId, String staffName, int tableId, HashMap<MenuItem, Integer> orderedMenuItems, HashMap<SetItem, Integer> orderedSetItems) {
        this.orderId = orderId;
        this.staffName = staffName;
        this.tableId = tableId;
        this.orderedMenuItems = orderedMenuItems;
        this.orderedSetItems = orderedSetItems;

    }

    // getters
    public int getOrderId() {return orderId;}
    public String getStaffName() {return staffName;}
    public int getTableId() {return tableId;}
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
        System.out.println("-------------------------------");
        System.out.println("Order ID: " + orderId);
        System.out.println("Staff: " + staffName);
        System.out.println("Table ID: " + tableId);
        System.out.println("-------------------");
        System.out.println("MenuItems:");
        for (MenuItem menuItem : orderedMenuItems.keySet()) {
            System.out.println("||" + orderedMenuItems.get(menuItem) + " x " + menuItem.getName());
        }
        System.out.println("-------------------");
        System.out.println("SetItems:");
        for (SetItem setItem : orderedSetItems.keySet()) {
            System.out.println("||" + orderedSetItems.get(setItem) + " x " + setItem.getName());
        }
    }
}
