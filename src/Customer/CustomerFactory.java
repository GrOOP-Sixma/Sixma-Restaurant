package Customer;

import java.util.Scanner;
/*
 * Represents a boundary class to get the user input
 * in order for the CustomerController class to perform the 
 * various method executions on the Customer class
 */
public class CustomerFactory {
	/*
	 * This CustomerFactory's customerController
	 */
    private CustomerController customerController;
    /*
     * The name of this CustomerFactory
     */
    private String name;

    // constructors
    /*
     * Creates a new CustomerFactory with the given name
     * @param name This CustomerFactory's name
     */
    public CustomerFactory(String name) {
        customerController = new CustomerController(name);
        this.name = name;
    }

    // getters
    /*
     * Gets this CustomerFactory's customerController
     * @return this CustomerFactory's customerController
     */
    public CustomerController getCustomerController() {return customerController;}

    // methods
    /*
     * Gets the input of the user
     * @return the user's input
     */
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
    
    /*
     * Runs the CustomerFactory to take in user input
     */
    public void run() {
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        while (choice != 0) {
            System.out.println("\nCustomer Manager:");
            System.out.println("1. Add Customer");
            System.out.println("2. Remove Customer");
            System.out.println("3. View Customers");
            System.out.println("0. Exit");
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
                        System.out.println("Invalid choice.");
                }
            }
        }
    }
    
    /*
     * Adds a new Customer into this CustomerFactory's
     * customerController's customerList
     */
    public void addCustomer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter name of new customer:");
        String name = sc.nextLine();

        System.out.println("Enter contact number of new customer:");
        int contactNo = getIntInput();
        while (contactNo < 80000000 || contactNo > 99999999) {
            System.out.println("Invalid contact number");
            contactNo = getIntInput();
        }

        System.out.println("Is the new customer a member:");
        System.out.println("1. Yes");
        System.out.println("2. No");
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
                    System.out.println("Invalid choice");
            }
        }
    }
    
    /*
     * Removes an existing Customer from this CustomerFactory's
     * customerController's customerList if the customer is in the customerList
     */
    public void removeCustomer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter name of customer to remove:");
        String name = sc.nextLine();

        System.out.println("Enter id of customer:");
        int id = getIntInput();
        while (id <= 0) {
            System.out.println("Invalid id");
            id = getIntInput();
        }
        if (customerController.removeCustomer(name, id) == 0) {
            System.out.println(name + " is not a registered customer");
        }
    }
    
    /*
     * View all the Customers on the customerController's customerList
     */
    public void viewCustomer() {
        customerController.viewCustomer();
    }
    
    /*
     * Saves the Customers in the customerController's customerList into a file
     */
    public void writeInstances() {
        customerController.writeInstances();
    }
}
