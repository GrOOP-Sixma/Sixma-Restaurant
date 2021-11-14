package Customer;

import java.util.Scanner;
/**
 * Represents a boundary class to get the user input
 * in order for the CustomerController class to perform the 
 * various method executions on the Customer class
 * @author tengwei
 * @version 1.0
 * @since 2021-11-14
 */
public class CustomerFactory {
	/**
	 * The customerController of this CustomerFactory
	 */
    private CustomerController customerController;
    /**
     * The name of this CustomerFactory
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
     * Creates a new CustomerFactory with the given name
     * @param name This CustomerFactory's name
     */
    public CustomerFactory(String name) {
        customerController = new CustomerController(name);
        this.name = name;
    }

    // getters
    /**
     * Gets this CustomerFactory's customerController
     * @return this CustomerFactory's customerController
     */
    public CustomerController getCustomerController() {return customerController;}

    // methods
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
     * Runs the CustomerFactory to take in user input
     */
    public void run() {
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        System.out.println("""
        $$$$$$\\                        $$\\                                                       $$\\      $$\\                                                             
        $$  __$$\\                       $$ |                                                      $$$\\    $$$ |                                                            
        $$ /  \\__|$$\\   $$\\  $$$$$$$\\ $$$$$$\\    $$$$$$\\  $$$$$$\\$$$$\\   $$$$$$\\   $$$$$$\\        $$$$\\  $$$$ | $$$$$$\\  $$$$$$$\\   $$$$$$\\   $$$$$$\\   $$$$$$\\   $$$$$$\\  
        $$ |      $$ |  $$ |$$  _____|\\_$$  _|  $$  __$$\\ $$  _$$  _$$\\ $$  __$$\\ $$  __$$\\       $$\\$$\\$$ $$ | \\____$$\\ $$  __$$\\  \\____$$\\ $$  __$$\\ $$  __$$\\ $$  __$$\\ 
        $$ |      $$ |  $$ |\\$$$$$$\\    $$ |    $$ /  $$ |$$ / $$ / $$ |$$$$$$$$ |$$ |  \\__|      $$ \\$$$  $$ | $$$$$$$ |$$ |  $$ | $$$$$$$ |$$ /  $$ |$$$$$$$$ |$$ |  \\__|
        $$ |  $$\\ $$ |  $$ | \\____$$\\   $$ |$$\\ $$ |  $$ |$$ | $$ | $$ |$$   ____|$$ |            $$ |\\$  /$$ |$$  __$$ |$$ |  $$ |$$  __$$ |$$ |  $$ |$$   ____|$$ |      
        \\$$$$$$  |\\$$$$$$  |$$$$$$$  |  \\$$$$  |\\$$$$$$  |$$ | $$ | $$ |\\$$$$$$$\\ $$ |            $$ | \\_/ $$ |\\$$$$$$$ |$$ |  $$ |\\$$$$$$$ |\\$$$$$$$ |\\$$$$$$$\\ $$ |      
         \\______/  \\______/ \\_______/    \\____/  \\______/ \\__| \\__| \\__| \\_______|\\__|            \\__|     \\__| \\_______|\\__|  \\__| \\_______| \\____$$ | \\_______|\\__|      
                                                                                                                                             $$\\   $$ |                    
                                                                                                                                             \\$$$$$$  |                    
                                                                                                                                              \\______/                     
        """);
        while (choice != 0) {
            System.out.println("");
            System.out.println(ANSI_GREEN + "1. Add Customer" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "2. Remove Customer" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "3. View Customers" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "0. Exit" + ANSI_RESET);
            loop: while (choice != 0) {
                choice = getIntInput();
                switch (choice) {
                    case 0:
                        continue;
                    case 1:
                        addCustomer();
                        break loop;
                    case 2:
                        removeCustomer();
                        break loop;
                    case 3:
                        viewCustomer();
                        break loop;
                    default:
                        System.out.println(ANSI_RED + "Invalid choice." + ANSI_RESET);
                }
            }
        }
    }
    
    /**
     * Adds a new customer into this CustomerFactory's
     * customerController's customerList
     */
    public void addCustomer() {
        Scanner sc = new Scanner(System.in);
        System.out.println(ANSI_GREEN + "\nEnter name of new customer:" + ANSI_RESET);
        String name = sc.nextLine();

        System.out.println(ANSI_GREEN + "Enter contact number of new customer:" + ANSI_RESET);
        int contactNo = getIntInput();
        while (contactNo < 80000000 || contactNo > 99999999) {
            System.out.println(ANSI_RED + "Invalid contact number" + ANSI_RESET);
            contactNo = getIntInput();
        }

        System.out.println(ANSI_GREEN + "Is the new customer a member:" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "1. Yes" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "2. No" + ANSI_RESET);
        Customer newCustomer;
        int choice = -1;
        while (choice != 1 && choice != 2) {
            choice = getIntInput();
            switch (choice) {
                case 1:
                    newCustomer = new Customer(name, contactNo, true);
                    customerController.addCustomer(newCustomer);
                    break;
                case 2:
                    newCustomer = new Customer(name, contactNo, false);
                    customerController.addCustomer(newCustomer);
                    break;
                default:
                    System.out.println("Invalid choice" + ANSI_RESET);
            }
        }
    }
    
    /**
     * Removes an existing customer from this CustomerFactory's
     * customerController's customerList if the customer is in the customerList
     */
    public void removeCustomer() {
        Scanner sc = new Scanner(System.in);
        System.out.println(ANSI_GREEN + "\nEnter name of customer to remove:" + ANSI_RESET);
        String name = sc.nextLine();

        System.out.println(ANSI_GREEN + "Enter id of customer:" + ANSI_RESET);
        int id = getIntInput();
        while (id <= 0) {
            System.out.println(ANSI_RED + "Invalid id" + ANSI_RESET);
            id = getIntInput();
        }
        if (customerController.removeCustomer(name, id) == 0) {
            System.out.println(ANSI_RED + name + " is not a registered customer" + ANSI_RESET);
        }
    }
    
    /**
     * View all of the customer in the customerController's customerList
     */
    public void viewCustomer() {
        customerController.viewCustomer();
    }
    
    /**
     * Save all of the customers in the customerController's customerList into a file
     */
    public void writeInstances() {
        customerController.writeInstances();
    }
}
