package Reservation;

import java.util.Calendar;
import java.util.Scanner;

import Customer.Customer;
import Table.Table;

import Customer.CustomerController;
import Table.TableController;

public class ReservationFactory {
    private ReservationController reservationController;
    private CustomerController customerController;
    private TableController tableController;

    public ReservationFactory() {reservationController = new ReservationController();}

    public void setCustomerController(CustomerController customerController) {this.customerController = customerController;}
    public void setTableController(TableController tableController) {this.tableController = tableController;}

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
        System.out.println("Making new reservation...");
        System.out.println("Enter the name of the customer");
        String name = scanner.nextLine();
        Customer customer = customerController.getCustomer(name);
        System.out.println("Enter the table id");
        int tableId = scanner.nextInt();
        Table table = tableController.getTable(tableId);
        System.out.println("Enter the number of people");
        int numOfPeople = scanner.nextInt();
        System.out.println("Enter the time of the reservation");
        int time = scanner.nextInt();
        System.out.println("Enter the date of the reservation");
        int date = scanner.nextInt();
        // todo convert time date to calendar
        Calendar calendar = Calendar.getInstance();
        reservationController.addReservation(new Reservation(customer, table, numOfPeople, calendar));
    }

    public void cancelReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Canceling reservation...");
        System.out.println("Enter the name of the customer");
        String name = scanner.nextLine();
        Customer customer = customerController.getCustomer(name);
        reservationController.removeReservation(customer);
        scanner.close();
    }

    public void viewReservations() {
        reservationController.viewReservations();
    }
}
