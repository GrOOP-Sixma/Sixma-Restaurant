package Factory;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import Food.FoodType;
import Food.MenuItem;
import Restaurant.RestaurantBack.Reservation;
import Restaurant.RestaurantBack.Staff;
import Restaurant.RestaurantFront.Table;

public class RRPSS implements Runnable {
    private Restaurant restaurant; // The restaurant
    private Reservation nullReservation; // A reservation that is null

    // starts the program
    public RRPSS(String Filename) {
        // check if restaurant file exists
        try {
            Path path = Path.of("./Factory/" + Filename);
            // if file exists, load file
            if (path.toFile().exists()) {
                restaurant = Restaurant.loadRestaurant(Filename);
            } else {
                // if file does not exist, create new file
                restaurant = new Restaurant(Filename);
                System.out.println("Create number of tables:");
                Scanner scanner = new Scanner(System.in);
                int numberOfTables = scanner.nextInt();
                restaurant.addTables(numberOfTables);
                scanner.close();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        Boolean exit = false;
        while (!exit) {
            printSwitch();
            exit = switchMethod(scanner);
        }
        scanner.close();
    }

    public void printSwitch() {
        System.out.println("What would you like to do?");
        System.out.println("----------------------------");
        // employees
        System.out.println("1: Hire an employee");
        System.out.println("2: Fire an employee");

        // menus
        System.out.println("3: Add a menu item");
        System.out.println("4: Remove a menu item");
        System.out.println("5: Create a new set menu");
        System.out.println("6: Add a set menu item");
        System.out.println("7: Remove a set menu item");
        System.out.println("8: Remove a set menu");

        // dealing with customers
        System.out.println("9: Show menu");
        System.out.println("10: Show set menus");
        System.out.println("11: Take an order");
        System.out.println("12: Show order");
        System.out.println("13: Print order invoice");

        // reservations
        System.out.println("14: Make a reservation");
        System.out.println("15: Change a reservation");
        System.out.println("16: Cancel a reservation");

        // backend
        System.out.println("17: Create new sales report");
        System.out.println("18: Show sales report");

        // exit
        System.out.println("19: Exit");
    }

    public Boolean switchMethod(Scanner scanner) {
        Boolean exit = false;
        switch (Integer.parseInt(scanner.nextLine())) {
            case 1:
                System.out.println("Hire an employee");
                System.out.println("----------------------------");
                System.out.println("Employee Name:");
                String name = scanner.nextLine();
                System.out.println("Employee Gender: ");
                Gender gender = scanner.nextLine();
                System.out.println("Employee ID:");
                int id = Integer.parseInt(scanner.nextLine());
                System.out.println("Employee Role:");
                String role = scanner.nextLine();
                restaurant.addEmployee(name, gender, id, role);
                break;
            case 2:
                System.out.println("Fire an employee");
                System.out.println("----------------------------");
                System.out.println("Employee ID:");
                id = Integer.parseInt(scanner.nextLine());
                restaurant.fireEmployee(id);
                break;
            case 3:
                System.out.println("Add a menu item");
                System.out.println("----------------------------");
                System.out.println("Food Type:");
                String foodType = scanner.nextLine();
                System.out.println("Food Name:");
                String foodName = scanner.nextLine();
                System.out.println("Food Description:");
                String foodDescription = scanner.nextLine();
                System.out.println("Food Price:");
                double foodPrice = Double.parseDouble(scanner.nextLine());
                FoodType type = FoodType.valueOf(foodType);
                restaurant.addMenuItem(type, foodName, foodDescription, foodPrice);
                break;
            case 4:
                System.out.println("Remove a menu item"); // todo
                System.out.println("----------------------------");
                System.out.println("Food Name:");
                break;
            case 5:
                System.out.println("Create a new set menu");
                System.out.println("----------------------------");
                System.out.println("Set Menu Name:");
                String setMenuName = scanner.nextLine();
                restaurant.createSetMenu(setMenuName);
                break;
            case 6:
                System.out.println("Add a set menu item");
                System.out.println("----------------------------");
                System.out.println("Set Menu Name:");
                setMenuName = scanner.nextLine();
                System.out.println("Food Type:");
                foodType = scanner.nextLine();
                type = FoodType.valueOf(foodType);
                System.out.println("Food Name:");
                foodName = scanner.nextLine();
                System.out.println("Food Description:");
                foodDescription = scanner.nextLine();
                System.out.println("Food Price:");
                foodPrice = Double.parseDouble(scanner.nextLine());
                MenuItem menuItem = new MenuItem(foodName, type, foodDescription, foodPrice);
                restaurant.addItemToSetMenu(setMenuName, menuItem);
                break;
            case 7:
                System.out.println("Remove a set menu item");
                System.out.println("----------------------------");
                System.out.println("Set Menu Name:");
                setMenuName = scanner.nextLine();
                System.out.println("Food Name:");
                foodName = scanner.nextLine();
                break;
            case 8:
                System.out.println("Remove a set menu");
                System.out.println("----------------------------");
                System.out.println("Set Menu Name:");
                setMenuName = scanner.nextLine();
                restaurant.removeSetMenu(setMenuName);
                break;
            case 9:
                System.out.println("Show menu");
                System.out.println("----------------------------");
                restaurant.getMenu();
                break;
            case 10:
                System.out.println("Show set menus");
                System.out.println("----------------------------");
                restaurant.getAvailablSetMenus();
                break;
            case 11:
                System.out.println("Take an order");
                System.out.println("----------------------------");
                System.out.println("Staff ID:");
                id = Integer.parseInt(scanner.nextLine());
                Staff staff = restaurant.getEmployee(id);
                System.out.println("Table ID:");
                int tableID = Integer.parseInt(scanner.nextLine());
                Table table = restaurant.getTable(tableID);
                ArrayList<MenuItem> orderedItems = restaurant.getOrderedItems();
                restaurant.takeOrder(staff, nullReservation, orderedItems, table, false);
                break;
            case 12:
                System.out.println("Show order");
                System.out.println("----------------------------");
                System.out.println("Order ID:");
                int orderID = Integer.parseInt(scanner.nextLine());
                restaurant.viewOrder(orderID);
                break;
            case 13:
                System.out.println("Print order invoice");
                System.out.println("----------------------------");
                System.out.println("Order ID:");
                orderID = Integer.parseInt(scanner.nextLine());
                restaurant.printOrderInvoice(orderID);
                break;
            case 14:
                System.out.println("Make a reservation");
                System.out.println("----------------------------");
                System.out.println("Customer Name:");
                name = scanner.nextLine();
                System.out.println("Customer Phone Number:");
                String phoneNumber = scanner.nextLine();
                System.out.println("Reservation Time:");
                String time = scanner.nextLine();
                // todo
                System.out.println("Reservation Date:");
                String date = scanner.nextLine();
                System.out.println("Reservation Number of People:");
                int numberOfPeople = Integer.parseInt(scanner.nextLine());
                System.out.println("Reservation Table ID:");
                int tableID2 = Integer.parseInt(scanner.nextLine());
                break;
            case 15:
                System.out.println("Change a reservation");
                System.out.println("----------------------------");
                System.out.println("Reservation ID:");
                // todo
                int reservationID = Integer.parseInt(scanner.nextLine());
                break;
            case 16:
                System.out.println("Cancel a reservation");
                System.out.println("----------------------------");
                System.out.println("Reservation Name:");
                String reservationName = scanner.nextLine();
                break;
            case 17:
                System.out.println("Create new sales report");
                System.out.println("----------------------------");
                restaurant.createNewSalesReport();
                break;
            case 18:
                System.out.println("Show sales report");
                System.out.println("----------------------------");
                System.out.println("Month");
                int month = Integer.parseInt(scanner.nextLine());
                System.out.println("Year");
                int year = Integer.parseInt(scanner.nextLine());
                restaurant.printSalesReport(month, year);
                break;
            case 19:
                exit = true;
                restaurant.saveRestaurant();
                break;
            default:
                System.out.println("Invalid input");
                break;
            }
        return exit;
    }
}