package Factory;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import Food.FoodType;
import Food.MenuItem;
import Reservation.Reservation;
import Staff.Staff;
import Table.Table;

public class RRPSS implements Runnable {
    private Restaurant restaurant; // The restaurant

    // starts the program
    // public RRPSS(String Filename) {
    //     // check if restaurant file exists
    //     try {
    //         Path path = Path.of("./Factory/" + Filename);
    //         // if file exists, load file
    //         if (path.toFile().exists()) {
    //             restaurant = Restaurant.loadRestaurant(Filename);
    //         } else {
    //             // if file does not exist, create new file
    //             restaurant = new Restaurant(Filename);
    //             System.out.println("Create number of tables:");
    //             Scanner scanner = new Scanner(System.in);
    //             int numberOfTables = scanner.nextInt();
    //             restaurant.addTables(numberOfTables);
    //             scanner.close();
    //         }
    //     } catch (Exception e) {
    //         System.out.println("Error: " + e.getMessage());
    //     }
    // }

    public RRPSS() {
        restaurant = new Restaurant("Sixma");
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Sixma Restaurant Reservation and Point of Sale System (RRPSS)");
        Boolean exit = false;
        while (!exit) {
            System.out.println("What would you like to do?");
            System.out.println("1. Menu Options");
            System.out.println("2. Set Menu Options");
            System.out.println("3. Staff Options");
            System.out.println("4. Table Options");
            System.out.println("5. Reservation Options");
            System.out.println("6. Point of Sale Options");
            System.out.println("7. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    restaurant.menuRun();
                    break;
                case 2:
                    restaurant.setMenuRun();
                    break;
                case 3:
                    restaurant.staffRun();
                    break;
                case 4:
                    restaurant.tableRun();
                    break;
                case 5:
                    restaurant.reservationRun();
                    break;
                case 6:
                    restaurant.orderRun();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
        scanner.close();
    }
}