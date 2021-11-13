package Reservation;

import java.util.Calendar;

import Customer.Customer;
import Table.Table;

public class Reservation {
    private final int reservationId;
    private static int nextReservationId = 1;
    private Calendar reservationDate;
    private Customer customer;
    private int numPax;
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
    public Reservation(Calendar reservationDate, Customer customer, int numPax, Table table) {
        reservationId = getNextReservationId();
        this.reservationDate = reservationDate;
        this.customer = customer;
        this.numPax = numPax;
        this.table = table;
        table.reserveTable();
    }

    public Reservation(int reservationId, Calendar reservationDate, Customer customer, int numPax, Table table) {
        this.reservationId = reservationId;
        this.reservationDate = reservationDate;
        this.customer = customer;
        this.numPax = numPax;
        this.table = table;
        table.reserveTable();
    }

    // getters
    public int getReservationId() {return reservationId;}
    public Calendar getReservationDate() {return reservationDate;}
    public Customer getCustomer() {return customer;}
    public String getCustomerName() {return customer.getName();}
    public int getCustomerContactNo() {return customer.getContactNo();}
    public int getNumPax() {return numPax;}
    public Table getTable() {return table;}

    // setters
    public static void setNextReservationId(int nextReservationId) {Reservation.nextReservationId = nextReservationId;}
    public void setReservationDate(Calendar reservationDate) {this.reservationDate = reservationDate;}
    public void setCustomer(Customer customer) {this.customer = customer;}
    public void setNumPax(int numPax) {this.numPax = numPax;}

    // methods
    public static int getNextReservationId() {
        return nextReservationId++;
    }

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
