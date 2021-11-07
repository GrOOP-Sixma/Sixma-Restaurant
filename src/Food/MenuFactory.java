package Food;

import java.util.Scanner;

import Factory.Restaurant;

public class MenuFactory {
    private Restaurant restaurant;

    public MenuFactory(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void run() {
        int choice = -1;
        Scanner scanner = new Scanner(System.in);
        while (choice != 0) {
            System.out.println("Enter 1 to add a new menu item");
            System.out.println("Enter 2 to remove a menu item");
            System.out.println("Enter 3 to update a menu item");
            System.out.println("Enter 4 to view all menu items");
            System.out.println("Enter 0 to exit");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addMenuItem();
                    break;
                case 2:
                    removeMenuItem();
                    break;
                case 3:
                    modifyMenuItem();
                    break;
                case 4:
                    viewMenuItems();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
        scanner.close();
    }

    public void addMenuItem() {
        System.out.println("Creating a new menu item");
        Scanner scanner = new Scanner(System.in);
        Menu menu = restaurant.getMenu();
        System.out.println("Enter the name of the menu item");
        String name = scanner.nextLine();
        System.out.println("Enter the type of the menu item");
        String type = scanner.nextLine();
        FoodType foodType = FoodType.valueOf(type);
        System.out.println("Enter the description of the menu item");
        String description = scanner.nextLine();
        System.out.println("Enter the price of the menu item");
        double price = scanner.nextDouble();
        MenuItem menuItem = new MenuItem(name, foodType, description, price);
        menu.addMenuItem(menuItem);
        scanner.close();
    }

    public void removeMenuItem() {
        System.out.println("Enter id of menu item to be removed");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        restaurant.getMenu().removeMenuItem(id);
        scanner.close();
    }

    public void modifyMenuItem() {
        System.out.println("Enter id of menu item to be modified");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        MenuItem menuItem = restaurant.getMenu().getMenuItem(id);
        int choice = -1;
        while (choice != 0) {
            System.out.println("Enter 0 to exit");
            System.out.println("Enter 1 to modify name");
            System.out.println("Enter 2 to modify type");
            System.out.println("Enter 3 to modify description");
            System.out.println("Enter 4 to modify price");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter new name");
                    String name = scanner.nextLine();
                    menuItem.setName(name);
                    break;
                case 2:
                    System.out.println("Enter new type");
                    String type = scanner.nextLine();
                    FoodType foodType = FoodType.valueOf(type);
                    menuItem.setFoodType(foodType);
                    break;
                case 3:
                    System.out.println("Enter new description");
                    String description = scanner.nextLine();
                    menuItem.setDescription(description);
                    break;
                case 4:
                    System.out.println("Enter new price");
                    double price = scanner.nextDouble();
                    menuItem.setPrice(price);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
        scanner.close();
    }

    public void viewMenuItems() {
        System.out.println("Viewing all menu items");
        restaurant.getMenu().printMenu();
    }
}
