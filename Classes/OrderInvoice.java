public class OrderInvoice extends Order {

	private Table tableID;
	private Calender date;
	private double subTotal;
	private double total;
	private int invoiceNumber;
	private Order orderItems;
	private boolean membership;

	public double calculateTotal() {
		// TODO - implement OrderInvoice.calculateTotal
		throw new UnsupportedOperationException();
	}

	public void getDate() {
		// TODO - implement OrderInvoice.getDate
		throw new UnsupportedOperationException();
	}

	public Table gettableID() {
		return this.tableID;
	}

}