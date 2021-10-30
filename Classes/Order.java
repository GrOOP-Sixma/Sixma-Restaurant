import java.util.ArrayList;

public class Order {

	private Staff staffOrder; // staff incarge of making this order
	private int orderID;    // order ID unique to this order
	private Reservation reservationName;    // reservation associated with this order
	protected ArrayList<Food> orderedItems;    // list of items ordered by this order
	private Table tableID;  // table associated with this order
    private Boolean isReservatation; // true if this order is a reservation

    // constructor

    public Order(Staff staffOrder, int orderID, Reservation reservationName, ArrayList<Food> orderedItems, Table tableID, Boolean isReservatation) {
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

    public ArrayList<Food> getOrderedItems() {
        return orderedItems;
    }

    public Table getTableID() {
        return tableID;
    }

    public Boolean getIsReservatation() {
        return isReservatation;
    }

    // update order
    public void addToOrder(Food food) {
        orderedItems.add(food);
    }

    // calculate total price of order
    public double getTotalPrice() {
        double totalPrice = 0;
        for (Food food : orderedItems) {
            totalPrice += food.getPrice();
        }
        return totalPrice;
    }
}