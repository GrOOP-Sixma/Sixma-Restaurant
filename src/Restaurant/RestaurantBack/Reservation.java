package Restaurant.RestaurantBack;

import java.util.Calendar;
import Restaurant.RestaurantFront.*;

public class Reservation {
	private final int reservationID;
	private static int nextReservationID = 1;
	private int numOfPax;
	private int customerContactNo;
	private String customerName;
	private Calendar reservationDate;
	private Table table;

	// constructor
	public Reservation(Customer customer, Table table, int numOfPax, Calendar reservationDate) {
		reservationID = getNextReservationID();
		this.numOfPax = numOfPax;
		this.customerContactNo = customer.getContactNo();
		this.customerName = customer.getName();
		this.reservationDate = reservationDate;
		this.table = table;
	}

	// getters
	public int getReservationID() {return reservationID;}
	public int getNumOfPax() {return numOfPax;}
	public int getCustomerContactNo() {return customerContactNo;}
	public String getCustomerName() {return customerName;}
    public Calendar getReservationDate() {return reservationDate;}
	public Table getTable() {return table;}

	// setters
	public void setNumOfPax(int numOfPax) {this.numOfPax = numOfPax;}
	public void setCustomerInfo(Customer customer) {
		this.customerContactNo = customer.getContactNo();
		this.customerName = customer.getName();
	}
	public void setReservationDate(Calendar reservationDate) {this.reservationDate = reservationDate;}
	public void setTable(Table table) {this.table = table;}

	// methods
	private static int getNextReservationID() {
		return nextReservationID++;
	}

	// toString
	public String toString(){
		return "Customer Name:" + this.customerName + "    Contact: " + this.customerContactNo + 
				"	 Reserved Date: " + this.reservationDate +
				"	 Reservation ID: "+ this.reservationID +
				"	 Table Number: "+ this.table.getTableID();
	}
}
