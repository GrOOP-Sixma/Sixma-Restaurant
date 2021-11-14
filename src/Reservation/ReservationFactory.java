package Reservation;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

import Customer.*;
import Table.*;
/*
 * Represents a boundary class to get the user input
 * in order for the ReservationController class to perform the
 * various method executions on the Reservation class
 */
public class ReservationFactory {
    /*
     * The customerController of this ReservationFactory
     */
    private CustomerController customerController;
    /*
     * The tableController of this ReservationFactory
     */
    private TableController tableController;
    /*
     * The reservationController of this ReservationFactory
     */
    private ReservationController reservationController;
    /*
     * The name of this ReservationFactory
     */
    private String name;

    // constructors
    /*
     * Creates a new ReservationFactory with the given name, customerController and tableController
     * @param name This ReservationFactory's name
     * @param customerController This ReservationFactory's customerController
     * @param tableController This ReservationFactory's tableController
     */
    public ReservationFactory(String name, CustomerController customerController, TableController tableController) {
        this.name = name;
        this.customerController = customerController;
        this.tableController = tableController;
        reservationController = new ReservationController(name, customerController, tableController);
        updateReservations();
    }

    // getters
    /*
     * Gets this ReservationFactory's customerController
     */
    public CustomerController getCustomerController() {return customerController;}
    /*
     * Gets this ReservationFactory's tableController
     */
    public TableController getTableController() {return tableController;}
    /*
     * Gets this ReservationFactory's reservationController
     */
    public ReservationController getReservationController() {return reservationController;}

    // methods
    /*
     * Gets the input of the user
     * @return the user's input
     */
    public int getIntInput() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (sc.hasNextInt()) {
                return sc.nextInt();
            }
            else {
                System.out.println("Invalid input.");
                sc.next();
            }
        }
    }

    /*
     * Runs the ReservationFactor to take in user input
     */
    public void run() {
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        while (choice != 0) {
            System.out.println("\nReservation Manager");
            System.out.println("1. Make reservation");
            System.out.println("2. Remove reservation");
            System.out.println("3. View reservations");
            System.out.println("4. Update reservations");
            System.out.println("0. Exit");
            loop: while (choice != 0) {
                choice = getIntInput();
                switch (choice) {
                    case 0:
                        continue;
                    case 1:
                        makeReservation();
                        break loop;
                    case 2:
                        removeReservation();
                        break loop;
                    case 3:
                        viewReservations();
                        break loop;
                    case 4:
                        updateReservations();
                        break loop;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        }
    }

    /*
     * Adds a new Reservation into this ReservationFactory's
     * reservationController's reservationList
     */
    public void makeReservation() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of customer:");
        String name = sc.nextLine();

        System.out.println("Enter contact number of customer:");
        int contactNo = getIntInput();
        while (contactNo < 80000000 || contactNo > 99999999) {
            System.out.println("Invalid contact number");
            contactNo = getIntInput();
        }

        Customer customer = customerController.getCustomer(name, contactNo);
        if (customer == null) {
            System.out.println("Customer not registered, creating new entry");
            System.out.println("Is the customer a member:");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int choice = -1;
            while (choice != 1 && choice != 2) {
                choice = getIntInput();
                switch (choice) {
                    case 1:
                        customer = new Customer(name, contactNo, true);
                        customerController.addCustomer(customer);
                        break;
                    case 2:
                        customer = new Customer(name, contactNo, false);
                        customerController.addCustomer(customer);
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        }

        System.out.println("Enter number of people dining in:");
        int numPax = getIntInput();
        while (numPax <= 0 || numPax > 10) {
            System.out.println("Invalid number of people");
            numPax = getIntInput();
        }

        Table table = tableController.getVacantTable(numPax);
        if (table == null) {
            System.out.println("There are no vacant tables for " + numPax + " people");
            return;
        }

        System.out.println("Enter date of reservation (DD/MM/YYYY hh:mm:ss):");
        String dateString = "";
        Calendar reservationDate;
        while (true) {
            boolean valid = false;
            while (!valid) {
                try {
                    dateString = sc.nextLine();
                    LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:ss").withResolverStyle(ResolverStyle.STRICT));
                    valid = true;
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date");
                }
            }

            String[] dateTime = dateString.split(" ");
            String[] dateAttributes = dateTime[0].split("/");
            int day = Integer.parseInt(dateAttributes[0]);
            int month = Integer.parseInt(dateAttributes[1]) - 1;
            int year = Integer.parseInt(dateAttributes[2]);
            String[] timeAttributes = dateTime[1].split(":");
            int hour = Integer.parseInt(timeAttributes[0]);
            int minute = Integer.parseInt(timeAttributes[1]);
            int second = Integer.parseInt(timeAttributes[2]);
            reservationDate = new GregorianCalendar(year, month, day, hour, minute, second);

            Calendar nowDate = Calendar.getInstance();
            if (reservationDate.before(nowDate)) {
                System.out.println("The reservation date is already over");
                continue;
            }
            break;
        }

        Reservation reservation = new Reservation(reservationDate, customer, numPax, table);
        reservationController.addReservation(reservation);
    }
    /*
     * Removes an existing Reservation from this ReservationFactory's
     * reservationController's reservationList if the reservation is in the reservation list
     */
    public void removeReservation() {
        System.out.println("Enter id of reservation to be removed:");
        int id = getIntInput();
        while (id <= 0) {
            System.out.println("Invalid Id");
            id = getIntInput();
        }

        if (reservationController.removeReservation(id) == 0) {
            System.out.println("There is no reservation with id " + id);
        }
    }


    /*
     * View all the Reservations on the reservationList
     */
    public void viewReservations() {
        reservationController.viewReservations();
    }

    /*
     * Updates the reservations in the reservationList
     */
    public void updateReservations() {
        reservationController.updateReservations();
        System.out.println("Reservations updated");
    }

    /*
     * Saves the Reservations in the reservationController' reserationList into a file
     */
    public void writeInstances() {
        reservationController.writeInstances();
    }
}
