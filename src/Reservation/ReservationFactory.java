package Reservation;

import java.util.Calendar;
import java.util.Scanner;

import Customer.Customer;
import Factory.Restaurant;
import Table.Table;

public class ReservationFactory {
    private Restaurant restaurant;

    public ReservationFactory(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void run() {
        int choice = -1;
        Scanner scanner = new Scanner(System.in);
        while (choice != 0) {
            System.out.println("\nReservation Menu");
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
        Customer customer = restaurant.getCustomer(name);
        System.out.println("Enter the table id");
        int tableId = scanner.nextInt();
        Table table = restaurant.getTable(tableId);
        System.out.println("Enter the number of people");
        int numOfPeople = scanner.nextInt();
        System.out.println("Enter the time of the reservation");
        int time = scanner.nextInt();
        System.out.println("Enter the date of the reservation");
        int date = scanner.nextInt();
        // todo convert time date to calendar
        Calendar calendar = Calendar.getInstance();
        restaurant.addReservation(new Reservation(customer, table, numOfPeople, calendar));
    }

    public void cancelReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Canceling reservation...");
        System.out.println("Enter the name of the customer");
        String name = scanner.nextLine();
        Customer customer = restaurant.getCustomer(name);
        // restaurant.removeReservation(new Reservation(customer, table, calendar));
        // todo @Ph4ntomDrake please allow removing via customer instead of via res id
        scanner.close();
    }

    public void viewReservations() {
        this.restaurant.viewReservations();
    }
}
