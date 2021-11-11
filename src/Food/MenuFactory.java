package Food;

import java.util.Scanner;

public class MenuFactory {
    private final Menu menu;

    // constructors
    public MenuFactory() {menu = new Menu();}

    // getters
    public Menu getMenu() {return menu;}

    // methods
    public double getDoubleInput() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (sc.hasNextDouble()) {
                return sc.nextDouble();
            }
            else {
                System.out.println("Invalid input.");
                sc.next();
            }
        }
    }

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

    public void run() {
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        while (choice != 0) {
            System.out.println("\nMenu Manager:");
            System.out.println("1. Create menu item");
            System.out.println("2. Update menu item");
            System.out.println("3. Remove menu item");
            System.out.println("4. View menu items");
            System.out.println("0. Exit");
            loop: while (choice != 0) {
                choice = getIntInput();
                switch (choice) {
                    case 0:
                        continue;
                    case 1:
                        addMenuItem();
                        break loop;
                    case 2:
                        modifyMenuItem();
                        break loop;
                    case 3:
                        removeMenuItem();
                        break loop;
                    case 4:
                        viewMenuItems();
                        break loop;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        }
    }

    public void addMenuItem() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of menu item:");
        String name = sc.nextLine();

        System.out.println("Enter price of menu item:");
        double price = getDoubleInput();
        while (price <= 0) {
            System.out.println("Invalid price");
            price = getDoubleInput();
        }

        System.out.println("Enter type of menu item:");
        System.out.println("1. Main Course");
        System.out.println("2. Drinks");
        System.out.println("3. Dessert");
        int foodType = getIntInput();
        while (foodType != 1 && foodType != 2 && foodType != 3) {
            System.out.println("Invalid choice");
            foodType = getIntInput();
        }

        System.out.println("Enter the description of the menu item:");
        String description = sc.nextLine();

        MenuItem menuItem;
        if (foodType == 1) {
            menuItem = new MenuItem(name, price, FoodType.MAIN_COURSE, description);
        }
        else if (foodType == 2) {
            menuItem = new MenuItem(name, price, FoodType.DRINKS, description);
        }
        else {
            menuItem = new MenuItem(name, price, FoodType.DESSERT, description);
        }
        menu.addMenuItem(menuItem);
    }

    public void removeMenuItem() {
        System.out.println("Enter id of menu item to be removed:");
        int id = getIntInput();
        while (id <= 0) {
            System.out.println("Invalid id");
            id = getIntInput();
        }

        if (menu.removeMenuItem(id) == 0) {
            System.out.println("There is no menu item with id " + id);
        }
    }

    public void modifyMenuItem() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id of menu item to be modified:");
        int id = getIntInput();
        while (id <= 0) {
            System.out.println("Invalid id");
            id = getIntInput();
        }
        MenuItem menuItem = menu.getMenuItem(id);
        if (menuItem == null) {
            System.out.println("There is no menu item with id " + id);
            return;
        }

        int choice = -1;
        while (choice != 0) {
            System.out.println("1. Modify name");
            System.out.println("2. Modify price");
            System.out.println("3. Modify type");
            System.out.println("4. Modify description");
            System.out.println("0. Back");
            loop: while (choice != 0) {
                choice = getIntInput();
                switch (choice) {
                    case 0:
                        continue;
                    case 1:
                        System.out.println("Enter new name of menu item:");
                        String name = sc.nextLine();
                        menuItem.setName(name);
                        break loop;
                    case 2:
                        System.out.println("Enter new price of menu item:");
                        double price = getDoubleInput();
                        while (price <= 0) {
                            System.out.println("Invalid price");
                            price = getDoubleInput();
                        }
                        menuItem.setPrice(price);
                        break loop;
                    case 3:
                        System.out.println("Enter new type of menu item:");
                        System.out.println("1. Main Course");
                        System.out.println("2. Drinks");
                        System.out.println("3. Dessert");
                        int foodType = getIntInput();
                        while (foodType != 1 && foodType != 2 && foodType != 3) {
                            System.out.println("Invalid choice");
                            foodType = getIntInput();
                        }
                        if (foodType == 1) {
                            menuItem.setFoodType(FoodType.MAIN_COURSE);
                        }
                        else if (foodType == 2) {
                            menuItem.setFoodType(FoodType.DRINKS);
                        }
                        else {
                            menuItem.setFoodType(FoodType.DESSERT);
                        }
                        break loop;
                    case 4:
                        System.out.println("Enter new description of menu item:");
                        String description = sc.nextLine();
                        menuItem.setDescription(description);
                        break loop;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        }
    }

    public void viewMenuItems() {
        menu.viewMenu();
    }

    public void writeInstances() {
        menu.writeInstances();
    }
}
