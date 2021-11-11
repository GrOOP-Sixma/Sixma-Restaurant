package Customer;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class CustomerController {
    private final ArrayList<Customer> customerList;

    // constructor
    public CustomerController() {
        customerList = new ArrayList<>();
        readInstances();
    }

    // methods
    public void addCustomer(String name, int contactNo, boolean isMember) {
        Customer customer = new Customer(name, contactNo, isMember);
        customerList.add(customer);
    }

    public void addCustomer(String name, int contactNo, int customerId, boolean isMember) {
        Customer customer = new Customer(name, contactNo, customerId, isMember);
        customerList.add(customer);
    }

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public int removeCustomer(String name, int customerId) {
        for (int i=0; i<customerList.size(); i++) {
            if (customerList.get(i).getName().equals(name) && customerList.get(i).getCustomerId() == customerId) {
                customerList.remove(i);
                return 1;
            }
        }
        return 0;
    }

    public Customer getCustomer(int customerId) {
        for (Customer customer : customerList) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    public Customer getCustomer(String name, int contactNo) {
        for (Customer customer : customerList) {
            if (customer.getName().equals(name) && customer.getContactNo() == contactNo) {
                return customer;
            }
        }
        return null;
    }

    public void viewCustomer() {
        System.out.println("\nCustomers:");
        for (Customer customer : customerList) {
            customer.printCustomer();
        }
    }

    public void writeInstances() {
        String name;
        int contactNo;
        int customerId;
        boolean isMember;
        try {
            FileWriter myWriter = new FileWriter(System.getProperty("user.dir") + "/tmp/Customer.txt");
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

    public void readInstances() {
        String name;
        int contactNo;
        int customerId;
        int isMember;
        int maxCustomerId = 0;
        try {
            File myObj = new File(System.getProperty("user.dir") + "/tmp/Customer.txt");
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
        }
    }
}
