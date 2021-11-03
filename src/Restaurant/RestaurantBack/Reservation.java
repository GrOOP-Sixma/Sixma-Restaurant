package Restaurant.RestaurantBack;
import java.util.Calendar;

import Restaurant.RestaurantFront.Table;
public class Reservation {

	private int reservationID = 0;
	private int numOfPax;
	private int customerContactNo;
	private String customerName;
	private Calendar reservationDate;
	private Calendar reservationTime;
	private Table tableNo;
	private Boolean reservationSuccess;

	// constructor
	public Reservation(Calendar reservationDate, Table tableNo, Boolean reservationSuccess ) {
		this.reservationID = reservationID;
		this.numOfPax = numOfPax;
		this.customerContactNo = customerContactNo;
		this.reservationDate = reservationDate;
		this.reservationTime = reservationTime;
		this.tableNo = tableNo;
		setReservationSuccess();
		
	}
	
	public int getReservationID() {
		return this.reservationID;
	}
	
	public int getNumOfPax() {
		return this.numOfPax;
	}
	
	public int getCustomerContactNo() {
		return this.customerContactNo;
	}

	
	public String getCustomerName() {
		return this.customerName;
	}

	public boolean getReservationSuccess() {
		return this.reservationSuccess;
	}

    public Calendar getReservationDate() {
        return this.reservationDate;
    }

    public Calendar getReservationTime(){
        return this.reservationTime;
    }

	// Set reservationSuccess to true and TableStatus to RESERVED
    
	public void setReservationSuccess() {
		this.reservationSuccess = true;
		this.tableNo.reserveTable();
		this.reservationID += 1;
	}


    public void changeReservationDate(Calendar reservationDate) {
        this.reservationDate = reservationDate;
    }

    public void cancelReservation() {
        this.reservationSuccess = false;
        this.tableNo.unreserveTable();
    }

	
	// Prints out the information of the reservation
	public String toString(){
		return "Customer Name:" + this.customerName + "    Contact: " + this.customerContactNo + 
				"	 Reserved Date: " + this.reservationDate + "   Reserved time: " + this.reservationTime + 
				"	 Reservation ID: "+ this.reservationID +
				"	 Table Number: "+ this.tableNo;
	}

}
