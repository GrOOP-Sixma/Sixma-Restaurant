import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Reservation {

	private int reservationID;
	private int numOfPax;
	private int customerContactNo;
	private String customerName;
	private Calendar reservationTime;
	private Calendar reservationDate;
	private Table tableNo;
	private boolean reservationSuccess;
	
	
	
	// Constructor for Reservation
	public Reservation(int reservationID, int numOfPax, int customerContactNo, String customerName, Calendar reservationTime,
					   Calendar reservationDate, Table tableNo, boolean reservationSuccess ) {
		this.reservationID = reservationID;
		this.numOfPax = numOfPax;
		this.customerContactNo = customerContactNo;
		this.reservationDate = reservationDate;
		this.reservationTime = reservationTime;
		this.tableNo = tableNo;
		this.reservationSuccess = false;
		
	}
	
	
	public int getReservationID() {
		return this.reservationID;
	}


	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}
	
	
	public int getNumOfPax() {
		return this.numOfPax;
	}
	
	public void setNumOfPax(int numOfPax) {
		this.numOfPax = numOfPax;
	}
	
	
	public int getCustomerContactNo() {
		return this.customerContactNo;
	}

	public void setCustomerContactNo(int customerContactNo) {
		this.customerContactNo = customerContactNo;
	}
	
	
	public String getCustomerName() {
		return this.customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	

	public boolean getReservationSuccess() {
		return this.reservationSuccess;
	}

	// Set reservationSuccess to true and TableStatus to RESERVED
	public void setReservationSuccess() {
		this.reservationSuccess = true;
		this.tableNo.setStatus(TableStatus.RESERVED);
	}
	

	public Calendar getReservationDate() {
		return this.reservationDate;
	}

	public void setReservationDate(Calendar reservationDate) {
		this.reservationDate = reservationDate;
	}

	public Calendar getReservationTime(){
		return this.reservationTime;
	}

	public void setReservationTime(Calendar reservationTime){
		this.reservationTime = reservationTime;
	}
	
	// Prints out the information of the reservation
	public String toString(){
		return "Customer Name:" + this.customerName + "    Contact: " + this.customerContactNo + 
				"	 Reserved Date: " + this.reservationDate + "   Reserved time: " + this.reservationTime + 
				"	 Reservation ID: "+ this.reservationID +
				"	 Table Number: "+ this.tableNo;
	}

}
