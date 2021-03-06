package Reservation;

import java.util.Calendar;

import Customer.Customer;
import Table.Table;
/**
 * Represents a reservation to be made by a customer for the restaurant
 * @author tengwei
 * @version 1.0
 * @since 2021-11-14
 */

public class Reservation {
    /**
     * The Id of this Reservation
     */
    private final int reservationId;
    /**
     * The Id of this nextReservationId
     */
    private static int nextReservationId = 1;
    /**
     * The Calendar object representing this Reservation date
     */
    private Calendar reservationDate;
    /**
     * The customer making this Reservation
     */
    private Customer customer;
    /**
     * The number of people coming for this Reservation
     */
    private int numPax;
    /**
     * The table of this Reservation
     */
    private Table table;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // constructors
    /**
     * Creates a new Reservation with This Reservation's date, customer, number of customers and table
     * @param reservationDate This Reservation's date
     * @param customer This Reservation's customer
     * @param numPax This Reservation's number of customers
     * @param table This Reservation's table
     */
    
    public Reservation(Calendar reservationDate, Customer customer, int numPax, Table table) {
        reservationId = getNextReservationId();
        this.reservationDate = reservationDate;
        this.customer = customer;
        this.numPax = numPax;
        this.table = table;
        table.reserveTable();
    }
    /**
     *Creates a new Reservation with This Reservation's reservationId, reservationDate, customer, number of customers and table
     * @param reservationId This Reservation's reservationId
     * @param reservationDate This Reservation's date
     * @param customer This Reservation's customer
     * @param numPax This Reservation's number of customers
     * @param table This Reservation's table
     */


    public Reservation(int reservationId, Calendar reservationDate, Customer customer, int numPax, Table table) {
        this.reservationId = reservationId;
        this.reservationDate = reservationDate;
        this.customer = customer;
        this.numPax = numPax;
        this.table = table;
        table.reserveTable();
    }

    // getters
    /**
     * Gets the reservation Id of This Reservation
     * @return this Reservation's reservationId
     */
    public int getReservationId() {return reservationId;}
    /**
     * Gets the reservation date of This Reservation
     * @return this Reservation's reservationDate
     */
    public Calendar getReservationDate() {return reservationDate;}
    /**
     * Gets the customer of This Reservation
     * @return this Reservation's customer
     */
    public Customer getCustomer() {return customer;}
    /**
     * Gets the name of the customer of This Reservation
     * @return this Reservation's customer name
     */
    public String getCustomerName() {return customer.getName();}
    /**
     * Gets the contact number of the customer of This Reservation
     * @return this Reservation's customer contact number
     */
    public int getCustomerContactNo() {return customer.getContactNo();}
    /**
     * Gets the number of customers of This Reservation
     * @return this Reservation's number of customers
     */
    public int getNumPax() {return numPax;}
    /**
     * Gets the table of This Reservation
     * @return this Reservation's table
     */
    public Table getTable() {return table;}

    // setters
    /**
     * Sets the next reservationId
     * @param nextReservationId the reservationId of the reservation after this Reservation
     */
    public static void setNextReservationId(int nextReservationId) {Reservation.nextReservationId = nextReservationId;}
    /**
     * Sets the date for this Reservation
     * @param reservationDate the date of This Reservation
     */
    public void setReservationDate(Calendar reservationDate) {this.reservationDate = reservationDate;}
    /**
     * Sets the customer for this Reservation
     * @param customer the customer for This Reservation
     */
    public void setCustomer(Customer customer) {this.customer = customer;}
    /**
     * Sets the number of people for this Reservation
     * @param numPax the number of people for This Reservation
     */
    public void setNumPax(int numPax) {this.numPax = numPax;}

    // methods
    /**
     * Gets the next Reservation's reservationId
     * @return nextReservationId incremented by 1
     */
    public static int getNextReservationId() {
        return nextReservationId++;
    }

    /**
     * Prints the details of this Reservation
     */
    public void printReservation() {
        System.out.println(ANSI_CYAN +  "-------------------------------" + ANSI_RESET);
        System.out.println(ANSI_CYAN +  "Reservation ID: " + reservationId  + ANSI_RESET);
        int year = reservationDate.get(Calendar.YEAR);
        int month = reservationDate.get(Calendar.MONTH);
        int day = reservationDate.get(Calendar.DAY_OF_MONTH);
        int hour = reservationDate.get(Calendar.HOUR_OF_DAY);
        int minute = reservationDate.get(Calendar.MINUTE);
        int second = reservationDate.get(Calendar.SECOND);
        System.out.printf(ANSI_CYAN +  "Reservation Date and Time: %02d/%02d/%4d %02d:%02d:%02d\n"  + ANSI_RESET, day, month + 1, year, hour, minute, second);
        System.out.println(ANSI_CYAN +  "Customer Name: " + customer.getName()  + ANSI_RESET);
        System.out.println(ANSI_CYAN +  "Contact Number: " + customer.getContactNo()  + ANSI_RESET);
        System.out.println(ANSI_CYAN +  "Number of People: " + numPax  + ANSI_RESET);
        System.out.println(ANSI_CYAN +  "Table ID: " + table.getTableId()  + ANSI_RESET);
    }
}
