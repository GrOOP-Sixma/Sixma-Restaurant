package Customer;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * Represents a control class to execute the methods on the Customer class
 * @author tengwei
 * @version 1.0
 * @since 2021-11-14
 */
public class CustomerController {
	/**
	 * The list of current customers that have reserved a table at the restaurant
	 */
    private final ArrayList<Customer> customerList;
    /**
     * The name of this CustomerController
     */
    private String name;

    // constructor
    /**
     * Creates a new CustomerController with the given name
     * @param name This CustomerController's name
     */
    public CustomerController(String name) {
        customerList = new ArrayList<>();
        this.name = name;
        readInstances();
    }
    
    /**
     * Gets the current and upcoming customers in the customerList
     * @return this CustomerController's customerList
     */
    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    // methods
    /**
     * Adds a customer into this customerController's customerList
     * @param name this customer's name
     * @param contactNo this customer's contact number
     * @param isMember this customer's membership
     */
    public void addCustomer(String name, int contactNo, boolean isMember) {
        Customer customer = new Customer(name, contactNo, isMember);
        customerList.add(customer);
    }
    /**
     * Adds a customer into this CustomerController's customerList
     * @param name this customer's name
     * @param contactNo this customer's contact number
     * @param customerId this customer's customerId
     * @param isMember this customer's membership
     */
    public void addCustomer(String name, int contactNo, int customerId, boolean isMember) {
        Customer customer = new Customer(name, contactNo, customerId, isMember);
        customerList.add(customer);
    }
    
    /**
     * Adds a customer into this CustomerController's customerList
     * @param customer this new customer object
     */
    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }
    
    /**
     * Removes a customer from this CustomerController's customerList 
     * if the customerList contains the customer
     * @param name this customer's name
     * @param customerId this customer's customerId
     * @return whether this customer has been removed from the customerList
     */
    public int removeCustomer(String name, int customerId) {
        for (int i=0; i<customerList.size(); i++) {
            if (customerList.get(i).getName().equals(name) && customerList.get(i).getCustomerId() == customerId) {
                customerList.remove(i);
                return 1;
            }
        }
        return 0;
    }
    
    /**
     * Gets the customer from this CustomerController's customerList
     * if the customerList contains the customer
     * @param customerId this customer's customerId
     * @return whether this customer is in the customerList
     */
    public Customer getCustomer(int customerId) {
        for (Customer customer : customerList) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null;
    }
    
    /**
     * Gets the customer from this CustomerController's customerList
     * @param name this customer's name
     * @param contactNo this customer's contact number
     * @return whether this customer is in the customerList
     */
    public Customer getCustomer(String name, int contactNo) {
        for (Customer customer : customerList) {
            if (customer.getName().equals(name) && customer.getContactNo() == contactNo) {
                return customer;
            }
        }
        return null;
    }

    /**
     * View all of the customers in this CustomerController's customerList
     */
    public void viewCustomer() {
        System.out.println("\nCustomers:");
        for (Customer customer : customerList) {
            customer.printCustomer();
        }
    }

    /**
     * Save the customers in this CustomerController's customerList into a file
     * @throws IOException If an input or output exception has occurred 
     */
    public void writeInstances() {
        String name;
        int contactNo;
        int customerId;
        boolean isMember;
        try {
            FileWriter myWriter = new FileWriter(System.getProperty("user.dir") + "/tmp/" + this.name + "Customer.txt");
            for (Customer customer : customerList) {
                name = customer.getName();
                contactNo = customer.getContactNo();
                customerId = customer.getCustomerId();
                isMember = customer.isMember();
                if (isMember) {
                    myWriter.write(name + ";" + contactNo + ";" + customerId + ";1\n");
                }
                else {
                    myWriter.write(name + ";" + contactNo + ";" + customerId + ";0\n");
                }
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Read the customers in the CustomerController's customerList
     * @throws FileNotFoundException If the file cannot be found
     * @throws IOException If an input or output exception has occurred
     */
    public void readInstances() {
        String name;
        int contactNo;
        int customerId;
        int isMember;
        int maxCustomerId = 0;
        try {
            File myObj = new File(System.getProperty("user.dir") + "/tmp/" + this.name + "Customer.txt");
            myObj.createNewFile(); // if file already exists will do nothing
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] attributes = data.split(";");
                name = attributes[0];
                contactNo = Integer.parseInt(attributes[1]);
                customerId = Integer.parseInt(attributes[2]);
                isMember = Integer.parseInt(attributes[3]);
                if (isMember == 1) {
                    addCustomer(name, contactNo, customerId, true);
                }
                else if (isMember == 0) {
                    addCustomer(name, contactNo, customerId, false);
                }
                if (customerId > maxCustomerId) {
                    maxCustomerId = customerId;
                }
            }
            Customer.setNextCustomerId(maxCustomerId + 1);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
