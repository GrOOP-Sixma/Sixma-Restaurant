package Customer;

import java.util.Scanner;

public class CustomerFactory {
    private CustomerController customerController;
    private String name;

    // constructors
    public CustomerFactory(String name) {
        customerController = new CustomerController(name);
        this.name = name;
    }

    // getters
    public CustomerController getCustomerController() {return customerController;}

    // methods
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

    public void viewCustomer() {
        customerController.viewCustomer();
    }

    public void writeInstances() {
        customerController.writeInstances();
    }
}
