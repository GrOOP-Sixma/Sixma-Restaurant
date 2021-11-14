package Food;

import java.util.Scanner;
/**
 * Represents a boundary class to get the user input
 * in order for the Menu class to perform the 
 * various method executions on the MenuItem class 
 * @author tengwei
 * @version 1.0
 * @since 2021-11-14
 */
import Factory.AsciiPrinter;

public class MenuFactory {
	/**
	 * The menu of this MenuFactory
	 */
    private final Menu menu;
    /**
     * The name of this MenuFactory
     */
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
    /**
     * Create a new MenuFactory with the given name
     * @param name This MenuFactory's name
     */
    public MenuFactory(String name) {
        menu = new Menu(name);
        this.name = name;
    }

    // getters
    /**
     * Gets the menu of this MenuFactory
     * @return this MenuFactory's menu 
     */
    public Menu getMenu() {return menu;}

    // methods
    /**
     * Gets the input of the user for double data type
     * @return the user's input
     */
    public double getDoubleInput() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (sc.hasNextDouble()) {
                return sc.nextDouble();
            }
            else {
                System.out.println(ANSI_RED + "Invalid input." + ANSI_RESET);
                sc.next();
            }
        }
    }
    /**
     * Gets the input of the user for int data type
     * @return the user's input
     */
    public int getIntInput() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (sc.hasNextInt()) {
                return sc.nextInt();
            }
            else {
                System.out.println(ANSI_RED + "Invalid input." + ANSI_RESET);
                sc.next();
            }
        }
    }

    /**
     * Runs the MenuFactory to take in user input
     */
    public void run() {
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        System.out.println(ANSI_YELLOW + """
        $$\\      $$\\                                     $$\\      $$\\                                                             
        $$$\\    $$$ |                                    $$$\\    $$$ |                                                            
        $$$$\\  $$$$ | $$$$$$\\  $$$$$$$\\  $$\\   $$\\       $$$$\\  $$$$ | $$$$$$\\  $$$$$$$\\   $$$$$$\\   $$$$$$\\   $$$$$$\\   $$$$$$\\  
        $$\\$$\\$$ $$ |$$  __$$\\ $$  __$$\\ $$ |  $$ |      $$\\$$\\$$ $$ | \\____$$\\ $$  __$$\\  \\____$$\\ $$  __$$\\ $$  __$$\\ $$  __$$\\ 
        $$ \\$$$  $$ |$$$$$$$$ |$$ |  $$ |$$ |  $$ |      $$ \\$$$  $$ | $$$$$$$ |$$ |  $$ | $$$$$$$ |$$ /  $$ |$$$$$$$$ |$$ |  \\__|
        $$ |\\$  /$$ |$$   ____|$$ |  $$ |$$ |  $$ |      $$ |\\$  /$$ |$$  __$$ |$$ |  $$ |$$  __$$ |$$ |  $$ |$$   ____|$$ |      
        $$ | \\_/ $$ |\\$$$$$$$\\ $$ |  $$ |\\$$$$$$  |      $$ | \\_/ $$ |\\$$$$$$$ |$$ |  $$ |\\$$$$$$$ |\\$$$$$$$ |\\$$$$$$$\\ $$ |      
        \\__|     \\__| \\_______|\\__|  \\__| \\______/       \\__|     \\__| \\_______|\\__|  \\__| \\_______| \\____$$ | \\_______|\\__|      
                                                                                                    $$\\   $$ |                    
                                                                                                    \\$$$$$$  |                    
                                                                                                    \\______/                     
        """ + ANSI_RESET);
        while (choice != 0) {
            System.out.println("");
            System.out.println(ANSI_YELLOW + "1. Create menu item" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "2. Update menu item" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "3. Remove menu item" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "4. View menu items" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "0. Exit" + ANSI_RESET);
            loop: while (choice != 0) {
                choice = getIntInput();
                switch (choice) {
                    case 0:
                        AsciiPrinter.print();
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
                        System.out.println(ANSI_RED + "Invalid choice" + ANSI_RESET);
                }
            }
        }
    }
    /**
     * Adds a new menuItem into this MenuFactory's menu
     */
    public void addMenuItem() {
        Scanner sc = new Scanner(System.in);
        System.out.println(ANSI_YELLOW + "Enter name of menu item:" + ANSI_RESET);
        String name = sc.nextLine();

        System.out.println(ANSI_YELLOW + "Enter price of menu item:" + ANSI_RESET);
        double price = getDoubleInput();
        while (price <= 0) {
            System.out.println(ANSI_RED + "Invalid price" + ANSI_RESET);
            price = getDoubleInput();
        }

        System.out.println(ANSI_YELLOW + "Enter type of menu item:" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "1. Main Course" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "2. Drinks" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "3. Dessert" + ANSI_RESET);
        int foodType = getIntInput();
        while (foodType != 1 && foodType != 2 && foodType != 3) {
            System.out.println(ANSI_YELLOW + "Invalid choice" + ANSI_RESET);
            foodType = getIntInput();
        }

        System.out.println(ANSI_YELLOW + "Enter the description of the menu item:" + ANSI_RESET);
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
    /**
     * Removes a menuItem from this MenuFactory's menu
     * if the menuItem is in the menu
     */
    public void removeMenuItem() {
        System.out.println(ANSI_YELLOW + "Enter id of menu item to be removed:" + ANSI_RESET);
        int id = getIntInput();
        while (id <= 0) {
            System.out.println(ANSI_RED + "Invalid id" + ANSI_RESET);
            id = getIntInput();
        }

        if (menu.removeMenuItem(id) == 0) {
            System.out.println(ANSI_RED + "There is no menu item with id " + id);
        }
    }
    /**
     * Modifies a menuItem in this MenuFactory's menu
     * if the menuItem is in the menu
     */
    public void modifyMenuItem() {
        Scanner sc = new Scanner(System.in);
        System.out.println(ANSI_YELLOW + "Enter id of menu item to be modified:" + ANSI_RESET);
        int id = getIntInput();
        while (id <= 0) {
            System.out.println(ANSI_RED + "Invalid id" + ANSI_RESET);
            id = getIntInput();
        }
        MenuItem menuItem = menu.getMenuItem(id);
        if (menuItem == null) {
            System.out.println(ANSI_RED + "There is no menu item with id " + id);
            return;
        }

        int choice = -1;
        while (choice != 0) {
            System.out.println(ANSI_YELLOW + "1. Modify name" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "2. Modify price" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "3. Modify type" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "4. Modify description" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "0. Back" + ANSI_RESET);
            loop: while (choice != 0) {
                choice = getIntInput();
                switch (choice) {
                    case 0:
                        continue;
                    case 1:
                        System.out.println(ANSI_YELLOW + "Enter new name of menu item:" + ANSI_RESET);
                        String name = sc.nextLine();
                        menuItem.setName(name);
                        break loop;
                    case 2:
                        System.out.println(ANSI_YELLOW + "Enter new price of menu item:" + ANSI_RESET);
                        double price = getDoubleInput();
                        while (price <= 0) {
                            System.out.println(ANSI_RED + "Invalid price" + ANSI_RESET);
                            price = getDoubleInput();
                        }
                        menuItem.setPrice(price);
                        break loop;
                    case 3:
                        System.out.println(ANSI_YELLOW + "Enter new type of menu item:" + ANSI_RESET);
                        System.out.println(ANSI_YELLOW + "1. Main Course" + ANSI_RESET);
                        System.out.println(ANSI_YELLOW + "2. Drinks" + ANSI_RESET);
                        System.out.println(ANSI_YELLOW + "3. Dessert" + ANSI_RESET);
                        int foodType = getIntInput();
                        while (foodType != 1 && foodType != 2 && foodType != 3) {
                            System.out.println(ANSI_RED + "Invalid choice" + ANSI_RESET);
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
                        System.out.println(ANSI_YELLOW + "Enter new description of menu item:" + ANSI_RESET);
                        String description = sc.nextLine();
                        menuItem.setDescription(description);
                        break loop;
                    default:
                        System.out.println(ANSI_RED + "Invalid choice" + ANSI_RESET);
                }
            }
        }
    }
    /**
     * View all of the menuItem on this MenuFactory's menu
     */
    public void viewMenuItems() {
        menu.viewMenu();
    }
    
    /**
     * Save all of the menuItem in this MenuFactory's menu
     */
    public void writeInstances() {
        menu.writeInstances();
    }
}
