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


public class ReservationFactory {
    private CustomerController customerController;
    private TableController tableController;
    private ReservationController reservationController;
    private String name;

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
    public ReservationFactory(String name, CustomerController customerController, TableController tableController) {
        this.name = name;
        this.customerController = customerController;
        this.tableController = tableController;
        reservationController = new ReservationController(name, customerController, tableController);
        updateReservations();
    }

    // getters
    public CustomerController getCustomerController() {return customerController;}
    public TableController getTableController() {return tableController;}
    public ReservationController getReservationController() {return reservationController;}

    // methods
    public int getIntInput() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (sc.hasNextInt()) {
                return sc.nextInt();
            }
            else {
                System.out.println(ANSI_RED+ "Invalid input." + ANSI_RESET);
                sc.next();
            }
        }
    }

    public void run() {
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        System.out.println(ANSI_PURPLE+ """
        $$$$$$$\\                                                                 $$\\     $$\\                           $$\\      $$\\                                                             
        $$  __$$\\                                                                $$ |    \\__|                          $$$\\    $$$ |                                                            
        $$ |  $$ | $$$$$$\\   $$$$$$$\\  $$$$$$\\   $$$$$$\\  $$\\    $$\\  $$$$$$\\  $$$$$$\\   $$\\  $$$$$$\\  $$$$$$$\\        $$$$\\  $$$$ | $$$$$$\\  $$$$$$$\\   $$$$$$\\   $$$$$$\\   $$$$$$\\   $$$$$$\\  
        $$$$$$$  |$$  __$$\\ $$  _____|$$  __$$\\ $$  __$$\\ \\$$\\  $$  | \\____$$\\ \\_$$  _|  $$ |$$  __$$\\ $$  __$$\\       $$\\$$\\$$ $$ | \\____$$\\ $$  __$$\\  \\____$$\\ $$  __$$\\ $$  __$$\\ $$  __$$\\ 
        $$  __$$< $$$$$$$$ |\\$$$$$$\\  $$$$$$$$ |$$ |  \\__| \\$$\\$$  /  $$$$$$$ |  $$ |    $$ |$$ /  $$ |$$ |  $$ |      $$ \\$$$  $$ | $$$$$$$ |$$ |  $$ | $$$$$$$ |$$ /  $$ |$$$$$$$$ |$$ |  \\__|
        $$ |  $$ |$$   ____| \\____$$\\ $$   ____|$$ |        \\$$$  /  $$  __$$ |  $$ |$$\\ $$ |$$ |  $$ |$$ |  $$ |      $$ |\\$  /$$ |$$  __$$ |$$ |  $$ |$$  __$$ |$$ |  $$ |$$   ____|$$ |      
        $$ |  $$ |\\$$$$$$$\\ $$$$$$$  |\\$$$$$$$\\ $$ |         \\$  /   \\$$$$$$$ |  \\$$$$  |$$ |\\$$$$$$  |$$ |  $$ |      $$ | \\_/ $$ |\\$$$$$$$ |$$ |  $$ |\\$$$$$$$ |\\$$$$$$$ |\\$$$$$$$\\ $$ |      
        \\__|  \\__| \\_______|\\_______/  \\_______|\\__|          \\_/     \\_______|   \\____/ \\__| \\______/ \\__|  \\__|      \\__|     \\__| \\_______|\\__|  \\__| \\_______| \\____$$ | \\_______|\\__|      
                                                                                                                                                                $$\\   $$ |                    
                                                                                                                                                                \\$$$$$$  |                    
                                                                                                                                                                \\______/                     
        """ + ANSI_RESET);
        while (choice != 0) {
            System.out.println(ANSI_PURPLE+ "1. Make reservation" + ANSI_RESET);
            System.out.println(ANSI_PURPLE+ "2. Remove reservation" + ANSI_RESET);
            System.out.println(ANSI_PURPLE+ "3. View reservations" + ANSI_RESET);
            System.out.println(ANSI_PURPLE+ "4. Update reservations" + ANSI_RESET);
            System.out.println(ANSI_PURPLE+ "0. Exit" + ANSI_RESET);
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
                        System.out.println(ANSI_RED+ "Invalid choice" + ANSI_RESET);
                }
            }
        }
    }

    public void makeReservation() {
        Scanner sc = new Scanner(System.in);
        System.out.println(ANSI_PURPLE+ "Enter name of customer:" + ANSI_RESET);
        String name = sc.nextLine();

        System.out.println(ANSI_PURPLE+ "Enter contact number of customer:" + ANSI_RESET);
        int contactNo = getIntInput();
        while (contactNo < 80000000 || contactNo > 99999999) {
            System.out.println(ANSI_RED+ "Invalid contact number" + ANSI_RESET);
            contactNo = getIntInput();
        }

        Customer customer = customerController.getCustomer(name, contactNo);
        if (customer == null) {
            System.out.println(ANSI_PURPLE+ "Customer not registered, creating new entry" + ANSI_RESET);
            System.out.println(ANSI_PURPLE+ "Is the customer a member:" + ANSI_RESET);
            System.out.println(ANSI_PURPLE+ "1. Yes" + ANSI_RESET);
            System.out.println(ANSI_PURPLE+ "2. No" + ANSI_RESET);
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
                        System.out.println(ANSI_RED+ "Invalid choice" + ANSI_RESET);
                }
            }
        }

        System.out.println(ANSI_PURPLE+ "Enter number of people dining in:" + ANSI_RESET);
        int numPax = getIntInput();
        while (numPax <= 0 || numPax > 10) {
            System.out.println(ANSI_RED+ "Invalid number of people" + ANSI_RESET);
            numPax = getIntInput();
        }

        Table table = tableController.getVacantTable(numPax);
        if (table == null) {
            System.out.println(ANSI_RED+ "There are no vacant tables for " + numPax + " people" + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_PURPLE+ "Enter date of reservation (DD/MM/YYYY hh:mm:ss):" + ANSI_RESET);
        String dateString = "";
        Calendar reservationDate;
        while (true) {
            boolean valid = false;
            while (!valid) {
                try {
                    dateString = sc.nextLine();
                    LocalDate.parse(dateString, DateTimeFormatter.ofPattern(ANSI_PURPLE+ "dd/MM/uuuu HH:mm:ss").withResolverStyle(ResolverStyle.STRICT));
                    valid = true;
                } catch (DateTimeParseException e) {
                    System.out.println(ANSI_RED+ "Invalid date" + ANSI_RESET);
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
                System.out.println(ANSI_RED+ "The reservation date is already over" + ANSI_RESET);
                continue;
            }
            break;
        }

        Reservation reservation = new Reservation(reservationDate, customer, numPax, table);
        reservationController.addReservation(reservation);
    }

    public void removeReservation() {
        System.out.println(ANSI_PURPLE+ "Enter id of reservation to be removed:" + ANSI_RESET);
        int id = getIntInput();
        while (id <= 0) {
            System.out.println(ANSI_RED+ "Invalid Id" + ANSI_RESET);
            id = getIntInput();
        }

        if (reservationController.removeReservation(id) == 0) {
            System.out.println(ANSI_RED+ "There is no reservation with id " + id + ANSI_RESET);
        }
    }

    public void viewReservations() {
        reservationController.viewReservations();
    }

    public void updateReservations() {
        reservationController.updateReservations();
        System.out.println(ANSI_PURPLE+ "Reservations updated" + ANSI_RESET);
    }

    public void writeInstances() {
        reservationController.writeInstances();
    }
}
