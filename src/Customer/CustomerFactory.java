package Customer;

import java.util.Scanner;

public class CustomerFactory {
    private CustomerController customerController;

    public CustomerFactory() {customerController = new CustomerController();}

    public CustomerController getCustomerController() {return customerController;}

    public void run() {
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        while (choice != 0) {
            System.out.println("1. Add Customer");
            System.out.println("2. Remove Customer");
            System.out.println("3. View Customer");
            System.out.println("0. Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    removeCustomer();
                    break;
                case 3:
                    viewCustomer();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }

        sc.close();
    }

    public void addCustomer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of new customer");
        String name = sc.nextLine();
        System.out.println("Enter contact number of new customer");
        int contactNo = sc.nextInt();
        while (contactNo < 80000000 || contactNo > 99999999) {
            System.out.println("Invalid number");
            contactNo = sc.nextInt();
        }
        System.out.println("Is the new customer a member");
        boolean isMember = sc.nextBoolean();

        Customer newCustomer = new Customer(name, contactNo, isMember);
        customerController.addCustomer(newCustomer);
        sc.close();
    }

    public void removeCustomer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of customer to remove");
        String name = sc.nextLine();
        System.out.println("Enter id of customer");
        int id = sc.nextInt();
        customerController.removeCustomer(name, id);
        sc.close();
    }

    public void viewCustomer() {customerController.viewCustomer();}
}
