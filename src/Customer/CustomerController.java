package Customer;

import java.util.ArrayList;


public class CustomerController {
    private final ArrayList<Customer> customerList;

    // constructor
    public CustomerController() {
        customerList = new ArrayList<>();
    }

    // methods
    public void addCustomer(String name, int contactNo, boolean isMember) {
        Customer customer = new Customer(name, contactNo, isMember);
        customerList.add(customer);
    }

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public void removeCustomer(String name, int customerId) {
        for (int i=0; i<customerList.size(); i++) {
            if (customerList.get(i).getName().equals(name) && customerList.get(i).getCustomerId() == customerId) {
                customerList.remove(i);
                break;
            }
        }
    }

    public Customer getCustomer(int customerId) {
        for (Customer customer : customerList)
            if (customer.getCustomerId() == customerId)
                return customer;

        return null;
    }

    // get customer by name
    public Customer getCustomer(String name) {
        for (Customer customer : customerList)
            if (customer.getName().equals(name))
                return customer;

        return null;
    }

    public void viewCustomer() {
        for (Customer customer : customerList) {
            System.out.println(customer);
        }
    }
}
