package Order;
import java.util.HashMap;

import Food.SetItem;
import Staff.Staff;
import Table.Table;

public class Order {

	private Staff staffOrder; // staff incharge of making this order
	private int orderID;    // order ID unique to this order
	private HashMap<Integer, Integer> orderedItems; // map of itemID to quantity
    private HashMap<SetItem, Integer> orderedSetItems; // map of setItem to quantity
	private Table tableID;  // table associated with this order

    // constructor

    public Order(Staff staffOrder, int orderID, HashMap<Integer,Integer> orderedItems, Table tableID) {
        this.staffOrder = staffOrder;
        this.orderID = orderID;
        this.orderedItems = orderedItems;
        this.tableID = tableID;
    }

    // getters
    public Staff getStaffOrder() {
        return staffOrder;
    }

    public int getOrderID() {
        return orderID;
    }

    public HashMap<Integer, Integer> getOrderedItems() {
        return orderedItems;
    }

    public HashMap<SetItem, Integer> getOrderedSetItems() {
        return orderedSetItems;
    }

    public Table getTableID() {
        return tableID;
    }

    // setters
    public void setOrderedItems(HashMap<Integer, Integer> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public void setSetItems(HashMap<SetItem, Integer> orderedSetItems) {
        this.orderedSetItems = orderedSetItems;
    }

}