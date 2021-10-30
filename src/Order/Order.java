package Order;
import java.util.ArrayList;

import Food.MenuItem;
import RestaurantBack.Reservation;
import RestaurantBack.Staff;
import RestuarantFront.Table;

public class Order {

	private Staff staffOrder; // staff incarge of making this order
	private int orderID;    // order ID unique to this order
	private Reservation reservationName;    // reservation associated with this order
	protected ArrayList<MenuItem> orderedItems;    // list of items ordered by this order
	private Table tableID;  // table associated with this order
    private Boolean isReservatation; // true if this order is a reservation

    // constructor

    public Order(Staff staffOrder, int orderID, Reservation reservationName, ArrayList<MenuItem> orderedItems, Table tableID, Boolean isReservatation) {
        this.staffOrder = staffOrder;
        this.orderID = orderID;
        this.reservationName = reservationName;
        this.orderedItems = orderedItems;
        this.tableID = tableID;
        this.isReservatation = isReservatation;
    }

    // getters
    public Staff getStaffOrder() {
        return staffOrder;
    }

    public int getOrderID() {
        return orderID;
    } 

    public Reservation getReservationName() {
        return reservationName;
    }

    public ArrayList<MenuItem> getOrderedItems() {
        return orderedItems;
    }

    public Table getTableID() {
        return tableID;
    }

    public Boolean getIsReservatation() {
        return isReservatation;
    }

    // update order
    public void addToOrder(MenuItem MenuItem) {
        orderedItems.add(MenuItem);
    }

    // calculate total price of order
    public double getTotalPrice() {
        double totalPrice = 0;
        for (MenuItem MenuItem : orderedItems) {
            totalPrice += MenuItem.getPrice();
        }
        return totalPrice;
    }

    // print order
    public void printOrder() {
        System.out.println("Order ID: " + orderID);
        System.out.println("Staff: " + staffOrder.getName());
        System.out.println("Table: " + tableID.getTableID());
        System.out.println("Reservation: " + reservationName.getReservationID());
        System.out.println("Items: ");
        for (MenuItem MenuItem : orderedItems) {
            System.out.println(MenuItem.getName());
        }
        System.out.println("Total Price: " + getTotalPrice());
    }
}