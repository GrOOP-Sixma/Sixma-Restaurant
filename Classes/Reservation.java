public class Reservation {

	private int reservationID;
	private int numOfPax;
	private int customerContactNo;
	private String customerName;
	private Calender reservationTime;
	private Table tableNo;
	private boolean reservationSuccess;
	private Calender reservationDate;

	public int getReservationID() {
		return this.reservationID;
	}

	/**
	 * 
	 * @param reservationID
	 */
	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}

	public int getNumOfPax() {
		return this.numOfPax;
	}

	/**
	 * 
	 * @param numOfPax
	 */
	public void setNumOfPax(int numOfPax) {
		this.numOfPax = numOfPax;
	}

	public int getCustomerContactNo() {
		return this.customerContactNo;
	}

	/**
	 * 
	 * @param customerContactNo
	 */
	public void setCustomerContactNo(int customerContactNo) {
		this.customerContactNo = customerContactNo;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	/**
	 * 
	 * @param customerName
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public boolean getReservationSuccess() {
		return this.reservationSuccess;
	}

	public void setReservationSuccess() {
		// TODO - implement Reservation.setReservationSuccess
		throw new UnsupportedOperationException();
	}

	public Calender getReservationDate() {
		return this.reservationDate;
	}

	/**
	 * 
	 * @param reservationDate
	 */
	public void setReservationDate(Calender reservationDate) {
		this.reservationDate = reservationDate;
	}

}