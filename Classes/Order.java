public class Order {

	private Staff staffOrder;
	private int orderID;
	private Reservation reservationName;
	private ArrayList<?> orderedItems;
	private Table tableID;

	public Staff getStaffOrder() {
		return this.staffOrder;
	}

	/**
	 * 
	 * @param staffOrder
	 */
	public void setStaffOrder(Staff staffOrder) {
		this.staffOrder = staffOrder;
	}

	public int getOrderID() {
		return this.orderID;
	}

	/**
	 * 
	 * @param orderID
	 */
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Reservation getReservationName() {
		return this.reservationName;
	}

	/**
	 * 
	 * @param reservationName
	 */
	public void setReservationName(Reservation reservationName) {
		this.reservationName = reservationName;
	}

	public ArrayList<MenuItems> getOrderedItems() {
		// TODO - implement Order.getOrderedItems
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param orderedItems
	 */
	public void setOrderedItems(ArrayList<MenuItems> orderedItems) {
		// TODO - implement Order.setOrderedItems
		throw new UnsupportedOperationException();
	}

	public double calculateItemTotalPrice() {
		// TODO - implement Order.calculateItemTotalPrice
		throw new UnsupportedOperationException();
	}

}