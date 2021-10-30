package RestaurantBack;
import java.util.Calendar;

import RestuarantFront.Table;

public class Reservation {

	private int reservationID;
	private int numOfPax;
	private int customerContactNo;
	private String customerName;
	private Calendar reservationTime;
	private Calendar reservationDate;
	private Table tableNo;
	private Boolean reservationSuccess;
	
	
	
	// Constructor for Reservation
	public Reservation(int reservationID, int numOfPax, int customerContactNo, String customerName, Calendar reservationTime, Calendar reservationDate, Table tableNo, Boolean reservationSuccess ) {
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
		this.tableNo.reserve();
	}


    public void changeReservationDate(Calendar reservationDate) {
        this.reservationDate = reservationDate;
    }

    public void cancelReservation() {
        this.reservationSuccess = false;
        this.tableNo.cancel();
    }

	
	// Prints out the information of the reservation
	public String toString(){
		return "Customer Name:" + this.customerName + "    Contact: " + this.customerContactNo + 
				"	 Reserved Date: " + this.reservationDate + "   Reserved time: " + this.reservationTime + 
				"	 Reservation ID: "+ this.reservationID +
				"	 Table Number: "+ this.tableNo;
	}

}
