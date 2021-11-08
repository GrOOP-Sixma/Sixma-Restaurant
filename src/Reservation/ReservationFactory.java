package Reservation;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Scanner;

import Customer.*;
import Table.*;

public class ReservationFactory implements Serializable {
    private ReservationController reservationController;
    private CustomerFactory customerFactory;
    private TableController tableController;

    public ReservationFactory() {reservationController = new ReservationController();}

    public void setTableController(TableController tableController) {this.tableController = tableController;}
    public void setCustomerFactory(CustomerFactory customerFactory) {this.customerFactory = customerFactory;}

    public void run() {
        int choice = -1;
        Scanner scanner = new Scanner(System.in);
        while (choice != 0) {
            System.out.println("Reservation Menu");
            System.out.println("1. Make a reservation");
            System.out.println("2. Cancel a reservation");
            System.out.println("3. View all reservations");
            System.out.println("0. Exit");
            switch (choice = scanner.nextInt()) {
                case 1:
                    makeReservation();
                    break;
                case 2:
                    cancelReservation();
                    break;
                case 3:
                    viewReservations();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        scanner.close();
    }

    public void makeReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the customer's name:");
        String name = scanner.nextLine();
        Customer newCustomer = customerFactory.addCustomer(name);
        System.out.println("Enter the number of people");
        int numPeople = scanner.nextInt();
        System.out.println("Enter the date of the reservation (YYYY-MM-DD)");
        String date = scanner.next(); // todo deal with regex later lmao
        Calendar calendar = Calendar.getInstance(); 
        calendar.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)) - 1, Integer.parseInt(date.substring(8, 10)));
        // todo loop through vacancy map and find the first available table based on date
        // Table table = tableController.getFirstAvailableTable(calendar, numPeople);
        // if (table == null) {
        //     System.out.println("No available tables");
        //     return;
        // }
        Table table = tableController.getVacantTableNumPax(numPeople);
        if (table == null) {
            System.out.println("No available tables");
            scanner.close();
            return;
        }
        reservationController.addReservation(newCustomer, table, numPeople, calendar);
        System.out.println("Reservation made");
        scanner.close();
    }

    public void cancelReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Canceling reservation...");
        System.out.println("Enter the name of the customer");
        String name = scanner.nextLine();
        Customer customer = customerFactory.getCustomerController().getCustomer(name);
        reservationController.removeReservation(customer);
        scanner.close();
    }

    public void viewReservations() {
        reservationController.viewReservations();
    }
}
